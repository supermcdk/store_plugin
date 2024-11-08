package hundeklemmen.nikolaialex05.resources;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import hundeklemmen.nikolaialex05.Utils;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;

import static hundeklemmen.nikolaialex05.resources.ResourceHandler.API_URL;

public class APISettings {

    @Setter
    @Getter
    private static int postResources;

    public static void fetchSettings(String authorization) throws Exception {

        String svar = Utils.get(API_URL + "/settings", authorization);
        if(svar == null) {
            throw new Exception("Failed to fetch settings");
        }


        Gson gson = new Gson();
        Type gsonType = new TypeToken<SettingsEndpoint>(){}.getType();
        SettingsEndpoint settings = gson.fromJson(svar, gsonType);

        if(settings == null || settings.getIntervals() == null || settings.getIntervals().getResources() == 0) {
            throw new Exception("Failed to parse settings");
        }

        postResources = settings.getIntervals().getResources();
    }
}
