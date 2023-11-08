package dev.neuralnexus.taterutils.common.commands;

import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.command.Sender;

/**
 * Home Command.
 */
public class HomeCommand implements Command {
    private String name = "home";

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
        return "Allows players to set and teleport to their home.";
    }

    @Override
    public String getUsage() {
        return "/home [set] [name]";
    }

    @Override
    public String getPermission() {
        return "taterutils.command.home";
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
