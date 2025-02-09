package fog.mcpp.block.entity;

import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;

public class CodeBlockBlockEntity extends BlockEntity {
    private boolean auto;
    private String content = "";
    private String filename = "untitled.mcpp";
    public BlockPos pos;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }

    public CodeBlockBlockEntity(BlockPos pos,BlockState state){
        super(ModBlockEntities.CODE_BLOCK, pos,state);
        this.pos = pos;
    }

    public void setGuiPos(BlockPos pos){
        this.pos = pos;
    }

    public void setAuto(boolean auto){
        boolean bl = this.auto;
        this.auto = auto;
    }

    @Override
    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup){
        super.writeNbt(nbt,registryLookup);
        nbt.putString("filename",filename);
        nbt.putString("content",content);
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup){
        super.readNbt(nbt,registryLookup);
        this.filename = nbt.getString("filename");
        this.content = nbt.getString("content");
    }
}
