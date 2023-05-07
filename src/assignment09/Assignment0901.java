package assignment09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Dictionary{
    String first, last;
    
    Dictionary(){}
    Dictionary(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dictionary that = (Dictionary) o;
        return Objects.equals(first, that.first) && Objects.equals(last, that.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, last);
    }
}

public class Assignment0901 {
    static HashMap<Dictionary, ArrayList<String>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sentence = "Show your flowcharts and conceal your tables and I will be mystified. Show your tables and your flowcharts will be obvious. [end]";
        makeTable(sentence);

        br.readLine();
    }

    public static void makeTable(String sentence){
        StringTokenizer st = new StringTokenizer(sentence);

        String[] words = new String[100];
        for (int i = 0; st.hasMoreTokens(); i++) {
            words[i] = st.nextToken();
        }

        HashMap<Dictionary, ArrayList<String>> map = new HashMap<>();
        int i = 0;
        while (true) {
            Dictionary dict = new Dictionary(words[i], words[i + 1]);
            String nextWord = words[i + 2];

            ArrayList<String> list = map.get(dict);
            if (list == null) {
                list = new ArrayList<>();
                map.put(dict, list);
            }
            if (!list.contains(nextWord)) {
                list.add(nextWord);
            }

            if (nextWord.equals("[end]")) break;
            i++;
        }

        for (Map.Entry<Dictionary, ArrayList<String>> entry : map.entrySet()) {
            System.out.println("[Key]:{" + entry.getKey().first + " " + entry.getKey().last + "} [Value]:" + entry.getValue());
        }
    }
}
