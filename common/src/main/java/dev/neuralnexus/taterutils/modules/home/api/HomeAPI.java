package dev.neuralnexus.taterutils.modules.home.api;

import com.google.gson.reflect.TypeToken;

import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterutils.api.AbstractLocation;
import dev.neuralnexus.taterutils.api.NamedLocation;

import java.lang.reflect.Type;
import java.util.*;

/** API for the home module. */
public class HomeAPI {
    /**
     * Get a player's home.
     *
     * @param player The player.
     * @param home The name of the home.
     */
    public Optional<NamedLocation> getHome(Player player, String home) {
        Type type = new TypeToken<Map<String, AbstractLocation>>() {}.getType();
        Optional<Map<String, AbstractLocation>> homes = player.getMeta("home", type);
        if (homes.isPresent()) {
            if (homes.get().containsKey(home)) {
                return Optional.of(new NamedLocation(home, homes.get().get(home)));
            }
        }
        return Optional.empty();
    }

    /**
     * Set a player's home.
     *
     * @param player The player.
     * @param home The name of the home.
     * @param location The location of the home.
     */
    public void setHome(Player player, String home, Location location) {
        Map<String, AbstractLocation> homes = this.getHomes(player);
        homes.put(home, new AbstractLocation(location));
        player.setMeta("home", homes);
    }

    /**
     * Delete a player's home.
     *
     * @param player The player.
     * @param home The name of the home.
     */
    public void deleteHome(Player player, String home) {
        Map<String, AbstractLocation> homes = this.getHomes(player);
        homes.remove(home);
        player.setMeta("home", homes);
    }

    /**
     * Get all of a player's homes.
     *
     * @param player The player.
     */
    public Map<String, AbstractLocation> getHomes(Player player) {
        Type type = new TypeToken<Map<String, AbstractLocation>>() {}.getType();
        Optional<Map<String, AbstractLocation>> homes = player.getMeta("home", type);
        return homes.orElse(new HashMap<>());
    }

    /**
     * Teleport a player to their home.
     *
     * @param player The player.
     */
    public boolean teleportHome(Player player, String home) {
        Optional<NamedLocation> playerHome = this.getHome(player, home);
        if (playerHome.isPresent()) {
            player.teleport(playerHome.get().getLocation());
            return true;
        }
        return false;
    }

    /**
     * Get list of invalid home names.
     *
     * @return The list of invalid home names.
     */
    public Set<String> getInvalidHomeNames() {
        Set<String> invalidHomeNames = new java.util.HashSet<>();
        invalidHomeNames.add("add");
        invalidHomeNames.add("create");
        invalidHomeNames.add("set");
        invalidHomeNames.add("rm");
        invalidHomeNames.add("remove");
        invalidHomeNames.add("del");
        invalidHomeNames.add("delete");
        invalidHomeNames.add("list");
        return invalidHomeNames;
    }
}
