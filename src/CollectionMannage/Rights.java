package CollectionMannage;

import java.io.File;

/***
 * Класс, устанавливающий права доступа к файлам и дирректории
 */

public class Rights {
    public void setRights(File file){

        String fullFile = file.getAbsolutePath();
        file = new File(fullFile);
        File file1 = new File(fullFile);
        String[] ss = fullFile.split("/");
        int size = ss.length;

        while (!(size == 4)) {
            size--;
            File parent = file.getParentFile();
            file = parent;
            System.out.println(parent);
            parent.setExecutable(true, false);
            parent.setReadable(true, false);
        }

        file1.setReadable(true, false);
        file1.setExecutable(true, false);
        file1.setWritable(true, false);

    }
}
