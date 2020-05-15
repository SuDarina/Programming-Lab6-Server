package CollectionMannage;

import Exceptions.FieldException;
import Exceptions.FileIsEmptyException;

import java.io.IOException;
import java.text.ParseException;

public class Start {
    public static void main(String[] args) throws ParseException, IOException, FileIsEmptyException, FieldException, org.json.simple.parser.ParseException, ClassNotFoundException, InterruptedException {

        UserInterface us = new UserInterface();
        System.out.println("Server started!");
        us.reader.read();
        us.setServer();

    }
}
