package Commands;

import Collection.Worker;
import CollectionMannage.JsonReader;
import Exceptions.FieldException;

import java.io.BufferedWriter;
import java.io.IOException;

/***
 * Класс, реализующий команду update_by_id id
 */


public class Update extends Comands<Worker> {
    public byte[] bb;
    public Update(){
        name = "update_by_id";
    }

    public void update(JsonReader jr, int id) throws IOException{
        StringBuilder answer = new StringBuilder();
        Remove remove = new Remove();
        int check = 0;
        Add add = new Add(jr);
        add.worker = args;
        System.out.println(args.toString());

        if (jr.idAll.contains(id)){

            remove.remove(jr, id);
            add.add_date();
            add.add();
            check++;
            jr.idAll.add(id);
        }
        if (!(check == 0)) {
            answer.append("[worker обновлен]");
            bb = String.valueOf(answer).getBytes();
        }else{
            System.out.println("[Нет worker'a с таким id]");
            answer.append("[Нет worker'a с таким id]");
            bb = String.valueOf(answer).getBytes();

        }
    }
}
