package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.inno.course.player.model.Player;
import ru.inno.course.player.service.PlayerService;
import ru.inno.course.player.service.PlayerServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerServiceDeleteTest {
    PlayerService playerService;

    @BeforeEach
    public void createService() {
        playerService = new PlayerServiceImpl();
    }

    @AfterEach
    public void deleteFile() throws IOException {
        Files.deleteIfExists(Path.of("./data.json"));
    }

    @Test
    public void deletePlayerTest() {
        int playerId = playerService.createPlayer("Jack");
        playerService.deletePlayer(playerId);
        Player player = playerService.getPlayerById(playerId);
        assertNull(player);
    }

    @Test
    public void deletePlayersTest() {
        int jackId = playerService.createPlayer("Jack");
        int alexId = playerService.createPlayer("Alex");
        playerService.deletePlayer(jackId);
        playerService.deletePlayer(alexId);
        Collection<Player> players = playerService.getPlayers();
        assertTrue(players.isEmpty());
    }
}
