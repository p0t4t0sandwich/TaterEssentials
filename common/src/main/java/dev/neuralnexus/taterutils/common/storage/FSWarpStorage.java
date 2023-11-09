package dev.neuralnexus.taterutils.common.storage;

import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.storage.Filesystem;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterlib.lib.gson.Gson;
import dev.neuralnexus.taterlib.lib.gson.GsonBuilder;
import dev.neuralnexus.taterlib.lib.gson.reflect.TypeToken;
import dev.neuralnexus.taterutils.common.api.modules.WarpAPI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

public class FSWarpStorage extends Filesystem implements WarpStorage {
    private Gson gson = new GsonBuilder().create();
    public FSWarpStorage(DatabaseConfig config) {
        super(config);
    }

    /**
     * Read a file.
     * @return The contents of the file.
     */

    private String read() {
        try {
            String file = getConnection() + File.separator + getDatabase() + File.separator + "warps" + ".json";
            return new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Write to a file..
     * @param json The contents of the file.
     */
    private void write( String json) {
        try {
            String file = getConnection() + File.separator + getDatabase() + File.separator + "warps" + ".json";
            Files.write(Paths.get(file), json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<WarpAPI.WarpLocation> getWarp(String warp) {
        Set<WarpAPI.WarpLocation> warps = getWarps();
        return warps.stream().filter(h -> h.name.equals(warp)).findFirst();
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void setWarp(Player player, String warp, Location location) {
        Set<WarpAPI.WarpLocation> warps = getWarps();
        warps.add(new WarpAPI.WarpLocation(warp, location.getWorld(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch()));
        String json = gson.toJson(warps);
        write(json);
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void deleteWarp(String warp) {
        Set<WarpAPI.WarpLocation> warps = getWarps();
        warps.removeIf(h -> h.name.equals(warp));
        String json = gson.toJson(warps);
        write(json);
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public Set<WarpAPI.WarpLocation> getWarps() {
        String json = read();
        java.lang.reflect.Type type = new TypeToken<Set<WarpAPI.WarpLocation>>(){}.getType();
        Set<WarpAPI.WarpLocation> warps = gson.fromJson(json, type);
        return warps == null ? new java.util.HashSet<>() : warps;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean teleportWarp(Player player, String warp) {
        WarpAPI.WarpLocation warps = getWarp(warp).orElse(null);
        if (warps == null) {
            return false;
        } else {
            player.teleport(warps.getLocation());
            return true;
        }
    }
}
