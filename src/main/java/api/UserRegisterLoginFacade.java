package api;

import entity.User;
import exception.UserLoginAlreadyExistException;
import exception.UserShortLengthLoginException;
import exception.UserShortLengthPasswordException;

public interface UserRegisterLoginFacade {
    boolean registerUser(User user) throws UserShortLengthLoginException, UserLoginAlreadyExistException, UserShortLengthPasswordException;
    boolean loginUser(String login, String password);
}
