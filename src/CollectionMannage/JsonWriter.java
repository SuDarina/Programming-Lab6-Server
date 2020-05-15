package CollectionMannage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/***
 * Класс, реализующий запись коллекции в в файл в формате json
 */

public class JsonWriter {
    JsonReader x;

    public JsonWriter(JsonReader x) {
        this.x = x;
    }

    public void write() throws  IOException {

            final Gson Gson = new GsonBuilder().setPrettyPrinting().create();
            String json = Gson.toJson(x.workers);
            PrintWriter pw = new PrintWriter(x.fileName);
            pw.print(json);
            pw.close();
    }

    public void clean() {
        try {
            FileWriter fstream1 = new FileWriter(x.fileName);// конструктор с одним параметром - для перезаписи
            BufferedWriter out1 = new BufferedWriter(fstream1); //  создаём буферезированный поток
            out1.write(""); // очищаем, перезаписав поверх пустую строку
            out1.close(); // закрываем
        } catch (Exception e) {
            System.err.println("Error in file cleaning: " + e.getMessage());
        }
    }
}

