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

    List<Photo> insertMultiPhoto(List<Photo> photos);

    Photo updatePhotoLoveUp(String photo_id, String user_id);

//    Photo updatePhotoLikeUp(String photo_id, String user_id);
//
//    Photo updatePhotoShareUp(String photo_id, String user_id);
}
