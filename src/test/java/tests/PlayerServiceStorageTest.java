package tests;

import helpers.MyWatcher;
import helpers.PlayerServiceResolver;
import helpers.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.inno.course.player.model.Player;
import ru.inno.course.player.service.PlayerService;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(PlayerServiceResolver.class)
public class PlayerServiceStorageTest {

    @Test
    @DisplayName("Запуск с пустым хранилищем")
    public void runEmptyStorageTest(@Players(0) PlayerService playerService){
        // подставляем пустой data.json
        Collection<Player> players = playerService.getPlayers();
        assertTrue(players.isEmpty());
    }

    @Test
    @DisplayName("Запуск с хранилищем наполненным большим кол-во игроков")
    public void runStorageWithManyPlayersTest(@Players(1000) PlayerService playerService){
        // подставляем data.json с 1000 игроками
        Collection<Player> players = playerService.getPlayers();
        assertTrue(players.size() == 1_000);
    }

    @Test
    @DisplayName("Запуск с хранилищем наполненным экстремально большим кол-во игроков")
    public void runStorageWithManyPlayersExtremelyBigTest(@Players(1_000_000) PlayerService playerService){
        // подставляем data.json с 1000000 игроками
        Collection<Player> players = playerService.getPlayers();
        assertTrue(players.size() == 1_000_000);
    }
}
