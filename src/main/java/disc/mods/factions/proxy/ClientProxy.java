package disc.mods.factions.proxy;

import disc.mods.factions.init.FactionsRenderers;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
        FactionsRenderers.RegisterRenderers();
    }
}
