package hundeklemmen.nikolaialex05;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.Classes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import hundeklemmen.nikolaialex05.Skript.*;
import hundeklemmen.nikolaialex05.Commands.SuperAwesomeStoreCommand;
import hundeklemmen.nikolaialex05.classes.betaling;
import hundeklemmen.nikolaialex05.classes.id;
import hundeklemmen.nikolaialex05.classes.pakke;
import hundeklemmen.nikolaialex05.classes.pakkeNavn;
import hundeklemmen.nikolaialex05.events.players;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import ch.njol.skript.util.Getter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.scheduler.BukkitRunnable;

import static ch.njol.skript.registrations.EventValues.registerEventValue;

public class SAStore extends JavaPlugin {

    private static DecimalFormat format = new DecimalFormat("##.##");

    public static SAStore instance;
    public static SkriptAddon addonInstance;
    public static String prefix = "&8[&aSuperAwesome Store&8]&r";
    public static File configFile;
    public static FileConfiguration config;
    public static File spigotFile;
    public static FileConfiguration spigot;
    public static long lastPaymentFetch = 0;

    public static String authorization = "";

    public static Logger log;

    @Override
    public void onEnable(){
        instance = this;
        log = getLogger();
        loadSpigot();
        getCommand("sastore").setExecutor(new SuperAwesomeStoreCommand());
        getCommand("sastore").setTabCompleter(new TabCompleteListener());
        getServer().getPluginManager().registerEvents(new players(), this);

        addonInstance = Skript.registerAddon(instance);
        createConfig();

        if(config.get("serverkey") != null) {
            overwrite();
        }
        //if(Bukkit.getPluginManager().getPlugin("Skript") != null && !config.getString("apikey").equalsIgnoreCase("")) {
        if(Bukkit.getPluginManager().getPlugin("Skript") != null && spigot.getBoolean("settings.bungeecord") == true && (config.getString("apikey") != null && !config.getString("apikey").equalsIgnoreCase(""))) {
            //Skript.registerEffect(anmod.class, "superpay anmod %player% om %number% emeralder for %string%");
            Skript.registerEffect(acceptqueue.class, "superawesome store accepter betaling %id%");
            Skript.registerEffect(declinequeue.class, "superawesome store afvis betaling %id% med grunden %string%");

            Classes.registerClass(new ClassInfo<>(id.class, "id")
                    .defaultExpression(new EventValueExpression<>(id.class))
                    .user("id")
                    .name("id")
                    .description("betalings id")
                    .parser(new Parser<id>() {

                        @Override
                        public String getVariableNamePattern() {
                            return ".+";
                        }

                        @Override
                        public id parse(String arg0, ParseContext arg1) {
                            return null;
                        }

                        @Override
                        public String toString(id arg0, int arg1) {
                            return arg0.toString();
                        }

                        @Override
                        public String toVariableNameString(id arg0) {
                            return arg0.toString();
                        }

                    }));

            Classes.registerClass(new ClassInfo<>(pakke.class, "pakke")
                    .defaultExpression(new EventValueExpression<>(pakke.class))
                    .user("pakke")
                    .name("pakke")
                    .description("pakke id")
                    .parser(new Parser<pakke>() {

                        @Override
                        public String getVariableNamePattern() {
                            return ".+";
                        }

                        @Override
                        public pakke parse(String arg0, ParseContext arg1) {
                            return null;
                        }

                        @Override
                        public String toString(pakke arg0, int arg1) {
                            return arg0.toString();
                        }

                        @Override
                        public String toVariableNameString(pakke arg0) {
                            return arg0.toString();
                        }

                    }));
            Classes.registerClass(new ClassInfo<>(pakkeNavn.class, "pakkenavn")
                    .defaultExpression(new EventValueExpression<>(pakkeNavn.class))
                    .user("pakkenavn")
                    .name("pakkenavn")
                    .description("pakke navn")
                    .parser(new Parser<pakkeNavn>() {

                        @Override
                        public String getVariableNamePattern() {
                            return ".+";
                        }

                        @Override
                        public pakkeNavn parse(String arg0, ParseContext arg1) {
                            return null;
                        }

                        @Override
                        public String toString(pakkeNavn arg0, int arg1) {
                            return arg0.toString();
                        }

                        @Override
                        public String toVariableNameString(pakkeNavn arg0) {
                            return arg0.toString();
                        }

                    }));


            Skript.registerEvent("superawesome store betaling", SimpleEvent.class, new Class[]{betalingEvent.class}, "superawesome store betaling");
            registerEventValue(betalingEvent.class, OfflinePlayer.class, new Getter<OfflinePlayer, betalingEvent>() {
                @Override
                public OfflinePlayer get(betalingEvent event) {
                    return event.getPlayer();
                }
            }, 0);
            registerEventValue(betalingEvent.class, String.class, new Getter<String, betalingEvent>() {
                @Override
                public String get(betalingEvent event) {
                    return event.getPakke();
                }
            }, 0);
            registerEventValue(betalingEvent.class, Number.class, new Getter<Number, betalingEvent>() {
                @Override
                public Number get(betalingEvent event) {
                    return event.getAmount();
                }
            }, 0);
            registerEventValue(betalingEvent.class, id.class, new Getter<id, betalingEvent>() {
                @Override
                public id get(betalingEvent event) {
                    return new id(event.getId());
                }
            }, 0);
            registerEventValue(betalingEvent.class, pakke.class, new Getter<pakke, betalingEvent>() {
                @Override
                public pakke get(betalingEvent event) {
                    return new pakke(event.getPakke());
                }
            }, 0);
            registerEventValue(betalingEvent.class, pakkeNavn.class, new Getter<pakkeNavn, betalingEvent>() {
                @Override
                public pakkeNavn get(betalingEvent event) {
                    return new pakkeNavn(event.getPakkeNavn());
                }
            }, 0);

            new BukkitRunnable() {
                @Override
                public void run() {
                    fetchPayments(false);
                }
            }.runTaskTimer(SAStore.instance, 20L, 20L * 30);
        }
    }


