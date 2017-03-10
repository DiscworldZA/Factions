package disc.mods.factions.proxy;

import disc.mods.factions.event.FactionsEventHandler;
import disc.mods.factions.init.FactionsTileEntities;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class CommonProxy extends disc.mods.core.proxy.CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
        FactionsTileEntities.init();
    }
}
