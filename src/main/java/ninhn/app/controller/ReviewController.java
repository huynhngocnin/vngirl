package ninhn.app.controller;

import ninhn.app.model.PhotoReview;
import ninhn.app.service.ReviewService;
import ninhn.app.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public List<PhotoReview> userPhotoReview(@RequestParam("userId") String userId, @RequestParam(defaultValue = "0", required = false) int page) {
        return this.reviewService.findByUploadId(userId, page);
    }

    @RequestMapping(value = "/user-photo-reject", method = RequestMethod.POST)
    public boolean userUploadRejectPhoto(@RequestParam("photoName") String photoName, @RequestParam("message") String message) {
        return this.storageService.adminRejectPhoto(photoName, message);
    }

    @RequestMapping(value = "/user-photo-approve", method = RequestMethod.POST)
    public boolean userUploadApprovePhoto(@RequestParam("photoName") String photoName) {
        return this.storageService.adminApprovePhoto(photoName);
    }

}
