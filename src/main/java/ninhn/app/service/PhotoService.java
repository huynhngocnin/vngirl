package ninhn.app.service;

import ninhn.app.model.Photo;

import java.util.List;

/**
 * Created by NinHN on 5/14/16.
 */
public interface PhotoService extends ModelService<Photo> {

    long countPhoto();

    Photo findById(String id);

    List<Photo> findByIds(List<String> ids);

    List<Photo> findByPhotoPage(int page);

    List<Photo> findByPhotoRandom();

    Photo updatePhotoLoveUp(String photo_id, String user_id);

    Photo updatePhotoLoveDown(String photo_id, String user_id);

}
