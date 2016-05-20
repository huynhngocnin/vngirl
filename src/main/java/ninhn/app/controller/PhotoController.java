package ninhn.app.controller;

import ninhn.app.model.Photo;
import ninhn.app.service.PhotoService;
import ninhn.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        return this.photoService.save(photos);
    }

    @RequestMapping(path = "photo-love")
    public boolean photoLove(@RequestParam String photoId, @RequestParam String userId){
        this.photoService.updatePhotoLoveUp(photoId, userId);
        this.userService.updateUserLoveUp(userId, photoId);
        return true;
    }

    @RequestMapping(path = "photo-like")
    public boolean photoLike(@RequestParam String photoId, @RequestParam String userId) {
        this.photoService.updatePhotoLikeUp(photoId, userId);
        this.userService.updateUserLikeUp(userId, photoId);
        return true;
    }

    @RequestMapping(path = "photo-share")
    public boolean photoShare(@RequestParam String photoId, @RequestParam String userId) {
        this.photoService.updatePhotoShareUp(photoId, userId);
        this.userService.updateUserShareUp(userId, photoId);
        return true;
    }

    @RequestMapping(path = "photo-test")
    public String photo() {
        return "Result Photo";
    }

}
