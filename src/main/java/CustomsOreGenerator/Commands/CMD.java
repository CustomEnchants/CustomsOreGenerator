package CustomsOreGenerator.Commands;

import CustomsOreGenerator.CustomsOreGeneratorPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public abstract class CMD {

    public final CustomsOreGeneratorPlugin instance = CustomsOreGeneratorPlugin.getInstance();
    private final String name;
    private final String desc;
    private final String args;
    private final String permission;
    private final boolean require_permission;

    public CMD(String name, String desc, String args, String permission, boolean require_permission) {
        this.name = name;
        this.desc = desc;
        this.args = args;
        this.permission = permission;
        this.require_permission = require_permission;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return desc;
    }

    public String getArgs() {
        return args;
    }

    public String getPermission() {
        return permission;
    }

    public boolean isPermissionRequired() {
        return require_permission;
    }


    public abstract void run(CommandSender cs, String[] args);

    public String fixColour(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }


}
