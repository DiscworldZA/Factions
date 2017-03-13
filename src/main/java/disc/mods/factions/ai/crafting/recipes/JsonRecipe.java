package disc.mods.factions.ai.crafting.recipes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import disc.mods.factions.registry.IRegisterable;

public class JsonRecipe
{
    public String result;
    public int count;
    public String[][] recipe;
    public int recipeSize;

    public JsonRecipe(int size)
    {
        recipeSize = size == 9 ? 3 : 2;
        recipe = new String[recipeSize][recipeSize];
    }

    public boolean hasResult()
    {
        return result != "";
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

    public static List<JsonRecipe> fromListJson(String Json)
    {
        List<JsonRecipe> recipes = new ArrayList<JsonRecipe>();
        Gson gson = new GsonBuilder().create();
        JsonObject objArray = gson.fromJson(Json, JsonObject.class);
        Iterator<Entry<String, JsonElement>> it = objArray.entrySet().iterator();
        while (it.hasNext())
        {
            recipes.add(JsonRecipe.fromJsonEntry(it.next()));
        }
        return recipes;
    }

    public static JsonRecipe fromJsonEntry(Entry<String, JsonElement> entry)
    {
        JsonObject obj = entry.getValue().getAsJsonObject();
        if (obj.has("recipe"))
        {
            JsonArray recipeArr = obj.get("recipe").getAsJsonArray();
            JsonRecipe recipe = new JsonRecipe(recipeArr.size() * 3);
            recipe.count = obj.get("count").getAsInt();
            recipe.result = entry.getKey();
            for (int x = 0; x < recipeArr.size(); x++)
            {
                JsonArray sArray = recipeArr.get(x).getAsJsonArray();
                for (int y = 0; y < sArray.size(); y++)
                {
                    recipe.recipe[x][y] = sArray.get(y) instanceof JsonNull ? null : sArray.get(y).getAsString();
                }
            }
            return recipe;
        }
        return new JsonRecipe(0);
    }
}
