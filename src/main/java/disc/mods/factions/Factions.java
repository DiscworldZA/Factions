package disc.mods.factions;

import disc.mods.factions.ai.crafting.FactionsCraftingHandler;
import disc.mods.factions.config.ConfigLoader;
import disc.mods.factions.config.FactionsConfig;
import disc.mods.factions.event.FactionsEventHandler;
import disc.mods.factions.init.FactionsBlocks;
import disc.mods.factions.init.FactionsEntities;
import disc.mods.factions.init.FactionsItems;
import disc.mods.factions.proxy.CommonProxy;
import disc.mods.factions.ref.References;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = References.Mod.Id, name = References.Mod.Name, version = References.Mod.Version, acceptedMinecraftVersions = References.Mod.MCVersion)
public class Factions
{
    @Instance
    public static Factions instance;

    // Proxy
    @SidedProxy(serverSide = References.Proxy.Common, clientSide = References.Proxy.Client)
    public static CommonProxy proxy;

    // Config
    public FactionsConfig config;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
        config = ConfigLoader.LoadConfig(event);
        config.preInit(event);
        FactionsBlocks.init();
        FactionsItems.init();
        FactionsEntities.init();
        FactionsCraftingHandler.registerCrafters();
        FactionsEventHandler.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        config.postInit(event);
    }

}
