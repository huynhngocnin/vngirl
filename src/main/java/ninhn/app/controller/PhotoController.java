package ninhn.app.controller;

import ninhn.app.model.Photo;
import ninhn.app.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(path = "photo-list")
    public List<Photo> getPhotoList(@RequestParam int page){
        return this.photoService.findByPhotoPage(page);
    }

    @RequestMapping(path = "photo-test")
    public String photo(){
        return "Result Photo";
    }

}
