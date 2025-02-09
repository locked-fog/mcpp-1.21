package fog.mcpp.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.ApiStatus;

public class CodeBlockScreen extends HandledScreen<CodeBlockScreenHandler> {
    private final CodeBlockScreenHandler handler;
    private TextFieldWidget textField; // 主编辑框
    private String fileName = Text.translatable("gui.mcpp.filename").toString(); // 文件名


    public CodeBlockScreen(CodeBlockScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler,inventory ,title);
        this.titleX = 0;
        this.handler = handler;
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
        this.textField = new TextFieldWidget(
                this.textRenderer,
                10, topMargin + 20, //X,Y
                this.width - 20, textFieldHeight, //Width, Height
                Text.translatable("gui.mcpp.code_block.editor.text_field")
        );
        this.textField.setMaxLength(10000); // 最大长度
        this.addDrawableChild(this.textField);

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
        renderBackground(context,mouseX,mouseY,delta);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void close(){
        super.close();
    }
}
