package Folders;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Solution {

    static Map<String, File> map = new LinkedHashMap<>();
    static List<String> list = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String root = null;
        try {
            root = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = new File(root);

        begin(file);

        for (Map.Entry<String, File> map1: map.entrySet()){
            list.add(map1.getKey());
        }

        Collections.sort(list);

        int i = 0;
        while (i<list.size()){
            for (Map.Entry<String, File> map1: map.entrySet()){
                if(list.get(i).equals(map1.getKey())){
                    reading(map1.getValue());
                    i++;
                }
        }}
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter (file);
            writer.write(String.valueOf(sb));
            writer.close();
            reader.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void begin(File file){
        for (File file1 : file.listFiles()) {
            if (searching(file1) != null)
                map.put(searching(file1).getName(),searching(file1));
        }
    }

    public static File searching(File file){
            if (file.getName().indexOf("txt") != -1){
                return file;
            }
            else {
                opening(file);
                return null;
            }
    }

    public static void opening(File file){
        String date = file.getAbsolutePath();
        begin(new File(date));
    }

    public static void reading(File file) {
        try (BufferedReader reader2 = Files.newBufferedReader(Paths.get(String.valueOf(file)))){
            String line;
            while ((line = reader2.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
