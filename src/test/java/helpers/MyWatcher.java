package helpers;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class MyWatcher implements TestWatcher {

    public void testSuccessful(ExtensionContext context) {
        System.out.println("Название: " + context.getDisplayName());
        System.out.println("Кол-во переданных параметров " + context.getTestMethod().get().getParameterCount());
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
}
