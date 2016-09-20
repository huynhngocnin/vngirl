package ninhn.app.service;

import ninhn.app.constant.SystemConstant;
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
    public int checkRoles(String userId) {
        User user = this.userRepository.findOne(userId);
        if (user != null) {
            if (SystemConstant.USER_ROLE_ADMIN == user.getRole()) {
                return SystemConstant.USER_ROLE_ADMIN;
            } else if (SystemConstant.USER_ROLE_USER == user.getRole()) {
                return SystemConstant.USER_ROLE_USER;
            }
        }
        return -1;
    }

    @Override
    public User findByFacebook(String facebook) {
        return this.userRepository.findByFacebook(facebook);
    }

    @Override
    public User updateUserLoveUp(String user_id, String photo_id) {
        User user = this.userRepository.findOne(user_id);
        if (user != null) {
            user.getLove().add(photo_id);
            return this.save(user);
        } else {
            return null;
        }
    }

    @Override
    public User updateUserLoveDown(String user_id, String photo_id) {
        User user = this.userRepository.findOne(user_id);
        if (user != null) {
            user.getLove().remove(photo_id);
            return this.save(user);
        } else {
            return null;
        }
    }

}
