package dev.neuralnexus.taterutils.common.storage;

import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.storage.Filesystem;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterlib.lib.gson.Gson;
import dev.neuralnexus.taterlib.lib.gson.GsonBuilder;
import dev.neuralnexus.taterlib.lib.gson.reflect.TypeToken;
import dev.neuralnexus.taterutils.common.api.modules.HomeAPI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Set;

public class FSHomeStorage extends Filesystem implements HomeStorage {
    private final Gson gson = new GsonBuilder().create();
    public FSHomeStorage(DatabaseConfig config) {
        super(config);
    }

    /**
     * Read a file.
     * @param uuid The UUID of the player.
     * @return The contents of the file.
     */
    private String read(String uuid) {
        try {
            final Path path = Paths.get(getConnection() + File.separator + getDatabase() + File.separator + uuid + ".json");
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
     * @param uuid The UUID of the player.
     * @param json The contents of the file.
     */
    private void write(String uuid, String json) {
        try {
            String file = getConnection() + File.separator + getDatabase() + File.separator + uuid + ".json";
            Files.write(Paths.get(file), json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<HomeAPI.PlayerHome> getHome(Player player, String home) {
        Set<HomeAPI.PlayerHome> homes = getHomes(player);
        return homes.stream().filter(h -> h.name.equals(home)).findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHome(Player player, String home, Location location) {
        Set<HomeAPI.PlayerHome> homes = getHomes(player);
        if (homes.stream().anyMatch(h -> h.name.equals(home))) {
            homes.removeIf(h -> h.name.equals(home));
        }
        homes.add(new HomeAPI.PlayerHome(home, location.getWorld(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch()));
        String json = gson.toJson(homes);
        write(player.getUniqueId().toString(), json);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteHome(Player player, String home) {
        Set<HomeAPI.PlayerHome> homes = getHomes(player);
        homes.removeIf(h -> h.name.equals(home));
        String json = gson.toJson(homes);
        write(player.getUniqueId().toString(), json);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<HomeAPI.PlayerHome> getHomes(Player player) {
        String json = read(player.getUniqueId().toString());
        java.lang.reflect.Type type = new TypeToken<Set<HomeAPI.PlayerHome>>(){}.getType();
        Set<HomeAPI.PlayerHome> homes = gson.fromJson(json, type);
        return homes == null ? new java.util.HashSet<>() : homes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean teleportHome(Player player, String home) {
        HomeAPI.PlayerHome playerHome = getHome(player, home).orElse(null);
        if (playerHome == null) {
            return false;
        } else {
            player.teleport(playerHome.getLocation());
            return true;
        }
    }
}
