package com.chatpong.chatpong;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chatpong.chatpong.core.config.PongChatConfig;
import com.chatpong.chatpong.core.sounds.SoundsHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import net.minecraftforge.server.command.ConfigCommand;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ChatPong.MOD_ID)
public class ChatPong {
	// Directly reference a log4j logger.

	public static PongChatConfig config;
	public static final String MOD_ID = "chatpong";
	public static final String MOD_NAME = "Chatpongpongpong";

	private static final Logger LOGGER = LogManager.getLogger();

	private String soundEffect;
	private String soundCategory;
	private float volume;
	private float pitch;

	public ChatPong() {
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            setupConfig();
        });
        DistExecutor.unsafeRunWhenOn(Dist.DEDICATED_SERVER, () -> () -> LOGGER.log(Level.ERROR, "You are loading " + MOD_NAME + " on a server. " + MOD_NAME + " is a client only mod!"));

        //Make sure the mod being absent on the other network side does not cause the client to display the server as incompatible
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
	}

	private void setupConfig() {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		config = new PongChatConfig(configBuilder);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, configBuilder.build(), MOD_ID + "-common.toml");

	}
	
	@SubscribeEvent
	public static void catchCommandCall(RegisterCommandsEvent event) {
		new EditPongChat(event.getDispatcher());
		ConfigCommand.register(event.getDispatcher());
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
