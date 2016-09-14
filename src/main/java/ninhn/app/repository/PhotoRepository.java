package ninhn.app.repository;

import ninhn.app.model.Photo;
import ninhn.app.model.PhotoReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Created by NinHN on 5/14/16.
 */
public interface PhotoRepository extends MongoRepository<Photo, String> {

    Photo findById(String id);

//    @Query("{\"$or\": [\n" +
//            "    {\"deleted\":{ false }, \n" +
//            "    {\"deleted\":{ null } \n" +
//            "    ]}")
    Page<Photo> findAll(Pageable pageable);

    Page<Photo> findByUploadId(String uploadId, Pageable pageable);


    Photo findByName(String photoName);
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
