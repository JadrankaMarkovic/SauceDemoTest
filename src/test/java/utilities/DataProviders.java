package utilities;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class DataProviders {
    @DataProvider(name = "userInventoryItemsProvider")
    public static Object[][] dataProviderInventoryItems(){
        return readJson("testData/inventory_items.json",
                "inventory_items");
    }

    public static Object[][] readJson(String fileName, String jsonObj){
        File file = new File(fileName);
        JsonElement jsonElement = null;

        try{
            jsonElement = JsonParser.parseReader(new FileReader(file));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        assert jsonElement !=null;
        JsonArray jsonArray = jsonElement.getAsJsonObject().getAsJsonArray(jsonObj);

        Object[][] testData = new Object[jsonArray.size()][4];
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObj2 = jsonArray.get(i).getAsJsonObject();

            int id = jsonObj2.get("id").getAsInt();
            String name = jsonObj2.get("name").getAsString();
            String description = jsonObj2.get("description").getAsString();
            double price = jsonObj2.get("price").getAsDouble();

            testData[i][0] = id;
            testData[i][1] = name;
            testData[i][2] = description;
            testData[i][3] = price;
        }
//            for (Object[] data : testData) {
//                System.out.println("Data: " + Arrays.toString(data));
//            }
                    return testData;
    }
}
