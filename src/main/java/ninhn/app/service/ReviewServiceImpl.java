package ninhn.app.service;

import ninhn.app.model.Photo;
import ninhn.app.repository.ReviewRepository;
import ninhn.app.until.PageUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by ninhn on 2016/08/01.
 */
public class ReviewServiceImpl extends ModelServiceImpl<Photo> implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        super(reviewRepository);
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Photo> findByPhotoPage(int page) {
        Page<Photo> photoPage = this.reviewRepository.findAll(PageUntil.getPageReviewNumber(page));
        if (photoPage != null) {
            List<Photo> photos = photoPage.getContent();
            if (photos != null && photos.size() > 0) {
                photos.forEach(photo -> photo.viewUp());
                return this.save(photos);
            }
            return null;
        }
        return null;
    }
}
