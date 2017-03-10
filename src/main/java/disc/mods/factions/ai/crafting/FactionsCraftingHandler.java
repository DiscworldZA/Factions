package disc.mods.factions.ai.crafting;

import java.util.HashMap;
import java.util.Map;

import disc.mods.factions.entity.EntityBuilder;
import disc.mods.factions.entity.EntityLivingAI;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FactionsCraftingHandler
{    
    private static Map<Class<? extends EntityLivingAI>, FactionCrafter> crafters = new HashMap<Class<? extends EntityLivingAI>, FactionCrafter>();

    private static void RegisterCrafter(Class<? extends EntityLivingAI> entityClass)
    {
        crafters.put(entityClass, new FactionCrafter(entityClass));
    }

    public static FactionCrafter getCrafter(EntityLivingAI ai)
    {
        return crafters.get(ai.getClass());
    }
    
    public static void registerCrafters()
    {
        RegisterCrafter(EntityBuilder.class);
    }
}
