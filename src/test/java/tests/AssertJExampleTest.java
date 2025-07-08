package tests;

import helpers.PlayerServiceResolver;
import helpers.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.inno.course.player.model.Player;
import ru.inno.course.player.service.PlayerService;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(PlayerServiceResolver.class)
public class AssertJExampleTest {


    @Test
    @DisplayName("Запуск с хранилищем наполненным большим кол-во игроков")
    public void runStorageWithManyPlayersTest(@Players(5) PlayerService playerService){
        // подставляем data.json с 1000 игроками
        Collection<Player> players = playerService.getPlayers();
        //  assertTrue(players.size() > 44444, "Сравнение размера массивов");
        //assertEquals(players.size(),999);
       // assertThat(players.size(), Matchers.greaterThan(44444));
      //  assertThat("Размер списка игроков", players.size(), Matchers.equalTo(999));
        //assertThat("Размер списка игроков", players.size(), Matchers.is(999));
       // assertThat("Размер списка игроков", players, Matchers.hasSize(999));
        assertThat(players).hasSize(4).allMatch(player -> !player.isOnline());
    }

    @Test
    @DisplayName("Запуск с хранилищем наполненным экстремально большим кол-во игроков")
    public void runStorageWithManyPlayersExtremelyBigTest(@Players(1_000_000) PlayerService playerService){
        // подставляем data.json с 1000000 игроками
        Collection<Player> players = playerService.getPlayers();
        assertTrue(players.size() == 1_000_000);
    }


    @Test
    public void justtest(){
      //  assertThat(100.5, Matchers.closeTo(100.0, 1));
    }

    @Test
    public void deletePlayersTest(@Players(3) PlayerService playerService) {
        int jackId = playerService.createPlayer("Jack");
        int alexId = playerService.createPlayer("Alex");

        Collection<Player> players = playerService.getPlayers();

       // assertThat(new Player(3, "Jack", 412412410, true), Matchers.in(players));
    }
}
