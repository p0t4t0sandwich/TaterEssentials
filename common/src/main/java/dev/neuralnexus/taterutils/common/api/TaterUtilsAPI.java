package dev.neuralnexus.taterutils.common.api;

/**
 * API wrapper class
 */
public class TaterUtilsAPI {
    private final Data data;

    public TaterUtilsAPI() {
        this.data = new Data();
    }

    /**
     * Data used throughout the plugin via the API.
     */
    static class Data {
        Data() {}
    }
}
