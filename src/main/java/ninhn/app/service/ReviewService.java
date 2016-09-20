package ninhn.app.service;

import ninhn.app.model.PhotoReview;

import java.util.List;

/**
 * Created by ninhn on 2016/08/01.
 */
public interface ReviewService extends ModelService<PhotoReview> {

    List<PhotoReview> findByPhotoPage(int page);

    List<PhotoReview> findByUploadId(String userId, int page);

    PhotoReview findByName(String name);

    Long deletePhotoReviewByName(String name);
}
