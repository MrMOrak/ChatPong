package com.chatpong.chatpong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("chatpong")
public class ChatPong {
	// Directly reference a log4j logger.
	private static final Logger LOGGER = LogManager.getLogger();

	public void pingPlayer(ITextComponent chatText) {

		ClientPlayerEntity player = Minecraft.getInstance().player;
		if (chatText.getString().contains(player.getName().getString())) {
			LOGGER.debug("Playing sound...");
			player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
			LOGGER.debug("Sound was successfully played by: ", player.getName().getString());
		}
	}
	
	
}
