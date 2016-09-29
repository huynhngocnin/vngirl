package ninhn.app.controller;

import ninhn.app.constant.ControllerConstant;
import ninhn.app.model.Photo;
import ninhn.app.service.PhotoService;
import ninhn.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ninhn.app.constant.SystemConstant.*;

/**
 * Created by NinHN on 5/16/16.
 */

@RestController
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = ControllerConstant.REQUEST_MAPPING_PHOTO_COUNT, method = RequestMethod.GET)
    public long getPhotoCount() {
        return this.photoService.countPhoto();
    }

    @RequestMapping(path = ControllerConstant.REQUEST_MAPPING_PHOTO_GET, method = RequestMethod.GET)
    public Photo getPhotoId(@RequestParam String id) {
        return this.photoService.findById(id);
    }

    @RequestMapping(path = ControllerConstant.REQUEST_MAPPING_PHOTO_GETS, method = RequestMethod.GET)
    public List<Photo> getPhotoIds(@RequestBody List<String> ids) {
        return this.photoService.findByIds(ids);
    }

    //Backup for old version
    @RequestMapping(path = ControllerConstant.REQUEST_MAPPING_PHOTO_LIST, method = RequestMethod.GET)
    public List<Photo> getPhotoList(@RequestParam(defaultValue = ControllerConstant.DEFAULT_VALUE_PAGE, required = false) int page) {
        Photo photo = new Photo();
        photo.setName(APP_UPDATE_MESSAGE);
        photo.setDescription(APP_UPDATE_MESSAGE);
        photo.setUrl(APP_UPDATE_IMAGE);
        photo.setUploadId(UPLOAD_ID);
        photo.setUploadName(UPLOAD_NAME);
        photo.setUploadAvatar(UPLOAD_AVATAR);
        photo.setCreateTime(new Date());
        List<Photo> photos = new ArrayList<>();
        photos.add(photo);
        return photos;
    }

    @RequestMapping(path = ControllerConstant.REQUEST_MAPPING_PHOTO_LIST_GLOBAL, method = RequestMethod.GET)
    public List<Photo> getPhotoListGlobal(@RequestParam(defaultValue = ControllerConstant.DEFAULT_VALUE_PAGE, required = false) int page) {
        return this.photoService.findByPhotoPageGlobal(page);
    }

    @RequestMapping(path = ControllerConstant.REQUEST_MAPPING_PHOTO_LIST_USER, method = RequestMethod.GET)
    public List<Photo> getPhotoListUser(@RequestParam String userId, @RequestParam(defaultValue = ControllerConstant.DEFAULT_VALUE_PAGE, required = false) int page) {
        return this.photoService.findByPhotoPageUser(userId, page);
    }

    @RequestMapping(path = ControllerConstant.REQUEST_MAPPING_PHOTO_RANDOM, method = RequestMethod.GET)
    public List<Photo> getPhotoRandom() {
        return this.photoService.findByPhotoRandom();
    }

    @RequestMapping(path = ControllerConstant.REQUEST_MAPPING_PHOTO_LOVE_UP, method = RequestMethod.GET)
    public Boolean photoLoveUp(@RequestParam String photoId, @RequestParam String userId) {
        if (this.photoService.updatePhotoLoveUp(photoId, userId) != null &&
                this.userService.updateUserLoveUp(userId, photoId) != null)
            return true;
        return false;
    }

    @RequestMapping(path = ControllerConstant.REQUEST_MAPPING_PHOTO_LOVE_DOWN, method = RequestMethod.GET)
    public Boolean photoLoveDown(@RequestParam String photoId, @RequestParam String userId) {
        if (this.photoService.updatePhotoLoveDown(photoId, userId) != null &&
                this.userService.updateUserLoveDown(userId, photoId) != null)
            return true;
        return false;
    }

}
