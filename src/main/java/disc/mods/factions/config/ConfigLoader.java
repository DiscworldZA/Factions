package disc.mods.factions.config;

import java.io.File;

import disc.mods.factions.ref.References;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigLoader
{
    public static FactionsConfig LoadConfig(FMLPreInitializationEvent event)
    {
        File configDirectory = new File(event.getModConfigurationDirectory(), References.Mod.Id);
        if (!configDirectory.exists())
        {
            configDirectory.mkdir();
        }

        File configFile = new File(configDirectory, "Factions.cfg");

        return new FactionsConfig(configFile);
    }
}
