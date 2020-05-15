package Exceptions;

/***
 * Исключение, при попытке считать worker'ов из файла с одним id
 */

public class NotUniqueId extends Exception{
    public NotUniqueId(String message) {
        super(message);
    }
}
