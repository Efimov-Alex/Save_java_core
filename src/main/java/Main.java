/*
В классе Main был сериализовыван Java класс, используя интерфейс Serializable, записаны сериализованные файлы
на жесткий диск, используя класс FileOutputStream, и упаковываны в архив с помощью ZipOutputStream.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress gameProgress1 = new GameProgress(200, 50, 1, 10.5);
        GameProgress gameProgress2 = new GameProgress(300, 60, 2, 12.5);
        GameProgress gameProgress3 = new GameProgress(400, 70, 3, 14.5);

        saveGame("I://Games//savegames//save1.dat", gameProgress1);
        saveGame("I://Games//savegames//save2.dat", gameProgress2);
        saveGame("I://Games//savegames//save3.dat", gameProgress3);

        List<String> listPaths = new ArrayList<>();
        listPaths.add("I://Games//savegames//save1.dat");
        listPaths.add("I://Games//savegames//save2.dat");
        listPaths.add("I://Games//savegames//save3.dat");

        zipFiles("I://Games//savegames//zip.zip", listPaths);

        File game1 = new File("I://Games//savegames//save1.dat");
        File game2 = new File("I://Games//savegames//save2.dat");
        File game3 = new File("I://Games//savegames//save3.dat");

        if (game1.delete())
            System.out.println("Файл может быть удален");
        else
            System.out.println("Файл не может быть удален");
        System.out.println();

        if (game2.delete())
            System.out.println("Файл может быть удален");
        else
            System.out.println("Файл не может быть удален");
        System.out.println();

        if (game3.delete())
            System.out.println("Файл может быть удален");
        else
            System.out.println("Файл не может быть удален");
    }

    public static void saveGame(String path, GameProgress gameProgress) {
        try {
            FileOutputStream outputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(gameProgress);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void zipFiles(String pathZip, List<String> listPathFiles) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathZip));) {
            for (int i = 0; i < listPathFiles.size(); i++) {
                FileInputStream fis = new FileInputStream(listPathFiles.get(i));

                ZipEntry entry = new ZipEntry(listPathFiles.get(i));
                zout.putNextEntry(entry);

                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);

                zout.write(buffer);

                fis.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}

