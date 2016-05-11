package ninhn.app.model;

/**
 * Created by ninhn on 5/11/2016.
 */
public class User extends Model {

    private String name;
    private String avatar;
    private long facebook;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
