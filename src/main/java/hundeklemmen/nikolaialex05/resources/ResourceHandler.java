package hundeklemmen.nikolaialex05.resources;

import hundeklemmen.nikolaialex05.SAStore;
import hundeklemmen.nikolaialex05.Utils;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import org.json.simple.JSONObject;

public class ResourceHandler {

    //private static final String API_URL = "https://api.superawesome.dk/storeapi/v2";
    public static final String API_URL = "http://localhost:80/storeapi/v2";

    private String authorization;

    private BukkitTask task;

    public ResourceHandler(String authorization) {
        this.authorization = authorization;
        Bukkit.getScheduler().runTaskAsynchronously(SAStore.instance, new Runnable() {
            @Override
            public void run() {
                try {
                    APISettings.fetchSettings(authorization);
                } catch (Exception e) {
                    Bukkit.getLogger().warning("Failed to fetch settings from API");
                    APISettings.setPostResources(10000);
                    e.printStackTrace();
                    return;
                }

                task = Bukkit.getScheduler().runTaskTimerAsynchronously(SAStore.instance, () -> {
                    try {
                        postResources();
                    } catch (Exception e) {
                        task.cancel();
                        Bukkit.getLogger().info("Failed to log resources, stopping service!");
                    }
                }, 0, 20L * APISettings.getPostResources());
            }
        });
    }

    public void postResources() throws Exception {

        JSONObject json = new JSONObject();
        json.put("tps", Utils.getTPSInt());
        json.put("cpu", Utils.getProcessCpuLoad());
        json.put("maxRam", Utils.getMaxRam());
        json.put("usedRam", Utils.getUsedRam());
        json.put("freeRam", Utils.getFreeRam());
        json.put("chunks_loaded", Utils.getLoadedChunks());
        json.put("entities", Utils.getEntities());
        json.put("plugins", Utils.getEnabledPlugins(Bukkit.getPluginManager().getPlugins()));

        String svar = Utils.post(API_URL + "/resources", json.toJSONString(), authorization);
        if(svar == null) {
            throw new Exception("Failed to request to '" + API_URL + "/resources'");
        }
    }
}
