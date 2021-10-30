package auth.dto;

import auth.service.DbService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.SQLException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private String full_name;
    private String password;
    private String prepassword;

    public static boolean isPassword(UserDto userDto) {
        if (userDto.getPassword().equals(userDto.getPrepassword())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean save(UserDto userDto) throws SQLException {
        UserDto userDto1 = new UserDto();
        userDto1.setUsername(userDto.getUsername());
        userDto1.setFull_name(userDto.getFull_name());
        userDto1.setPassword(userDto.getPassword());
        if (DbService.userSave(userDto1)) {
            return true;
        } else {
            return false;
        }
    }
}
