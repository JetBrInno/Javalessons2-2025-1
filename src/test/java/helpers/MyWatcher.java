package helpers;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class MyWatcher implements TestWatcher, BeforeAllCallback, AfterAllCallback {

    private int count = 0;

    public void testSuccessful(ExtensionContext context) {
        System.out.println("Название: " + context.getDisplayName());
        System.out.println("Кол-во переданных параметров " + context.getTestMethod().get().getParameterCount());
        count++;
    }

    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("Название: " + context.getDisplayName());
        System.out.println("Кол-во переданных параметров " + context.getTestMethod().get().getParameterCount());
        System.out.println("Причина падения: " + cause.getMessage());
    }

    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        System.out.println("Название: " + context.getDisplayName());
        System.out.println("Причина: " + reason.get());
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        System.out.println("Тесты запущены");
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        String head = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Отчет</title>
                </head>
                <body>     
                """;
        String tail = """
                    </body>
                </html>
                """;
        Files.writeString(Path.of("report.html"), head + "<h1>Кол-во пройденных тестов: " + count + "</h1>" + tail);
        System.out.println("Тесты завершены");
    }
}
