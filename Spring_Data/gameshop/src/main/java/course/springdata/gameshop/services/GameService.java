package course.springdata.gameshop.services;

import course.springdata.gameshop.config.domain.dtos.AddGameDto;
import course.springdata.gameshop.config.domain.dtos.DeleteGameDto;
import course.springdata.gameshop.config.domain.dtos.UserDto;

public interface GameService {
    String addGame(AddGameDto addGameDto);

    void setLoggedUser(UserDto userDto);

    String deleteGame(DeleteGameDto deleteGameDto);
}
