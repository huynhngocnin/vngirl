package ninhn.app.controller;

import ninhn.app.constant.ControllerConstant;
import ninhn.app.model.Photo;
import ninhn.app.model.User;
import ninhn.app.service.PhotoService;
import ninhn.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(path = ControllerConstant.REQUEST_MAPPING_USER_GET, method = RequestMethod.GET)
    public User getUser(@RequestParam String id) {
        if (!StringUtils.isEmpty(id)) {
            return this.userService.findById(id);
        }
        return null;
    }

    @RequestMapping(path = ControllerConstant.REQUEST_MAPPING_USER_GET_FACEBOOK, method = RequestMethod.GET)
    public User getUserFacebook(@RequestParam String facebook) {
        if (!StringUtils.isEmpty(facebook)) {
            return this.userService.findByFacebook(facebook);
        }
        return null;
    }

    @RequestMapping(path = ControllerConstant.REQUEST_MAPPING_USER_REGISTER, method = RequestMethod.POST)
    public User registerUser(@RequestBody User user) {
        if (user != null) {
            user.setCreateTime(new Date());
            return this.userService.findAndRegisterUser(user);
        }
        return null;
    }

    @RequestMapping(path = ControllerConstant.REQUEST_MAPPING_USER_PHOTO_LOVE, method = RequestMethod.GET)
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

    @RequestMapping(path = "testOK")
    public String test() {
        return "OK";
    }
}
