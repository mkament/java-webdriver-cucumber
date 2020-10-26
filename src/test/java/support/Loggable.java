package support;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Loggable {

    default Logger getLogger() {
        return LogManager.getLogger(this.getClass());
    }

    default void logDebug(String message) {
        getLogger().debug(message);
    }

    default void logInfo(String message) {
        getLogger().info(message);
    }

    default void logError(String message) {
        getLogger().error(message);
    }
}