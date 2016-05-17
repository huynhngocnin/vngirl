package ninhn.app.until;

import static ninhn.app.constant.SystemConstant.PHOTOS_IN_PAGE;
import static ninhn.app.constant.SystemConstant.LAST_UP_TIME;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

/**
 * Created by NinHN on 5/14/16.
 */
public class PageUntil {

    public static Pageable getPageDefault() {
        return getPageDefault(null);
    }

    public static Pageable getPageDefault(String columnSort) {
        Sort sort = new Sort(Sort.Direction.ASC, columnSort);
        if (StringUtils.isEmpty(columnSort)) {
            return new PageRequest(0, PHOTOS_IN_PAGE);
        }
        return new PageRequest(0, PHOTOS_IN_PAGE, sort);
    }

    public static Pageable getPageNumber(int page) {
        Sort sort = new Sort(Sort.Direction.DESC, LAST_UP_TIME);
        return new PageRequest(page, PHOTOS_IN_PAGE, sort);
    }
}
