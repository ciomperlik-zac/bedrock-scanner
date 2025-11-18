package com.nunyabiz_.bedrockscanner;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.LevelResource;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class Scanner {
    public static Path getSavePath() {
        Path base = Path.of("bedrockscanner");

        Minecraft mc = Minecraft.getInstance();

        if (mc.getCurrentServer() == null) {
            return base.resolve(mc.getSingleplayerServer().getWorldPath(LevelResource.ROOT).getParent()
                    .getFileName().toString() + ".scan");
        } else {
            return base.resolve(mc.getCurrentServer().ip.replaceAll("[^A-Za-z0-9]", "") + ".scan");
        }
    }

    private static String posToString(BlockPos pos) {
        return pos.getX() + " " + pos.getY() + " " + pos.getZ() + " Bedrock";
    }

    public static void scan(int size) {
        int radius = Math.min(64, size / 2);

        Minecraft mc = Minecraft.getInstance();

        try {
            Files.createDirectories(getSavePath().getParent());
            BufferedWriter writer = new BufferedWriter(new FileWriter(getSavePath().toFile()));

            for (int x = -radius; x < radius; x++) {
                for (int z = -radius; z < radius; z++) {
                    BlockPos topPos = new BlockPos(mc.player.getBlockX() + x, 123, mc.player.getBlockZ() + z);
                    BlockPos bottomPos = topPos.atY(4);

                    if (mc.level.getBlockState(topPos).is(Blocks.BEDROCK)) {
                        writer.write(posToString(topPos));
                        writer.newLine();
                    }

                    if (mc.level.getBlockState(bottomPos).is(Blocks.BEDROCK)) {
                        writer.write(posToString(bottomPos));
                        writer.newLine();
                    }
                }
            }

            writer.close();
        } catch (Exception ignored) {

        }
    }
}
