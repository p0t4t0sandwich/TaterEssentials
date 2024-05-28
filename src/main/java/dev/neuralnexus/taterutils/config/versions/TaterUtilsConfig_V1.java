package dev.neuralnexus.taterutils.config.versions;

import dev.neuralnexus.taterlib.config.sections.ModuleConfig;
import dev.neuralnexus.taterutils.config.TaterUtilsConfig;
import dev.neuralnexus.taterutils.config.sections.BadSpawnsConfig;

import java.util.List;

public class TaterUtilsConfig_V1 implements TaterUtilsConfig {
    private final List<ModuleConfig> modules;
    private final BadSpawnsConfig badSpawns;

    public TaterUtilsConfig_V1(List<ModuleConfig> modules, BadSpawnsConfig badSpawns) {
        this.modules = modules;
        this.badSpawns = badSpawns;
    }

    @Override
    public int version() {
        int version = 1;
        return version;
    }

    @Override
    public List<ModuleConfig> modules() {
        return modules;
    }

    @Override
    public BadSpawnsConfig badSpawns() {
        return badSpawns;
    }
}
