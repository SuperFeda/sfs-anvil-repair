package net.superfeda.sfs_anvil_repair;

import net.fabricmc.api.ModInitializer;

import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;

import net.neoforged.fml.config.ModConfig;


public class SFsAnvilRepair implements ModInitializer {
    
    @Override
    public void onInitialize() {
        SFsAnvilRepairConfig.init();
        ConfigRegistry.INSTANCE.register(
                CommonClass.MOD_ID,
                ModConfig.Type.COMMON,
                SFsAnvilRepairConfig.SPEC
        );
        CommonClass.init();
    }
}
