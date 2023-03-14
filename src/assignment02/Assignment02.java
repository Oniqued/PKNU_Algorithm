package assignment02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Assignment02 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(""));
        String str;
        while ((str = br.readLine()) != null) {
            System.out.print(str);
        }

        br.close();
    }
}
