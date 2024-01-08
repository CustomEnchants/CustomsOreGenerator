package CustomsOreGenerator;

import CustomsOreGenerator.Commands.CustomsOreGeneratorCommand;
import CustomsOreGenerator.Listener.BlockListener;
import CustomsOreGenerator.Objects.ChangeableBlocks;
import CustomsOreGenerator.Util.FileUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class CustomsOreGeneratorPlugin extends JavaPlugin {

    private static CustomsOreGeneratorPlugin instance;
    private final ArrayList<ChangeableBlocks> changeableBlocks = new ArrayList<>();
    private FileUtil fileutil;

    public static CustomsOreGeneratorPlugin getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        fileutil = new FileUtil();
        getFileUtil().setup(getDataFolder());
        Bukkit.getServer().getPluginManager().registerEvents(new BlockListener(), this);
        getCommand("CustomsOreGenerator").setExecutor(new CustomsOreGeneratorCommand());
    }

    public void onDisable() {
        instance = null;
    }

    public FileUtil getFileUtil() {
        return fileutil;
    }

    public ArrayList<ChangeableBlocks> getChangeableBlocks() {
        return changeableBlocks;
    }

}
