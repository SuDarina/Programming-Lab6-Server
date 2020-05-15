//public class StrangeCode {
//}



//с селекторами

//public  void setServer() throws IOException, ClassNotFoundException, FileIsEmptyException, java.text.ParseException, FieldException, ParseException, InterruptedException {
//        selector = Selector.open();
//        ssc = ServerSocketChannel.open();
//        ssc.bind(new InetSocketAddress(3645));
//
//        ssc.configureBlocking(false);
//
//        ssc.register(selector, SelectionKey.OP_ACCEPT);
//
//        buffer = ByteBuffer.allocate(1024);
//        while (true) {
//        int select = selector.select();
//        if (select == 0)
//        continue;
//
//        Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
//
//        while (selectedKeys.hasNext()) {
//        k = selectedKeys.next();
//        try {
//        if (k.channel() == ssc) {
//
//        channel = ssc.accept();
//        channel.configureBlocking(false);
//        channel.register(selector, SelectionKey.OP_READ, SelectionKey.OP_WRITE);
//
//        } else {
//        buffer.flip();
//        ((SocketChannel) k.channel()).read(buffer);
//        bais = new ByteArrayInputStream((buffer.array()));
//        ois = new ObjectInputStream(bais);
//        }
//
//        ServerWork();
//        } finally {
//        // ServerWork();
//        selectedKeys.remove();
//        }
//        }
//        }
//        }
//
//        public void ServerWork() throws IOException, FileIsEmptyException, ClassNotFoundException, java.text.ParseException, FieldException, ParseException, InterruptedException {
//
////                            ((SocketChannel) k.channel()).read(buffer);
//        bais = new ByteArrayInputStream((buffer.array()));
//
//
//        try {
////                                    while (interactive) {
////                                        bais = new ByteArrayInputStream((buffer.array()));
////                                        ois = new ObjectInputStream(bais);
//
//        comands = (Comands) ois.readObject();
//        buffer.clear();
//        System.out.println("Доставлено " + comands.getName());
//        comand = comands.getName();
//        //ois.close();
////                                    }
//
//        } catch (StreamCorruptedException e) {
//        Thread.sleep(5000);
//        System.out.println("ждет ввода");
//        ServerWork();
//        }
//
//        }
//
//
////                    }finally{
////                        selectedKeys.remove();




// Изначальный для блокирующей программы

//        boolean work = true;
//        try {
//            while (work) {
//                interactive = true;
//                socket = server.accept();
//                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//                ois = new ObjectInputStream(socket.getInputStream());
//
//
//                while (interactive) {
//                    comands = (Comands) ois.readObject();
//                    System.out.println("Доставлено " + comands.getName());
//                    comand = comands.getName();
//                    execute();
//
//                }
//            }
//        } catch (java.net.SocketException e) {
//            System.out.println("Пользователь вышел из приложения");
//            setServer();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }