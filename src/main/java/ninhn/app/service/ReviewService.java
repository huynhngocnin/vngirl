package ninhn.app.service;

import ninhn.app.model.Photo;
import ninhn.app.model.PhotoReview;

import java.util.List;

/**
 * Created by ninhn on 2016/08/01.
 */
public interface ReviewService extends ModelService<PhotoReview> {

    List<PhotoReview> findByPhotoPage(int page);

    PhotoReview findByName(String photoName);
}
