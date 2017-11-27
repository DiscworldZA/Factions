package disc.mods.factions.ai.crafting.recipes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.inventory.EmptyContainer;
import disc.mods.factions.inventory.InventoryAI;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

public class JsonRecipe
{
    public String result;
    public int count;
    public String[][] recipe;
    public int recipeSize;
    ItemStack oredictStack = ItemStack.EMPTY;

    public JsonRecipe(int size)
    {
        recipeSize = size == 9 ? 3 : 2;
        recipe = new String[recipeSize][recipeSize];
    }

    public boolean hasResult()
    {
        return result != "";
    }

    public NonNullList<ItemStack> getResult()
    {
        NonNullList<ItemStack> list = NonNullList.<ItemStack> create();
        list.add(new ItemStack(Item.getByNameOrId(result), count));
        return list;
    }

    public NonNullList<ItemStack> getIngredients(InventoryAI inventory)
    {
        NonNullList<ItemStack> ingredients = NonNullList.<ItemStack> create();
        for (int x = 0; x < recipe.length; x++)
        {
            for (int y = 0; y < recipe[x].length; y++)
            {
                if(recipe[x][y] == null) continue;
                if (inventory == null)
                {
                    oredictStack = OreDictionary.getOres(recipe[x][y]).get(0);
                }
                else
                {
                    oredictStack = inventory.oreDictMatch(OreDictionary.getOres(recipe[x][y]));
                }
                if (oredictStack.isEmpty())
                {
                    if(OreDictionary.getOres(recipe[x][y]).size() > 0)
                    {
                        oredictStack = OreDictionary.getOres(recipe[x][y]).get(0);
                    }
                    else
                    {
                        oredictStack = new ItemStack(Item.getByNameOrId(recipe[x][y]));
                    }
                }
                if (ingredients.stream().anyMatch(stack -> stack.isItemEqual(oredictStack)))
                {
                    ItemStack stackInList = ingredients.stream().filter(stack -> stack.isItemEqual(oredictStack)).findFirst().get();
                    stackInList.setCount(stackInList.getCount() + oredictStack.getCount());
                }
                else
                {
                    ingredients.add(oredictStack);
                }
            }
        }
        return ingredients;
    }

    public String toJson()
    {
        JsonObject obj = new JsonObject();
        JsonObject recipeObj = new JsonObject();
        recipeObj.addProperty("count", count);
        JsonArray recipeArr = new JsonArray();
        for (String[] sA : recipe)
        {
            JsonArray sArray = new JsonArray();
            for (String s : sA)
            {
                JsonElement el = s == null ? JsonNull.INSTANCE : new JsonPrimitive(s);
                sArray.add(el);
            }
            recipeArr.add(sArray);
        }
        recipeObj.add("recipe", recipeArr);
        obj.add(result, recipeObj);
        Gson gson = new GsonBuilder().create();
        return gson.toJson(obj);
    }

    public boolean isValid()
    {
        InventoryCrafting craftMatrix = new InventoryCrafting(new EmptyContainer(), recipeSize, recipeSize);
        int index = -1;
        for (int x = 0; x < recipeSize; x++)
        {
            for (int y = 0; y < recipeSize; y++)
            {
                index++;
                if (recipe[x][y] == null)
                    continue;
                craftMatrix.setInventorySlotContents(index, OreDictionary.getOres(recipe[x][y]).get(0));
            }
        }
        ItemStack resultCraft = CraftingManager.getInstance().findMatchingRecipe(craftMatrix, null);
        ItemStack result = new ItemStack(Item.getByNameOrId(this.result));
        return resultCraft.isItemEqual(result);
    }
}
