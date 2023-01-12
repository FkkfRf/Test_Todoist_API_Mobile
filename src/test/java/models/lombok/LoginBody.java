package models.lombok;

import lombok.Data;

@Data
public class LoginBody {
    private String email;
    private String password;
    private Boolean permanent_login;
    private String pkce_oauth;
    private Boolean web_session;
}
