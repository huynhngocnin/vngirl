package ninhn.app.service;

import ninhn.app.model.Photo;
import ninhn.app.repository.PhotoRepository;
import ninhn.app.until.PageUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by NinHN on 5/14/16.
 */
@Service
@Transactional
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
    public List<Photo> findByPhotoPage(int page) {
        Page<Photo> photoPage = this.photoRepository.findAll(PageUntil.getPageNumber(page));
        if(photoPage!=null) {
            return photoPage.getContent();
        }
        return null;
    }

    @Override
    public List<Photo> findByPhotoRandom() {
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
