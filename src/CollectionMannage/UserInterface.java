package CollectionMannage;

import Collection.Worker;
import Commands.*;
import Exceptions.FieldException;
import Exceptions.FileIsEmptyException;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.*;

/***
 * Класс для запроса ввода у пользователя и вывода результата действия команд
 */

public class UserInterface {



    public String comand;
    public String comand1 = "";
    String firstScript = "";
    Comands comands;


    JsonReader reader = new JsonReader();

   public ServerSocket server;
    ByteBuffer writeBuf = ByteBuffer.allocate(3000);
    byte[] bytes;
    Selector selector;
    ServerSocketChannel ssc;
    ObjectInputStream ois = null;
    SelectionKey key;
    Scanner scr = new Scanner(System.in);




    Help help = new Help();
    Show show = new Show();
    Remove remove = new Remove();
    Clear clear = new Clear();
    Save save = new Save();
    MaxStatus maxStatus = new MaxStatus();
    Update update = new Update();
    AddIfMin addIfMin = new AddIfMin();
    RemoveGreater removeG = new RemoveGreater();
    RemoveLower removeL = new RemoveLower();
    ExecuteScript executeScript = new ExecuteScript();
    Info info = new Info();
    Add add;

    Scanner scaner;
    Scanner oldScaner;

    public UserInterface()  {
    }

