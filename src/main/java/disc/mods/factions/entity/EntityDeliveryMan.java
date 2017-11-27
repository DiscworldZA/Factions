package disc.mods.factions.entity;

import disc.mods.core.util.ResourceLocationHelper;
import disc.mods.factions.ref.References;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityDeliveryMan extends EntityHumanoidAI
{
    public EntityDeliveryMan(World worldIn)
    {
        super(worldIn);
    }

    @Override
    public ResourceLocation getTexture()
    {
        return ResourceLocationHelper.getEntityLocation(References.Mod.Id, "deliveryman.png");
    }

    @Override
    public int getInventorySize()
    {
        return 36;
    }

}
