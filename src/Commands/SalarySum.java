package Commands;

import Collection.Worker;
import CollectionMannage.JsonReader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.stream.Stream;

/***
 * Класс, реализующий команду sum_of_salary
 */

public class SalarySum extends Comands{
    public byte[] bb;
    public SalarySum(){
        name = "sum_of_salary";
    }
    long sum = 0;
    public void salarySum(JsonReader jr)  {
        StringBuilder answer = new StringBuilder();
        sum = jr.workers.stream().map(x -> x.getSalary()).reduce((s1, s2) -> s1+s2).orElse((long)0);

        System.out.println("Сумма salary всех worker'ов: "+sum);
        answer.append("Сумма salary всех worker'ов: ");
        answer.append(sum);
        answer.append("\n");
        bb = String.valueOf(answer).getBytes();

    }
}
