package ninhn.app.service;

import ninhn.app.model.Photo;
import ninhn.app.repository.PhotoRepository;
import ninhn.app.until.PageUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NinHN on 5/14/16.
 */
@Service
@Transactional
public class PhotoServiceImpl extends ModelServiceImpl<Photo> implements PhotoService {

    private final PhotoRepository photoRepository;
//
//    @Autowired
//    private MongoTemplate mongoTemplate;

    @Autowired
    public PhotoServiceImpl(PhotoRepository photoRepository) {
        super(photoRepository);
        this.photoRepository = photoRepository;
    }

    @Override
    public long countPhoto() {
        return this.photoRepository.count();
    }

    @Override
    public Photo findById(String id) {
        Photo photo = this.photoRepository.findOne(id);
        photo.viewUp();
        return this.save(photo);

    }

    @Override
    public List<Photo> findByIds(List<String> ids) {
        Iterable<Photo> iterable = this.photoRepository.findAll(ids);
        List<Photo> photos = new ArrayList<>();
        iterable.forEach(photo -> photos.add(photo));
        return photos;
    }

    @Override
    public List<Photo> findByPhotoPage(int page) {
        Page<Photo> photoPage = this.photoRepository.findAll(PageUntil.getPageNumber(page));
        if (photoPage != null && photoPage.getSize() > 0) {
            List<Photo> photos = photoPage.getContent();
            photos.forEach(photo -> photo.viewUp());
            return this.save(photos);
        }
        return null;
    }

    @Override
    public List<Photo> findByPhotoRandom() {
        long count = this.photoRepository.count();
        Page<Photo> page = this.photoRepository.findAll(PageUntil.getPageRandom(count));
        return page.getContent();
    }

    @Override
    public List<Photo> insertMultiPhoto(List<Photo> photos) {
        return this.photoRepository.save(photos);
    }

    @Override
    public Photo updatePhotoLoveUp(String photo_id, String user_id) {
        Photo photo = this.photoRepository.findOne(photo_id);
        photo.getLove().add(user_id);
        return this.save(photo);
    }

//    @Override
//    public Photo updatePhotoLikeUp(String photo_id, String user_id) {
//        Photo photo = this.photoRepository.findOne(photo_id);
//        photo.getLike().add(user_id);
//        return this.save(photo);
//    }
//
//    @Override
//    public Photo updatePhotoShareUp(String photo_id, String user_id) {
//        Photo photo = this.photoRepository.findOne(photo_id);
//        photo.getShare().add(user_id);
//        return this.save(photo);
//    }
}
