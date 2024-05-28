/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.config.versions;

import dev.neuralnexus.taterlib.config.sections.ModuleConfig;
import dev.neuralnexus.taterutils.config.TaterUtilsConfig;
import dev.neuralnexus.taterutils.config.sections.*;

import java.util.List;

public class TaterUtilsConfig_V1 implements TaterUtilsConfig {
    private final List<ModuleConfig> modules;
    private final BadSpawnsConfig badSpawns;
    private final ChatFormatterConfig chatFormatter;
    private final GameModeConfig gameMode;
    private final GodModeConfig godMode;
    private final HomeConfig home;
    private final JoinAndQuitConfig joinAndQuit;
    private final MOTDConfig motd;
    private final OreWatcherConfig oreWatcher;
    private final PingConfig ping;
    private final SlashLobbyConfig slashLobby;
    private final SpawnConfig spawn;

    public TaterUtilsConfig_V1(
            List<ModuleConfig> modules,
            BadSpawnsConfig badSpawns,
            ChatFormatterConfig chatFormatter,
            GameModeConfig gameMode,
            GodModeConfig godMode,
            HomeConfig home,
            JoinAndQuitConfig joinAndQuit,
            MOTDConfig motd,
            OreWatcherConfig oreWatcher,
            PingConfig ping,
            SlashLobbyConfig slashLobby,
            SpawnConfig spawn) {
        this.modules = modules;
        this.badSpawns = badSpawns;
        this.chatFormatter = chatFormatter;
        this.gameMode = gameMode;
        this.godMode = godMode;
        this.home = home;
        this.joinAndQuit = joinAndQuit;
        this.motd = motd;
        this.oreWatcher = oreWatcher;
        this.ping = ping;
        this.slashLobby = slashLobby;
        this.spawn = spawn;
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

    @Override
    public HomeConfig home() {
        return home;
    }

    @Override
    public JoinAndQuitConfig joinAndQuit() {
        return joinAndQuit;
    }

    @Override
    public MOTDConfig motd() {
        return motd;
    }

    @Override
    public OreWatcherConfig oreWatcher() {
        return oreWatcher;
    }

    @Override
    public PingConfig ping() {
        return ping;
    }

    @Override
    public SlashLobbyConfig slashLobby() {
        return slashLobby;
    }

    @Override
    public SpawnConfig spawn() {
        return spawn;
    }
}
