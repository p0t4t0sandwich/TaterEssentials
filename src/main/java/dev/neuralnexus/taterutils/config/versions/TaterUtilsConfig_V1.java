package dev.neuralnexus.taterutils.config.versions;

import dev.neuralnexus.taterlib.config.sections.ModuleConfig;
import dev.neuralnexus.taterutils.config.TaterUtilsConfig;
import dev.neuralnexus.taterutils.config.sections.BadSpawnsConfig;
import dev.neuralnexus.taterutils.config.sections.ChatFormatterConfig;
import dev.neuralnexus.taterutils.config.sections.GameModeConfig;
import dev.neuralnexus.taterutils.config.sections.GodModeConfig;

import java.util.List;

public class TaterUtilsConfig_V1 implements TaterUtilsConfig {
    private final List<ModuleConfig> modules;
    private final BadSpawnsConfig badSpawns;
    private final ChatFormatterConfig chatFormatter;
    private final GameModeConfig gameMode;
    private final GodModeConfig godMode;

    public TaterUtilsConfig_V1(
            List<ModuleConfig> modules,
            BadSpawnsConfig badSpawns,
            ChatFormatterConfig chatFormatter,
            GameModeConfig gameMode,
            GodModeConfig godMode) {
        this.modules = modules;
        this.badSpawns = badSpawns;
        this.chatFormatter = chatFormatter;
        this.gameMode = gameMode;
        this.godMode = godMode;
    }

    @Override
    public int version() {
        return 1;
    }

    @Override
    public List<ModuleConfig> modules() {
        return modules;
    }

    @Override
    public BadSpawnsConfig badSpawns() {
        return badSpawns;
    }

    @Override
    public ChatFormatterConfig chatFormatter() {
        return chatFormatter;
    }

    @Override
    public GameModeConfig gameMode() {
        return gameMode;
    }

    @Override
    public GodModeConfig godMode() {
        return godMode;
    }
}
