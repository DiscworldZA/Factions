package disc.mods.factions;

import disc.mods.factions.ai.crafting.FactionsCraftingHandler;
import disc.mods.factions.config.ConfigLoader;
import disc.mods.factions.config.FactionsConfig;
import disc.mods.factions.init.FactionsBlocks;
import disc.mods.factions.init.FactionsCapabilities;
import disc.mods.factions.init.FactionsEntities;
import disc.mods.factions.init.FactionsEventHandlers;
import disc.mods.factions.init.FactionsItems;
import disc.mods.factions.init.FactionsPackets;
import disc.mods.factions.network.FactionsPacketHandler;
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

    public static Factions getInstance()
    {
        return instance;
    }

    // Proxy
    @SidedProxy(serverSide = References.Proxy.Common, clientSide = References.Proxy.Client)
    public static CommonProxy proxy;

    // Config
    public FactionsConfig config;

    private static FactionsPacketHandler network;

    public static FactionsPacketHandler getNetwork()
    {
        return network != null ? network : (network = new FactionsPacketHandler());
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // Proxy preInit
        proxy.preInit(event);

        // Config Load
        config = ConfigLoader.LoadConfig(event);
        config.preInit(event);

        // Initialization
        FactionsBlocks.init();
        FactionsItems.init();
        FactionsEntities.init();
        FactionsCapabilities.init();
        FactionsEventHandlers.init();
        FactionsPackets.init();
        FactionsCraftingHandler.registerCrafters();
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
