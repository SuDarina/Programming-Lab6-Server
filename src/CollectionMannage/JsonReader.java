package CollectionMannage;


import Collection.*;
import Exceptions.FieldException;
import Exceptions.NotUniqueId;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/***
 * Класс, реализующий считывание коллекции из json-файла
 */

public class JsonReader {

    JSONParser parser = new JSONParser();
    WorkerComparator workerComparator = new WorkerComparator();
    public PriorityQueue<Worker> workers = new PriorityQueue<>(workerComparator);
    public List<Integer> idAll = new ArrayList<>();
    public String fileName = System.getenv("FILENAME");
    File file = new File(fileName);

    public void read(){

        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader(fileName));
            for (Object o : a) {
                Worker worker1 = new Worker();
                JSONObject worker = (JSONObject) o;

                String name = (String) worker.get("name");
                worker1.setName(name);

                long id = (long) worker.get("id");


                idAll.add((int) id);
                for (int i = 0; i < idAll.size()-1; i++) {
                    for (int j = i+1; j < idAll.size(); j++) {
                        if(idAll.get(i) == idAll.get(j)) throw new NotUniqueId("Not unique id!");
                    }
                }
                worker1.setId((int) id);

                JSONObject coordinates = (JSONObject) worker.get("coordinates");
                Coordinates coord = new Coordinates();

                long x = (long) coordinates.get("x");
                coord.setX((Integer.parseInt(String.valueOf(x))));
                double y = (double) coordinates.get("y");
                coord.setY((float) y);
                worker1.setCoordinates(coord);

                long salary = (long) worker.get("salary");
                worker1.setSalary(salary);

                String position = (String) worker.get("position");
                try {
                    Position pos = Position.valueOf(position);
                    worker1.setPosition(pos);
                }catch(NullPointerException e){
                    worker1.setPosition(null);
                }

                String status = (String) worker.get("status");
                Status stat = Status.valueOf(status);
                worker1.setStatus(stat);

                JSONObject person = (JSONObject) worker.get("person");
                double height = (double) person.get("height");
                String passportID = (String) person.get("passportID");
                Person person1 = new Person();
                person1.setHeight(height);
                person1.setPassportID(passportID);
                worker1.setPerson(person1);

                JSONObject sd = (JSONObject) worker.get("startDate");
                JSONObject date = (JSONObject) sd.get("date");
                JSONObject time = (JSONObject) sd.get("time");
                long year = (long) date.get("year");
                long month = (long) date.get("month");
                long day = (long) date.get("day");
                long hour = (long) time.get("hour");
                long minute = (long) time.get("minute");
                LocalDateTime startDate = LocalDateTime.of((int)year, (int)month, (int)day, (int)hour, (int)minute);
                worker1.setStartDate(startDate);
                worker1.setCreationDate();

                workers.add(worker1);
            }

        } catch (FileNotFoundException e) {
            if(file.exists()){
                Rights rights = new Rights();
                rights.setRights(file);

                read();
            }else{
                System.out.println("[файла не существует]");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("Неправильный формат файла");
            System.exit(0);
        } catch (FieldException e) {
            System.out.println("Поменяйте значения полей в файле json.json и перезапустите прилажение " + "\n" +
                    "Проверьте, чтобы значения полей были:"+ "\n" +
                    "id: Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически\n" +
                    "name: Поле не может быть null, Строка не может быть пустой\n" +
                    "coordinates: Поле не может быть null\n" +
                    "     x: Значение поля должно быть больше -329, Поле не может быть null\n" +
                    "     y: Значение поля должно быть больше -805\n"+
                    "    private long salary: Значение поля должно быть больше 0\n" +
                    "    private Position position: Поле может быть null\n" +
                    "    private Status status: Поле не может быть null\n" +
                    "    private Person person: Поле не может быть null");
            System.exit(0);
        } catch (NotUniqueId e) {
            System.out.println("Значение id в json.json должно быть уникальным, поменяйте значение данного поля");
            System.exit(0);
        }
    }

    public int generateId() {
        int max_id = 0;
        for (int i : idAll) {
            if (i > max_id)
                max_id = i;
        }
        System.out.println(max_id+1);

        System.out.println(idAll.toString());

        return max_id+1;

    }

}


