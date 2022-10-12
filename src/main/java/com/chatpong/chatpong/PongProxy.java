package com.chatpong.chatpong;

import com.chatpong.chatpong.core.config.PongChatConfig;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.config.ModConfig.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber
public class PongProxy {

	public PongProxy() {
		

	}


	@SubscribeEvent
	public static void catchCommandCall(RegisterCommandsEvent event) {
		new EditPongChat(event.getDispatcher());

		ConfigCommand.register(event.getDispatcher());
	}

}
