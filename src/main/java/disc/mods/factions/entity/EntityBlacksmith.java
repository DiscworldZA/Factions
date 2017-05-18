package disc.mods.factions.entity;

import disc.mods.core.utils.ResourceLocationHelper;
import disc.mods.factions.ref.References;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityBlacksmith extends EntityHumanoidAI
{

    public EntityBlacksmith(World worldIn)
    {
        super(worldIn);
    }

    @Override
    public ResourceLocation getTexture()
    {
        return new ResourceLocationHelper().getEntityLocation(References.Mod.Id, "blacksmith.png");
    }

    @Override
    public int getInventorySize()
    {
        return 36;
    }

}
