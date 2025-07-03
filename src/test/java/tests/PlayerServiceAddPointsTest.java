package tests;

import helpers.MyWatcher;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.inno.course.player.model.Player;
import ru.inno.course.player.service.PlayerService;
import ru.inno.course.player.service.PlayerServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Проверка начисления очков")
@ExtendWith(MyWatcher.class)
public class PlayerServiceAddPointsTest {
    PlayerService playerService;

    @BeforeEach
    public void createService() {
        playerService = new PlayerServiceImpl();
    }

    @AfterEach
    public void deleteFile() throws IOException {
        Files.deleteIfExists(Path.of("./data.json"));
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, 0, 100})
    @Tag("ДОМЕН-СИСТЕМА ОПЛАТЫ")
   // @Disabled("Тест не актуален")
    // @Tags({@Tag("нег"),@Tag("регресс")})
   // @ValueSource(booleans = {true, false, true})
    @DisplayName("Начислить разное кол-во очков")
    public void addPointsTest(int points) {
        int playerId = playerService.createPlayer("Jack");
        Player player = playerService.getPlayerById(playerId);
        playerService.addPoints(playerId, points);

        assertEquals(points, player.getPoints());
        System.out.println(player.getPoints());
    }
}

