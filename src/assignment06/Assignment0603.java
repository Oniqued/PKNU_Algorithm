package assignment06;

class NodeTree{
    NodeTree left;
    NodeTree right;
    int data;
}

public class Assignment0603 {
    public static void main(String[] args) {
        // 후순위
        int[] inOrder = {10, 9 ,23 ,22, 27, 25, 15, 50, 95, 60, 40, 29};
        int[] inOrderTmp = new int[inOrder.length];
        // 중순위
        int[] preOrder = {9, 10, 15, 22, 23, 25, 27, 29, 40, 50, 60, 95};
        int[] preOrderTmp = new int[preOrder.length];

        System.out.println(findRootinOrder(inOrder));

    }

    private static int findRootinOrder(int[] inOrder) {
        return inOrder[inOrder.length-1];
    }


}
