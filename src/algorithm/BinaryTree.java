package algorithm;

class Node {
    Node left = null;
    Node right = null;
    int key;

    public int getKey() {
        return key;
    }
}

public class BinaryTree {
    Node root = null;

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        int[] arr = {12, 8, 7, 5, 10, 9, 11, 17, 14, 20};
        for (int i = 0; i < arr.length; i++) {
            bt.add(arr[i]);
        }
        bt.ascendingTraversal();
        bt.descendingTraversal();
    }

    public void add(int key) {
        Node newNode = new Node();
        newNode.key = key;

        if (root == null) {
            root = newNode;
        } else {
            root = addNode(root, newNode);
        }
    }

    private Node addNode(Node node, Node newNode) {
        if (node == null)
            return newNode;
        else if (node.key > newNode.key)
            node.left = addNode(node.left, newNode);
        else
            node.right = addNode(node.right, newNode);
        return node;
    }

    public void search(int key) {
        searchNode(root, key);
    }

    private Node searchNode(Node node, int key) {
        if (node == null)
            throw new RuntimeException("해당 값을 가진 노드를 찾을 수 없습니다.");

        if (node.key > key)
            node.right = searchNode(node.right, key);
        else if (node.key < key)
            node.left = searchNode(node.left, key);
        else
            System.out.println("해당 값을 가진 노드를 찾았습니다.");

        return node;
    }

    public void descendingTraversal() {
        //내림차순 순회
        System.out.print("내림차순 순회: ");
        rightInorderTraversal(root);
        System.out.println();
    }

    private void rightInorderTraversal(Node node){
        //우측 중위 순회
        if(node == null)
            return;
        rightInorderTraversal(node.right);
        System.out.printf("%d ", node.key);
        rightInorderTraversal(node.left);
    }


    public void ascendingTraversal() {
        //오름차순 순회
        System.out.print("오름차순 순회: ");
        leftInorderTraversal(root);
        System.out.println();
    }

    private void leftInorderTraversal(Node node) {
        //좌측 중위 순회
        if(node == null)
            return;
        leftInorderTraversal(node.left);
        System.out.printf("%d ", node.key);
        leftInorderTraversal(node.right);
    }

}