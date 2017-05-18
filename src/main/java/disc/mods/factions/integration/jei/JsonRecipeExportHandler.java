package disc.mods.factions.integration.jei;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import disc.mods.core.utils.FileIOHelper;
import disc.mods.core.utils.OreDictHelper;
import disc.mods.core.utils.PlayerUtils;
import disc.mods.factions.ai.crafting.recipes.JsonRecipe;
import disc.mods.factions.ref.References;
import mezz.jei.api.gui.IGuiIngredient;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.transfer.IRecipeTransferError;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class JsonRecipeExportHandler implements IRecipeTransferHandler
{
    protected Class containerClass;

    public JsonRecipeExportHandler(Class<? extends Container> containerClass)
    {
        this.containerClass = containerClass;
    }

    @Override
    public Class getContainerClass()
    {
        return containerClass;
    }

    @Override
    public IRecipeTransferError transferRecipe(Container container, IRecipeLayout recipeLayout, EntityPlayer player, boolean maxTransfer, boolean doTransfer)
    {
        if (!doTransfer)
            return null;
        Map<Integer, ? extends IGuiIngredient<ItemStack>> inputs = recipeLayout.getItemStacks().getGuiIngredients();
        JsonRecipe recipe = new JsonRecipe(inputs.size() - 1);
        List<ItemStack> stacks = new ArrayList<ItemStack>();
        for (int i = 0; i < inputs.size(); i++)
        {
            if (!inputs.get(i).isInput())
            {
                recipe.result = inputs.get(i).getAllIngredients().get(0).getItem().getRegistryName().toString();
                recipe.count = inputs.get(i).getAllIngredients().get(0).getCount();
            }
        }
        for (Slot slot : container.inventorySlots)
        {
            if (slot.inventory instanceof InventoryCrafting)
            {
                IGuiIngredient<ItemStack> ingredient = inputs.get(slot.getSlotIndex() + 1);

                if (ingredient != null && ingredient.getAllIngredients().size() > 0)
                {
                    int x = slot.getSlotIndex() / recipe.recipeSize;
                    int y = slot.getSlotIndex() % recipe.recipeSize;
                    recipe.recipe[x][y] = OreDictHelper.getOreDictEquivalent(ingredient.getAllIngredients());
                    stacks.add(ingredient.getAllIngredients().get(0));
                }
                else
                {
                    stacks.add(null);
                }
            }
        }
        if (recipe.hasResult())
        {
            File file = new File("config/" + References.Mod.Id + "/exportRecipe.json");
            FileIOHelper.CreateFile(file);
            FileIOHelper.WriteFile(file, recipe.toJson());
            PlayerUtils.sendMessage(player, recipe.result + ": written to exportRecipe.json");
        }
        return null;
    }
}
