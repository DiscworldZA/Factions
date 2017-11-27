package disc.mods.factions.faction.buildings;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import disc.mods.core.util.FileIOHelper;
import disc.mods.factions.ref.References;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class JsonBuilding
{
    public enum FunctionalBlock
    {
        Crafting, Storage;
    }

    private File jsonFile;

    public String[][][] buildingMap;
    public HashMap<String, Vec3d> functionalBlocks = new HashMap<String, Vec3d>();

    public JsonBuilding(Class<? extends FactionBuilding> factionClass)
    {
        jsonFile = new File("config/" + References.Mod.Id + "/buildings/" + factionClass.getSimpleName().replace("Building", "") + ".json");
        if (!jsonFile.exists())
        {
            FileIOHelper.CreateFile(jsonFile);
            FileIOHelper.WriteFile(jsonFile, "{}");
        }
        loadBuilding();
    }

    protected void loadBuilding()
    {
        String json = FileIOHelper.ReadFile(jsonFile);
        if (!json.isEmpty())
        {
            Gson gson = new GsonBuilder().create();
            JsonObject obj = gson.fromJson(json, JsonObject.class);
            JsonObject func = obj.getAsJsonObject("functional");
            for (Entry<String, JsonElement> e : func.entrySet())
            {
                JsonArray array = e.getValue().getAsJsonArray();
                Vec3d vector = new Vec3d(array.get(0).getAsInt(), array.get(1).getAsInt(), array.get(2).getAsInt());
                functionalBlocks.put(e.getKey(), vector);
            }
        }
    }

    public void save()
    {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(buildingMap);
        FileIOHelper.WriteFile(jsonFile, json);
    }

    public BlockPos getFunctionalBlockPos(FunctionalBlock block, BlockPos pos)
    {
        Vec3d vec = functionalBlocks.get(block.toString());
        return pos.add(vec.xCoord, vec.yCoord, vec.zCoord);
    }
}
