package Collection;

import Exceptions.FieldException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/***
 * Класс, экземпляры которого хранятся в коллекции
 */

public class Worker implements Comparable, Serializable {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long salary; //Значение поля должно быть больше 0
    private LocalDateTime startDate; //Поле не может быть null
    private Position position; //Поле может быть null
    private Status status; //Поле не может быть null
    private Person person; //Поле не может быть null


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public long getSalary() {
        return salary;
    }

    public Position getPosition() {
        return position;
    }

    public Status getStatus() {
        return status;
    }

    public Person getPerson() {
        return person;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
        //return  DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(startDate);
    }

    public void setId(int id) throws FieldException {
        this.id = id;
        if (id == 0)
            throw new FieldException("Exception in Field : id");
    }

    public void setName(String name) throws FieldException {
        this.name = name;
        if (name == null || name.isEmpty())
            throw new FieldException("Exception in field : name");
    }

    public void setCoordinates(Coordinates coordinates) throws FieldException {
        this.coordinates = coordinates;
        if (coordinates == null)
            throw new FieldException("Exception in field : coordinates");
    }

    public void setSalary(long salary) throws FieldException {
        this.salary = salary;
        if (salary <= 0)
            throw new FieldException("Exception in field : salary");
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setStatus(Status status) throws FieldException {
        this.status = status;
        if (status == null)
            throw new FieldException("Exception in field : status");
    }

    public void setPerson(Person person) throws FieldException {
        this.person = person;
        if (person == null)
            throw new FieldException("Exception in field : person");
    }

    public void setStartDate(LocalDateTime startDate) {
       // LocalDateTime startDate = LocalDateTime.now();
//        DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(startDate);
        this.startDate = startDate;
    }

    public void setCreationDate() {
       // LocalDateTime startDate = LocalDateTime.now();
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        ZonedDateTime creationDate = ZonedDateTime.of(startDate , zoneId);
        this.creationDate = creationDate;
    }

    @Override
    public int compareTo(Object obj) {
        if (this.salary > ((Worker) obj).salary)
            return (1);

        if (this.salary < ((Worker) obj).salary)
            return (-1);
        if (this.salary == ((Worker) obj).salary)
            return (this.name.length() - ((Worker) obj).name.length());
        return (0);

//        int tmp = (int) (this.salary - ((Worker) obj).salary);
//        if (tmp == 0)
//            return this.name.length() - ((Worker) obj).name.length();
//        else
//            return tmp;
    }


    @Override
    public String toString() {
        return "Worker[ID:" + id + ", Name: " + name + ", Coordinates: " + coordinates + ", salary: " + salary + ", Position: " + position + ", Status: " + status + ", Person: " + person + ", CreationDate: "+creationDate+"]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else
            return false;
    }
}

