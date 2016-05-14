package ninhn.app.model;

import java.util.List;

/**
 * Created by NinHN on 5/11/2016.
 */
public class User extends Model {

    private String name;
    private String birthday;
    private String from;
    private String avatar;
    private long facebook;
    private List<String> like;
    private List<String> share;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getFacebook() {
        return facebook;
    }

    public void setFacebook(long facebook) {
        this.facebook = facebook;
    }

    public List<String> getLike() {
        return like;
    }

    public void setLike(List<String> like) {
        this.like = like;
    }

    public List<String> getShare() {
        return share;
    }

    public void setShare(List<String> share) {
        this.share = share;
    }
}
