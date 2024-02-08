package net.bosshub.armorhud.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.bosshub.armorhud.ArmorHud;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ArmorHudOverlay implements HudRenderCallback {
    private static final Identifier INVENTORY_SLOT_TEXTURE = new Identifier(ArmorHud.MOD_ID, "textures/inventory_slot.png");

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        int x = 0;
        int y = 0;

        MinecraftClient client = MinecraftClient.getInstance();

        if(client != null) {
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            x = width / 2;
            y = height;
        }

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, INVENTORY_SLOT_TEXTURE);

        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if(player == null) return;

        int startX = 192;

        int slotSize = 22;
        int armorSize = 20;
        int slotDiff = 2;
        int offset = 1;

        for(int i = 0; i < 4; i++) {
            ItemStack itemStack = player.getInventory().getArmorStack(i);
            drawContext.drawTexture(INVENTORY_SLOT_TEXTURE, x - startX + (i * slotSize) - (slotDiff * i), y - slotSize, 0, 0, slotSize, slotSize, slotSize, slotSize);
            drawContext.drawItem(itemStack, x - (startX - slotDiff) + (i * armorSize) + offset, (y - armorSize) + offset);
        }

        // TODO: Replace inventory slot png
        // TODO: Draw durability.
    }
}
