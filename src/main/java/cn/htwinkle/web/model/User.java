package cn.htwinkle.web.model;

import cn.htwinkle.web.model.base.BaseUser;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class User extends BaseUser<User> {
    public static final User dao = new User().dao();


    public void removeSecretParams() {
        setUserPass(null);
        setUserSalt(null);
    }

}
