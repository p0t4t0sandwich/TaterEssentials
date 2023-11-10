package dev.neuralnexus.taterutils.common.storage.warp;

import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.storage.Filesystem;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterlib.lib.gson.Gson;
import dev.neuralnexus.taterlib.lib.gson.GsonBuilder;
import dev.neuralnexus.taterutils.common.api.modules.NamedLocation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Set;

public class FSWarpStorage extends Filesystem implements WarpStorage {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
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
     * Write to a file.
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
    public Optional<NamedLocation> getWarp(String warp) {
        Set<NamedLocation> warps = getWarps();
        return warps.stream().filter(h -> h.getName().equals(warp)).findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWarp(String warp, Location location) {
        Set<NamedLocation> warps = getWarps();
        warps.add(new NamedLocation(warp, location.getWorld(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch()));
        String json = gson.toJson(warps);
        write(json);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteWarp(String warp) {
        Set<NamedLocation> warps = getWarps();
        warps.removeIf(h -> h.getName().equals(warp));
        String json = gson.toJson(warps);
        write(json);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<NamedLocation> getWarps() {
        String json = read();
        Set<NamedLocation> warps = gson.fromJson(json, NamedLocation.getType());
        return warps == null ? new java.util.HashSet<>() : warps;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean teleportWarp(Player player, String warpName) {
        NamedLocation warp = getWarp(warpName).orElse(null);
        if (warp == null) {
            return false;
        } else {
            player.teleport(warp.getLocation());
            return true;
        }
    }
}
