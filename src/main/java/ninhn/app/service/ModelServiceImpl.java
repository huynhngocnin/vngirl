package ninhn.app.service;


import ninhn.app.model.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by NinHN on 5/11/16.
 */
public class ModelServiceImpl<T extends Model> implements ModelService<T> {

    MongoRepository<T, String> repository;

    public ModelServiceImpl(MongoRepository<T, String> repository) {
        this.repository = repository;
    }

    @Override
    public T save(T t) {
        if (t == null) {
            throw new IllegalArgumentException("model must not empty");
        }
        t.setLastUpTime(new Date());
        return repository.save(t);
    }

    @Override
    public List<T> save(List<T> ts) {
        if (ts == null || ts.size() == 0) {
            throw new IllegalArgumentException("models must not empty");
        }
        for (int i = 0; i < ts.size(); i++) {
            ts.get(i).setLastUpTime(new Date());
        }
        return repository.save(ts);
    }

    @Override
    public T update(T t) {
        if (t == null) throw new IllegalArgumentException("model must not empty");
        return null;
    }

    @Override
    public boolean delete(String id) {
        if (StringUtils.isEmpty(id)) throw new IllegalArgumentException("Id must not empty");
        return false;
    }

    @Override
    public T findById(String id) {
        if (StringUtils.isEmpty(id)) throw new IllegalArgumentException("Id must not empty");
        return repository.findOne(id);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
