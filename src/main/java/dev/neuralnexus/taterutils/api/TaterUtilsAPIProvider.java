/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.api;

/** API Provider */
public class TaterUtilsAPIProvider {
    private static TaterUtilsAPI instance = null;

    /**
     * Get the instance of the API
     *
     * @return The instance of the API
     */
    public static TaterUtilsAPI get() {
        if (instance == null) {
            throw new NotLoadedException();
        }
        return instance;
    }

    /**
     * DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY
     *
     * @param instance: The instance of TaterUtils
     */
    public static void register(TaterUtilsAPI instance) {
        if (TaterUtilsAPIProvider.instance != null) {
            throw new IllegalStateException("TaterAPI has already been registered!");
        }
        TaterUtilsAPIProvider.instance = instance;
    }

    /** DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY */
    public static void unregister() {
        instance = null;
    }

    /**
     * Throw this exception when the API hasn't loaded yet, or you don't have the BeeNameGenerator
     * plugin installed.
     */
    private static final class NotLoadedException extends IllegalStateException {
        private static final String MESSAGE =
                "The API hasn't loaded yet, or you don't have the TaterUtils plugin installed.";

        NotLoadedException() {
            super(MESSAGE);
        }
    }
}
