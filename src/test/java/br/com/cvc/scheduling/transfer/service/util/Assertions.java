package br.com.cvc.scheduling.transfer.service.util;

public class Assertions {


    public static void assertDoesNotThrow(FailingRunnable action) {
        try {
            action.run();
        } catch(Exception ex) {
            throw new Error("expected action not to throw, but it did!", ex);
        }
    }
}
