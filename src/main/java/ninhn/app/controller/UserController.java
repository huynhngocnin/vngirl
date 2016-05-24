package ninhn.app.controller;

import ninhn.app.model.User;
import ninhn.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by ninhn on 5/11/2016.
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(path = "get-user")
    public User getUser(@RequestParam String id) {
        return this.userService.findById(id);
    }

    @RequestMapping(path = "get-user-facebook")
    public User getUserFacebook(@RequestParam String facebook) {
        return this.userService.findByFacebook(facebook);
    }

    @RequestMapping(path = "register-user")
    public User registerUser(@RequestBody User user) {
        user.setCreateTime(new Date());
        return this.userService.findAndRegisterUser(user);
    }

    @RequestMapping(path = "test")
    public String test() {
        return "ok";
    }
}
