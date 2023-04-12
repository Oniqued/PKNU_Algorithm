package assignment06;

public class BinaryTree<E> {
    public static class Node<E>{
        E data;
        Node<E> left;
        Node<E> right;

        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    Node<E> root;

    public BinaryTree(){
        root = null;
    }

    public BinaryTree(Node<E> root) {
        this.root = root;
    }

    // 노드 생성자
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        root = new Node<E>(data);
        if (leftTree != null) {
            root.left = leftTree.root;
        }
    }

    public static void main(String[] args) {

    }
}
