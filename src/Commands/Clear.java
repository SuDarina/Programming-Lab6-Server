package Commands;

import CollectionMannage.JsonReader;
import Exceptions.FieldException;
import Exceptions.FileIsEmptyException;
import org.json.simple.parser.ParseException;

import java.io.BufferedWriter;
import java.io.IOException;

/***
 * Класс, реализующий команду clear
 */

public class Clear extends Comands {
    public byte [] bb;
    StringBuilder answer = new StringBuilder();
    public Clear(){
        name = "clear";
    }
    public void clear(JsonReader jr) {
        while(!(jr.workers.isEmpty())) {
            jr.workers.remove();
        }
        System.out.println("[коллекция удалена]");
        answer.append("[коллекция удалена]");
        bb = String.valueOf(answer).getBytes();
    }
}
