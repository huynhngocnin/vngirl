package ninhn.app.service;


import ninhn.app.model.User;


/**
 * Created by NinHN on 5/11/2016.
 */
public interface UserService extends ModelService<User>{


    /**
     * return a user if exist in database vice versa save and return a user
     *
     * @param user
     * @return user registered
     */
    User findAndRegisterUser(User user);

    int checkRoles(String userId);

    /**
     * find User by facebook
     * @param facebook
     * @return
     */
    User findByFacebook(String facebook);

    User updateUserLoveUp(String user_id, String photo_id);

    User updateUserLoveDown(String user_id, String photo_id);

}
