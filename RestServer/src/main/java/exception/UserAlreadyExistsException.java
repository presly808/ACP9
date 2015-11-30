package exception;

/**
 * Created by serhii on 26.11.15.
 */
public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
