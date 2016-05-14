package ninhn.app.repository;

import ninhn.app.model.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by NinHN on 5/14/16.
 */
public interface PhotoRepository extends MongoRepository<Photo, String> {

    Photo findById(String id);

    List<Photo> find20PhotoPage(int page);

    List<Photo> find5PhotoRandom();

    int insertMultiPhoto(List<Photo> photos);

    boolean updatePhotoViewUp(String photo_id);

    boolean updatePhotoLikeUp(String photo_id);

    boolean updatePhotoShareUp(String photo_id);
}
