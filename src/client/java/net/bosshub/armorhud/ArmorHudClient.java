package net.bosshub.armorhud;

import net.bosshub.armorhud.hud.ArmorHudOverlay;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class ArmorHudClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HudRenderCallback.EVENT.register(new ArmorHudOverlay());
	}
}