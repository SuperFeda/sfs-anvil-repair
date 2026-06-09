package net.superfeda.sfs_anvil_repair;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(CommonClass.MOD_ID)
public class SFsAnvilRepair {

    public SFsAnvilRepair(ModContainer modContainer, IEventBus eventBus) {
        modContainer.registerConfig(ModConfig.Type.COMMON, SFsAnvilRepairConfig.SPEC);
        CommonClass.init();
    }
}