    public  void setServer() throws IOException, ClassNotFoundException, FileIsEmptyException, java.text.ParseException, FieldException, ParseException {
        selector = Selector.open();
        ssc = ServerSocketChannel.open();
        server = ssc.socket();
        server.bind(new InetSocketAddress(3555));
        ssc.configureBlocking(false);
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        serverWork();
    }
    public void serverWork() throws ClassNotFoundException, FileIsEmptyException, java.text.ParseException, FieldException, ParseException, IOException {
        try {
            while (true) {

                selector.select(2000);

                if (System.in.available() > 0)
                            if (scr.next().equals("save"))
                                save.save(reader);


                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    key = keys.next();
                    keys.remove();

                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel channel = server.accept();
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(3000));

                    } else if (key.isReadable()) {

                        readClient();
                        if (!(comand1.equals("execute_script"))) {
                            System.out.println("имя: " + comands.getName());
                            comand = comands.getName();
                            execute();
                        } else {
                            executeScript.comands.remove(0);
                            execute();
                        }

                    }
                }

            }
        } catch (IOException | InterruptedException e){
            System.out.println("Server work has been interrupted because of some problems with connection");
            save.save(reader);
        }
    }

    public void readClient() throws IOException, ClassNotFoundException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        channel.read(buffer);
        buffer.flip();
        System.out.println("получено: "+new String(buffer.array(), buffer.position(), buffer.remaining()));
        byte[] bytes = buffer.array();
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ois = new ObjectInputStream(bais);
        comands = (Comands) ois.readObject();
        bais.close();
        ois.close();
    }

    public void writeClient(byte[] bytes) throws IOException, ClassNotFoundException, ParseException, FileIsEmptyException, FieldException, java.text.ParseException {
        writeBuf = (ByteBuffer) key.attachment();
        writeBuf.flip();
        writeBuf.clear();
        writeBuf.put(bytes);
        SocketChannel channel = (SocketChannel) key.channel();
        writeBuf.flip();
        channel.write(writeBuf);
        if (writeBuf.hasRemaining()) {
            writeBuf.compact();
        } else {
            writeBuf.clear();
        }
        System.out.println(new String(writeBuf.array()));
        System.out.println("отправлено");

        if (!(comand.equals("exit")) && !(comand1.equals("execute_script")) && !(comand.equals("execute_script")) && !(comand.equals("pause"))) {

            key.interestOps(SelectionKey.OP_READ);
            serverWork();
        } else if (comand.equals("exit")){

            key.channel().close();

        }
    }



    public void execute() throws ParseException, FieldException, IOException, FileIsEmptyException, java.text.ParseException, ClassNotFoundException, InterruptedException {

        switch (comand) {
            case ("help"):
                help.help();
                bytes = help.bb;
                writeClient(bytes);
                break;
            case ("exit"):
                save.save(reader);
                bytes = "[программа завершена]".getBytes();
                writeClient(bytes);
                break;
            case ("show"):
                show.show(reader);
                bytes = show.bb;
                writeClient(bytes);
                break;
            case ("add"):
                add = new Add(reader);

                add.worker = (Worker) comands.getArgs();

                add.add_id();
                add.add_date();
                add.add();
                bytes = add.bb;
                writeClient(bytes);
                if (comand1.equals("pause")) {
                    comand1 = "execute_script";
                }
                break;
            case ("remove_by_id"):
                int id;
                if (!(comand1.equals("execute_script"))) {
                    try {
                        id = Integer.parseInt((String) comands.getArgs());
                        remove.remove(reader, id);
                        bytes = remove.bb;
                    } catch (NumberFormatException e) {
                        System.out.println("Вы ввели не число, введите команду заново");
                        bytes = ("Вы ввели не число, введите команду заново").getBytes();
                    }
                } else {
                    if (scaner.hasNextInt() | oldScaner.hasNextInt()) {
                        if (scaner.hasNextInt()) {
                            id = scaner.nextInt();
                            remove.remove(reader, id);
                        }
                        if (oldScaner.hasNextInt()) {
                            id = oldScaner.nextInt();
                            remove.remove(reader, id);
                        }
                    } else {
                        System.out.println("Вы ввели не число, введите команду заново");
                        bytes = ("Вы ввели не число, введите команду заново").getBytes();
                        try {
                            scaner.next();
                        } catch (NoSuchElementException e) {
                            oldScaner.next();
                        }
                    }
                }
                writeClient(bytes);
                break;


            case ("clear"):
                clear.clear(reader);
                bytes = clear.bb;
                writeClient(bytes);
                break;

            case ("sum_of_salary"):
                writeBuf.clear();
                SalarySum sSum = new SalarySum();
                sSum.salarySum(reader);
                bytes = sSum.bb;
                writeClient(bytes);
                break;

            case ("max_by_status"):
                maxStatus.maxStatus(reader);
                bytes = maxStatus.bb;
                writeClient(bytes);
                break;

            case ("update_by_id"):
                update.setArgs((Worker) comands.getArgs());
                int id2 = 0;
                if (!(comand1.equals("execute_script"))) {
                    id2 = update.getArgs().getId();
                    ((Worker) comands.getArgs()).setId(id2);
                    try {
                        update.update(reader, id2);
                        bytes = update.bb;
                        writeClient(bytes);
                    } catch (NumberFormatException e) {
                        System.out.println("Вы ввели не число, введите команду заново");
                        bytes = ("Вы ввели не число, введите команду заново").getBytes();
                        writeClient(bytes);
                        break;
                    }
                }
                else {
                    if (scaner.hasNextInt() | oldScaner.hasNextInt()) {
                        if (scaner.hasNextInt()) {
                            id2 = scaner.nextInt();
                            ((Worker) comands.getArgs()).setId(id2);
                            update.update(reader, id2);
                            bytes = update.bb;
                            writeClient(bytes);
                        }else{
                            id2 = oldScaner.nextInt();
                            System.out.println(id2);
                            ((Worker) comands.getArgs()).setId(id2);
                            update.update(reader, id2);
                            bytes = update.bb;
                            writeClient(bytes);
                        }
                    } else {
                        System.out.println("Вы ввели не число, введите команду заново");
                        bytes = ("Вы ввели не число, введите команду заново").getBytes();
                        writeClient(bytes);
                        try {
                            scaner.next();
                        } catch (NoSuchElementException e) {
                            oldScaner.next();
                        }
                    }
                }
                break;

            case ("add_if_min"):
                addIfMin.setArgs((Worker) comands.getArgs());
                addIfMin.addIfMin(reader);
                bytes = addIfMin.add1.bb;
                writeClient(bytes);
                break;

            case ("remove_greater"):
                removeG.setArgs((Worker) comands.getArgs());
                removeG.removeG(reader);
                bytes = removeG.bb;
                writeClient(bytes);
                break;

            case ("remove_lower"):
                removeL.setArgs((Worker) comands.getArgs());
                removeL.removeL(reader);
                bytes = removeL.bb;
                writeClient(bytes);
                break;

            case ("execute_script"):
                String scriptFile;
                if (!(comand1.equals("execute_script"))) {
                    scriptFile = (String) comands.getArgs();
                    firstScript = scriptFile;
                } else {
                    try {
                        scriptFile = scaner.next();
                    } catch (NoSuchElementException e) {
                        scaner = oldScaner;
                        scriptFile = scaner.next();
                    }
                    try {
                        if (scriptFile.equals(firstScript)) throw new StackOverflowError();
                    } catch (StackOverflowError e) {
                        firstScript = "";
                        System.out.println("Невозможно вызвать команду execute_script при выполнении команды execute_script с одним файлом");

                        bytes = ("Невозможно вызвать команду execute_script при выполнении команды execute_script с одним файлом").getBytes();
                        writeClient(bytes);

                        break;
                    }
                }
                File file = new File(scriptFile);
                try {
                    scaner = new Scanner(file);
                    if (!(comand1.equals("execute_script"))) {
                        oldScaner = scaner;
                    }

                } catch (FileNotFoundException e) {
                    if (file.exists()) {
                        Rights rights = new Rights();
                        rights.setRights(file);
                        scaner = new Scanner(file);
                        if (!(comand1.equals("execute_script"))) {
                            oldScaner = scaner;
                        }
                    } else {
                        System.out.println("файла не существует");
                        bytes = "файла не существует".getBytes();
                        writeClient(bytes);
                        if (firstScript.equals(scriptFile)) {
                            bytes = ("[выполнение скрипта закончено]").getBytes();
                            writeClient(bytes);
                        }

                        break;

                    }
                }
                executeScript.executeScript(scriptFile);

                bytes = executeScript.bb;
                writeClient(bytes);

                while (!(executeScript.comands.isEmpty())) {
                    comand1 = "execute_script";
                    try {
                        comand = scaner.next();
                    } catch (NoSuchElementException e) {
                        comand = oldScaner.next();
                    }

                    if (comand.equals("add") || comand.equals("add_if_min") || comand.equals("update_by_id")){
                        bytes = "[введите worker'а]".getBytes();
                        writeClient(bytes);

                        selector.select();
                        Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                        key = keys.next();
                        readClient();

                    }

                    if (comand.equals("count_less_than_start_date")){
                        bytes = "[введите дату и время]".getBytes();
                        writeClient(bytes);

                        selector.select();
                        Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                        key = keys.next();
                        readClient();
                    }
                    executeScript.comands.remove(0);
                    execute();
                }
                comand1 = "";
                bytes = ("[выполнение скрипта закончено]").getBytes();
                writeClient(bytes);

                break;

            case ("info"):
                info.info(reader);
                bytes = info.bb;
                writeClient(bytes);
                break;

            case ("count_less_than_start_date"):
                CountLessStartDate countDate = new CountLessStartDate(reader);
                countDate.ldt = (LocalDateTime) comands.getArgs();
                countDate.countDateTime();
                bytes = countDate.bb;
                writeClient(bytes);
                break;

            default:
                System.out.println("такой команды нет, повторите ввод");
                bytes = "такой команды нет, повторите ввод".getBytes();
                writeClient(bytes);
                break;
        }
    }

}





