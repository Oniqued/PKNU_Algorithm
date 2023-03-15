package assignment02;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Assignment02 {
    static int N, x, y, x_, y_;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        readFile();
        if(move(x,y)){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }
    }

    static void readFile() throws IOException{
        //경로를 수정해야 합니다.
        String filePath = "./src/assignment02/input.txt"; //경로
        Scanner sc = new Scanner(new File(filePath));

        N = sc.nextInt();
        board = new int[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                board[i][j] = sc.nextInt();
            }
        }

        x = sc.nextInt();
        y = sc.nextInt();
        x_ = sc.nextInt();
        y_ = sc.nextInt();
    }

    static boolean move(int sx, int sy) {
        //목표지점 도달시 정지
        if (sx == x_ && sy == x_) {
            return true;
        }
        //동쪽에
        return false;
    }
}
