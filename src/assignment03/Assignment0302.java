package assignment03;

public class Assignment0302 {
    static int N = 8;
    static int[][] maze = {
            {0, 0, 0, 0, 0, 0, 0, 1},
            {0, 1, 1, 0, 1, 1, 0, 1},
            {0, 0, 0, 1, 0, 0, 0, 1},
            {0, 1, 0, 0, 1, 1, 0, 0},
            {0, 1, 1, 1, 0, 0, 0, 1},
            {0, 1, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 1, 0, 0, 0, 1},
            {0, 1, 1, 1, 0, 1, 0, 0}
    };

    static int PATHWAY_COLOUR = 0; // white
    static int WALL_COLOUR = 1; // blue
    static int BLOCKED_COLOUR = 2; // red
    static int PATH_COLOUR = 3; // green

    public static void main(String[] args) {
        System.out.println("The number of paths to the exit is: " + countPaths(0, 0,0));
    }

    static int countPaths(int x, int y, int count) {
        if (x<0 || y<0 || x>=N || y>=N || maze[x][y] != PATHWAY_COLOUR)
            return count;
        else if (x==N-1 && y==N-1) {
            maze[x][y] = PATH_COLOUR;
            count++;
            maze[x][y] = PATHWAY_COLOUR;
            return count;
        }
        maze[x][y] = PATH_COLOUR;
        count = countPaths(x-1,y,count);
        count = countPaths(x,y+1,count);
        count = countPaths(x+1,y,count);
        count = countPaths(x,y-1,count);
        maze[x][y] = PATHWAY_COLOUR;
        return count;
    }

}