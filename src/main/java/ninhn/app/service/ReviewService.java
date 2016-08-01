package ninhn.app.service;

import ninhn.app.model.Photo;

import java.util.List;

/**
 * Created by ninhn on 2016/08/01.
 */
public interface ReviewService extends ModelService<Photo> {

    List<Photo> findByPhotoPage(int page);
}
