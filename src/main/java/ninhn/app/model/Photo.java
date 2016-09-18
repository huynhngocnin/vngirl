package ninhn.app.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NinHN on 5/14/16.
 */
public class Photo extends Model implements Serializable {

    private String name;
    private String description;
    private String url;
    private String uploadId;
    private String uploadName;
    private long view = 0;
    private List<String> love = new ArrayList<>();
    private String uploadAvatar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }

    public long getView() {
        return view;
    }

    public void setView(long view) {
        this.view = view;
    }

    public List<String> getLove() {
        return love;
    }

    public void setLove(List<String> love) {
        this.love = love;
    }

    public String getUploadAvatar() {
        return uploadAvatar;
    }

    public void setUploadAvatar(String uploadAvatar) {
        this.uploadAvatar = uploadAvatar;
    }

    public long viewUp() {
        this.view += 1;
        return this.view;
    }

    public static Photo convertFromPhotoReview(PhotoReview photoReview){
        Photo photo = new Photo();
        photo.setId(photoReview.getId());
        photo.setCreateTime(photoReview.getCreateTime());
        photo.setLastUpTime(photoReview.getLastUpTime());
        photo.setDeleted(photoReview.isDeleted());
        photo.setName(photoReview.getName());
        photo.setDescription(photoReview.getDescription());
        photo.setUrl(photoReview.getUrl());
        photo.setUploadId(photoReview.getUploadId());
        photo.setUploadName(photoReview.getUploadName());
        photo.setUploadAvatar(photoReview.getUploadAvatar());
        return photo;
    }

}
