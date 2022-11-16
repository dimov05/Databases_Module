package course.springdata.gameshop.services;

import course.springdata.gameshop.config.domain.dtos.UserLoginDto;
import course.springdata.gameshop.config.domain.dtos.UserRegisterDto;

public interface UserService {
    String registerUser(UserRegisterDto userRegisterDto);

    String loginUser(UserLoginDto loginDto);

    String logOut();
}
