package net.kallen.kse.command.warden;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class SetWardenWarningCommand {

    private int setWarningLevel;

    public SetWardenWarningCommand(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("warden")
                .requires(commandSourceStack -> commandSourceStack.hasPermission(2))
                .then(Commands.literal("warning")
                .then(Commands.literal("set")
                .then(Commands.argument("level", IntegerArgumentType.integer(0,4))
                        .executes(commandContext -> execute(commandContext, IntegerArgumentType.getInteger(commandContext, "level")))))));
    }

    private int execute(CommandContext<CommandSourceStack> context, int setWarningLevel) {

        System.out.println(context.getLastChild());
        ServerPlayer player = context.getSource().getPlayer();
        assert player != null;


        player.getWardenSpawnTracker().get().setWarningLevel(setWarningLevel);
        context.getSource().sendSuccess(() -> Component.literal("Warning Level Set to " + setWarningLevel), true);


        return 1;

    }
}
