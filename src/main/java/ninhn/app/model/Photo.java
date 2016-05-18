package ninhn.app.model;


import java.util.List;

/**
 * Created by NinHN on 5/14/16.
 */
public class Photo extends Model {

    private String name;
    private String description;
    private String url;
    private String uploader;
    private long view;
    private List<String> like;
    private List<String> share;

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

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public long getView() {
        return view;
    }

    public void setView(long view) {
        this.view = view;
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

    public long viewUp(){
        this.view += 1;
        return this.view;
    }

}
