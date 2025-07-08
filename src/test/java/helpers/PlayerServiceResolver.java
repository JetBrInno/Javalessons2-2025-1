package helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.helpers.AnnotationHelper;
import ru.inno.course.player.model.Player;
import ru.inno.course.player.service.PlayerService;
import ru.inno.course.player.service.PlayerServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import static helpers.PlayerGenerator.generate;

public class PlayerServiceResolver implements ParameterResolver, AfterEachCallback {
    PlayerService playerService;

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(PlayerService.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Players annotation = AnnotationHelper.findAnnotation(parameterContext.getAnnotatedElement(), Players.class);
        int numPlayers = annotation.value();

        Set<Player> players = generate(numPlayers);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(Path.of("./data.json").toFile(), players);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        playerService = new PlayerServiceImpl();
        return playerService;
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
       Files.deleteIfExists(Path.of("./data.json"));
    }
}
