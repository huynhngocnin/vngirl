package ninhn.app.repository;

import ninhn.app.model.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by NinHN on 08/01/16.
 */
public interface ReviewRepository extends MongoRepository<Photo, String> {

    Page<Photo> findAll(Pageable pageable);

    Photo findByName(String photoName);

}
