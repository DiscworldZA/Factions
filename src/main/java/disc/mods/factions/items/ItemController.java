package disc.mods.factions.items;

import disc.mods.factions.ai.actions.GetItemAction;
import disc.mods.factions.ai.actions.PutItemAction;
import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.ref.Names;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
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
                
            }
        }
        return EnumActionResult.FAIL;
    }
}
