package dev.neuralnexus.taterutils.modules.godmode.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.storage.Filesystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/** Filesystem implementation of the GodMode storage. */
public class FSGodModeStorage extends Filesystem implements GodModeStorage {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public FSGodModeStorage(DatabaseConfig config) {
        super(config);
    }

    /**
     * Read a file.
     *
     * @return The contents of the file.
     */
    private String read() {
        try {
            final Path path =
                    Paths.get(
                            getConnection()
                                    + File.separator
                                    + getDatabase()
                                    + File.separator
                                    + "godModeData"
                                    + ".json");
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Write to a file.
     *
     * @param json The contents of the file.
     */
    private void write(String json) {
        try {
            String file =
                    getConnection()
                            + File.separator
                            + getDatabase()
                            + File.separator
                            + "godModeData"
                            + ".json";
            Files.write(Paths.get(file), json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean getGodMode(Player player) {
        String json = read();
        if (json == null || json.isEmpty()) {
            write(gson.toJson(false));
            return false;
        }
        return gson.fromJson(json, Boolean.class);
    }

    /** {@inheritDoc} */
    @Override
    public void setGodMode(Player player, boolean godMode) {
        write(gson.toJson(godMode));
    }
}
