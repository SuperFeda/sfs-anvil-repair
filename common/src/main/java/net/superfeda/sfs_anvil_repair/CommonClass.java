package net.superfeda.sfs_anvil_repair;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CommonClass {
    public static final String MOD_ID = "sfs_anvil_repair";
    public static final String MOD_NAME = "SFsAnvilRepair";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {
        LOGGER.info("was loaded");
    }
}
