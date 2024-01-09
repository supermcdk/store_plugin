package hundeklemmen.nikolaialex05;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import javax.management.*;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Utils {
    public static String color(String input){
        if(input != null) {
            return ChatColor.translateAlternateColorCodes('&', input);
        }
        return "Fejl ved farveloading";
    }
    public static String[] color(String[] input){
        if(input != null) {
            List<String> listString = new ArrayList<>();
            for(String inputIndex : input) {
                listString.add(ChatColor.translateAlternateColorCodes('&', inputIndex));
            }

            return listString.toArray(new String[0]);
        }
        return new String[]{"Fejl ved farveloading"};
    }

    public static String get(String url, String authorization){
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);

            // add request header
            request.setHeader("User-Agent", "Mozilla/5.0");
            request.setHeader("authorization", authorization);

            HttpResponse response = client.execute(request);

            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch(IOException e){
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            return null;
        }
    }

    public static String post(String url, String obj, String authorization) {
        String payload = obj.toString();
        StringEntity entity = new StringEntity(payload,
                ContentType.APPLICATION_JSON);

        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);

            // add header
            post.setHeader("User-Agent", "Mozilla/5.0");
            post.setHeader("authorization", authorization);
            post.setEntity(entity);
            HttpResponse response = client.execute(post);
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
    public static String put(String url, String obj, String authorization) {
        String payload = obj.toString();
        StringEntity entity = new StringEntity(payload,
                ContentType.APPLICATION_JSON);

        try {
            HttpClient client = new DefaultHttpClient();
            HttpPut put = new HttpPut(url);

            // add header
            put.setHeader("User-Agent", "Mozilla/5.0");
            put.setHeader("authorization", authorization);
            put.setEntity(entity);
            HttpResponse response = client.execute(put);
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
    public static String delete(String url, String authorization) {

        try {
            HttpClient client = new DefaultHttpClient();
            HttpDelete delete = new HttpDelete(url);

            // add header
            delete.setHeader("User-Agent", "Mozilla/5.0");
            delete.setHeader("authorization", authorization);;
            HttpResponse response = client.execute(delete);
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static String getTPS() {

        String name1 = Bukkit.getServer().getClass().getPackage().getName();
        String version = name1.substring(name1.lastIndexOf('.') + 1);

        Class<?> mcsclass = null;

        DecimalFormat format = new DecimalFormat("##.##");

        Object si = null;
        Field tpsField = null;

        try {
            mcsclass = Class.forName("net.minecraft.server." + version + "." + "MinecraftServer");

            si = mcsclass.getMethod("getServer").invoke(null);

            tpsField = si.getClass().getField("recentTps");

        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | NoSuchFieldException | InvocationTargetException e) {
            e.printStackTrace();
        }

        double[] tps = null;

        try {
            tps = ((double[]) tpsField.get(si));

        } catch (IllegalArgumentException e) { e.printStackTrace(); } catch (IllegalAccessException e) { e.printStackTrace();}

        String response = format.format(tps[0]);
        if (StringUtils.substring(response, 0, 1).equalsIgnoreCase("2")||StringUtils.substring(response, 0, 1).equalsIgnoreCase("3")||StringUtils.substring(response, 0, 1).equalsIgnoreCase("4")||StringUtils.substring(response, 0, 1).equalsIgnoreCase("5")||StringUtils.substring(response, 0, 1).equalsIgnoreCase("6")) {
            response = "20,00";
        }
        return response;
    }

    public static double getProcessCpuLoad() {
        Double response = null;
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = ObjectName.getInstance("java.lang:type=OperatingSystem");
            AttributeList list = mbs.getAttributes(name, new String[]{ "ProcessCpuLoad" });

            if (list.isEmpty())     return Double.NaN;

            Attribute att = (Attribute)list.get(0);
            Double value  = (Double)att.getValue();
            if (value == -1.0)      return Double.NaN;
            response = ((int)(value * 1000) / 10.0);
            return response;
        } catch (MalformedObjectNameException | InstanceNotFoundException | ReflectionException e) {
            e.printStackTrace();
        }
        return response;
    };

    public static int getEnabledPlugins(Plugin[] plugins) {
        return (int) Stream.of(plugins).filter(Plugin::isEnabled).count();
    }
}
