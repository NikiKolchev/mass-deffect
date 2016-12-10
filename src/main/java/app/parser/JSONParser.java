package app.parser;

import app.io.interfaces.FileIO;
import app.parser.interfaces.FileParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Component(value = "JSONParser")
public class JSONParser implements FileParser {

    private Gson gson;

    @Autowired
    private FileIO fileIO;

    public JSONParser() {
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }


    @Override
    public <T> T read(Class<T> objectClass, String file) throws IOException, JAXBException {
        String jsonObject = this.fileIO.read(file);
        T object = this.gson.fromJson(jsonObject, objectClass);

        return object;
    }

    @Override
    public <T> void write(T object, String file) throws FileNotFoundException {
        String jsonObject = this.gson.toJson(object);
        this.fileIO.write(file, jsonObject);
    }
}