    @Override
    public void onDisable(){

    }

    public static void fetchPayments(boolean isSocket) {
        if(SAStore.lastPaymentFetch > new Date().getTime() - 5000) return;
        SAStore.lastPaymentFetch = new Date().getTime();

        Bukkit.getScheduler().runTaskAsynchronously(SAStore.instance, new Runnable() {
            @Override
            public void run() {
                String svar = Utils.get("https://api.superawesome.dk/storeapi/purchases", SAStore.authorization);
                Gson gson = new Gson();
                Type listType = new TypeToken<List<betaling>>(){}.getType();
                List<betaling> betalinger = gson.fromJson(svar, listType);

                Bukkit.getScheduler().scheduleSyncDelayedTask(SAStore.instance, new Runnable() {
                    @Override
                    public void run() {
                        for (betaling betal : betalinger) {
                            Bukkit.getServer().getPluginManager().callEvent(
                                    new betalingEvent(
                                            Bukkit.getOfflinePlayer(
                                                    UUID.fromString(betal.getUuid())
                                            ),
                                            betal.getPakke(),
                                            betal.getPakkeNavn(),
                                            betal.getAmount(),
                                            betal.getId()
                                    )
                            );
                        };
                    }
                }, 0);
            }
        });
    }
    public static void createConfig() {
        configFile = new File(SAStore.instance.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            SAStore.instance.saveResource("config.yml", false);
        }
        config = new YamlConfiguration();
        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        if(config.contains("apikey") && config.getString("apikey").toString().length() != 0) {
            authorization = config.getString("apikey");
        }
    }

    public static void overwrite() {
        configFile = new File(SAStore.instance.getDataFolder(), "config.yml");
        SAStore.instance.saveResource("config.yml", true);
    }

    public static void loadSpigot() {
        spigotFile = new File("./", "spigot.yml");
        spigot = new YamlConfiguration();
        try {
            spigot.load(spigotFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
