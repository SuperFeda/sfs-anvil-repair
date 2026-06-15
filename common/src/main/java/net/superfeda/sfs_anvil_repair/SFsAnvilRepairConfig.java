package net.superfeda.sfs_anvil_repair;

import net.neoforged.neoforge.common.ModConfigSpec;

public class SFsAnvilRepairConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.ConfigValue<String> REPAIR_ITEM;
    public static final ModConfigSpec.IntValue USAGE_COST;

    static {
        BUILDER.push("Anvil Repair Settings");

        REPAIR_ITEM = BUILDER
                .comment("The item used to repair the anvil.", "Default: minecraft:iron_ingot")
                .define("repairItem", "minecraft:iron_ingot");

        USAGE_COST = BUILDER
                .comment("The amount of the repairItem consumed per one anvil repair.")
                .defineInRange("usageCost", 1, 1, 64);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    // for fabric
    public static void init() {}
}
