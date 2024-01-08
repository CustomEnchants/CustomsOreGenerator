package CustomsOreGenerator.Objects;

import org.bukkit.Material;

public class ChangeableBlocks {

    private final Material material;
    private final int minimumChance;
    private final int maximumChance;

    public ChangeableBlocks(Material type, int minimumChance, int maximumChance) {
        this.material = type;
        this.minimumChance = minimumChance;
        this.maximumChance = maximumChance;
    }

    public Material getMaterial() {
        return material;
    }

    public int getMinimumChance() {
        return minimumChance;
    }

    public int getMaximumChance() {
        return maximumChance;
    }

}
