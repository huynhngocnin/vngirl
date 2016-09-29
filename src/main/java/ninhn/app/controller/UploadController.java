package ninhn.app.controller;

import com.google.api.services.storage.model.StorageObject;
import ninhn.app.constant.ControllerConstant;
import ninhn.app.constant.PhotoConstant;
import ninhn.app.constant.SystemConstant;
import ninhn.app.model.PhotoReview;
import ninhn.app.service.StorageService;
import ninhn.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.ResourceBundle;


/**
 * Created by NinHN on 6/11/16.
 */
@RestController
@RequestMapping("/api/gcp/storage/public")
public class UploadController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = ControllerConstant.REQUEST_MAPPING_UPLOAD, method = RequestMethod.POST)
    public List<StorageObject> upload(@RequestParam(ControllerConstant.REQUEST_PARAM_FILE) MultipartFile[] multipartFiles) {
        return this.storageService.uploads(multipartFiles);
    }

    @RequestMapping(value = ControllerConstant.REQUEST_MAPPING_LIST, method = RequestMethod.GET)
    public List<StorageObject> list() throws IOException, GeneralSecurityException {
        return this.storageService.listBucket();
    }

    @RequestMapping(value = ControllerConstant.REQUEST_MAPPING_USER_PHOTO_UPLOAD)
    public StorageObject userUploadPhoto(@RequestParam(ControllerConstant.REQUEST_PARAM_PHOTO_FILE) MultipartFile multipartFile, @RequestParam(ControllerConstant.REQUEST_PARAM_PHOTO_INFO) String strPhotoReview) {
        return this.storageService.userUploadPhoto(multipartFile, strPhotoReview);
    }

    @RequestMapping(value = ControllerConstant.REQUEST_MAPPING_ADMIN_PHOTO_UPLOAD)
    public StorageObject adminUploadPhoto(@RequestParam(ControllerConstant.REQUEST_PARAM_PHOTO_FILE) MultipartFile multipartFile, @RequestParam(ControllerConstant.REQUEST_PARAM_PHOTO_INFO) String strPhoto,
                                          @RequestParam(ControllerConstant.REQUEST_PARAM_USER_ID) String userId) {
        if (SystemConstant.USER_ROLE_ADMIN == this.userService.checkRoles(userId)) {
            return this.storageService.adminUploadPhoto(multipartFile, strPhoto);
        }
        return null;
    }

}
