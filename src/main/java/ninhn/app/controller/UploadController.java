package ninhn.app.controller;

import com.google.api.services.storage.model.StorageObject;
import ninhn.app.model.Photo;
import ninhn.app.service.ReviewService;
import ninhn.app.service.StorageService;
import ninhn.app.until.DefaultUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import static ninhn.app.constant.SystemConstant.*;
import static ninhn.app.constant.SystemConstant.BLANK;

/**
 * Created by NinHN on 6/11/16.
 */
@RestController
//@RequestMapping("/api/aws/s3/public")
@RequestMapping("/api/gcp/storage/public")
public class UploadController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public List<StorageObject> upload(@RequestParam("file") MultipartFile[] multipartFiles) {
        return this.storageService.uploads(multipartFiles);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<StorageObject> list() throws IOException, GeneralSecurityException {
        return this.storageService.listBucket();
    }

    @RequestMapping(value = "/user-photo-upload", method = RequestMethod.POST)
    public StorageObject userUploadPhoto(@RequestParam("photo-file") MultipartFile multipartFile, @RequestParam("photo-info") Photo photo) {
        return this.storageService.userUploadPhoto(multipartFile, photo);
    }

    @RequestMapping(value = "/user-photo-delete", method = RequestMethod.POST)
    public boolean userUploadPhoto(@RequestParam("photo-name") String photoName, @RequestParam("photo-is-publish") boolean isPublish) {
        return this.storageService.userDeletePhoto(photoName, isPublish);
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
