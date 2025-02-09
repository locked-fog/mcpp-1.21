package fog.mcpp.gui;

import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;


import static fog.mcpp.MCPP.MOD_ID;

public class ModScreenHandlers {
    public static final ScreenHandlerType<CodeBlockScreenHandler> CODE_BLOCK_SCREEN_HANDLER =register(Identifier.of(MOD_ID, "code_block"), CodeBlockScreenHandler::new);


    public static <T extends ScreenHandler> ScreenHandlerType<T> register(Identifier id, ScreenHandlerType.Factory<T> factory) {
        return Registry.register(Registries.SCREEN_HANDLER, id, new ScreenHandlerType<>(factory, FeatureFlags.DEFAULT_ENABLED_FEATURES));
    }
    public static void initModScreenHandlers() {
    }
}
