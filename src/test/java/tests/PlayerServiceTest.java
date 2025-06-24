package tests;

import ru.inno.course.player.model.Player;
import ru.inno.course.player.service.PlayerService;
import ru.inno.course.player.service.PlayerServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PlayerServiceTest {

    // Проблемы, которые нужно решить с помощью фреймворка
    // 1. Много буков (код становится слишком большим)
    // 2. Не совсем понятно, где начался первый тест, где второй и тд и тп
    // 3. Неинформатинвый вывод в консоли
    // 4. Взаимосвязанные тесты
    // 5. Повторяющиеся действия (предусловие, постусловие)

    public static void main(String[] args) throws Exception {

        ///  ТЕСТ 1 Создать игрока с валидным именем
        PlayerService playerService = new PlayerServiceImpl();

        String name = "Alex";

        int alexId = playerService.createPlayer(name);

        Player alex = playerService.getPlayerById(alexId);


        if (name.equals(alex.getNick())) {
            System.out.println("Имя пользователя совпадает с именем созданного пользователя");
        } else {
            throw new Exception("Имя пользователя не совпадает, тест упал");
        }


        Files.deleteIfExists(Path.of("./data.json"));

        ///  ТЕСТ 2 Создать игрока с повторяющ. именем
        playerService = new PlayerServiceImpl();

        name = "DuplicateName";

        playerService.createPlayer(name);

        try {
            playerService.createPlayer(name);
            System.out.println("Пользователь создан, хотя не должен был быть создан. Тест провален!");
        } catch (IllegalArgumentException exception)
        {
            System.out.println("Тест прошел!");
        }

        Files.deleteIfExists(Path.of("./data.json"));
    }
}
