package ninhn.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ninhn on 5/11/2016.
 */

@RestController
public class UserController {



    @RequestMapping(path = "test")
    public String test() {
        return "ok";
    }
}
