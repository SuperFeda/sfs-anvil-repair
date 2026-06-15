package net.superfeda.sfs_anvil_repair;

import net.fabricmc.api.ModInitializer;

import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;

import net.neoforged.fml.config.ModConfig;


public class SFsAnvilRepair implements ModInitializer {

    @Override
    public void onInitialize() {
        SFsAnvilRepairConfig.init();
        NeoForgeConfigRegistry.INSTANCE.register(
                CommonClass.MOD_ID,
                ModConfig.Type.COMMON,
                SFsAnvilRepairConfig.SPEC
        );
        CommonClass.init();
    }
}
