package assignment06;

import java.awt.*;
import java.io.*;
import java.nio.Buffer;
import java.util.NoSuchElementException;
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
        while (true) {
            try {
                System.out.print("$ ");
                st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                if(cmd == null) continue;
                if (cmd.equals("exit")) break; // 쉘 종료
                else if (cmd.equals("read")) { // 파일 읽고 이진탐색트리 구성
                    String fileName = st.nextToken();
                    read(fileName);
                }else if (cmd.equals("list")) list(); // 트리를 inorder로 순회하며 출력
                else if (cmd.equals("find")) { // 이름으로 검색
                    String name = st.nextToken();
                    find(name);
                }else if (cmd.equals("add")) { // 새로운 사람 추가
                    String name = st.nextToken();
                    add(name);
                }else if (cmd.equals("trace")) { // 이름이 주어지면 해당 노드에까지 도달하는 과정을 출력
                    String name = st.nextToken();
                    trace(name);
                }else if (cmd.equals("delete")) { // 해당 인원 삭제
                    String name = st.nextToken();
                    delete(name);
                } else if (cmd.equals("save")) { // 구성된 트리의 내용을 동일한 형식으로 저장
                    String fileName = st.nextToken();
                    save(fileName);
                }else{
                    System.out.println("ERROR: 알 수 없는 명령어");
                }
            } catch (NoSuchElementException e) {
                System.out.println("ERROR: 알 수 없는 명령어");
            }
        }
    }


    private static void read(String fileName) throws IOException{
        StringTokenizer st;
        AddressBook user;
        String fileDir = "src/assignment06/" + fileName;
        try{
            BufferedReader file = new BufferedReader(new FileReader(fileDir));
            file.readLine(); // 첫 라인은 index 이므로 제외
            String line;
            while ((line = file.readLine()) != null) {
                st = new StringTokenizer(line, "\t");
                user = new AddressBook();
                user.setName(st.nextToken());
                user.setCompany(st.nextToken().replaceAll("\"", ""));
                user.setAddress(st.nextToken().replaceAll("\"", ""));
                user.setZip(st.nextToken());
                user.setPhone(st.nextToken());
                user.setEmail(st.nextToken());
                add(user);
            }
            file.close();
        }catch(FileNotFoundException e){
            System.out.printf("ERROR: '%s' 파일을 찾을 수 없습니다.\n", fileName);
        }
//        Scanner file = new Scanner(new File(fileDir));
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

    // 대상의 인적사항 출력폼
    private static void printUserInfo(Node node){
        System.out.printf("%s\n", node.user.getName());
        System.out.printf("\tCompany: %s\n", node.user.getCompany());
        System.out.printf("\tAddress: %s\n", node.user.getAddress());
        System.out.printf("\tZipCode: %s\n", node.user.getZip());
        System.out.printf("\tPhones: %s\n", node.user.getPhone());
        System.out.printf("\tEmail: %s\n", node.user.getEmail());
    }

    // inorder로 트리를 순회 left root right
    private static void list() {
        inorderTraversal(root);
    }

    // 재귀적으로 노드를 순회
    private static void inorderTraversal(Node node){
        if(node == null) return;
        inorderTraversal(node.left);
        printUserInfo(node);
        inorderTraversal(node.right);
    }

    // 이름으로 대상을 검색
    private static void find(String name) {
        findNode(root, name, false);
    }
    // root에서부터 이름으로 대상을 검색한다. trace == true면, 대상으로 가는 방문 경로를 추적한다.
    private static Node findNode(Node node, String name, boolean trace) {
        if (node == null) {
            System.out.printf("ERROR: '%s'를 찾을 수 없습니다.\n", name);
            return null;
        }
        if(node.user.getName().compareTo(name) > 0) {
            if(trace) System.out.println(node.user.getName());
            findNode(node.left, name, trace);
        }
        else if(node.user.getName().compareTo(name) < 0) {
            if(trace) System.out.println(node.user.getName());
            findNode(node.right, name, trace);
        }
        else if(node.user.getName().equals(name)) {
            if(!trace) printUserInfo(node);
            else System.out.println(node.user.getName());
        }
        return node;
    }

    private static void add(String name) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        AddressBook user = new AddressBook();
        user.setName(name);
        System.out.print("\tCompany? ");
        user.setCompany(br.readLine());
        System.out.print("\tAddress? ");
        user.setAddress(br.readLine());
        System.out.print("\tZipCode? ");
        user.setZip(br.readLine());
        System.out.print("\tPhones? ");
        user.setPhone(br.readLine());
        System.out.print("\tEmail? ");
        user.setEmail(br.readLine());

        add(user);
    }

    private static void trace(String name) {
        findNode(root, name, true);
    }

    private static void delete(String name) {

    }

    private static void save(String fileName) {

    }
}
