package com.chatpong.chatpong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chatpong.chatpong.core.config.PongChatConfig;
import com.chatpong.chatpong.core.sounds.SoundsHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Chat;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.server.command.ConfigCommand;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ChatPong.MOD_ID)
public class ChatPong {
	// Directly reference a log4j logger.

	public static final String MOD_ID = "chatpong";

	private static final Logger LOGGER = LogManager.getLogger();

	private String soundEffect = PongChatConfig.soundEffect.get();
	private String soundCategory = PongChatConfig.soundCategory.get();
	private float volume = PongChatConfig.volume.get();
	private float pitch = PongChatConfig.pitch.get();

	public ChatPong() {

		
		
		ModLoadingContext.get().registerConfig(Type.COMMON, PongChatConfig.SPEC, "chatpong-common.toml");
		
		MinecraftForge.EVENT_BUS.register(this);

	}


	@SubscribeEvent
	public void pingPlayer(ClientChatReceivedEvent event) {

		ClientPlayerEntity player = Minecraft.getInstance().player;
		if (player.getUUID() == event.getSenderUUID())
			return;
		if (event.getMessage().getString().contains(player.getName().getString())) {

			LOGGER.debug("Playing sound...");
			SoundsHandler.playSound(soundEffect, pitch, volume, soundCategory);
			LOGGER.debug("Sound was successfully played by: " + player.getName().getString());
		}
	}

	

}
