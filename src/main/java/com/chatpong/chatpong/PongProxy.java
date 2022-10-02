package com.chatpong.chatpong;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PongProxy {
	
	private static ChatPong pongChat = new ChatPong();
	



	@SubscribeEvent
	public static void catchNameCall(ClientChatReceivedEvent event) {
		pongChat.pingPlayer(event.getMessage());

	}
	
	

}
