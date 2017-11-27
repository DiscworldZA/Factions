package disc.mods.factions.event;

import disc.mods.core.util.PlayerUtils;
import disc.mods.factions.block.BlockBuildable;
import disc.mods.factions.block.BlockFaction;
import disc.mods.factions.capabilities.IFactionCapability;
import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.faction.FactionHandler;
import disc.mods.factions.tileentity.TileEntityBuilding;
import disc.mods.factions.utils.CapabilityHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class FactionsEventHandler
{
    @SubscribeEvent
    public void NotifyAiLoaded(EntityJoinWorldEvent event)
    {
        if (event.getWorld().isRemote)
            return;
        if (event.getEntity() instanceof EntityLivingAI)
        {
            EntityLivingAI entity = (EntityLivingAI) event.getEntity();
            entity.getFactionBuilding().notifyAiLoaded(entity);
        }
    }

    @SubscribeEvent
    public void Player_Login(PlayerLoggedInEvent event)
    {
        IFactionCapability cap = CapabilityHelper.getFactionCapability(event.player);
        if (cap.hasFaction())
        {
            cap.sync(event.player);
        }
    }

    @SubscribeEvent
    public void BlockRightClicked(PlayerInteractEvent.RightClickBlock event)
    {
        if (event.getEntity() instanceof EntityPlayer)
        {
            if (event.getItemStack().getItem() instanceof ItemBlock)
            {
                ItemBlock itemBlock = (ItemBlock) event.getItemStack().getItem();
                IFactionCapability cap = CapabilityHelper.getFactionCapability(event.getEntityPlayer());
                if (itemBlock.getBlock() instanceof BlockFaction)
                {
                    if (cap.hasFaction())
                    {
                        event.setCanceled(true);
                        if (event.getWorld().isRemote)
                            PlayerUtils.sendMessage(event.getEntityPlayer(), "Already in Faction!");
                    }
                }
                else if (itemBlock.getBlock() instanceof BlockBuildable)
                {
                    if (!cap.hasFaction())
                    {
                        event.setCanceled(true);
                        if (event.getWorld().isRemote)
                            PlayerUtils.sendMessage(event.getEntityPlayer(), "No Faction!");
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void BlockPlaced(BlockEvent.PlaceEvent event)
    {
        TileEntity tile = event.getWorld().getTileEntity(event.getPos());

        if (tile instanceof TileEntityBuilding)
        {
            IFactionCapability cap = CapabilityHelper.getFactionCapability(event.getPlayer());
            if (cap.hasFaction())
            {
                TileEntityBuilding tileFaction = (TileEntityBuilding) tile;
                tileFaction.getFactionBuilding().faction = FactionHandler.getFaction(cap.getFactionName());
            }
        }
    }

}
