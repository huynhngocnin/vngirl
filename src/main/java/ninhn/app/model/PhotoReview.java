package ninhn.app.model;

import java.io.Serializable;

/**
 * Created by NinHN on 8/27/16.
 */
public class PhotoReview extends Photo implements Serializable{
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
