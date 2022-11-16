package course.springdata.gameshop.services;

import course.springdata.gameshop.config.domain.dtos.AddGameDto;
import course.springdata.gameshop.config.domain.dtos.DeleteGameDto;
import course.springdata.gameshop.config.domain.dtos.UserDto;
import course.springdata.gameshop.config.domain.entities.Game;
import course.springdata.gameshop.config.domain.entities.Role;
import course.springdata.gameshop.repositories.GameRepository;
import course.springdata.gameshop.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final GameRepository gameRepo;
    private UserDto userDto;

    public GameServiceImpl(ValidatorUtil validatorUtil, ModelMapper modelMapper, GameRepository gameRepo) {
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.gameRepo = gameRepo;
    }

    @Override
    public String addGame(AddGameDto addGameDto) {
        StringBuilder sb = new StringBuilder();
        if (isLoggedUserAnAdmin()) {
            sb.append("invalid logged user - not an admin");
        } else if (validatorUtil.isValid(addGameDto)) {
            Game game = this.modelMapper.map(addGameDto, Game.class);
            this.gameRepo.saveAndFlush(game);

            sb.append(String.format("Added %s",
                    game.getTitle()));
            System.out.println();
        } else {
            this.validatorUtil.violations(addGameDto)
                    .forEach(error -> sb.append(error.getMessage()).append(System.lineSeparator()));
        }

        return sb.toString();
    }

    @Override
    public void setLoggedUser(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public String deleteGame(DeleteGameDto deleteGameDto) {
        StringBuilder sb = new StringBuilder();
        if (isLoggedUserAnAdmin()) {
            sb.append("invalid logged user - not an admin");
        } else {
            Optional<Game> game = this.gameRepo.findById(deleteGameDto.getId());

            if (game.isPresent()) {
                this.gameRepo.delete(game.get());
                sb.append(String.format("Game %s was deleted",
                        game.get().getTitle()));
            } else {
                sb.append("Cannot find game");
            }
        }
        return sb.toString();
    }

    private boolean isLoggedUserAnAdmin() {
        return this.userDto == null || this.userDto.getRole().equals(Role.USER);
    }
}
