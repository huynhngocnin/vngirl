package ninhn.app.service;
//[START all]
/*
 * Copyright (c) 2014 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

import com.google.api.client.http.InputStreamContent;
import com.google.api.services.storage.Storage;
import com.google.api.services.storage.model.ObjectAccessControl;
import com.google.api.services.storage.model.Objects;
import com.google.api.services.storage.model.StorageObject;
import ninhn.app.model.Photo;
import ninhn.app.until.DefaultUntil;
import ninhn.app.until.StorageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ninhn.app.constant.SystemConstant.BLANK;
import static ninhn.app.constant.SystemConstant.EXTEND_JPEG;
import static ninhn.app.constant.SystemConstant.EXTEND_JPG;
import static ninhn.app.constant.SystemConstant.EXTEND_PNG;
import static ninhn.app.constant.SystemConstant.UPLOAD_PUBLIC;
import static ninhn.app.constant.SystemConstant.UPLOAD_REVIEW;

/**
 * Main class for the Cloud Storage JSON API sample.
 * <p>
 * Demonstrates how to make an authenticated API call using the Google Cloud Storage API client
 * library for java, with Application Default Credentials.
 */
@Service
public class StorageService {

    /**
     * Global instance of the JSON factory.
     */
    private static final String CONTENT_TYPE = "image/*";
    private static final String BUCKET_NAME = "vn_girl";

    @Autowired
    private PhotoService photoService;

    // [START list_bucket]

    /**
     * Fetch a list of the objects within the given bucket.
     *
     * @return a list of the contents of the specified bucket.
     */
    public List<StorageObject> listBucket()
            throws IOException, GeneralSecurityException {
        Storage client = StorageFactory.getService();
        Storage.Objects.List listRequest = client.objects().list(BUCKET_NAME);

        List<StorageObject> results = new ArrayList<StorageObject>();
        Objects objects;

        // Iterate through each page of results, and add them to our results list.
        do {
            objects = listRequest.execute();
            // Add the items in this page of results to the list we'll return.
            results.addAll(objects.getItems());

            // Get the next page, in the next iteration of this loop.
            listRequest.setPageToken(objects.getNextPageToken());
        } while (null != objects.getNextPageToken());

        return results;
    }
    // [END list_bucket]

    // [START upload_stream]

    /**
     * Uploads data to an object in a bucket.
     *
     * @param multipartFile the file to upload.
     */
    public StorageObject uploadFile(MultipartFile multipartFile)
            throws IOException, GeneralSecurityException {
        InputStreamContent contentStream = new InputStreamContent(
                CONTENT_TYPE, multipartFile.getInputStream());
        String fileName = multipartFile.getOriginalFilename();
        // Setting the length improves upload performance
        contentStream.setLength(multipartFile.getSize());
        StorageObject objectMetadata = new StorageObject()
                // Set the destination object name
                .setName(UPLOAD_PUBLIC + multipartFile.getOriginalFilename())
                // Set the access control list to publicly read-only
                .setAcl(Arrays.asList(
                        new ObjectAccessControl().setEntity("allUsers").setRole("READER")));

        // Do the insert
        Storage client = StorageFactory.getService();
        Storage.Objects.Insert insertRequest = client.objects().insert(
                BUCKET_NAME, objectMetadata, contentStream);
        //Upload to cloud storage
        StorageObject object = insertRequest.execute();
        //Get url of object
        String urlMedia = object.getMediaLink();
        fileName = fileName.replace(EXTEND_JPG, BLANK);
        fileName = fileName.replace(EXTEND_JPEG, BLANK);
        fileName = fileName.replace(EXTEND_PNG, BLANK);
        //Save DB
        this.photoService.save(DefaultUntil.photoInsert(urlMedia, fileName));
        return object;
    }
    // [END upload_stream]

    public List<StorageObject> uploads(MultipartFile[] multipartFiles) {
        List<StorageObject> storageObjects = new ArrayList<>();

        Arrays.stream(multipartFiles)
                .filter(multipartFile -> !StringUtils.isEmpty(multipartFile.getOriginalFilename()))
                .forEach(multipartFile -> {
                    try {
                        storageObjects.add(uploadFile(multipartFile));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (GeneralSecurityException gsException) {
                        gsException.printStackTrace();
                    }
                });

        return storageObjects;
    }

    /**
     * @param multipartFile
     * @param photo
     * @return
     */
    public StorageObject userUploadPhoto(MultipartFile multipartFile, Photo photo) {
        try {
            InputStreamContent contentStream = new InputStreamContent(
                    CONTENT_TYPE, multipartFile.getInputStream());
            // Setting the length improves upload performance
            contentStream.setLength(multipartFile.getSize());
            StorageObject objectMetadata = new StorageObject()
                    // Set the destination object name
                    .setName(UPLOAD_PUBLIC + System.currentTimeMillis() + multipartFile.getOriginalFilename())
                    // Set the access control list to publicly read-only
                    .setAcl(Arrays.asList(
                            new ObjectAccessControl().setEntity("allUsers").setRole("READER")));

            Storage client = StorageFactory.getService();
            Storage.Objects.Insert insertRequest = client.objects().insert(
                    BUCKET_NAME, objectMetadata, contentStream);
            //Upload to cloud storage
            StorageObject object = insertRequest.execute();
            //Get url of object
            String urlMedia = object.getMediaLink();
            photo.setUrl(urlMedia);
            //Save DB
            this.photoService.save(photo);
            return object;
        } catch (IOException ioException) {
            return null;
        } catch (GeneralSecurityException gsException) {
            return null;
        }
    }

    public boolean userDeletePhoto(String photoName, boolean isPublish) {
        try {
            deleteObject(photoName);
            return true;
        } catch (IOException ioException) {
            return false;
        } catch (GeneralSecurityException gsException) {
            return false;
        }
    }

    public boolean adminApprovePhoto(String photoName) {
        try {
            Storage client = StorageFactory.getService();
            StorageObject object = client.objects().get(BUCKET_NAME, photoName).execute();
            Storage.Objects.Copy copyRequest = client.objects().copy(BUCKET_NAME, UPLOAD_REVIEW, BUCKET_NAME, UPLOAD_PUBLIC, object);
            copyRequest.execute();
            deleteObject(photoName);
            return true;
        } catch (IOException ioException) {
            return false;
        } catch (GeneralSecurityException gsException) {
            return false;
        }
    }

    public boolean adminRejectPhoto(String photoId) {
        return true;
    }

    // [START delete_object]

    /**
     * Deletes an object in a bucket.
     *
     * @param path the path to the object to delete.
     */
    public static void deleteObject(String path)
            throws IOException, GeneralSecurityException {
        Storage client = StorageFactory.getService();
        client.objects().delete(BUCKET_NAME, path).execute();
    }
    // [END delete_object]

}
//[END all]
