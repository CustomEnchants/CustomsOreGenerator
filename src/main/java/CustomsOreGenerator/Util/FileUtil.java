package CustomsOreGenerator.Util;

import CustomsOreGenerator.CustomsOreGeneratorPlugin;
import CustomsOreGenerator.Objects.ChangeableBlocks;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class FileUtil {

    private final CustomsOreGeneratorPlugin instance = CustomsOreGeneratorPlugin.getInstance();
    private File conf;
    public ArrayList<String> worlds_ignored = new ArrayList<>();

    public void loadValues(boolean reload) {
        if(reload){
            worlds_ignored.clear();
            instance.getChangeableBlocks().clear();
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(conf);
        worlds_ignored = (ArrayList<String>) config.getStringList("worlds-ignored");
        ConfigurationSection blocks = config.getConfigurationSection("Blocks");
        for(String string : blocks.getKeys(false)){
            ConfigurationSection section = blocks.getConfigurationSection(string);
            Material material;
            try{
                material = Material.getMaterial(section.getString("material"));
            }catch(Exception e){
                System.out.println(section.getString("material") + " is a invalid material");
                continue;
            }
            int minChance = section.getInt("minimumChance");
            int maxChance = section.getInt("maximumChance");
            instance.getChangeableBlocks().add(new ChangeableBlocks(material,minChance,maxChance));
        }
    }

    public void setup(File dir) {
        if (!dir.exists()) {
            dir.mkdirs();
        }
        conf = new File(dir + File.separator + "config.yml");
        if (!conf.exists()) {
            FileConfiguration config = YamlConfiguration.loadConfiguration(conf);
            config.set("worlds-ignored", Arrays.asList("world", "world_nether", "world_the_end"));
            ConfigurationSection blocks = config.createSection("Blocks");
            ConfigurationSection coalOre = blocks.createSection("CoalOre");
            coalOre.set("minimumChance",61);
            coalOre.set("maximumChance",100);
            coalOre.set("material",Material.COAL_ORE.name());

            ConfigurationSection ironOre = blocks.createSection("IronOre");
            ironOre.set("minimumChance",40);
            ironOre.set("maximumChance",60);
            ironOre.set("material",Material.IRON_ORE.name());

            ConfigurationSection lapisOre = blocks.createSection("LapisOre");
            lapisOre.set("minimumChance",31);
            lapisOre.set("maximumChance",39);
            lapisOre.set("material",Material.LAPIS_ORE.name());

            ConfigurationSection redstoneOre = blocks.createSection("RedstoneOre");
            redstoneOre.set("minimumChance",21);
            redstoneOre.set("maximumChance",30);
            redstoneOre.set("material",Material.REDSTONE_ORE.name());

            ConfigurationSection goldOre = blocks.createSection("GoldOre");
            goldOre.set("minimumChance",10);
            goldOre.set("maximumChance",20);
            goldOre.set("material",Material.GOLD_ORE.name());

            ConfigurationSection diamondOre = blocks.createSection("DiamondOre");
            diamondOre.set("minimumChance",5);
            diamondOre.set("maximumChance",9);
            diamondOre.set("material",Material.DIAMOND_ORE.name());

            ConfigurationSection emeraldOre = blocks.createSection("EmeraldOre");
            emeraldOre.set("minimumChance",0);
            emeraldOre.set("maximumChance",4);
            emeraldOre.set("material",Material.EMERALD_ORE.name());
            try {
                config.save(conf);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        loadValues(true);
    }

}
