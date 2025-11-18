package com.nunyabiz_.bedrockscanner;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BedrockScanner implements ClientModInitializer {
    public static final String MOD_ID = "bedrock-scanner";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("bedrockscan")
                    .then(ClientCommandManager.argument("size", IntegerArgumentType.integer(1)).executes(context -> {
                        FabricClientCommandSource source = context.getSource();
                        int size = IntegerArgumentType.getInteger(context, "size");

                        source.sendFeedback(Component.literal("Running bedrock scan with size: " + size));
                        Scanner.scan(size);
                        Minecraft.getInstance().player.displayClientMessage(Component.literal("Scan complete!"), false);

                        return 1;
                    })));
        });
    }
}