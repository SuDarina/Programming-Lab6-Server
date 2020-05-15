package Commands;

import Collection.Worker;
import CollectionMannage.JsonReader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/***
 * Класс, реализующий команду info
 */

public class Info {
    LocalDateTime minDate;
    public byte[] bb;

    public void info(JsonReader jr) throws IOException {
        String answer;
        LocalDateTime ldt = jr.workers.element().getStartDate();
        for (Worker worker : jr.workers) {
            if (worker.getStartDate().getYear() < ldt.getYear())
                minDate = worker.getStartDate();
            if (worker.getStartDate().getYear() == ldt.getYear()) {
                if (worker.getStartDate().getDayOfYear() < ldt.getDayOfYear())
                    minDate = worker.getStartDate();
                if (worker.getStartDate().getDayOfYear() == ldt.getDayOfYear()) {
                    if (worker.getStartDate().getHour() < ldt.getHour())
                        minDate = worker.getStartDate();
                    if (worker.getStartDate().getHour() == ldt.getHour()) {
                        if (worker.getStartDate().getMinute() <= ldt.getMinute()) {
                            minDate = worker.getStartDate();
                        }
                    }
                }
            }
        }
        System.out.println("Тип коллекции: " + jr.workers.getClass().toString() + "\nРазмер коллекции: " + jr.workers.size()
                + "\nДата инициализации: " + minDate);
        answer = ("Тип коллекции: " + jr.workers.getClass().toString() + "\nРазмер коллекции: " + jr.workers.size()
                + "\nДата инициализации: " + minDate);
        bb = answer.getBytes();
    }
}
