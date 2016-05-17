package ninhn.app.service;

import ninhn.app.model.Photo;

import java.util.List;

/**
 * Created by NinHN on 5/14/16.
 */
public interface PhotoService extends ModelService<Photo> {

    Photo findById(String id);

    List<Photo> findByPhotoPage(int page);

    List<Photo> findByPhotoRandom();

    int insertMultiPhoto(List<Photo> photos);

    boolean updatePhotoViewUp(String photo_id);

    boolean updatePhotoLikeUp(String photo_id);

    boolean updatePhotoShareUp(String photo_id);
}
