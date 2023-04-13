package assignment06;

import java.awt.*;
import java.io.*;
import java.nio.Buffer;
import java.util.Scanner;
import java.util.StringTokenizer;

class Node{
    Node left = null;
    Node right = null;
    AddressBook user = null;
}

public class Assignment0610 {
    static Node root = null;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        AddressBook user = new AddressBook();
        while (true) {
            System.out.print("$ ");
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if (cmd.equals("exit")) break;
            else if (cmd.equals("read")) {
                String fileName = st.nextToken();
                read(fileName);
            }else if (cmd.equals("list")) list();
            else if (cmd.equals("find")) {
                String name = st.nextToken();
                find(name);
            }else if (cmd.equals("add")) {
                String name = st.nextToken();
                add(name);
            }else if (cmd.equals("trace")) {
                String name = st.nextToken();
                trace(name);
            }else if (cmd.equals("delete")) {
                String name = st.nextToken();
                delete(name);
            } else if (cmd.equals("save")) {
                String fileName = st.nextToken();
                save(fileName);
            }else{
                System.out.println("ERROR : 알 수 없는 명령어");
            }
        }
    }


    private static void read(String fileName) throws IOException{
        StringTokenizer st;
        AddressBook user;
        String fileDir = "C:\\Users\\Onique\\Desktop\\File\\PKNU\\3-1\\알고리즘\\Algorithm\\src\\assignment06\\" + fileName;
        BufferedReader file = new BufferedReader(new FileReader(fileDir));
//        Scanner file = new Scanner(new File(fileDir));
        file.readLine(); // 첫 라인은 index 이므로 제외
        String line;
        while ((line = file.readLine()) != null) {
            st = new StringTokenizer(line, "\t");
            user = new AddressBook();
            user.setName(st.nextToken());
            user.setCompany(st.nextToken());
            user.setAddress(st.nextToken());
            user.setZip(st.nextToken());
            user.setPhone(st.nextToken());
            user.setEmail(st.nextToken());
            add(user);
        }
        file.close();
    }

    // read()시, user객체를 읽어서 이진탐색트리에 넣음
    private static void add(AddressBook user) {
        Node newNode = new Node();
        newNode.user = user;
        if (root == null) root = newNode; //노드 최초 삽입
        else root = add(root, newNode);
    }

    // node가 이진탐색트리에 들어갈 위치 찾기
    private static Node add(Node node, Node newNode) {
        if(node == null) return newNode;
        else if(node.user.getName().compareTo(newNode.user.getName()) > 0){
            node.left = add(node.left, newNode);
        }else{
            node.right = add(node.right, newNode);
        }
        return node;
    }

    private static void list() {

    }

    private static void find(String name) {

    }

    private static void add(String name) {

    }

    private static void delete(String name) {

    }

    private static void trace(String name) {

    }

    private static void save(String fileName) {

    }
}
