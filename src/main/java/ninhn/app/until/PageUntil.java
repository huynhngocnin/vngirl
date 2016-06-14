package ninhn.app.until;

import static ninhn.app.constant.SystemConstant.PHOTOS_IN_PAGE;
import static ninhn.app.constant.DBConstant.CREATE_TIME;
import static ninhn.app.constant.SystemConstant.PHOTOS_IN_RANDOM;
import static ninhn.app.constant.DBConstant.VIEW;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Created by NinHN on 5/14/16.
 */
public class PageUntil {

    private static Sort sort;

//    public static Pageable getPageDefault(String columnSort) {
//        Sort sort = new Sort(Sort.Direction.ASC, columnSort);
//        if (StringUtils.isEmpty(columnSort)) {
//            return new PageRequest(0, PHOTOS_IN_PAGE);
//        }
//        return new PageRequest(0, PHOTOS_IN_PAGE, sort);
//    }

    public static Pageable getPageNumber(int page) {
        sort = new Sort(Sort.Direction.DESC, CREATE_TIME);
        return new PageRequest(page, PHOTOS_IN_PAGE, sort);
    }

    public static Pageable getPageRandom(long total) {
        return new PageRequest(RandomUntil.getRandomPhotoPage(total), PHOTOS_IN_RANDOM);
    }
}
