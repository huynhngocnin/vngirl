package ninhn.app.service;

import ninhn.app.model.PhotoReview;
import ninhn.app.repository.ReviewRepository;
import ninhn.app.until.PageUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ninhn on 2016/08/01.
 */
@Service
@Transactional
public class ReviewServiceImpl extends ModelServiceImpl<PhotoReview> implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        super(reviewRepository);
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<PhotoReview> findByPhotoPage(int page) {
        Page<PhotoReview> photoPage = this.reviewRepository.findAll(PageUntil.getPageReviewNumber(page));
        if (photoPage != null) {
            List<PhotoReview> photos = photoPage.getContent();
            return photos;
        }
        return null;
    }

    @Override
    public List<PhotoReview> findByUploadId(String userId, int page) {
        Page<PhotoReview> photoPage = this.reviewRepository.findByUploadId(userId, PageUntil.getPageReviewNumber(page));
        return photoPage.getContent();
    }



    @Override
    public PhotoReview findByName(String name) {
        return this.reviewRepository.findByName(name);
    }

    @Override
    public PhotoReview deletePhotoReviewByName(String name){
        return this.reviewRepository.deletePhotoReviewByName(name);
    }
}
