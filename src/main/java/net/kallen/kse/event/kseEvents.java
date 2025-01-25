package net.kallen.kse.event;


import net.kallen.kse.command.warden.*;
import net.kallen.kse.kse;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = kse.MOD_ID)
public class kseEvents {



    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new GetWardenWarningCommand(event.getDispatcher());
        new IncreaseWardenWarningCommand(event.getDispatcher());
        new DecreaseWardenWarningCommand(event.getDispatcher());
        new ResetWardenWarningCommand(event.getDispatcher());
        new SetWardenWarningCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }
}