package CustomsOreGenerator.Commands;

import CustomsOreGenerator.Commands.Sub.Reload;
import CustomsOreGenerator.CustomsOreGeneratorPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class CustomsOreGeneratorCommand implements CommandExecutor {

    private final CustomsOreGeneratorPlugin instance = CustomsOreGeneratorPlugin.getInstance();
    private final ArrayList<CMD> commands = new ArrayList<>();

    public CustomsOreGeneratorCommand(){
        commands.add(new Reload());
    }

    private String fixColour(String input){
        return ChatColor.translateAlternateColorCodes('&',input);
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String s, String[] args) {
        if(cmd.getName().equalsIgnoreCase("CustomsOreGenerator")){
            if (args.length == 0) {
                sendHelp(cs, cmd);
                return false;
            }
            String subcommand = args[0].toLowerCase();
            if (commands.stream().noneMatch(c -> c.getName().equalsIgnoreCase(subcommand))) {
                sendHelp(cs, cmd);
                return false;
            }
            commands.stream().filter(c -> c.getName().equalsIgnoreCase(subcommand)).findFirst().get().run(cs, args);
            return false;
        }
        return false;
    }

    private void sendHelp(CommandSender cs, Command cmd) {
        cs.sendMessage(fixColour("&7&m--------------&b"+instance.getDescription().getName()+"&7&m--------------"));
        commands.forEach(c -> {
            if (!c.isPermissionRequired()) {
                cs.sendMessage(fixColour("&b/" + cmd.getName() + " &7" + c.getName() + " &7" + c.getArgs() + " &8(&7" + c.getDescription() + "&8)"));
            } else {
                if (cs.hasPermission(c.getPermission())) {
                    cs.sendMessage(fixColour("&b/" + cmd.getName() + " &7" + c.getName() + " &7" + c.getArgs() + " &8(&7" + c.getDescription() + "&8)"));
                }
            }
        });
    }
}
