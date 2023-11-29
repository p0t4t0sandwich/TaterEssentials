package dev.neuralnexus.taterutils.common.modules.mclogs.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.Sender;
import dev.neuralnexus.taterutils.common.api.CommandUtils;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.common.modules.mclogs.api.MCLogsAPI;

/** MCLogs Command. */
public class MCLogsCommand implements Command {
    private String name = "mclogs";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return "Upload logs to mclo.gs.";
    }

    @Override
    public String getUsage() {
        return "/mclogs <upload | list | get>";
    }

    @Override
    public String getPermission() {
        return "taterutils.command.alert";
    }

    @Override
    public boolean execute(Sender sender, String label, String[] args) {
        if (!CommandUtils.senderHasPermission(sender, getPermission())) {
            return true;
        }
        MCLogsAPI api = TaterUtilsAPIProvider.get().getMCLogsAPI();

        switch (args.length) {
            case 0:
                sender.sendMessage("§cUsage: " + getUsage());
                break;
            case 1:
                switch (args[0]) {
                    case "upload":
                    case "list":
                    case "get":
                        sender.sendMessage("Not implemented yet.");
                        break;
                    default:
                        sender.sendMessage("§cUsage: " + getUsage());
                        break;
                }
                break;
        }
        return true;
    }
}
