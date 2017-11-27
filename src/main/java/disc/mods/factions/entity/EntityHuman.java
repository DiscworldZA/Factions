package disc.mods.factions.entity;

import disc.mods.core.util.ResourceLocationHelper;
import disc.mods.factions.ref.References;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityHuman extends EntityHumanoidAI
{
    public EntityHuman(World worldIn)
    {
        super(worldIn);
    }

    @Override
    public ResourceLocation getTexture()
    {
        return new ResourceLocationHelper().getEntityLocation(References.Mod.Id, "human.png");
    }

    @Override
    public int getInventorySize()
    {
        return 1;
    }

}
