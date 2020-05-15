package Commands;

import Collection.Worker;
import CollectionMannage.JsonReader;
import Exceptions.FieldException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Comparator;


/***
 * Класс, реализующий команду add_if_min
 */

public class AddIfMin extends Comands<Worker> {

    public Add add1;
    public Worker worker;
    public byte[] bb;

    public AddIfMin(){
        name = "add_if_min";
        args = worker;
    }

    public void addIfMin(JsonReader jr) throws FieldException, IOException {
        StringBuilder answer = new StringBuilder();

        add1 = new Add(jr);
        add1.worker = args;
        add1.add_id();
        add1.add_date();

        Comparator comparator = jr.workers.comparator();
        long min = jr.workers.stream().filter((o1) -> comparator.compare(o1, add1.worker) < 0).count();
        if (min == 0)
            add1.add();
         else {
            jr.idAll.remove ((Integer) add1.worker.getId());
            System.out.println("[worker не добавлен]");
            answer.append("\n[worker не добавлен]");
            add1.bb = String.valueOf(answer).getBytes();
        }
    }

}


