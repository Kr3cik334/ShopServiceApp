package facade;

import api.UserRegisterLoginFacade;
import api.UserService;
import entity.User;
import exception.UserLoginAlreadyExistException;
import exception.UserShortLengthLoginException;
import exception.UserShortLengthPasswordException;
import service.UserServiceImpl;

public class UserRegisterLoginFacadeImpl implements UserRegisterLoginFacade {
    private UserService userService = UserServiceImpl.getInstance();
    private static  UserRegisterLoginFacade instance = null;

    private UserRegisterLoginFacadeImpl() {

    }

    public static UserRegisterLoginFacade getInstance() {
        if (instance == null) {
            instance = new UserRegisterLoginFacadeImpl();
        }
        return instance;
    }

    public boolean registerUser(User user) throws UserShortLengthLoginException, UserLoginAlreadyExistException, UserShortLengthPasswordException {
        return userService.addUser(user);
    }

    public boolean loginUser(String login, String password) {
        if (userService.isCorrectLoginAndPassword(login,password)) {
            return true;
        }
        return false;
    }
}
