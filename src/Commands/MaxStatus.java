package Commands;

import Collection.Worker;
import CollectionMannage.JsonReader;

import java.io.BufferedWriter;
import java.io.IOException;

/***
 * Класс, реализующий команду max_by_status
 */

public class MaxStatus extends Comands{
    public MaxStatus(){
        name = "max_by_status";
    }
    public byte[] bb;
    public void maxStatus(JsonReader jr) throws IOException {
        String answer;
        int max = 0;
        Worker maxWorker = new Worker();
        for(Worker worker : jr.workers){
            if (worker.getStatus().toString().length() > max) {
                max = worker.getStatus().toString().length();
                maxWorker = worker;
            }
        }
        System.out.println(maxWorker.toString());
        answer = maxWorker.toString();
        bb = answer.getBytes();
    }
}
