package dev.neuralnexus.taterutils.modules.mclogs.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterutils.api.CommandUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.mclogs.api.MCLogsAPI;

/** MCLogs Command. */
public class MCLogsCommand implements Command {
    private String name = "mclogs";

    @Override
    public String name() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String description() {
        return "Upload logs to mclo.gs.";
    }

    @Override
    public String usage() {
        return "/mclogs <upload | list | get>";
    }

    @Override
    public String permission() {
        return "taterutils.command.alert";
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!CommandUtils.senderHasPermission(sender, permission())) {
            return true;
        }
        MCLogsAPI api = TaterUtilsAPIProvider.get().mcLogsAPI();

        if (args.length == 0) {
            sender.sendMessage("§cUsage: " + usage());
            return true;
        }
        switch (args[0]) {
            case "list":
                if (args.length == 1) {
                    sender.sendMessage("§cUsage: " + usage()); // TODO add as message
                    return true;
                }
            case "upload":
                if (args.length == 1) {
                    sender.sendMessage("§cUsage: " + usage()); // TODO add as message
                    return true;
                }
                break;
            case "get":
                if (args.length == 1) {
                    sender.sendMessage("§cUsage: " + usage()); // TODO add as message
                    return true;
                }
                break;
            default:
                sender.sendMessage("§cUsage: " + usage()); // TODO add as message
                return true;
        }
        return true;
    }
}
