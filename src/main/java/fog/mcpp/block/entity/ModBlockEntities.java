package fog.mcpp.block.entity;

import fog.mcpp.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static fog.mcpp.MCPP.MOD_ID;

public class ModBlockEntities {
    public static final BlockEntityType<CodeBlockBlockEntity> CODE_BLOCK = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(MOD_ID, "code_block"),
            BlockEntityType.Builder.create(
                    CodeBlockBlockEntity::new,
                    ModBlocks.CODE_BLOCK
            ).build(null)
    );

    public static void initModBlockEntities() {
        CODE_BLOCK.getClass();
    }
}
