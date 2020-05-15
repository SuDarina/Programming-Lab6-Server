package Commands;

import Collection.Worker;
import CollectionMannage.JsonReader;
import CollectionMannage.WorkerComparator;
import Exceptions.FileIsEmptyException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Stream;


/***
 * Класс, реализующий команду show
 */

public class Show extends Comands {

    public byte[] bb;

    public Show() {
        name = "show";
    }

    public void show(JsonReader js) {
        WorkerComparator workerComparator = new WorkerComparator();
        Queue<Worker> pq = new PriorityQueue<>(workerComparator);
        String answer;

        try {
            StringBuilder sb = new StringBuilder();
            pq.addAll(js.workers);
            while (!(pq.isEmpty())) {
                answer = pq.poll().toString();
                sb.append(answer);
                sb.append("\n");


            }
            sb.deleteCharAt(sb.lastIndexOf("\n"));
            bb = (String.valueOf(sb).getBytes());


            if (js.workers.isEmpty()) throw new FileIsEmptyException();

        } catch (FileIsEmptyException e) {
            System.out.println("[коллекция пустая]");
            bb = ("[коллекция пустая]").getBytes();
        }
    }
}
