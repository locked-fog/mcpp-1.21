package fog.mcpp;

import fog.mcpp.block.ModBlocks;
import fog.mcpp.block.entity.ModBlockEntities;
import fog.mcpp.gui.ModScreenHandlers;
import fog.mcpp.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MCPP implements ModInitializer {
	public static final String MOD_ID = "mcpp";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Loading MCPP");
		ModBlocks.initModBlocks();
		LOGGER.info("Blocks loaded!");
		ModItems.initModItems();
		LOGGER.info("Items loaded!");
		ModBlockEntities.initModBlockEntities();
		LOGGER.info("Block Entities loaded!");
		ModScreenHandlers.initModScreenHandlers();
		LOGGER.info("Screen Handlers loaded!");


		LOGGER.info("MCPP loaded!");
	}
}