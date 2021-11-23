package fr.epsilonmc.api.io;

import lombok.SneakyThrows;

import java.io.*;

public class FileOperations {

    @SneakyThrows
    public static void write(File file, String data)  {
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(data);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    @SneakyThrows
    public static String read(File file) {
        BufferedReader bufferedReader = getReader(file);
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        while((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }

    @SneakyThrows
    public static BufferedReader getReader(File file) {
        FileReader fileReader = new FileReader(file);
        return new BufferedReader(fileReader);
    }

}
