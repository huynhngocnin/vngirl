package ninhn.app.controller;

import ninhn.app.model.Photo;
import ninhn.app.service.PhotoService;
import ninhn.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by NinHN on 5/16/16.
 */

@RestController
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "photo-count", method = RequestMethod.GET)
    public long getPhotoCount() {
        return this.photoService.countPhoto();
    }

    @RequestMapping(path = "photo-get", method = RequestMethod.GET)
    public Photo getPhotoId(@RequestParam String id) {
        return this.photoService.findById(id);
    }

    @RequestMapping(path = "photo-gets", method = RequestMethod.GET)
    public List<Photo> getPhotoIds(@RequestBody List<String> ids) {
        return this.photoService.findByIds(ids);
    }

    //Backup for old version
    @RequestMapping(path = "photo-list", method = RequestMethod.GET)
    public List<Photo> getPhotoList(@RequestParam(defaultValue = "0", required = false) int page) {
        return this.photoService.findByPhotoPageGlobal(page);
    }

    @RequestMapping(path = "photo-list-global", method = RequestMethod.GET)
    public List<Photo> getPhotoListGlobal(@RequestParam(defaultValue = "0", required = false) int page) {
        return this.photoService.findByPhotoPageGlobal(page);
    }

    @RequestMapping(path = "photo-list-user", method = RequestMethod.GET)
    public List<Photo> getPhotoListUser(@RequestParam String userId, @RequestParam(defaultValue = "0", required = false) int page) {
        return this.photoService.findByPhotoPageUser(userId, page);
    }

    @RequestMapping(path = "photo-random", method = RequestMethod.GET)
    public List<Photo> getPhotoRandom() {
        return this.photoService.findByPhotoRandom();
    }

//    @RequestMapping(path = "photo-insert", method = RequestMethod.POST)
//    public List<Photo> insertPhotos(@RequestBody List<Photo> photos) {
//        if (photos != null && photos.size() > 0) {
//            photos.forEach(photo -> photo.setCreateTime(new Date()));
//            return this.photoService.save(photos);
//        }
//        return null;
//    }

    @RequestMapping(path = "photo-love-up", method = RequestMethod.GET)
    public Boolean photoLoveUp(@RequestParam String photoId, @RequestParam String userId) {
        if (this.photoService.updatePhotoLoveUp(photoId, userId) != null &&
                this.userService.updateUserLoveUp(userId, photoId) != null)
            return true;
        return false;
    }

    @RequestMapping(path = "photo-love-down", method = RequestMethod.GET)
    public Boolean photoLoveDown(@RequestParam String photoId, @RequestParam String userId) {
        if (this.photoService.updatePhotoLoveDown(photoId, userId) != null &&
                this.userService.updateUserLoveDown(userId, photoId) != null)
            return true;
        return false;
    }

}
