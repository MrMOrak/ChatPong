package com.chatpong.chatpong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chatpong.chatpong.core.config.PongChatConfig;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.config.ModConfig.Type;

@Mod("chatpong")
public class EditPongChat {
	private static final Logger LOGGER = LogManager.getLogger();

	EditPongChat(CommandDispatcher<CommandSource> dispatcher) {

		dispatcher
				.register(
						Commands.literal(
								"chatpong").then(
										Commands.literal("editall")
												.then(Commands
														.argument("category", StringArgumentType.string()).then(
																Commands.argument("sound", StringArgumentType.string())
																		.then(Commands
																				.argument("volume",
																						FloatArgumentType.floatArg())
																				.then(Commands
																						.argument("pitch",
																								FloatArgumentType
																										.floatArg())
																						.executes((command) -> {
																							storePingProperties(
																									command);
																							return 0;
																						})))))));

		dispatcher.register(Commands.literal("pongchat").then(Commands.literal("edit").then(Commands.literal("category")
				.then(Commands.argument("category name", StringArgumentType.string()).executes((command) -> {
					return 0;
				}))).then(Commands.literal("sound")
						.then(Commands.argument("sound name", StringArgumentType.string()).executes((command) -> {
							return 0;
						})))
				.then(Commands.literal("volume")
						.then(Commands.argument("volume", FloatArgumentType.floatArg()).executes((command) -> {
							return 0;
						})))
				.then(Commands.literal("pitch")
						.then(Commands.argument("pitch", FloatArgumentType.floatArg()).executes((command) -> {
							return 0;
						})))));
	}

	public void storePingProperties(CommandContext<CommandSource> context) throws CommandSyntaxException {

		String sound = StringArgumentType.getString(context, "sound");
		String category = StringArgumentType.getString(context, "category");
		float volume = FloatArgumentType.getFloat(context, "volume");
		float pitch = FloatArgumentType.getFloat(context, "pitch");

		new ModConfig(Type.COMMON, PongChatConfig.SPEC, null);

		PongChatConfig.soundEffect.set(sound);
		PongChatConfig.soundCategory.set(category);
		PongChatConfig.volume.set(volume);
		PongChatConfig.pitch.set(pitch);

		LOGGER.debug("Successfully changed pingsound");

		PongChatConfig.BUILDER.build();

	}
}
