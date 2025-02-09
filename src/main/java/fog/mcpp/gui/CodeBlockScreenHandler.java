package fog.mcpp.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientCommonPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.math.BlockPos;

public class CodeBlockScreenHandler extends ScreenHandler {
    private BlockPos pos;
    private final PropertyDelegate propertyDelegate;

    public CodeBlockScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos) {
        super(ModScreenHandlers.CODE_BLOCK_SCREEN_HANDLER, syncId);
        this.pos = pos;
        this.propertyDelegate = new ArrayPropertyDelegate(3);
        this.addProperties(this.propertyDelegate);

        propertyDelegate.set(0, pos.getX());
        propertyDelegate.set(1, pos.getY());
        propertyDelegate.set(2, pos.getZ());
    }

    public CodeBlockScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId,playerInventory,BlockPos.ORIGIN);
        this.pos = new BlockPos(
                propertyDelegate.get(0),
                propertyDelegate.get(1),
                propertyDelegate.get(2)
        );
    }

    public BlockPos getPos(){
        return pos;
    }

    public void setPos(BlockPos pos){
        this.pos = pos;
    }


    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

}
