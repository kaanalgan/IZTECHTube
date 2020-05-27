package iztechtube.dataformatter;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import iztechtube.video.Video;

import java.lang.reflect.Type;

import java.util.*;

public class JSONFormatter implements IFormatter<Map<Integer, Video>>{

    private final Gson gson;

    public JSONFormatter() {
        this.gson = new GsonBuilder()
                .setDateFormat("EEE, dd MMM yyyy HH:mm:ss ZZZ")
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes field) {
                        if (field.getDeclaringClass() == Observable.class && field.getName().equals("changed")) {
                            return true;
                        }
                        else return field.getDeclaringClass() == Observable.class && field.getName().equals("obs");
                    }
                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .setPrettyPrinting()
                .create();
    }

    public String toFormat(Video video){
        return gson.toJson(video);
    }

    public Video toVideoObject(String video){
        return gson.fromJson(video,Video.class);
    }

    public Map<Integer,Video> toObject(String videoMap){
        Type videoMapType = new TypeToken<Map<Integer,Video>>() {}.getType();
        return gson.fromJson(videoMap,videoMapType);
    }

    public String toFormat(Map<Integer,Video> videoMap){
        return gson.toJson(videoMap);
    }

}
