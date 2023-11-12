package dev.neuralnexus.taterutils.common.storage.spawn;

import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.storage.Filesystem;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterlib.lib.gson.Gson;
import dev.neuralnexus.taterlib.lib.gson.GsonBuilder;
import dev.neuralnexus.taterutils.common.api.AbstractLocation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Filesystem implementation of the spawn storage.
 */
public class FSSpawnStorage extends Filesystem implements SpawnStorage {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public FSSpawnStorage(DatabaseConfig config) {
        super(config);
    }

    /**
     * Read a file.
     * @return The contents of the file.
     */
    private String read() {
        try {
            String file = getConnection() + File.separator + getDatabase() + File.separator + "spawn" + ".json";
            return new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Write to a file.
     * @param json The contents of the file.
     */
    private void write(String json) {
        try {
            String file = getConnection() + File.separator + getDatabase() + File.separator + "spawn" + ".json";
            Files.write(Paths.get(file), json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Location> getSpawn() {
        return Optional.ofNullable(gson.fromJson(read(), AbstractLocation.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpawn(Location location) {
        write(gson.toJson(new AbstractLocation(location)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteSpawn() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean teleportSpawn(Player player) {
        return false;
    }
}
