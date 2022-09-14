package apitests.restassured;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserModel {
    private String name;
    private String job;
    private String email;
    private String password;

}
