package course.springdata.gameshop;

import course.springdata.gameshop.config.domain.dtos.AddGameDto;
import course.springdata.gameshop.config.domain.dtos.DeleteGameDto;
import course.springdata.gameshop.config.domain.dtos.UserLoginDto;
import course.springdata.gameshop.config.domain.dtos.UserRegisterDto;
import course.springdata.gameshop.services.GameService;
import course.springdata.gameshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class Runner implements CommandLineRunner {
    private final UserService userService;
    private final BufferedReader reader;
    private final GameService gameService;

    @Autowired
    public Runner(UserService userService, BufferedReader bufferedReader, GameService gameService) {
        this.userService = userService;
        this.reader = bufferedReader;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            String[] tokens = reader.readLine().split("\\|");
            switch (tokens[0]) {
                case "RegisterUser":
                    UserRegisterDto registerDto = new UserRegisterDto(tokens[1], tokens[2], tokens[3], tokens[4]);

                    System.out.println(this.userService.registerUser(registerDto));
                    break;
                case "LoginUser":
                    UserLoginDto loginDto = new UserLoginDto(tokens[1], tokens[2]);
                    System.out.println(this.userService.loginUser(loginDto));
                    break;
                case "Logout":
                    System.out.println(this.userService.logOut());
                    break;
                case "AddGame":
                    LocalDate date = LocalDate.parse(tokens[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    AddGameDto addGameDto = new AddGameDto(tokens[1], new BigDecimal(tokens[2]),
                            Double.parseDouble(tokens[3]), tokens[4], tokens[5], tokens[6], date);
                    System.out.println(this.gameService.addGame(addGameDto));
                    break;
                case "DeleteGame":
                    DeleteGameDto deleteGameDto = new DeleteGameDto(Long.parseLong(tokens[1]));
                    System.out.println(this.gameService.deleteGame(deleteGameDto));
                    break;
            }
        }

    }
}
