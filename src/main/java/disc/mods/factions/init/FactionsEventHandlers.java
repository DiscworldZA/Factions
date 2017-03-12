package disc.mods.factions.init;

import disc.mods.factions.capabilities.CapabilityEventHandler;
import disc.mods.factions.event.FactionsEventHandler;
import net.minecraftforge.common.MinecraftForge;

public class FactionsEventHandlers
{
    public static void init()
    {
        MinecraftForge.EVENT_BUS.register(new FactionsEventHandler());
        MinecraftForge.EVENT_BUS.register(new CapabilityEventHandler());
    }

}
