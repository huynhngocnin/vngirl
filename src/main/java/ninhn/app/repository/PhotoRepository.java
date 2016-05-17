package ninhn.app.repository;

import ninhn.app.model.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by NinHN on 5/14/16.
 */
public interface PhotoRepository extends MongoRepository<Photo, String> {

    Photo findById(String id);

    Page<Photo> findAll(Pageable pageable);

//    List<Photo> find5PhotoRandom();
//
//    int insertMultiPhoto(List<Photo> photos);
//
//    boolean updatePhotoViewUp(String photo_id);
//
//    boolean updatePhotoLikeUp(String photo_id);
//
//    boolean updatePhotoShareUp(String photo_id);
}
