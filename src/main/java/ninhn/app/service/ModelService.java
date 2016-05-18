package ninhn.app.service;

/**
 * Created by NinHN on 5/11/16.
 */

import ninhn.app.model.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface  ModelService <T extends Model> {
    /**
     * save model
     * @param t
     * @return
     */
    T save(T t);

    /**
     * save models
     * @param t
     * @return
     */
    List<T> save(List<T> t);

    /**
     * update model
     * @param t
     * @return
     */
    T update(T t);

    /**
     * delete id
     * @param id
     * @return
     */
    boolean delete(String id);

    /**
     * find model by Id
     * @param id
     * @return
     */
    T findById(String id);

    /**
     * find all
     * @param pageable
     * @return
     */
    Page<T> findAll(Pageable pageable);
}
