package ninhn.app.repository;

import ninhn.app.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * Created by NinHN on 5/11/2016.
 */
public interface UserRepository extends MongoRepository<User,String>{

    /**
     * find user by facebook
     *
     * @param facebook
     * @return
     */
    User findByFacebook(long facebook);


}
