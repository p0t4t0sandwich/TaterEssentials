package dev.neuralnexus.taterutils.common.modules.mclogs.api;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterutils.common.TaterUtils;

import gs.mclo.api.MclogsClient;
import gs.mclo.api.response.InsightsResponse;
import gs.mclo.api.response.UploadLogResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/** API for the MCLogs module. */
public class MCLogsAPI {
    private final MclogsClient client;

    public MCLogsAPI() {
        client =
                new MclogsClient("TaterUtils-MCLogsModule/" + TaterUtils.Constants.PROJECT_VERSION);
        client.setProjectName("TaterUtils-MCLogsModule");
        client.setProjectVersion(TaterUtils.Constants.PROJECT_VERSION);
        client.setCustomUserAgent(
                "TaterUtils-MCLogsModule/" + TaterUtils.Constants.PROJECT_VERSION);
        client.setMinecraftVersion(TaterAPIProvider.get().minecraftVersion().getVersion());
    }

    /**
     * Uploads a log to MCLogs.
     *
     * @param log The log to upload.
     * @return The URL of the log.
     */
    public Optional<UploadLogResponse> uploadLog(String log) {
        try {
            return Optional.ofNullable(client.uploadLog(log).get());
        } catch (InterruptedException | ExecutionException e) {
            TaterUtils.getLogger().info("Failed to upload log.");
            return Optional.empty();
        }
    }

    /**
     * Get raw log data from MCLogs.
     *
     * @param id The ID of the log.
     * @return The raw log data.
     */
    public Optional<String> getRawLogContent(String id) {
        try {
            return Optional.ofNullable(client.getRawLogContent(id).get());
        } catch (InterruptedException | ExecutionException | IOException e) {
            TaterUtils.getLogger().info("Failed to get raw log content.");
            return Optional.empty();
        }
    }

    /**
     * Get the insights of a log from MCLogs.
     *
     * @param id The ID of the log.
     * @return The insights of the log.
     */
    public Optional<InsightsResponse> getLogInsights(String id) {
        try {
            return Optional.ofNullable(client.getInsights(id).get());
        } catch (InterruptedException | ExecutionException e) {
            TaterUtils.getLogger().info("Failed to get log insights.");
            return Optional.empty();
        }
    }

    /**
     * Upload the latest.log file to MCLogs.
     *
     * @return The UploadLogResponse of the upload.
     */
    public Optional<UploadLogResponse> uploadLatestLog() {
        return uploadLog("logs" + File.separator + "latest.log");
    }

    /**
     * Upload debug.log file to MCLogs.
     *
     * @return The UploadLogResponse of the upload.
     */
    public Optional<UploadLogResponse> uploadLatestDebugLog() {
        return uploadLog("logs" + File.separator + "debug.log");
    }

    /**
     * Upload latest crash report to MCLogs.
     *
     * @return The UploadLogResponse of the upload.
     */
    public Optional<UploadLogResponse> uploadLatestCrashReport() {
        try {
            Path dir = Paths.get("." + File.separator + "crash-reports");
            Optional<Path> lastFilePath =
                    Files.list(dir)
                            .filter(f -> !Files.isDirectory(f))
                            .max(Comparator.comparingLong(f -> f.toFile().lastModified()));
            if (lastFilePath.isPresent()) {
                return uploadLog(lastFilePath.get().toString());
            }
        } catch (IOException e) {
            TaterUtils.getLogger().info("No crash reports found.");
        }
        return Optional.empty();
    }

    /**
     * List all logs in the ./logs directory.
     *
     * @return A list of all logs in the ./logs directory.
     */
    public Set<String> listLogs() {
        return Arrays.stream(client.listLogsInDirectory("." + File.separator))
                .collect(Collectors.toSet());
    }

    /**
     * List all crash reports in the ./crash-reports directory.
     *
     * @return A list of all crash reports in the ./crash-reports directory.
     */
    public Set<String> listCrashReports() {
        return Arrays.stream(client.listCrashReportsInDirectory("." + File.separator))
                .collect(Collectors.toSet());
    }
}
