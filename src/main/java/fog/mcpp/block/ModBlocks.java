package fog.mcpp.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import static fog.mcpp.MCPP.MOD_ID;

public class ModBlocks {
    public static final Block CODE_BLOCK;

    public static void initModBlocks(){
        CODE_BLOCK.getClass();
    }

    public static Block register(String id, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, id), block);
    }

    static{
        CODE_BLOCK = register("code_block",
                new CodeBlock(
                        AbstractBlock.Settings.create()
                                .mapColor(MapColor.BLUE)
                                .requiresTool()
                                .strength(-1.0F,3600000.0F)
                                .dropsNothing(),
                        false
                )
        );
    }
}
