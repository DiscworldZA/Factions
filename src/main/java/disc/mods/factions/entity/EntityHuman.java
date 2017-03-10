package disc.mods.factions.entity;

import disc.mods.core.ref.Values;
import disc.mods.core.utils.ResourceLocationHelper;
import disc.mods.factions.ai.InventoryAI;
import disc.mods.factions.ai.actions.FollowAction;
import disc.mods.factions.ref.FactionsSettings;
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
    public void createInventory()
    {
        inventory = new InventoryAI(this, 1);
    }

}
