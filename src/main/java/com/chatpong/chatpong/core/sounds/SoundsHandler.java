package com.chatpong.chatpong.core.sounds;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundsHandler {

	public static SoundEvent SOUND_TO_BE_PLAYED;
	private static final Logger LOGGER = LogManager.getLogger();

	@OnlyIn(Dist.CLIENT)
	public static void playSound(String name, float pitch, float volume, String categoryName) {
		ResourceLocation location = new ResourceLocation(name);
		SoundEvent event = (SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(location);
		SoundCategory category = getCategoryByName(categoryName);
		Minecraft.getInstance().getSoundManager().play(new SimpleSound(event == null ? location : event.getLocation(), category, volume , pitch,  false, 0, ISound.AttenuationType.NONE, 0.0D, 0.0D, 0.0D, true));

		if(event == null) {
			LOGGER.log(Level.WARN, "Could not find sound but attempted to play anyway : {}", location);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static SoundCategory getCategoryByName(String name) {
		
		for(SoundCategory value : SoundCategory.values())
		
			if(value.getName().equals(name)) {
				return value;
			}
		
		return SoundCategory.MASTER;
	}

}
