package fog.mcpp.gui;

import fog.mcpp.MCPP;
import fog.mcpp.block.entity.CodeBlockBlockEntity;
import fog.mcpp.gui.widget.CodeEditorWidget;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.ApiStatus;
import org.lwjgl.glfw.GLFW;
import fog.mcpp.MCPP;

public class CodeBlockScreen extends HandledScreen<CodeBlockScreenHandler> {
    private final CodeBlockScreenHandler handler;
    private BlockPos blockPos;
    private CodeEditorWidget codeEditor; // 主编辑框
    private String fileName = Text.translatable("gui.mcpp.filename").toString(); // 文件名
    private float scrollOffset = 0;

    public CodeBlockScreen(CodeBlockScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler,inventory ,title);
        this.titleX = 0;
        this.handler = handler;
        this.blockPos = handler.getPos();
    }

    @Override
    public void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {}

    @Override
    protected void init() {
        super.init();
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(title)) / 2;

        // 控件位置
        int topMargin = 20;
        int buttonHeight = 20;
        int textFieldHeight = this.height - topMargin - buttonHeight - 30;

        // Title
        Text titleText = Text.of(Text.translatable("gui.mcpp.code_block.editor.opened_file") + fileName);
        this.addDrawableChild(new TextWidget(
                (this.width - this.textRenderer.getWidth(titleText)) / 2, // 居中
                topMargin,
                titleText,
                textRenderer
        ));

        // TextField
        this.codeEditor = new CodeEditorWidget(
                MinecraftClient.getInstance(),
                this.width - 20,
                this.height - 60,
                this.titleY + 20,
                this.height - 40
        );
        this.addDrawableChild(this.codeEditor);

        if(client.world != null){
            BlockEntity blockEntity = client.world.getBlockEntity(blockPos);
            if(blockEntity instanceof CodeBlockBlockEntity codeBlockBlockEntity){
                codeEditor.write(codeBlockBlockEntity.getContent());
            }
        }

        // 按钮布局
        int buttonWidth = 60;
        int buttonY = this.height - buttonHeight - 10;

        // Open Button
        //TODO: Open Button
        ButtonWidget openButton = ButtonWidget.builder(
                Text.translatable("gui.mcpp.code_block.editor.open"),
                button -> {}
        ).dimensions(this.width - buttonWidth - 10,buttonY,buttonWidth,buttonHeight).build();

        // Save Button
        //TODO: Save Button
        ButtonWidget saveButton = ButtonWidget.builder(
                Text.translatable("gui.mcpp.code_block.editor.save"),
                button -> {}
        ).dimensions((this.width - buttonWidth) / 2,buttonY,buttonWidth,buttonHeight).build();

        // Close Button
        ButtonWidget closeButton = ButtonWidget.builder(
                Text.translatable("gui.mcpp.code_block.editor.close"),
                button -> this.close()
        ).dimensions(10,buttonY,buttonWidth,buttonHeight).build();

        this.addDrawableChild(openButton);
        this.addDrawableChild(saveButton);
        this.addDrawableChild(closeButton);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context,mouseX,mouseY,delta);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if(keyCode == GLFW.GLFW_KEY_E){
            return false;
        }
        if(keyCode == GLFW.GLFW_KEY_ESCAPE){
            return super.keyPressed(keyCode,scanCode,modifiers);
        }
        return this.codeEditor.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers){
        MCPP.LOGGER.info("CodeBlockScreen.charTyped caught.");
        return this.codeEditor.charTyped(chr,modifiers);
    }

    @Override
    public void close(){
        if(client.world != null){
            BlockEntity blockEntity = client.world.getBlockEntity(blockPos);
            if(blockEntity instanceof CodeBlockBlockEntity codeBlockBlockEntity){
                codeBlockBlockEntity.setContent(codeEditor.getText());
            }
        }

        super.close();
    }


}
