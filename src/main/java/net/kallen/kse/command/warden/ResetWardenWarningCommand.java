package net.kallen.kse.command.warden;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class ResetWardenWarningCommand {

    public ResetWardenWarningCommand(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("warden")
                .then(Commands.literal("warning")
                .then(Commands.literal("reset")
                        .executes(this::execute))));
    }

    private int execute(CommandContext<CommandSourceStack> context) {

        ServerPlayer player = context.getSource().getPlayer();
        assert player != null;
        player.getWardenSpawnTracker().get().setWarningLevel(0);


        context.getSource().sendSuccess(() -> Component.literal("Reset Warden Warning"),
                true);

        return 1;

    }
}
