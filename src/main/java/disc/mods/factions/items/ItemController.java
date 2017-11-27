package disc.mods.factions.items;

import disc.mods.factions.ai.task.DebugTask;
import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.ref.Names;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemController extends FactionsItem
{
    private EntityLivingAI entity;

    public ItemController()
    {
        super(Names.Items.Controller);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        if (entity instanceof EntityLivingAI)
        {
            this.entity = (EntityLivingAI) entity;
        }
        return false;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote)
        {
            if (entity != null)
            {
                entity.factionTasks.add(new DebugTask("A", 6));
                entity.factionTasks.add(new DebugTask("B", 2));
                entity.factionTasks.add(new DebugTask("C", 4));
                entity.factionTasks.add(new DebugTask("D", 1));
            }
        }
        return EnumActionResult.FAIL;
    }
}
