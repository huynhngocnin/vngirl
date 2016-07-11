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
    public static final int PHOTOS_IN_RANDOM = 4;

    public static final String UPLOAD_PUBLIC = "photo/public/";
    public static final String UPLOAD_REVIEW = "photo/review/";

    public static final String BLANK = "";
    public static final String EXTEND_JPG = ".jpg";
    public static final String EXTEND_JPEG = ".jpeg";
    public static final String EXTEND_PNG = ".png";

    public static final String UPLOAD_AVATAR ="https://storage.googleapis.com/vn_girl/photo/avatar.jpg";
    public static final String UPLOAD_NAME ="David L. Evans";
    public static final String UPLOAD_ID ="1178079645550097";

}
