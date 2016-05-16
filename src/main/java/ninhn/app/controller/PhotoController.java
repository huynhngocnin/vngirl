package ninhn.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by NinHN on 5/16/16.
 */

@RestController
public class PhotoController {

    @RequestMapping(path = "photo-test")
    public String photo(){
        return "Result Photo";
    }

}
