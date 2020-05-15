package Commands;

import CollectionMannage.JsonReader;
import CollectionMannage.JsonWriter;
import Exceptions.FieldException;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/***
 * Класс, реализующий команду save
 */

public class Save extends Comands {
    public Save(){
        name = "save";
    }
    public void save(JsonReader jr) throws ParseException, FieldException, IOException {
        JsonWriter jw = new JsonWriter(jr);
        jw.write();
    }
}
