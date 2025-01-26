package net.kallen.kse.command.warden;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class IncreaseWardenWarningCommand {

    public IncreaseWardenWarningCommand(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("warden")
                .requires(commandSourceStack -> commandSourceStack.hasPermission(2))
                .then(Commands.literal("warning")
                .then(Commands.literal("increase")
                        .executes(this::execute))));
    }

    private int execute(CommandContext<CommandSourceStack> context) {

        ServerPlayer player = context.getSource().getPlayer();
        assert player != null;
        int warningLevel = player.getWardenSpawnTracker().get().getWarningLevel();
            if(warningLevel < 4) {
                warningLevel++;
                player.getWardenSpawnTracker().get().setWarningLevel(warningLevel);


                int finalWarningLevel = warningLevel;
                context.getSource().sendSuccess(() -> Component.literal("Warden Warning set to " + finalWarningLevel),
                        true);
            }else{
                context.getSource().sendFailure(Component.literal("Warden Warning already at max"));
            }

        return 1;

    }
}
