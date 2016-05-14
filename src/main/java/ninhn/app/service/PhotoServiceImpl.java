package ninhn.app.service;

import ninhn.app.model.Photo;
import ninhn.app.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

/**
 * Created by NinHN on 5/14/16.
 */
public class PhotoServiceImpl extends ModelServiceImpl<Photo> implements PhotoService {

    private final PhotoRepository photoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    public PhotoServiceImpl(PhotoRepository photoRepository) {
        super(photoRepository);
        this.photoRepository = photoRepository;
    }

    @Override
    public List<Photo> find20PhotoPage(int page) {
        return null;
    }

    @Override
    public List<Photo> find5PhotoRandom() {
        return null;
    }

    @Override
    public int insertMultiPhoto(List<Photo> photos) {
        return 0;
    }

    @Override
    public boolean updatePhotoViewUp(String photo_id) {
        return false;
    }

    @Override
    public boolean updatePhotoLikeUp(String photo_id) {
        return false;
    }

    @Override
    public boolean updatePhotoShareUp(String photo_id) {
        return false;
    }
}
