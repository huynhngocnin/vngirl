package ninhn.app.controller;

import ninhn.app.model.Photo;
import ninhn.app.model.User;
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
 * Created by ninhn on 5/11/2016.
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PhotoService photoService;


    @RequestMapping(path = "user-get")
    public User getUser(@RequestParam String id) {
        return this.userService.findById(id);
    }

    @RequestMapping(path = "user-get-facebook")
    public User getUserFacebook(@RequestParam String facebook) {
        return this.userService.findByFacebook(facebook);
    }

    @RequestMapping(path = "user-register")
    public User registerUser(@RequestBody User user) {
        user.setCreateTime(new Date());
        return this.userService.findAndRegisterUser(user);
    }

    @RequestMapping(path = "user-photo-love")
    public List<Photo> getPhotoLove(@RequestParam String userId) {
        User user = this.userService.findById(userId);
        if (user != null) {
            List<String> loves = user.getLove();
            return this.photoService.findByIds(loves);
        }
        return null;
    }

    @RequestMapping(path = "test")
    public String test() {
        return "ok";
    }
}
