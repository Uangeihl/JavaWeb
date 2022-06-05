package cn.smbms.exception;

public class LoginException extends RuntimeException{

    private static final long serialVersionUID = -6686585794889405886L;

    public LoginException() {
        super("用户名或密码错误！");
    }
}
