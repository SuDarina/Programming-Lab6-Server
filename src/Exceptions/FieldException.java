package Exceptions;

/***
 * Исключение, выбрасываемое при нарушении параметров полей класса Worker, Coordinates, Person
 */

public class FieldException extends Exception{

    public FieldException(String message) {
        super(message);
    }
}
