package ninhn.app.controller;

import ninhn.app.model.Photo;
import ninhn.app.model.User;
import ninhn.app.service.PhotoService;
import ninhn.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
        if (!StringUtils.isEmpty(id)) {
            return this.userService.findById(id);
        }
        return null;
    }

    @RequestMapping(path = "user-get-facebook")
    public User getUserFacebook(@RequestParam String facebook) {
        if (!StringUtils.isEmpty(facebook)) {
            return this.userService.findByFacebook(facebook);
        }
        return null;
    }

    @RequestMapping(path = "user-register")
    public User registerUser(@RequestBody User user) {
        if (user != null) {
            user.setCreateTime(new Date());
            return this.userService.findAndRegisterUser(user);
        }
        return null;
    }

    @RequestMapping(path = "user-photo-love")
    public List<Photo> getPhotoLove(@RequestParam String userId) {
        if (!StringUtils.isEmpty(userId)) {
            User user = this.userService.findById(userId);
            if (user != null) {
                List<String> loves = user.getLove();
                return this.photoService.findByIds(loves);
            }
        }
        return null;
    }

    @RequestMapping(path = "test")
    public String test() {
        return "ok";
    }
}
