package assignment06;

import java.util.HashMap;
import java.util.Map;

public class Assignment0603 {
    class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }


    public static void main(String[] args) {
        int[] inorder = {9, 10, 15, 22, 23, 25, 27, 29, 40, 50, 60, 95};
        int[] postorder = {10, 9, 23, 22, 27, 25, 15, 50, 95, 60, 40, 29};

        Assignment0603 tree = new Assignment0603();
        tree.buildTreeFromTraversal(inorder, postorder);
        tree.prettyPrint();

    }

    Node root;

    public void buildTreeFromTraversal(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        root = buildTreeFromTraversalRec(postorder.length - 1, 0, inorder.length - 1, postorder, inorderIndexMap);
    }

    private Node buildTreeFromTraversalRec(int postStart, int inStart, int inEnd, int[] postorder, Map<Integer, Integer> inorderIndexMap) {
        if (postStart < 0 || inStart > inEnd) {
            return null;
        }

        Node node = new Node(postorder[postStart]);
        int inIndex = inorderIndexMap.get(node.data);

        node.right = buildTreeFromTraversalRec(postStart - 1, inIndex + 1, inEnd, postorder, inorderIndexMap);
        node.left = buildTreeFromTraversalRec(postStart - (inEnd - inIndex) - 1, inStart, inIndex - 1, postorder, inorderIndexMap);

        return node;
    }

    public void printLevelOrder() {
        int height = treeHeight(root);
        for (int i = 1; i <= height; i++) {
            printGivenLevel(root, i);
            System.out.println();
        }
    }

    private int treeHeight(Node root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = treeHeight(root.left);
            int rightHeight = treeHeight(root.right);

            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    private void printGivenLevel(Node root, int level) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            System.out.print(root.data + " ");
        } else if (level > 1) {
            printGivenLevel(root.left, level - 1);
            printGivenLevel(root.right, level - 1);
        }
    }

    public void prettyPrint() {
        printTree(root, 0, "H");
    }

    private void printTree(Node node, int level, String edge) {
        if (node != null) {
            printTree(node.right, level + 1, "/");
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            if (!edge.equals("H")) {
                System.out.print(edge);
                System.out.print("── ");
            }
            System.out.println(node.data);
            printTree(node.left, level + 1, "\\");
        }
    }

}
