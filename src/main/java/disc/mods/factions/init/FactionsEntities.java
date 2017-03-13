package disc.mods.factions.init;

import java.util.Random;

import disc.mods.core.utils.ResourceLocationHelper;
import disc.mods.factions.Factions;
import disc.mods.factions.entity.EntityBlacksmith;
import disc.mods.factions.entity.EntityDeliveryMan;
import disc.mods.factions.entity.EntityHuman;
import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.entity.EntityLumberjack;
import disc.mods.factions.ref.Names;
import disc.mods.factions.ref.References;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class FactionsEntities
{
    public static int FactionModEnitityId = 0;

    private static void RegisterEntityAI(Class<? extends EntityLivingAI> entity, String Name)
    {
        long x = entity.getName().hashCode();
        Random random = new Random(x);
        int eggPrimary = random.nextInt() * 16777215; // first egg color
        int eggSecondary = random.nextInt() * 16777215;
        EntityRegistry.registerModEntity(ResourceLocationHelper.getEntityLocation(References.Mod.Id, Name), entity, Name, FactionModEnitityId, Factions.instance, 64, 1, true, eggPrimary, eggSecondary);
        FactionModEnitityId++;
    }

    public static void init()
    {
        RegisterEntityAI(EntityHuman.class, Names.Entities.Human);
        RegisterEntityAI(EntityBlacksmith.class, Names.Entities.Builder);
        RegisterEntityAI(EntityDeliveryMan.class, Names.Entities.DeliveryMan);
        RegisterEntityAI(EntityLumberjack.class, Names.Entities.Lumberjack);
    }
}
