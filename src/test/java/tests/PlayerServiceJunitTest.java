package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.inno.course.player.model.Player;
import ru.inno.course.player.service.PlayerService;
import ru.inno.course.player.service.PlayerServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerServiceJunitTest {

    // Проблемы, которые нужно решить с помощью фреймворка
    // 1. !ВЫПОЛНЕНО! Много буков (код становится слишком большим)
    // 2. !ВЫПОЛНЕНО! Не совсем понятно, где начался первый тест, где второй и тд и тп
    // 3. !ВЫПОЛНЕНО! Неинформатинвый вывод в консоли
    // 4. !ВЫПОЛНЕНО! Взаимосвязанные тесты
    // 5. Повторяющиеся действия (предусловие, постусловие)

    PlayerService playerService;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Этот код выполнится один раз");
    }

    @BeforeEach
    public void createService() {
        playerService = new PlayerServiceImpl();
    }

    @AfterEach
    public void deleteFile() throws IOException {
        Files.deleteIfExists(Path.of("./data.json"));
    }

    @Test
    public void createPlayerWithValidName() {
        String name = "Alex";
        int alexId = playerService.createPlayer(name);
        Player alex = playerService.getPlayerById(alexId);

        assertEquals(name, alex.getNick(), "Сравниваем имя пользователя");
    }

    @Test
    public void createPlayerWithDuplicateName() throws IOException {
        String name = "DuplicateName";
        playerService.createPlayer(name);

        assertThrows(IllegalArgumentException.class, () -> playerService.createPlayer(name));
    }
}
