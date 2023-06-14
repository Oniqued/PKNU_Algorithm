package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = null;
        String filePath = "src/assignment05/webLog.csv";
        ArrayList<LogEntry> logEntries = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                LogEntry entry = new LogEntry(fields[0], fields[1], fields[2], fields[3]);
                logEntries.add(entry);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(-1);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        // 명령어 처리
        Scanner sc = new Scanner(System.in);
        StringTokenizer st;
        while(true){
            System.out.print("$ ");
            st = new StringTokenizer(sc.nextLine(), " ");
            String cmd1 = st.nextToken();
            String cmd2 = st.nextToken();
            if(cmd1.equals("read")){
                // 이미 파일을 읽었으므로 skip
            }else if(cmd1.equals("sort")){
                String cmd3 = st.nextToken();
                if(cmd3.equals("-t")){ // 시간으로 정렬
                    Collections.sort(logEntries, new Comparator<LogEntry>(){
                        @Override
                        public int compare(LogEntry e1, LogEntry e2){
                            return e1.getTimeStamp().compareTo(e2.getTimeStamp());
                        }
                    });
                }else if(cmd3.equals("-ip")){ // IP로 정렬
                    Collections.sort(logEntries, new Comparator<LogEntry>(){
                        @Override
                        public int compare(LogEntry e1, LogEntry e2){
                            int ipCompare = e1.getIpAddress().compareTo(e2.getIpAddress());
                            if(ipCompare == 0){ // IP가 같으면 시간으로 정렬
                                return e1.getTimeStamp().compareTo(e2.getTimeStamp());
                            }
                            return ipCompare;
                        }
                    });
                }else{
                    System.out.println("잘못된 명령어입니다.");
                    continue;
                }

                // 정렬된 로그 출력
                for(LogEntry entry: logEntries){
                    System.out.println(entry.getTimeStamp());
                    System.out.println("IP: " + entry.getIpAddress());
                    System.out.println("URL: " + entry.getUrl());
                    System.out.println("Status: " + entry.getStatus());
                }

            }else if(cmd1.equals("exit")){
                break;
            }else{
                System.out.println("잘못된 명령어입니다.");
            }
        }
    }
}

class LogEntry {
    private String ipAddress;
    private String timeStamp;
    private String url;
    private String status;

    public LogEntry(String ipAddress, String timeStamp, String url, String status){
        this.ipAddress = ipAddress;
        this.timeStamp = timeStamp;
        this.url = url;
        this.status = status;
    }

    public String getIpAddress(){
        return ipAddress;
    }

    public String getTimeStamp(){
        return timeStamp;
    }

    public String getUrl(){
        return url;
    }

    public String getStatus(){
        return status;
    }
}
