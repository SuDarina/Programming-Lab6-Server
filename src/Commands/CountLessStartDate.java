package Commands;

import Collection.Worker;
import CollectionMannage.DateComparator;
import CollectionMannage.JsonReader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;

/***
 * Класс, реализующий команду count_less_than_start_date {startDate}
 */

public class CountLessStartDate extends Comands<LocalDateTime> {
    int i;

    JsonReader jr;

    public LocalDateTime ldt;
    public byte[] bb;


    public CountLessStartDate(JsonReader jr) {
        this.jr = jr;
        name = "count_less_than_start_date";
    }


    public void countDateTime()  {
        StringBuilder answer = new StringBuilder();

        args = ldt;
        DateComparator dc = new DateComparator();
        PriorityQueue<LocalDateTime> l_ldt;
        l_ldt = jr.workers.stream().map(x -> x.getStartDate()).filter(o -> dc.compare(o, ldt) < 0).collect(Collectors.toCollection(PriorityQueue::new));
        i = l_ldt.size();
        System.out.println(ldt);

        answer.append(ldt);
        answer.append("\n");

        System.out.println("количество элементов: " + i);

        answer.append("количество элементов: ");
        answer.append(i);

        bb = String.valueOf(answer).getBytes();
    }
}

