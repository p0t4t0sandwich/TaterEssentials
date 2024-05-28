package dev.neuralnexus.taterutils.modules.badspawns.api;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.world.BlockPos;

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

    /** Check if the entity is banned in the region. */
    public boolean canSpawn(Entity entity) {
        boolean result = mobs.contains(entity.type());
        if (worlds.contains(entity.world().dimension())) {
            result = true;
        }
        if (biomes.contains(entity.biome())) {
            result = true;
        }
        BlockPos entityPos = entity.location().blockPosition();
        BlockPos minPos = min.toBlockPos();
        BlockPos maxPos = max.toBlockPos();
        if ((entityPos.x() >= minPos.x() && entityPos.x() <= maxPos.x())
                && (entityPos.y() >= minPos.y() && entityPos.y() <= maxPos.y())
                && (entityPos.z() >= minPos.z() && entityPos.z() <= maxPos.z())) {
            result = true;
        }
        if ((float) Math.random() <= chance && chance != 0) {
            result = true;
        }
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
