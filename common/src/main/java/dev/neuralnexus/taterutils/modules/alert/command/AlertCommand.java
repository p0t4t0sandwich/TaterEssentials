package dev.neuralnexus.taterutils.modules.alert.command;

import dev.neuralnexus.taterlib.Utils;
import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterutils.api.CommandUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.alert.api.AlertAPI;

/** Alert Command. */
public class AlertCommand implements Command {
    private String name = "alert";

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
        return "Sends an alert to everyone on the server/network.";
    }

    @Override
    public String usage() {
        return "/alert <message>";
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
        AlertAPI api = TaterUtilsAPIProvider.get().alertAPI();

        api.broadcast(Utils.substituteSectionSign("&4" + String.join(" ", args)));
        return true;
    }
}
