package CustomsOreGenerator.Listener;

import CustomsOreGenerator.CustomsOreGeneratorPlugin;
import CustomsOreGenerator.Objects.ChangeableBlocks;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

import java.util.Arrays;
import java.util.Random;

public class BlockListener implements Listener {

    private final BlockFace[] faces = {BlockFace.SELF, BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST};
    private final CustomsOreGeneratorPlugin instance = CustomsOreGeneratorPlugin.getInstance();

    private boolean isLavaOrWater(Block b) {
        return b.getType() == Material.LAVA || b.getType() == Material.STATIONARY_LAVA || b.getType() == Material.WATER || b.getType() == Material.STATIONARY_WATER;
    }
//(ignoreCancelled = true, priority = EventPriority.MONITOR)
    @EventHandler
    public void onBlockFromToEvent(BlockFromToEvent event) {
        if (instance.getFileUtil().worlds_ignored.contains(event.getBlock().getWorld().getName())) return;
        if (instance.getChangeableBlocks().size() == 0){
            return;
        }
        if (!isLavaOrWater(event.getBlock())) return;
        if (event.getToBlock().getType() != Material.AIR) return;
        if (!generatesCobble(event.getBlock().getType(), event.getToBlock())) return;

        Random rand = new Random();
        int chance = rand.nextInt(100) + 1;
        if (instance.getChangeableBlocks().stream().noneMatch(cb -> chance >= cb.getMinimumChance() && chance <= cb.getMaximumChance()))
            return;
        ChangeableBlocks cab = instance.getChangeableBlocks().stream().filter(cb -> chance >= cb.getMinimumChance() && chance <= cb.getMaximumChance()).findFirst().orElse(null);
        event.getToBlock().setType(cab.getMaterial());
        event.getToBlock().getState().update(true);
    }

    private boolean generatesCobble(Material mat, Block b) {
        Material material1 = (mat == Material.WATER) || mat == Material.STATIONARY_WATER ? Material.LAVA : Material.WATER;
        Material material2 = (mat == Material.WATER) || mat == Material.STATIONARY_WATER ? Material.STATIONARY_LAVA : Material.STATIONARY_WATER;
        return Arrays.stream(faces).map(blockFace -> b.getRelative(blockFace, 1)).anyMatch(block -> block.getType().equals(material1) || block.getType().equals(material2));
    }


}
