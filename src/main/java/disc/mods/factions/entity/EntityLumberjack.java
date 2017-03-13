package disc.mods.factions.entity;

import disc.mods.factions.ref.References;
import disc.mods.core.utils.ResourceLocationHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityLumberjack extends EntityLivingAI
{

    public EntityLumberjack(World worldIn)
    {
        super(worldIn);
    }

    @Override
    public ResourceLocation getTexture()
    {
        return ResourceLocationHelper.getEntityLocation(References.Mod.Id, "lumberjack.png");
    }

    @Override
    public int getInventorySize()
    {
        return 36;
    }

}
