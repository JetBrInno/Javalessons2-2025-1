package helpers;

import ru.inno.course.player.model.Player;

import java.util.HashSet;
import java.util.Set;

public class PlayerGenerator {

    public static Set<Player> generate(int count){
        Set<Player> players = new HashSet<>();
        for (int i = 0; i < count; i++) {
            players.add(new Player(i, "Name " + i, i*10, true));
        }
        return players;
    }
}
