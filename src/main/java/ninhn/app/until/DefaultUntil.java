package ninhn.app.until;

import ninhn.app.model.Photo;

import java.util.Date;

import static ninhn.app.constant.SystemConstant.UPLOAD_ID;
import static ninhn.app.constant.SystemConstant.UPLOAD_NAME;
import static ninhn.app.constant.SystemConstant.UPLOAD_AVATAR;

/**
 * Created by NinHN on 6/11/16.
 */
public class DefaultUntil {
    public static Photo photoInsert(String fileUrl, String fileName) {
        Photo photo = new Photo();
        photo.setName(fileName);
        photo.setDescription(fileName);
        photo.setUrl(fileUrl);
        photo.setUploadId(UPLOAD_ID);
        photo.setUploadName(UPLOAD_NAME);
        photo.setUploadAvatar(UPLOAD_AVATAR);
        photo.setCreateTime(new Date());
        return photo;
    }
}
