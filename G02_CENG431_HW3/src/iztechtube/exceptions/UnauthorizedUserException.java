package iztechtube.exceptions;

public class UnauthorizedUserException extends Exception{

    public UnauthorizedUserException(String msg){
        super(msg);
    }
}
