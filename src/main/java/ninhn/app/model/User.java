package ninhn.app.model;

import java.util.List;

/**
 * Created by NinHN on 5/11/2016.
 */
public class User extends Model {

    private String name;
    private String phone;
    private String birthday;
    private String from;
    private String email;
    private String facebook;
    private List<String> love;
//    private List<String> like;
//    private List<String> share;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public List<String> getLove() {
        return love;
    }

    public void setLove(List<String> love) {
        this.love = love;
    }

//    public List<String> getLike() {
//        return like;
//    }
//
//    public void setLike(List<String> like) {
//        this.like = like;
//    }
//
//    public List<String> getShare() {
//        return share;
//    }
//
//    public void setShare(List<String> share) {
//        this.share = share;
//    }

}
