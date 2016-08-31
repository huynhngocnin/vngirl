package ninhn.app.controller;

import com.google.api.services.storage.model.StorageObject;
import ninhn.app.model.PhotoReview;
import ninhn.app.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;


/**
 * Created by NinHN on 6/11/16.
 */
@RestController
@RequestMapping("/api/gcp/storage/public")
public class UploadController {

    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public List<StorageObject> upload(@RequestParam("file") MultipartFile[] multipartFiles) {
        return this.storageService.uploads(multipartFiles);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<StorageObject> list() throws IOException, GeneralSecurityException {
        return this.storageService.listBucket();
    }

    @RequestMapping(value = "/user-photo-upload")
    public StorageObject userUploadPhoto(@RequestParam("photo-file") MultipartFile multipartFile, PhotoReview photoReview) {
        return this.storageService.userUploadPhoto(multipartFile, photoReview);
    }

    @RequestMapping(value = "/user-photo-delete", method = RequestMethod.POST)
    public boolean userUploadDeletePhoto(@RequestParam("photo-name") String photoName, @RequestParam("photo-is-publish") boolean isPublish) {
        return this.storageService.userDeletePhoto(photoName, isPublish);
    }

    @RequestMapping(value = "/user-photo-reject", method = RequestMethod.POST)
    public boolean userUploadRejectPhoto(@RequestParam("photo-name") String photoName) {
        return false;
    }

    @RequestMapping(value = "/user-photo-approve", method = RequestMethod.POST)
    public boolean userUploadApprovePhoto(@RequestParam("photo-name") String photoName) {
        return false;
    }

//    @Autowired
//    private S3Wrapper s3Wrapper;
//
//    @RequestMapping(value = "/upload", method = RequestMethod.POST)
//    public List<PutObjectResult> upload(@RequestParam("file") MultipartFile[] multipartFiles) {
//        return s3Wrapper.upload(multipartFiles);
//    }
//
//    @RequestMapping(value = "/download", method = RequestMethod.GET)
//    public ResponseEntity<byte[]> download(@RequestParam String key) throws IOException {
//        return s3Wrapper.download(key);
//    }
//
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public List<S3ObjectSummary> list() throws IOException {
//        return s3Wrapper.list();
//    }
}
