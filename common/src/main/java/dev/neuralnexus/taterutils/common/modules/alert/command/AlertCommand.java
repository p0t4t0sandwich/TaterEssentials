package dev.neuralnexus.taterutils.common.modules.alert.command;

import dev.neuralnexus.taterlib.common.Utils;
import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterutils.common.api.CommandUtils;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.common.modules.alert.api.AlertAPI;

/**
 * Alert Command.
 */
public class AlertCommand implements Command {
    private String name = "alert";

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
        return "Sends an alert to everyone on the server/network.";
    }

    @Override
    public String getUsage() {
        return "/alert <message>";
    }

    @Override
    public String getPermission() {
        return "taterutils.command.alert";
    }

    @Override
    public String execute(String[] args) {
        return null;
    }

    @Override
    public boolean execute(Sender sender, String label, String[] args) {
        if (!CommandUtils.senderHasPermission(sender, getPermission())) {
            return true;
        }
        AlertAPI api = TaterUtilsAPIProvider.get().getAlertAPI();

        api.broadcast(Utils.substituteSectionSign("&4" + String.join(" ", args)));
        return true;
    }
}
