package project;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import project.Characters.Character;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonManager {
    public static TypeReference<List<User>> userTypeReference = new TypeReference<>() {};
    public static TypeReference<List<Character>> characterTypeReference = new TypeReference<>() {};
    private ObjectMapper objectMapper;
    private String path;
    private File file;

    public JsonManager(String path) {
        this.path = path;
        file = new File(path);
        objectMapper = new ObjectMapper();
    }

    public <T> void writeArray( List<T> arr) throws IOException {
        objectMapper.writeValue(file, arr);
    }

    public <T> List<T> readArray(TypeReference<List<T>> typeReference) throws IOException {
        return objectMapper.readValue(file, typeReference);
    }

    public void writeObject(Object obj) throws IOException {
        objectMapper.writeValue(file, obj);
    }
    public <T> T readObject(Class<T> object) throws IOException {
        return objectMapper.readValue(file, object);
    }

    public <T extends Enum<T>> T readEnum(String value, Class<T> enumClass) throws IOException {
        return objectMapper.readValue("\"" + value + "\"", enumClass);
    }

    public void writeEnum(Enum<?> value) throws IOException {
        objectMapper.writeValue(file, value);
    }
}

