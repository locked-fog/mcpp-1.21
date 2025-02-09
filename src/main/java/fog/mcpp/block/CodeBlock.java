package fog.mcpp.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fog.mcpp.MCPP;
import fog.mcpp.block.entity.CodeBlockBlockEntity;
import fog.mcpp.gui.CodeBlockScreenHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static fog.mcpp.MCPP.MOD_ID;

public class CodeBlock extends BlockWithEntity implements OperatorBlock {

    public static final MapCodec<CodeBlock> CODEC = RecordCodecBuilder.mapCodec(
            (instance) -> instance.group(
                    createSettingsCodec(),
                    Codec.BOOL.fieldOf("automatic").forGetter((block) -> block.auto)
            ).apply(instance, CodeBlock::new)
    );
    private final boolean auto;

    public MapCodec<CodeBlock> getCodec() {
        return CODEC;
    }

    public CodeBlock(Settings settings, boolean auto) {
        super(settings);
        this.setDefaultState((BlockState) this.stateManager.getDefaultState());
        this.auto = auto;
    }

    public BlockEntity createBlockEntity(BlockPos pos, BlockState state){
        return new CodeBlockBlockEntity(pos,state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit){
        MCPP.LOGGER.info("CodeBlock.onUse");
        if(!world.isClient){
            player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos){
        return new SimpleNamedScreenHandlerFactory(
                (syncId, playerInventory, player) -> new CodeBlockScreenHandler(syncId, playerInventory),
                Text.translatable("container.mcpp.code_block")
        );
    }
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
