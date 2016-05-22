package ninhn.app.service;

import ninhn.app.model.User;
import ninhn.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by NinHN on 5/11/2016.
 */
@Service
@Transactional
public class UserServiceImpl extends ModelServiceImpl<User> implements UserService {

    private final UserRepository userRepository;
//
//    @Autowired
//    private MongoTemplate mongoTemplate;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }


    @Override
    public User findAndRegisterUser(User user) {
        User userdb = this.findByFacebook(user.getFacebook());
        if (userdb != null) {
            return userdb;
        }
        return super.save(user);
    }

    @Override
    public User findByFacebook(String facebook) {
        return this.userRepository.findByFacebook(facebook);
    }

    @Override
    public User updateUserLoveUp(String user_id, String photo_id) {
        User user = this.userRepository.findOne(user_id);
        user.getLove().add(photo_id);
        return this.save(user);
    }

}
