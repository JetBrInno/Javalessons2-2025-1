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

import static org.junit.jupiter.api.Assertions.*;

public class PlayerServiceGetTest {
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
    public void getPlayerTest() {
        int playerId = playerService.createPlayer("Jack");
        Player player = playerService.getPlayerById(playerId);
        //assertEquals(playerId, player.getId());
        assertNotNull(player);
    }

    @Test
    public void getNonexistentPlayerTest() {
        int notExistsId = 12345;
        Player player = playerService.getPlayerById(notExistsId);
        assertNull(player);
    }
}
