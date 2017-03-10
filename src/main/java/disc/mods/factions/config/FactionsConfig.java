package disc.mods.factions.config;

import java.io.File;

import disc.mods.core.DiscCore;
import disc.mods.core.config.CoreConfig;
import disc.mods.core.ref.CoreSettings;
import disc.mods.factions.Factions;
import disc.mods.factions.ref.FactionsSettings;
import disc.mods.factions.ref.References;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class FactionsConfig extends Configuration
{
    public static FactionsConfig GetConfig()
    {
        return Factions.instance.config;
    }

    public FactionsConfig(File file)
    {
        super(file);
    }

    public void preInit(FMLPreInitializationEvent event)
    {
        FactionsSettings.Load(this);
    }

    public void postInit(FMLPostInitializationEvent event)
    {
        if (this.hasChanged())
        {
            this.save();
        }
    }
}
