package helpers;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class MyWatcher implements TestWatcher {

    public void testSuccessful(ExtensionContext context) {
        System.out.println(context.getDisplayName());
        System.out.println(context.getTestMethod().get().getParameterCount());
    }
}
