package it.dax.klefkey.offline.util;

import java.io.*;

public class ReaderWriterUtils{

    public static Object readObjectFile(String path, String fileName) throws IOException, ClassNotFoundException{
        File file = new File(path+fileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Object object = objectInputStream.readObject();

        objectInputStream.close();
        fileInputStream.close();

        return object;
    }

    public static void writeObjectFile(String path, String fileName, Object object) throws IOException {
        File file = new File(path+fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(object);

        objectOutputStream.flush();
        objectOutputStream.close();
        fileOutputStream.flush();
        fileOutputStream.close();
    }

}