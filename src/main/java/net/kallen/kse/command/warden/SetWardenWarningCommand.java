package net.kallen.kse.command.warden;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class SetWardenWarningCommand {

    private int setWarningLevel;

    public SetWardenWarningCommand(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("warden")
                .then(Commands.literal("warning")
                .then(Commands.literal("set")
                        .executes(this::execute))));
    }

    private int execute(CommandContext<CommandSourceStack> context) {

        ServerPlayer player = context.getSource().getPlayer();
        assert player != null;
        int warningLevel = player.getWardenSpawnTracker().get().getWarningLevel();


        context.getSource().sendSuccess(() -> Component.literal("Work In Progress"),
                true);

        return 1;

    }
}
