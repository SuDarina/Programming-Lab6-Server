package Commands;

import Collection.Coordinates;
import Collection.Person;
import Collection.Worker;
import CollectionMannage.JsonReader;
import Exceptions.FieldException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

/***
 * Класс, реализующий команду add
 */

public class Add extends Comands<Worker> {
    JsonReader js;
    public byte[] bb;

    public Add(JsonReader js) {
        name = "add";
        args = worker;
        this.js = js;
    }


    Scanner scr = new Scanner(System.in);
    StringBuilder answer = new StringBuilder();

    public Worker worker = new Worker();
    Coordinates coordinates = new Coordinates();
    Person person = new Person();

    public void add_id() throws FieldException, IOException {
        System.out.println("id нового Worker'a:");
        answer.append("id нового Worker'a:\n");
        worker.setId(js.generateId());
        answer.append(worker.getId());
        answer.append("\n");
        js.idAll.add(worker.getId());
    }

    //        public void add_name(){
//            System.out.println("Введите name: ");
//            try {
//                String name = scr.nextLine();
//                worker.setName(name);
//            }catch (FieldException e){
//                System.out.println("name не может быть null");
//                add_name();
//            }
//        }
//
//        public void add_x() {
//            System.out.println("Введите Coordinates: " + "\n" + "x (Integer): ");
//            try {
//                String x = scr.nextLine();
//                coordinates.setX(Integer.parseInt(x));
//
//            }catch (NumberFormatException e){
//                System.out.println("Неправильный ввод данных, введите заново");
//                add_x();
//            } catch(InputMismatchException e) {
//                System.out.println("Неправильный ввод данных");
//                add_x();
//
//            } catch (FieldException e){
//                System.out.println("x не может быть null и меньше -329");
//                add_x();
//            }
//        }
//
//        public void add_y() {
//            System.out.println("y (float): ");
//            try {
//                float y = Float.parseFloat(scr.nextLine());
//                coordinates.setY(y);
//            } catch (NumberFormatException e) {
//                System.out.println("Неправильный ввод данных");
//                add_y();
//
//            } catch (FieldException e) {
//                System.out.println("y не может быть больше -805");
//                add_y();
//
//            }
//        }
//
//            public void add_coordinates () {
//
//                try {
//                    worker.setCoordinates(coordinates);
//                } catch (FieldException e) {
//                    System.out.println("coordinates не может быть null");
//                    add_coordinates();
//                }
//            }
//
//
//       public void add_height(){
//           System.out.println("Введите Person: " + "\n" + "height (double): ");
//           try{
//               double height = Double.parseDouble(scr.nextLine());
//               person.setHeight(height);
//           } catch (NumberFormatException e) {
//               System.out.println("Неправильный ввод данных");
//               add_height();
//
//           } catch (FieldException e) {
//               System.out.println("Значение поля должно быть больше 0");
//               add_height();
//
//           }
//       }
//
//       public void add_passportID(){
//           System.out.println("passportID (String): ");
//           try{
//               String passportId = scr.nextLine();
//               person.setPassportID(passportId);
//           } catch (FieldException e){
//               System.out.println("Длина строки должна быть не меньше 9 и не больше 41");
//               add_passportID();
//           }
//       }
//
//        public void add_person()  {
//            try {
//                worker.setPerson(person);
//            } catch(FieldException e){
//                System.out.println("поле не может быть null");
//                add_person();
//            }
//        }
//
//        public void add_salary()  {
//            System.out.println("Введите salary (long): ");
//            try {
//                String salary = scr.nextLine();
//                worker.setSalary(Long.parseLong(salary));
//            }catch (FieldException e){
//                System.out.println("Значение поля должно быть больше 0");
//                add_salary();
//            }catch(InputMismatchException e){
//                scr.nextLine();
//                System.out.println("Неправильный ввод данных");
//                add_salary();
//            } catch (NumberFormatException e){
//                System.out.println("Неправильный ввод данных, вы ввели не число");
//                add_salary();
//            }
//        }
//
//        public void add_position() {
//
//            System.out.println("Введите Positiomn: " + "\n" +
//                    "Выберите из перечисленных: " + "\n" +
//                    "DIRECTOR," + "\n" +
//                    "MANAGER, " + "\n" +
//                    "HUMAN_RESOURCES, " + "\n" +
//                    "HEAD_OF_DEPARTMENT, " + "\n" +
//                    "LEAD_DEVELOPER;");
//
//            try{
//                    String position = scr.nextLine();
//                    if (!(position.equals(""))) {
//                        Position pos = Position.valueOf(position);
//                        worker.setPosition(pos);
//                    }else{
//                        worker.setPosition(null);
//                    }
//
//            } catch (IllegalArgumentException e) {
//                System.out.println("Нет такого значения enum");
//                add_position();
//
//            }
//        }
//
//        public void add_status() {
//
//            try{
//            System.out.println("Введите Status: " + "\n" +
//                    "Выберите из перечисленных: " + "\n" +
//                    "HIRED, " + "\n" +
//                    "RECOMMENDED_FOR_PROMOTION, " + "\n" +
//                    "REGULAR; ");
//                    String status = scr.nextLine();
//                    Status stat = Status.valueOf(status);
//                    worker.setStatus(stat);
//            } catch (FieldException e) {
//                System.out.println("Поле не может быть null");
//                add_status();
//            }catch(IllegalArgumentException e){
//                System.out.println("Нет такого значения enum");
//                add_status();
//            }
//        }
    public void add_date() throws IOException {
        worker.setStartDate(LocalDateTime.now());
        worker.setCreationDate();
        System.out.println("[даты установлены]");
        answer.append("[даты установлены]\n");
    }

    public void add() throws IOException {
        js.workers.add(worker);
        System.out.println("[worker добавлен]");
        answer.append("[worker добавлен]");
        bb = String.valueOf(answer).getBytes();

    }
}
