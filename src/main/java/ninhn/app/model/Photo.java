package ninhn.app.model;


import java.util.List;

/**
 * Created by NinHN on 5/14/16.
 */
public class Photo extends Model {

    private String name;
    private String description;
    private String url;
    private long uploader;
    private long view;
    private List<Long> like;
    private List<Long> share;

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

    public long getUploader() {
        return uploader;
    }

    public void setUploader(long uploader) {
        this.uploader = uploader;
    }

    public long getView() {
        return view;
    }

    public void setView(long view) {
        this.view = view;
    }

    public List<Long> getLike() {
        return like;
    }

    public void setLike(List<Long> like) {
        this.like = like;
    }

    public List<Long> getShare() {
        return share;
    }

    public void setShare(List<Long> share) {
        this.share = share;
    }
}
