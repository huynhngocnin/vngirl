package ninhn.app.controller;

import ninhn.app.model.Photo;
import ninhn.app.service.PhotoService;
import ninhn.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(path = "photo-count")
    public long getPhotoCount() {
        return this.photoService.countPhoto();
    }

    @RequestMapping(path = "photo-get")
    public Photo getPhotoId(@RequestParam String id) {
        return this.photoService.findById(id);
    }

    @RequestMapping(path = "photo-gets")
    public List<Photo> getPhotoIds(@RequestBody List<String> ids) {
        return this.photoService.findByIds(ids);
    }

    @RequestMapping(path = "photo-list")
    public List<Photo> getPhotoList(@RequestParam(defaultValue = "0", required = false) int page) {
        return this.photoService.findByPhotoPage(page);
    }

    @RequestMapping(path = "photo-random")
    public List<Photo> getPhotoRandom() {
        return this.photoService.findByPhotoRandom();
    }

    @RequestMapping(path = "photo-insert")
    public List<Photo> insertPhotos(@RequestBody List<Photo> photos) {
        if (photos != null && photos.size() > 0) {
            photos.forEach(photo -> photo.setCreateTime(new Date()));
            return this.photoService.save(photos);
        }
        return null;
    }

    @RequestMapping(path = "photo-love-up")
    public boolean photoLoveUp(@RequestParam String photoId, @RequestParam String userId) {
        if (this.photoService.updatePhotoLoveUp(photoId, userId) != null &&
                this.userService.updateUserLoveUp(userId, photoId) != null)
            return true;
        return false;
    }

    @RequestMapping(path = "photo-love-down")
    public boolean photoLoveDown(@RequestParam String photoId, @RequestParam String userId) {
        if (this.photoService.updatePhotoLoveDown(photoId, userId) != null &&
                this.userService.updateUserLoveDown(userId, photoId) != null)
            return true;
        return false;
    }

}
