package assignment07;

import java.io.*;
import java.util.StringTokenizer;

public class TreeConverter {
    class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    Node root;

    private void preOrderToFile(Node node, BufferedWriter bw) throws IOException {
        if (node == null) {
            bw.write("# ");
            return;
        }

        bw.write(node.data + " ");
        preOrderToFile(node.left, bw);
        preOrderToFile(node.right, bw);
    }

    public void saveTreeToFile(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            preOrderToFile(root, bw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Node preOrderToTree(StringTokenizer st) {
        if (!st.hasMoreTokens()) return null;

        String token = st.nextToken();
        if (token.equals("#")) {
            return null;
        }

        Node node = new Node(Integer.parseInt(token));
        node.left = preOrderToTree(st);
        node.right = preOrderToTree(st);

        return node;
    }

    public void loadTreeFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);

            root = preOrderToTree(st);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
