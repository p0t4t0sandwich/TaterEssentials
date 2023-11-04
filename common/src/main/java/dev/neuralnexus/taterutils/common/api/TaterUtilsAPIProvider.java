package dev.neuralnexus.taterutils.common.api;

import dev.neuralnexus.taterutils.common.TaterUtils;

/**
 * TaterUtils API Provider
 */
public class TaterUtilsAPIProvider {
    private static TaterUtils instance = null;

    /**
     * Get the instance of BeeNameGenerator
     * @return The instance of BeeNameGenerator
     */
    public static TaterUtils get() {
        if (instance == null) {
            throw new NotLoadedException();
        }
        return instance;
    }

    /**
     * DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY
     * @param instance: The instance of TaterUtils
     */
    public static void register(TaterUtils instance) {
        TaterUtilsAPIProvider.instance = instance;
    }

    /**
     * DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY
     */
    public static void unregister() {
        instance = null;
    }

    /**
     * Throw this exception when the API hasn't loaded yet, or you don't have the BeeNameGenerator plugin installed.
     */
    private static final class NotLoadedException extends IllegalStateException {
        private static final String MESSAGE = "The API hasn't loaded yet, or you don't have the TaterUtils plugin installed.";

        NotLoadedException() {
            super(MESSAGE);
        }
    }
}
