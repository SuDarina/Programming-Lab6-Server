package Commands;

import Collection.Worker;
import CollectionMannage.JsonReader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Iterator;

/***
 * Класс, реализующий команду remove_by_id id
 */

public class Remove extends Comands<Integer> {
    public byte[] bb;
    public Remove(){
        name = "remove_by_id";
    }
    public void remove(JsonReader jr, int id)  {
        StringBuilder answer  = new StringBuilder();;
        args = id;
        if (!(jr.idAll.contains(id))) {
            System.out.println("[нет worker'а с таким id]");
            answer.append("[нет worker'а с таким id]");
            bb = String.valueOf(answer).getBytes();
        }
        Iterator<Worker> i = jr.workers.iterator();
        while (i.hasNext()) {
            Worker worker = i.next();
            if (worker.getId() == id) {
                i.remove();
                jr.idAll.remove(jr.idAll.indexOf(id));
                System.out.println("[worker удалён]");
                answer.append("[worker удалён]");
                bb = String.valueOf(answer).getBytes();
                }
            }
    }
}