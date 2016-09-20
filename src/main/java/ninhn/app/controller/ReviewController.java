package ninhn.app.controller;

import ninhn.app.constant.ControllerConstant;
import ninhn.app.constant.SystemConstant;
import ninhn.app.model.PhotoReview;
import ninhn.app.service.ReviewService;
import ninhn.app.service.StorageService;
import ninhn.app.service.UserService;
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
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/photo-review-user")
    public List<PhotoReview> userPhotoReview(@RequestParam("userId") String userId, @RequestParam(defaultValue = "0", required = false) int page) {
        return this.reviewService.findByUploadId(userId, page);
    }

    @RequestMapping(value = "/photo-review-admin")
    public List<PhotoReview> adminPhotoReview(@RequestParam("userId") String userId, @RequestParam(defaultValue = "0", required = false) int page) {
        if (SystemConstant.USER_ROLE_ADMIN == this.userService.checkRoles(userId)) {
            return this.reviewService.findByPhotoPage(page);
        }
        return ControllerConstant.ARRAY_EMPTY;
    }

    @RequestMapping(value = "/photo-review-approve")
    public Boolean userUploadApprovePhoto(@RequestParam("userId") String userId, @RequestParam("photoName") String photoName, @RequestParam("approve") String approve, @RequestParam("message") String message) {
        if (SystemConstant.USER_ROLE_ADMIN == this.userService.checkRoles(userId)) {
            if (ControllerConstant.REVIEW_APPROVE.equals(approve)) {
                return this.storageService.adminApprovePhoto(photoName);
            } else {
                return this.storageService.adminRejectPhoto(photoName, message);
            }
        }
        return false;
    }

}
