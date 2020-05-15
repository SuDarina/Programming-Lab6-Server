package Commands;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/***
 * Класс, реализующий команду execute_script {scriptFile}
 */

public class ExecuteScript extends Comands<String> {
    public byte[] bb;
    public ExecuteScript(){
        name = "execute_script";
    }
    int i;
    String comand = "empty";
    public List<String> comands = new ArrayList<>();

    public void executeScript(String scriptFile) throws IOException {
        StringBuilder answer = new StringBuilder();

        args = scriptFile;
        FileInputStream fis = new FileInputStream(scriptFile);
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuilder sb = new StringBuilder();
        System.out.println("Содержимое скрипта:");

        answer.append("Содержимое скрипта:\n");
        while ((i = bis.read()) != -1) {
            if (i != 10) {
                sb.append((char) i);
            } else {
                String s = String.valueOf(sb);
                if (!(s.equals(""))) {
                    comand = s;
                    comands.add(s);
                    System.out.println(comand);
                    answer.append(comand);
                    answer.append("\n");
                    sb.setLength(0);
                }
            }
        }
        String s = String.valueOf(sb);
        if (!(s.equals(""))) {
            comand = s;
            comands.add(s);
            System.out.println(comand);
            answer.append(comand);
            answer.append("\n");
        }if(comand.equals("empty")) {
            System.out.println("[файл пустой]");
            answer.append("[файл пустой]\n");
        }
        System.out.println("\nВыполнение:");

        answer.append("Выполнение:\n");
        bb = String.valueOf(answer).getBytes();
        fis.close();


    }
}
// execute_script /Users/dariasupriadkina/IdeaProjects/LabaProga_6/Server/src/Script