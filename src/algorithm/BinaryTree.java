package algorithm;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

class Node {
    Node left = null;
    Node right = null;
    int key;
}

public class BinaryTree {
    static Node root = null;

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        int[] arr = {12, 7, 17, 6, 10, 8, 5, 14, 13, 20};
        for (int i = 0; i < arr.length; i++) {
            bt.add(arr[i]);
        }
        bt.ascendingTraversal();
        bt.descendingTraversal();
        bt.search(14);
        bt.remove(14);
        bt.ascendingTraversal();
        bt.descendingTraversal();
        levelOrderTraversal();
        System.out.println("arr is BST: " + isBST());
        System.out.println("count of InternalNodes: " + getNodeCount(root));
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
        if (node.key > key)
            node.left = searchNode(node.left, key);
        else if (node.key < key)
            node.right = searchNode(node.right, key);
        else if(node.key == key){
            System.out.println("해당 값을 가진 노드를 찾았습니다.");
            System.out.printf("Node: %d\n", node.key);
        }else{
            System.out.println("해당 노드를 찾을 수 없습니다.");
        }
        return node;
    }

    private void remove(int key) {
        root = removeNode(root, key);
    }

    private Node removeNode(Node node, int key) {
        // 삭제할 노드 찾기
        if (node.key > key) {
            node.left = removeNode(node.left, key);
        }else if(node.key < key){
            node.right = removeNode(node.right, key);
        }else{ // 지금 위치의 노드가 삭제할 노드인 경우
            if (node.left != null) { // 왼쪽에도 노드가 있다? 왼쪽 노드의 서브트리에서 가장 큰 값과 자신을 바꿔도 무방
                Node child = findMaxNode(node.left); // child는 key와 바꿀 대상
                swap(node, child); // child와 현재 노드의 값만 swap (안에 데이터만 바꿔야 노드들과의 관계를 유지하기 때문)
                node.left = removeNode(node.left, key);
            } else if (node.right != null) {
                Node child = findMinNode(node.right);
                swap(node, child);
                node.right = removeNode(node.right, key);
            } else{ // 지금 위치의 노드가 자식이 없는 노드인 경우
                return null;
            }
        }
        return node;
    }

    // 트리에서 값이 가장 큰 노드를 찾고싶다면, 오른쪽으로 끝까지 내려가면 된다.
    private Node findMaxNode(Node node) {
        if (node.right == null) return node;
        return findMaxNode(node.right);
    }

    // 트리에서 값이 가장 작은 노드를 찾고싶다면, 왼쪽으로 끝까지 내려가면 된다.
    private Node findMinNode(Node node) {
        if(node.left == null) return node;
        return findMinNode(node.left);
    }

    private void swap(Node node, Node child) {
        int tmp = child.key;
        child.key = node.key;
        node.key = tmp;
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

    private static void leftInorderTraversal(Node node) {
        //좌측 중위 순회
        if(node == null)
            return;
        leftInorderTraversal(node.left);
        System.out.printf("%d ", node.key);
        leftInorderTraversal(node.right);
    }

    static private void levelOrderTraversal(){
        System.out.println("Level-Order");
        levelOrder(root);
        System.out.println();
    }

    static private void levelOrder(Node node) {
        if (node == null) {
            System.out.println("Tree is empty");
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
                System.out.print(currentNode.key + " ");

            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }

            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
    }

    public static boolean isBST() {
        return isBSTRec(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isBSTRec(Node node, int minValue, int maxValue) {
        if (node == null) {
            return true;
        }

        if (node.key < minValue || node.key > maxValue) {
            return false;
        }

        return isBSTRec(node.left, minValue, node.key - 1) &&
                isBSTRec(node.right, node.key + 1, maxValue);
    }

    public static int getNodeCount(Node root) {
        int count = 0;

        if(root != null)
            count = 1 + getNodeCount(root.left) + getNodeCount(root.right);

        return count;
    }
}

/*
8. (참고) 노드의 개수, 단말 노드의 개수, 높이 구하기
1. 노드의 개수 구하기
노드의 개수를 세기 위해서는 트리안의 노드들을 전체적으로 순회하여야 한다.

각각의 서브트리에 대하여 순환 호출한 다음, 반환되는 값에 1을 더하여 반환한다.



public static int getNodeCount(Node root) {
	int count = 0;

	if(root != null)
		count = 1 + getNodeCount(root.left) + getNodeCount(root.right);

	return count;
}
2. 단말 노드 개수 구하기
단말 노드의 개수를 세기 위해서는 트리안의 노드들을 전체적으로 순회하여야 한다.

순회하면서 만약 왼쪽 자식과 오른쪽 자식이 동시에 0이 되면 단말 노드이므로 1을 반환한다.

만약 그렇지 않다면 비 단말 노드이므로 각각의 서브 트리에 대하여 순환 호출한 다음, 반환되는 값을 서로 더하면 된다.



public static int getLeafCount(Node root) {
	int count = 0;

	if(root != null) {
		if(root.left == null && root.right == null)
			return 1;
		else
			count = getLeafCount(root.left) + getLeafCount(root.right);
	}

	return count;
}
3. 높이 구하기
public static int getHeight(Node root) {
	int height = 0;

	if(root != null)
		height = 1 + Math.max(getHeight(root.left), getHeight(root.right));

	return height;
}

 */