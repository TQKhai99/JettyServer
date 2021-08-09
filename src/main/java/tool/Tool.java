package tool;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Tool {
    public static JsonObject getBodyReq (HttpServletRequest req) throws IOException {
        Reader reader = req.getReader();
        JsonObject json = (JsonObject) new JsonParser().parse(reader);
        return json;
    }

    public static List<String> getListFromJson(JsonArray json){
        List<String> res = new ArrayList<>();
        if(json == null) {
            return res;
        }
        for(int i = 0; i < json.size(); i++){
            res.add(json.get(i).getAsString());
        }
        return res;
    }
}
