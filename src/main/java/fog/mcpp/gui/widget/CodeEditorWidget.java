package fog.mcpp.gui.widget;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.glfw.GLFW;
import fog.mcpp.MCPP;

import java.util.ArrayList;
import java.util.List;

public class CodeEditorWidget extends EntryListWidget<CodeEditorWidget.CodeLineEntry> {
    private final List<String> lines = new ArrayList<>();
    private int cursorLine = 0;
    private int cursorColumn = 0;

    public CodeEditorWidget(MinecraftClient client, int width, int height, int top, int bottom) {
        super(client, width, height, top, 12);
        this.lines.add("");
        this.setFocused(true);
        this.updateEntries();
    }

    public void write(String text){
        String[] newLines = text.split("\n", -1);
        for(int i = 0;i < newLines.length;i++){
            if(i==0){
                String currentLine = lines.get(cursorLine);
                String beforeCursor = currentLine.substring(0,cursorColumn);
                String afterCursor = currentLine.substring(cursorColumn);
                lines.set(cursorLine, lines.get(cursorLine) + newLines[i]);
            }else{
                lines.add(cursorLine + i, newLines[i]);
            }
        }
        cursorLine += newLines.length - 1;
        clampCursor();
        updateEntries();
    }

    private void updateEntries(){
        clearEntries();
        for(int i = 0;i < lines.size();i++){
            addEntry(new CodeLineEntry(lines.get(i),i));
        }
        setScrollAmount(getScrollAmount());
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }

    public class CodeLineEntry extends EntryListWidget.Entry<CodeLineEntry> {
        private final String line;
        private final int lineNumber;

        public CodeLineEntry(String line,int lineNumber){
            this.line = line;
            this.lineNumber = lineNumber;
        }

        @Override
        public void render(DrawContext context, int index, int y,int x,int entryWidth,int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta){
            TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
            context.drawText(textRenderer, line, x, y, 0xFFFFFF,false);

            if(this.lineNumber == cursorLine){
                int cursorX = x + textRenderer.getWidth(line.substring(0,cursorColumn));
                context.fill(cursorX, y, cursorX + 1, y + 9, 0xFF00FF00);
            }
        }
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        MCPP.LOGGER.info("CodeEditor caught input");
        return handleCharInput(chr);
    }

    private boolean handleCharInput(char chr){
        if(chr >=32 && chr != 127){
            write(String.valueOf(chr));
            return true;
        }
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        // 处理方向键、删除键等
        switch (keyCode){
            case GLFW.GLFW_KEY_UP:
                MCPP.LOGGER.info("KEY UP PRESSED");
                cursorLine--;
                clampCursor();
                return true;
            case GLFW.GLFW_KEY_DOWN:
                cursorLine++;
                clampCursor();
                return true;
            case GLFW.GLFW_KEY_LEFT:
                cursorColumn--;
                clampCursor();
                return true;
            case GLFW.GLFW_KEY_RIGHT:
                cursorColumn++;
                clampCursor();
                return true;
            case GLFW.GLFW_KEY_BACKSPACE:
                handleBackspace();
                updateEntries();
                return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private void handleBackspace(){
        String currentLine = getCurrentLine();
        if(cursorColumn  > 0){
            String newLine = currentLine.substring(0,cursorColumn - 1) + currentLine.substring(cursorColumn);
            lines.set(cursorLine,newLine);
            cursorColumn--;
        } else if(cursorLine > 0) {
            String beforeLine = lines.get(cursorLine - 1);
            lines.set(cursorLine - 1, beforeLine + currentLine);
            lines.remove(cursorLine);
            cursorLine--;
            cursorColumn = beforeLine.length();
        }
    }
    @Override
    protected int getScrollbarX() {
        return this.width - 6;
    }

    @Override
    public int getRowWidth() {
        return this.width - 12;
    }

    public String getText(){
        return String.join("\n",lines);
    }

    private String getCurrentLine(){
        return lines.get(MathHelper.clamp(cursorLine,0,lines.size() - 1));
    }

    private void clampCursor(){
        cursorLine = MathHelper.clamp(cursorLine,0,lines.size() - 1);
        cursorColumn = MathHelper.clamp(cursorColumn,0,getCurrentLine().length());
    }
}
