package ninhn.app.constant;

import ninhn.app.model.Photo;
import ninhn.app.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NinHN on 5/14/16.
 */
public class SystemConstant {
    public static final List<User> USER_LIST_EMPTY = new ArrayList<>();
    public static final List<Photo> PHOTO_LIST_EMPTY = new ArrayList<>();

    public static final int PHOTOS_IN_PAGE = 30;
    public static final int PHOTOS_IN_RANDOM = 6;
    public static final String LAST_UP_TIME = "lastUpTime";
}
