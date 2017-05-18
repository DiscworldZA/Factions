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

import scala.reflect.internal.Trees.This;

public class JsonRecipeFactory
{
    public static List<JsonRecipe> fromListJson(String Json)
    {
        List<JsonRecipe> recipes = new ArrayList<JsonRecipe>();
        Gson gson = new GsonBuilder().create();
        JsonObject objArray = gson.fromJson(Json, JsonObject.class);
        Iterator<Entry<String, JsonElement>> it = objArray.entrySet().iterator();
        while (it.hasNext())
        {
            recipes.add(JsonRecipeFactory.fromJsonEntry(it.next()));
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
            if (recipe.isValid())
            {
                return recipe;
            }
            else
            {
                new Exception("Invalid Recipe Trying to Load: " + recipe.toJson()).printStackTrace();
            }
        }
        return new JsonRecipe(0);
    }
}
