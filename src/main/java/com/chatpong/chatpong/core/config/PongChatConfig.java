package com.chatpong.chatpong.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class PongChatConfig{


	public final ForgeConfigSpec.ConfigValue<String> soundEffect;
	public final ForgeConfigSpec.ConfigValue<Float> volume;
	public final ForgeConfigSpec.ConfigValue<Float> pitch;
	public final ForgeConfigSpec.ConfigValue<String> soundCategory;

	public PongChatConfig(ForgeConfigSpec.Builder BUILDER)  {

		BUILDER.comment("Config for choosing sound, volume and pitch").push("Chatpong");
		
		soundEffect = BUILDER.comment(
				"Resource Location based name of the sound file to play when Minecraft finishes loading.\\nEG: \\\"ui.button.click\\\" or \\\"entity.experience_orb.pickup\\\"\\n\\nThis can also be a mod sound if the mod is installed.\\nEG: \\\"modname:modsound.boing\\\"\\n\\nIf you want to use external sounds, consider looking into the mod Additional Resources ")
				.define("SoundEffect", "entity.experience_orb.pickup");
		volume = BUILDER.comment("Volume of ping sound :  ").define("Volume", 1.0f);
		pitch = BUILDER.comment("Pitch of ping sound: ").define("Pitch", 1.0f);
		soundCategory = BUILDER.comment(
				"Sound category for the sound played when Minecraft finishes loading. EG: \"ambient\" or \"music\". Defaults to \"master\" if ChatPong cannot find your category.")
				.define("Category", "master");

		BUILDER.pop();

	}

}
