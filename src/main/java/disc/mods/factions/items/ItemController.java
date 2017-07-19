package disc.mods.factions.items;

import disc.mods.factions.ai.task.CraftingTask;
import disc.mods.factions.ai.task.FollowTask;
import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.faction.buildings.JsonBuilding;
import disc.mods.factions.faction.buildings.JsonBuilding.FunctionalBlock;
import disc.mods.factions.ref.Names;
import disc.mods.factions.registry.Registries;
import disc.mods.factions.tileentity.TileEntityBlacksmith;
import disc.mods.factions.tileentity.TileEntityBuilding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
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
                entity.factionTasks.addTask(new FollowTask(entity, player));
            }
        }
        return EnumActionResult.FAIL;
    }
}
