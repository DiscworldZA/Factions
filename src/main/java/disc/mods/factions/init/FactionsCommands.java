package disc.mods.factions.init;

import disc.mods.factions.command.RequestCommand;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class FactionsCommands
{
    public static void init(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new RequestCommand());
    }
}
