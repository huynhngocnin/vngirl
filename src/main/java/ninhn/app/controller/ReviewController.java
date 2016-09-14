package ninhn.app.controller;

import com.google.api.services.storage.model.StorageObject;
import ninhn.app.model.PhotoReview;
import ninhn.app.service.ReviewService;
import ninhn.app.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;


/**
 * Created by NinHN on 6/11/16.
 */
@RestController
@RequestMapping("/storage/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/photo-review-user")
    public List<PhotoReview> userPhotoReview(@RequestParam String userId, @RequestParam(defaultValue = "0", required = false) int page) {
        return this.reviewService.findByUploadId(userId, page);
    }

//    @RequestMapping(value = "/user-photo-delete", method = RequestMethod.POST)
//    public boolean userUploadDeletePhoto(@RequestParam("photo-name") String photoName, @RequestParam("photo-is-publish") boolean isPublish) {
//        return this.storageService.userDeletePhoto(photoName, isPublish);
//    }

    @RequestMapping(value = "/user-photo-reject", method = RequestMethod.POST)
    public boolean userUploadRejectPhoto(@RequestParam("photoName") String photoName) {
        return this.storageService.adminRejectPhoto(photoName);
    }

    @RequestMapping(value = "/user-photo-approve", method = RequestMethod.POST)
    public boolean userUploadApprovePhoto(@RequestParam("photoName") String photoName) {
        return this.storageService.adminApprovePhoto(photoName);
    }

}
