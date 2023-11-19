package dev.neuralnexus.taterutils.common.modules.orewatcher.api;

import dev.neuralnexus.taterlib.common.player.Player;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/** API for the OreWatcher module. */
public class OreWatcherAPI {
    private final Data data;

    public OreWatcherAPI() {
        this.data = new Data();
    }

    /**
     * Adds ore mined.
     *
     * @param player The player.
     * @param amount The amount of ore mined.
     */
    public void addOreMined(Player player, int amount) {
        Optional<OreMined> oreMined = data.getOreMined(player);
        if (oreMined.isPresent()) {
            oreMined.get().addAmount(amount);
        } else {
            data.addOreMined(new OreMined(player, amount));
        }
    }

    /**
     * Get ore mined.
     *
     * @param player The player.
     * @return The ore mined.
     */
    public Optional<OreMined> getOreMined(Player player) {
        return data.getOreMined(player);
    }

    /**
     * Get all ore mined.
     *
     * @return All ore mined.
     */
    public Set<OreMined> getOreMined() {
        return data.getOreMined();
    }

    /** Resets the average per minute. */
    public void resetAveragePerMinute() {
        data.resetAveragePerMinute();
    }

    /** Class representing the ore the player has mined in a given period of time. */
    public static class OreMined {
        private final Player player;
        private int amount;
        private long firstMined = System.currentTimeMillis();
        private long lastMined = System.currentTimeMillis();

        /**
         * Creates a new OreMined.
         *
         * @param player The player.
         * @param amount The amount of ore mined.
         */
        public OreMined(Player player, int amount) {
            this.player = player;
            this.amount = amount;
        }

        /**
         * Gets the player.
         *
         * @return The player.
         */
        public Player getPlayer() {
            return player;
        }

        /**
         * Adds to the amount of ore mined.
         *
         * @param amount The amount to add.
         */
        public void addAmount(int amount) {
            this.lastMined = System.currentTimeMillis();
            this.amount += amount;
        }

        /**
         * Get the time delta between the first and last time the player mined ore.
         *
         * @return The time delta between the first and last time the player mined ore.
         */
        public long getTimeDelta() {
            return lastMined - firstMined;
        }

        /**
         * Get the average amount of ore mined per minute.
         *
         * @return The average amount of ore mined per minute.
         */
        public double getAveragePerMinute() {
            return (double) amount / getTimeDelta() / 60000;
        }
    }

    /** The data for the API. */
    static class Data {
        private final Set<OreMined> oreMined = new HashSet<>();

        /**
         * Adds ore mined.
         *
         * @param oreMined The ore mined.
         */
        public void addOreMined(OreMined oreMined) {
            this.oreMined.add(oreMined);
        }

        /**
         * Gets the ore mined.
         *
         * @return The ore mined.
         */
        public Set<OreMined> getOreMined() {
            return oreMined;
        }

        /**
         * Gets the ore mined by a player.
         *
         * @param player The player.
         * @return The ore mined.
         */
        public Optional<OreMined> getOreMined(Player player) {
            return oreMined.stream()
                    .filter(oreMined -> oreMined.getPlayer().equals(player))
                    .findFirst();
        }

        /** Resets the average per minute. */
        public void resetAveragePerMinute() {
            oreMined.forEach(
                    oreMined -> {
                        oreMined.firstMined = System.currentTimeMillis();
                        oreMined.lastMined = System.currentTimeMillis();
                        oreMined.amount = 0;
                    });
        }
    }
}
