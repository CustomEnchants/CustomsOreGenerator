package CustomsOreGenerator.Commands.Sub;

import CustomsOreGenerator.Commands.CMD;
import org.bukkit.command.CommandSender;

public class Reload extends CMD {

    public Reload() {
        super("Reload", "Reload the configuration","", "CustomsOreGeneratorCommand.reload", true);
    }

    @Override
    public void run(CommandSender cs, String[] args) {
        if (!cs.hasPermission(getPermission())) {
            cs.sendMessage(fixColour("&b&l(!) &bCustomsOreGenerator &cYou don't have permission to execute this command!"));
            return;
        }
        instance.getFileUtil().loadValues(true);
        cs.sendMessage(fixColour("&b&l(!) &bCustomsOreGenerator &7You have reloaded the configuration"));
    }
}
