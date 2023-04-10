package assignment05;

import java.io.*;
import java.util.*;

public class Assignment0508 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st;
        List<String[]> rows = null;
        while(true){
            System.out.print("$ ");
            st = new StringTokenizer(sc.nextLine(), " ");
            String cmd1 = st.nextToken();
            String cmd2;
            if(cmd1.equals("exit")) break;
            if(cmd1.equals("read")){
                cmd2 = st.nextToken();
                String csvFile = "C:\\Users\\Onique\\Desktop\\File\\PKNU\\3-1\\알고리즘\\Algorithm\\src\\assignment05\\" + cmd2;
                BufferedReader br = new BufferedReader(new FileReader(csvFile));
                rows = new ArrayList<String[]>();
                String line;
                while ((line = br.readLine()) != null) {
                    String[] row = line.split(",");
                    rows.add(row);
                }
                br.close();
            } else if (cmd1.equals("sort")) {
                cmd2 = st.nextToken();
                if (cmd2.equals("-ip")) {
                    rows.sort(new Comparator<String[]>() {
                        @Override
                        public int compare(String[] row1, String[] row2) {
                            String ip1 = row1[0];
                            String ip2 = row2[0];
                            return ip1.compareTo(ip2);
                        }
                    });
                }else if (cmd2.equals("-t")) {
                    rows.sort(new Comparator<String[]>() {
                        @Override
                        public int compare(String[] row1, String[] row2) {
                            String t1 = row1[1];
                            String t2 = row2[1];
                            return t1.compareTo(t2);
                        }
                    });
                }
            } else if(cmd1.equals("print")){
                for (String[] row : rows) {
                    System.out.println(String.join(",", row));
                }
            }else {
                System.out.println("잘못된 명령어입니다.");
            }
        }
    }

    private static void commandReader(String cmd) {

    }
}

