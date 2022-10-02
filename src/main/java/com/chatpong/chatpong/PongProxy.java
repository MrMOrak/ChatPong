package com.chatpong.chatpong;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PongProxy {
	
	private static final ChatPong pongChat = new ChatPong();
	



	@SubscribeEvent
	public static void catchNameCall(ClientChatReceivedEvent event) {
		if(event.getSenderUUID() == Minecraft.getInstance().player.getUUID()) {
			return;
		}
		pongChat.pingPlayer(event.getMessage());

	}
	
	

}
