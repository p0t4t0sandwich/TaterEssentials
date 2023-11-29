package dev.neuralnexus.taterutils.modules.home.storage;

import dev.neuralnexus.taterlib.lib.gson.Gson;
import dev.neuralnexus.taterlib.lib.gson.GsonBuilder;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.storage.Filesystem;
import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterutils.api.NamedLocation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Set;

/** Filesystem implementation of the home storage. */
public class FSHomeStorage extends Filesystem implements HomeStorage {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public FSHomeStorage(DatabaseConfig config) {
        super(config);
    }

    /**
     * Read a file.
     *
     * @param uuid The UUID of the player.
     * @return The contents of the file.
     */
    private String read(String uuid) {
        try {
            final Path path =
                    Paths.get(
                            getConnection()
                                    + File.separator
                                    + getDatabase()
                                    + File.separator
                                    + uuid
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
     * @param uuid The UUID of the player.
     * @param json The contents of the file.
     */
    private void write(String uuid, String json) {
        try {
            String file =
                    getConnection()
                            + File.separator
                            + getDatabase()
                            + File.separator
                            + uuid
                            + ".json";
            Files.write(Paths.get(file), json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** {@inheritDoc} */
    @Override
    public Optional<NamedLocation> getHome(Player player, String home) {
        Set<NamedLocation> homes = getHomes(player);
        return homes.stream().filter(h -> h.getName().equals(home)).findFirst();
    }

    /** {@inheritDoc} */
    @Override
    public void setHome(Player player, String home, Location location) {
        Set<NamedLocation> homes = getHomes(player);
        if (homes.stream().anyMatch(h -> h.getName().equals(home))) {
            homes.removeIf(h -> h.getName().equals(home));
        }
        homes.add(
                new NamedLocation(
                        home,
                        location.getWorld(),
                        location.getX(),
                        location.getY(),
                        location.getZ(),
                        location.getYaw(),
                        location.getPitch()));
        String json = gson.toJson(homes);
        write(player.getUniqueId().toString(), json);
    }

    /** {@inheritDoc} */
    @Override
    public void deleteHome(Player player, String home) {
        Set<NamedLocation> homes = getHomes(player);
        homes.removeIf(h -> h.getName().equals(home));
        String json = gson.toJson(homes);
        write(player.getUniqueId().toString(), json);
    }

    /** {@inheritDoc} */
    @Override
    public Set<NamedLocation> getHomes(Player player) {
        String json = read(player.getUniqueId().toString());
        Set<NamedLocation> homes = gson.fromJson(json, NamedLocation.getType());
        return homes == null ? new java.util.HashSet<>() : homes;
    }

    /** {@inheritDoc} */
    @Override
    public boolean teleportHome(Player player, String home) {
        NamedLocation playerHome = getHome(player, home).orElse(null);
        if (playerHome == null) {
            return false;
        } else {
            player.teleport(playerHome.getLocation());
            return true;
        }
    }
}
