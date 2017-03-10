package disc.mods.factions.entity;

import disc.mods.core.utils.ResourceLocationHelper;
import disc.mods.factions.ai.InventoryAI;
import disc.mods.factions.ref.References;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityBuilder extends EntityHumanoidAI
{

    public EntityBuilder(World worldIn)
    {
        super(worldIn);
    }

    @Override
    public ResourceLocation getTexture()
    {
        return new ResourceLocationHelper().getEntityLocation(References.Mod.Id, "builder.png");
    }

    @Override
    public void createInventory()
    {
        inventory = new InventoryAI(this, 36);
    }

}
