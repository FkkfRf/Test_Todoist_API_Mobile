package models.lombok;

import lombok.Data;

@Data
public class LoginSuccessResponse {
    private int id;
    private String token;
}
