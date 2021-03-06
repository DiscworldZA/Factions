package disc.mods.factions.init;

import disc.mods.factions.client.renderer.entity.RenderHumanoid;
import disc.mods.factions.entity.EntityBlacksmith;
import disc.mods.factions.entity.EntityDeliveryMan;
import disc.mods.factions.entity.EntityHuman;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class FactionsRenderers
{
    public static void RegisterRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityHuman.class, RenderHumanoid::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBlacksmith.class, RenderHumanoid::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDeliveryMan.class, RenderHumanoid::new);
    }
}
