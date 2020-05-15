package Commands;

import Collection.Worker;
import CollectionMannage.JsonReader;
import Exceptions.FieldException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/***
 * Класс, реализующий команду remove_lower
 */

public class RemoveLower extends Comands<Worker> {
    Worker worker1;
    public byte[] bb;
    int j = 0;
    public Add add;
    public RemoveLower(){
        name = "remve_lower";
        args = worker1;
    }

    public void removeL(JsonReader jr) throws FieldException, IOException {
        StringBuilder answer = new StringBuilder();
        add = new Add(jr);
        add.add_id();
        add.worker = args;
        add.add_date();

        Comparator comparator = jr.workers.comparator();


        PriorityQueue<Worker> workers;

        int k = jr.workers.size();
        workers = jr.workers.stream().filter((o1) -> comparator.compare(o1, add.worker) < 0).collect(Collectors.toCollection(PriorityQueue::new));
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
