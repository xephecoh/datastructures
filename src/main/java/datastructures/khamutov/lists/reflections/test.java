package datastructures.khamutov.lists.reflections;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class test {
    public static void main(String[] args) throws IOException, InterruptedException {
        FileInputStream fileInputStream = new FileInputStream("src/test.txt");
        System.out.println(fileInputStream.read());

        int value;
        while((value=fileInputStream.read())!=-1){
            System.out.println((char) value);
        }

        Thread.sleep(10_0000);

        while((value=fileInputStream.read())!=-1){
            System.out.println((char) value);
        }

    }
}
