package course.springdata.gameshop.services;

import course.springdata.gameshop.config.domain.dtos.UserDto;
import course.springdata.gameshop.config.domain.dtos.UserLoginDto;
import course.springdata.gameshop.config.domain.dtos.UserRegisterDto;
import course.springdata.gameshop.config.domain.entities.Role;
import course.springdata.gameshop.config.domain.entities.User;
import course.springdata.gameshop.repositories.UserRepository;
import course.springdata.gameshop.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final GameService gameService;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final UserRepository userRepo;
    private UserDto loggedUser;

    public UserServiceImpl(GameService gameService, ValidatorUtil validatorUtil, ModelMapper modelMapper, UserRepository userRepo) {
        this.gameService = gameService;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
    }


    @Override
    public String registerUser(UserRegisterDto userRegisterDto) {
        StringBuilder sb = new StringBuilder();

        if (!userRegisterDto.getConfirmPassword().equals(userRegisterDto.getPassword())) {
            sb.append("Passwords don't match");
        } else if (this.validatorUtil.isValid(userRegisterDto)) {
            User user = this.modelMapper.map(userRegisterDto, User.class);

            if (userRepo.count() == 0) {
                user.setRole(Role.ADMIN);
            } else {
                user.setRole(Role.USER);
            }
            sb.append(String.format("%s was registered",
                    userRegisterDto.getFullName()));
            this.userRepo.saveAndFlush(user);
        } else {
            this.validatorUtil.violations(userRegisterDto)
                    .forEach(e -> sb.append(String.format("%s%n",
                            e.getMessage())));
        }


        return sb.toString().trim();
    }

    @Override
    public String loginUser(UserLoginDto loginDto) {
        Optional<User> user = this.userRepo.findAllByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        StringBuilder sb = new StringBuilder();

        if (user.isPresent()) {
            if (this.loggedUser != null) {
                sb.append("User is already logged in");
            } else {
                this.loggedUser = this.modelMapper.map(user.get(), UserDto.class);
                this.gameService.setLoggedUser(this.loggedUser);
                sb.append(String.format("Successfully logged in %s%n",
                        user.get().getFullName()));
            }
        } else {
            sb.append("Incorrect email / password");
        }

        return sb.toString();
    }

    @Override
    public String logOut() {
        if (this.loggedUser == null) {
            return "Cannot log out. No user was logged in.";
        } else {
            String name = loggedUser.getFullName();
            this.loggedUser = null;
            return String.format("Use %s successfully logged out%n", name);
        }
    }
}
