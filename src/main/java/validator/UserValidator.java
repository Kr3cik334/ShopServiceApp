package validator;

import entity.User;
import exception.UserShortLengthLoginException;
import exception.UserShortLengthPasswordException;

import java.io.IOException;

public class UserValidator {
    private static final int MIN_LENGHT_PASSWORD = 6;
    private static final int MIN_LENGHT_LOGIN = 4;

    private static UserValidator instance = null;

    private UserValidator() {

    }

    public static UserValidator getInstance() {
        if (instance == null) {
            instance = new UserValidator();
        }
        return instance;
    }
    public static boolean isValidate(User user) throws UserShortLengthLoginException, UserShortLengthPasswordException {
        if (isPasswordLengthNoEnough(user.getPassword()))
            throw new UserShortLengthPasswordException("Password is too short");

        if (isLoginLengthNoEnough(user.getLogin()))
            throw new UserShortLengthLoginException("Login is too short");

        return true;
    }

    private static boolean isPasswordLengthNoEnough(String password) {
        return password.length() >= MIN_LENGHT_PASSWORD;
    }
    private static boolean isLoginLengthNoEnough(String login) {
        return login.length() >= MIN_LENGHT_LOGIN;
    }

}
