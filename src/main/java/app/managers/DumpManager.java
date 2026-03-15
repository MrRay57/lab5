package app.managers;

import app.models.Movie;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeMap;

/**
 * Менеджер для сериализации и десериализации коллекции в/из формата JSON с использованием Gson.
 */
public class DumpManager {
    private final Gson gson;

    public DumpManager() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(ZonedDateTime.class, new JsonSerializer<ZonedDateTime>() {
                    @Override
                    public JsonElement serialize(ZonedDateTime src, Type typeOfSrc, JsonSerializationContext context) {
                        return new JsonPrimitive(src.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
                    }
                })
                .registerTypeAdapter(ZonedDateTime.class, new JsonDeserializer<ZonedDateTime>() {
                    @Override
                    public ZonedDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return ZonedDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_ZONED_DATE_TIME);
                    }
                })
                .setPrettyPrinting()
                .create();
    }

    public TreeMap<Long, Movie> readCollection(String json) {
        if (json == null || json.trim().isEmpty()) {
            return new TreeMap<>();
        }
        try {
            Type collectionType = new TypeToken<TreeMap<Long, Movie>>(){}.getType();
            TreeMap<Long, Movie> collection = gson.fromJson(json, collectionType);
            if (collection == null) {
                return new TreeMap<>();
            }
            return collection;
        } catch (JsonSyntaxException e) {
            System.err.println("Ошибка синтаксиса JSON в файле. Коллекция будет пустой.");
            return new TreeMap<>();
        }
    }

    public String writeCollection(TreeMap<Long, Movie> collection) {
        return gson.toJson(collection);
    }
}