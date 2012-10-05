package net.uberkat.obsidian.client;

import org.lwjgl.opengl.GL11;
import net.minecraft.src.*;
import net.uberkat.obsidian.common.ContainerAdvancedElectricMachine;
import net.uberkat.obsidian.common.EnumColor;
import net.uberkat.obsidian.common.ObsidianUtils;
import net.uberkat.obsidian.common.TileEntityTheoreticalElementizer;

public class GuiTheoreticalElementizer extends GuiContainer
{
    private TileEntityTheoreticalElementizer tileEntity;

    public GuiTheoreticalElementizer(InventoryPlayer inventory, TileEntityTheoreticalElementizer tentity)
    {
        super(new ContainerAdvancedElectricMachine(inventory, tentity));
        tileEntity = tentity;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everythin in front of the items)
     */
    protected void drawGuiContainerForegroundLayer()
    {
    	String displayText = "";
        fontRenderer.drawString("Theoretical Elementizer", 32, 6, 0x404040);
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
        if(tileEntity.isActive)
        {
        	displayText = "Status: " + Integer.toString(tileEntity.operatingTicks/10) + "%";
        }
        else {
        	displayText = "Status: " + EnumColor.DARK_RED.code + "Off";
        }
        fontRenderer.drawString(displayText, 80, 60, 0x404040);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        int texture = mc.renderEngine.getTexture("/gui/GuiElementizer.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(texture);
        int guiWidth = (width - xSize) / 2;
        int guiHeight = (height - ySize) / 2;
        drawTexturedModalRect(guiWidth, guiHeight, 0, 0, xSize, ySize);
        int displayInt;
        
        displayInt = tileEntity.getScaledChargeLevel(12);
        drawTexturedModalRect(guiWidth + 56, guiHeight + 36 + 12 - displayInt, 176, 12 - displayInt, 14, displayInt + 2);

        displayInt = tileEntity.getScaledProgress(24);
        drawTexturedModalRect(guiWidth + 79, guiHeight + 34, 176, 14, displayInt + 1, 16);
    }
}