package dev.neuralnexus.taterutils.common.commands;

import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.command.Sender;

public class SetWarpCommand implements Command {
    private String name = "warp";

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return "Sets a warp location!";
    }

    @Override
    public String getUsage() {
        return "/setwarp [name]";
    }

    @Override
    public String getPermission() {
        return "taterutils.command.setwarp";
    }

    @Override
    public String execute(String[] args) {
        return null;
    }

    @Override
    public boolean execute(Sender sender, String label, String[] args) {
        return true;
    }
}
