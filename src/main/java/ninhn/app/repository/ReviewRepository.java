package ninhn.app.repository;

import ninhn.app.model.PhotoReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by NinHN on 08/01/16.
 */
public interface ReviewRepository extends MongoRepository<PhotoReview, String> {

    Page<PhotoReview> findAll(Pageable pageable);

    Page<PhotoReview> findByUploadId(String uploadId, Pageable pageable);

    PhotoReview findByName(String name);

    PhotoReview deletePhotoReviewByName(String name);

}
