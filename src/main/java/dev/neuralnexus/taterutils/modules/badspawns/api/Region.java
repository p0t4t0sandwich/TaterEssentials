/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.badspawns.api;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.world.BlockPos;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.List;

/** Region that can be used for bad spawns. */
@ConfigSerializable
public class Region {
    @Setting private String name;
    @Setting private String type;
    @Setting private float chance;
    @Setting private Position min;
    @Setting private Position max;
    @Setting private List<String> worlds;
    @Setting private List<String> biomes;
    @Setting private List<String> mobs;

    public String name() {
        return name;
    }

    public String type() {
        return type;
    }

    public float chance() {
        return chance;
    }

    public Position min() {
        return min;
    }

    public Position max() {
        return max;
    }

    public List<String> worlds() {
        return worlds;
    }

    public List<String> biomes() {
        return biomes;
    }

    public List<String> mobs() {
        return mobs;
    }

    /** Is the entity within the region? */
    public boolean isWithin(Entity entity) {
        if (!worlds.contains(entity.world().dimension()) && !worlds.isEmpty()) {
            //
            // System.out.println(entity.world().dimension() + " not within " + worlds);
            //
            return false;
        }
        if (!biomes.contains(entity.biome()) && !biomes.isEmpty()) {
            //
            // System.out.println(entity.biome() + " not within " + biomes);
            //
            return false;
        }
        BlockPos entityPos = entity.location().blockPosition();
        BlockPos minPos = min.toBlockPos();
        BlockPos maxPos = max.toBlockPos();
        if (entityPos.x() < minPos.x() || entityPos.x() > maxPos.x()) {
            //
            // System.out.println(
            //        entityPos.x() + " x-pos not within " + minPos.x() + " and " + maxPos.x());
            //
            return false;
        }
        if (entityPos.y() < minPos.y() || entityPos.y() > maxPos.y()) {
            //
            // System.out.println(
            //        entityPos.y() + " y-pos not within " + minPos.y() + " and " + maxPos.y());
            //
            return false;
        }
        //
        // System.out.println(
        //        entityPos.z() + " z-pos not within " + minPos.z() + " and " + maxPos.z());
        //
        return !(entityPos.z() < minPos.z()) && !(entityPos.z() > maxPos.z());
        //
        // System.out.println("Entity is within region");
        //
    }

    /** Check if the entity is banned in the region. */
    public boolean canSpawn(Entity entity) {
        boolean isWithinResult = isWithin(entity);
        //
        // System.out.println("---------------------------------------------");
        // System.out.println("Region: " + name);
        // System.out.println("Entity: " + entity.type());
        // System.out.println("Is within result: " + isWithinResult);
        //

        boolean mobResult = mobs.contains(entity.type().asString()) || mobs.isEmpty();
        //
        // System.out.println("Mob result: " + mobResult);
        //
        boolean chanceResult = !((float) Math.random() <= chance && chance != 0);
        //
        // System.out.println("Chance result: " + chanceResult);
        //

        boolean result = isWithinResult && mobResult && chanceResult;
        //
        // System.out.println("Final result: " + result);
        // System.out.println("---------------------------------------------");
        //
        if (type.equals("blacklist")) {
            return !result;
        }
        return result;
    }

    @ConfigSerializable
    public static class Position {
        @Setting private String x;
        @Setting private String y;
        @Setting private String z;

        public String x() {
            return x;
        }

        public String y() {
            return y;
        }

        public String z() {
            return z;
        }

        public BlockPos toBlockPos() {
            int realX;
            int realY;
            int realZ;

            if (x.equals("*")) {
                realX = 3_000_000;
            } else if (x.startsWith("-*")) {
                realX = -3_000_000;
            } else {
                realX = Integer.parseInt(x);
            }

            if (y.equals("*")) {
                realY = 320;
            } else if (y.startsWith("-*")) {
                realY = -64;
            } else {
                realY = Integer.parseInt(y);
            }

            if (z.equals("*")) {
                realZ = 3_000_000;
            } else if (z.startsWith("-*")) {
                realZ = -3_000_000;
            } else {
                realZ = Integer.parseInt(z);
            }

            return new BlockPos(realX, realY, realZ);
        }
    }
}
