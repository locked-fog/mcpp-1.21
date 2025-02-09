package fog.mcpp;

import fog.mcpp.gui.CodeBlockScreen;
import fog.mcpp.gui.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static fog.mcpp.MCPP.MOD_ID;
public class MCPPClient implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("Loading MCPP Client");
        LOGGER.info("Registering Screens");
        HandledScreens.register(
                ModScreenHandlers.CODE_BLOCK_SCREEN_HANDLER,
                CodeBlockScreen::new
        );
    }
}
