package assignment09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Dictionary 클래스 정의
class Dictionary {
    String first, last;

    // Dictionary 생성자
    Dictionary(String first, String last) {
        this.first = first;
        this.last = last;
    }

    // 객체 비교 메서드 재정의
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dictionary that = (Dictionary) o;
        return Objects.equals(first, that.first) && Objects.equals(last, that.last);
    }

    // 객체의 해시코드 생성 메서드 재정의
    @Override
    public int hashCode() {
        return Objects.hash(first, last);
    }
}

// 메인 클래스 정의
public class Assignment0901 {

    // 메인 메서드
    public static void main(String[] args) throws IOException {
        // 파일 경로 설정
        String fileDir = "src/assignment09/HarryPotter.txt";
        // 단어를 저장할 ArrayList 생성
        ArrayList<String> words = new ArrayList<>();
        // 파일 읽기 위한 BufferedReader 생성
        BufferedReader file = new BufferedReader(new FileReader(fileDir));
        String line;
        // 파일의 각 줄을 읽어 토큰화
        while ((line = file.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                words.add(st.nextToken());
            }
        }
        // 파일 리소스 닫기
        file.close();

        // Markov 체인을 저장할 HashMap 생성
        HashMap<Dictionary, ArrayList<String>> map = new HashMap<>();
        int i = 0;
        // Markov 체인 생성
        while (i + 2 < words.size()) {
            Dictionary dict = new Dictionary(words.get(i), words.get(i + 1));
            String nextWord = words.get(i + 2);

            ArrayList<String> list = map.get(dict);
            if (list == null) {
                list = new ArrayList<>();
                map.put(dict, list);
            }
            // 테이블 내용 중복 확인
            if (!list.contains(nextWord)) {
                list.add(nextWord);
            }

            if (nextWord.equals("[end]")) break;
            i++;
        }

        // 시작 단어 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        // 입력된 문자열 토큰화
        StringTokenizer st = new StringTokenizer(input);

        String startWord1 = st.nextToken();
        String startWord2 = st.nextToken();
        int maxLength = 100;

        // 랜덤 텍스트 생성
        generateRandomText(map, startWord1, startWord2, maxLength);
    }

    private static void generateRandomText(HashMap<Dictionary, ArrayList<String>> map, String startWord1, String startWord2, int maxLength) {
        StringBuilder sb = new StringBuilder();
        String currentWord1 = startWord1;
        String currentWord2 = startWord2;

        sb.append(currentWord1).append(" ").append(currentWord2);
        maxLength -= 2;

        while (maxLength > 0) {
            Dictionary dict = new Dictionary(currentWord1, currentWord2);
            ArrayList<String> suffixes = map.get(dict);

            if (suffixes == null || suffixes.isEmpty()) {
                break;
            }

            int randomIndex = new Random().nextInt(suffixes.size());
            String nextWord = suffixes.get(randomIndex);

            if (nextWord.equals("[end]")) {
                break;
            }

            sb.append(" ").append(nextWord);
            currentWord1 = currentWord2;
            currentWord2 = nextWord;
            maxLength--;
        }

        System.out.println(sb.toString());
    }
}
