package Commands;

import Collection.Worker;
import CollectionMannage.JsonReader;
import Exceptions.FieldException;
import Exceptions.FileIsEmptyException;
import org.json.simple.parser.ParseException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/***
 * Класс, реализующий команду remove_greater
 */

public class RemoveGreater extends Comands<Worker>{
    Worker worker;
    public byte[] bb;
    int j = 0;
    public RemoveGreater(){
        name = "remove_greater";
        args = worker;
    }
    public void removeG(JsonReader jr) throws  FieldException, IOException {
        StringBuilder answer = new StringBuilder();
        Add add = new Add(jr);
        add.worker = args;
        add.add_id();
        add.add_date();
        jr.idAll.remove(jr.idAll.indexOf(add.worker.getId()));

        Comparator comparator = jr.workers.comparator();


        PriorityQueue<Worker> workers;

        int k = jr.workers.size();
        workers = jr.workers.stream().filter((o1) -> comparator.compare(o1, add.worker) > 0).collect(Collectors.toCollection(PriorityQueue::new));
        j = workers.size();
        jr.workers.clear();
        jr.workers.addAll(workers);
        jr.idAll = jr.workers.stream().map(x -> x.getId()).collect(Collectors.toCollection(ArrayList::new));

        if (!(j==0))
            answer.append("удалено " + (k-j) + " " + "worker");
        else{
            answer.append("[таких worker'ов нет]");
        }

        bb = String.valueOf(answer).getBytes();
    }
}
