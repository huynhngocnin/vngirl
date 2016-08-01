package ninhn.app.until;

import static ninhn.app.constant.SystemConstant.PHOTOS_IN_PAGE;
import static ninhn.app.constant.SystemConstant.REVIEW_IN_PAGE;
import static ninhn.app.constant.DBConstant.CREATE_TIME;
import static ninhn.app.constant.SystemConstant.PHOTOS_IN_RANDOM;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Created by NinHN on 5/14/16.
 */
public class PageUntil {

    private static Sort sort;

    public static Pageable getPagePhotoNumber(int page) {
        sort = new Sort(Sort.Direction.DESC, CREATE_TIME);
        return new PageRequest(page, PHOTOS_IN_PAGE, sort);
    }

    public static Pageable getPageReviewNumber(int page) {
        sort = new Sort(Sort.Direction.DESC, CREATE_TIME);
        return new PageRequest(page, REVIEW_IN_PAGE, sort);
    }

    public static Pageable getPageRandom(long total) {
        return new PageRequest(RandomUntil.getRandomPhotoPage(total), PHOTOS_IN_RANDOM);
    }
}
