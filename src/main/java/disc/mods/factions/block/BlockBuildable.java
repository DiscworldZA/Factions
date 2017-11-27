package disc.mods.factions.block;

import disc.mods.core.block.CoreTileEntityBlock;
import disc.mods.core.util.PlayerUtils;
import disc.mods.factions.Factions;
import disc.mods.factions.capabilities.IFactionCapability;
import disc.mods.factions.creativetab.Tabs;
import disc.mods.factions.faction.buildings.JsonBuilding;
import disc.mods.factions.faction.buildings.JsonBuilding.FunctionalBlock;
import disc.mods.factions.registry.Registries;
import disc.mods.factions.tileentity.TileEntityBuilding;
import disc.mods.factions.utils.CapabilityHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockBuildable extends CoreTileEntityBlock
{
    public BlockBuildable(String Name)
    {
        super(Name);
        this.setCreativeTab(Tabs.Factions);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote && hand == EnumHand.MAIN_HAND)
        {
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof TileEntityBuilding)
            {
                TileEntityBuilding building = (TileEntityBuilding) tile;
                PlayerUtils.sendMessage(playerIn, building.getFactionBuilding().faction.Name);
            }
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        super.onBlockPlacedBy(world, pos, state, placer, stack);
        if (placer instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) placer;
            IFactionCapability cap = CapabilityHelper.getFactionCapability(player);
            if (cap.hasFaction())
            {
                TileEntity tile = world.getTileEntity(pos);
                if (tile instanceof TileEntityBuilding)
                {
                    TileEntityBuilding building = (TileEntityBuilding) tile;
                    cap.getFaction().buildings.add(building.getFactionBuilding());
                    if(!world.isRemote) building.getFactionBuilding().spawnTestEntity();
                    JsonBuilding jsonB = Registries.BuildingRegistry.get(building.getFactionBuilding());

                    BlockPos craftingBench = jsonB.getFunctionalBlockPos(FunctionalBlock.Crafting, building.getPos());
                    BlockPos chest = jsonB.getFunctionalBlockPos(FunctionalBlock.Storage, building.getPos());

                    world.setBlockState(craftingBench, Blocks.CRAFTING_TABLE.getDefaultState());
                    world.setBlockState(chest, Blocks.CHEST.getDefaultState());
                }
            }
        }
    }

    @Override
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state)
    {
        super.onBlockDestroyedByPlayer(worldIn, pos, state);
    }

    public void registerItemModel(ItemBlock itemBlock)
    {
        Factions.proxy.registerItemRenderer(itemBlock, 0);
    }
}
