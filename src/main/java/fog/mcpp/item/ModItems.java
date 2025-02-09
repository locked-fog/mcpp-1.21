package fog.mcpp.item;

import fog.mcpp.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.OperatorOnlyBlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import static fog.mcpp.MCPP.MOD_ID;

public class ModItems {

    public static final Item CODE_BLOCK_ITEM = register("code_block", ModBlocks.CODE_BLOCK,new Item.Settings().rarity(Rarity.EPIC));

    public ModItems(){
    }

    public static void initModItems(){

    }

    public static Item register(String id, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, id), item);
    }

    public static Item register(String id, Block block, Item.Settings settings){
        BlockItem blockItem = new BlockItem(block, settings);
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID,id),blockItem);
    }
}
