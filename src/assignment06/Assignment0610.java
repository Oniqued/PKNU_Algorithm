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
            System.out.printf("ERROR: '%s'을(를) 찾을 수 없습니다.\n", name);
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
        System.out.printf("'%s'이(가) 추가 되었습니다.\n", name);
    }

    // 노드 경로 추적
    private static void trace(String name) {
        findNode(root, name, true);
    }

    // 노드 삭제
    private static void delete(String name) {
        root = deleteNode(root, name);
    }

    private static Node deleteNode(Node node, String name) {
        if(node == null) {
            System.out.printf("ERROR: '%s'을(를) 찾을 수 없습니다.\n", name);
            return null;
        }
        // 트리를 순회하면서 삭제할 노드가 있는 위치를 찾음
        if (node.user.getName().compareTo(name) > 0) {
            node.left = deleteNode(node.left, name);
        } else if (node.user.getName().compareTo(name) < 0) {
            node.right = deleteNode(node.right, name);
        }else if(node.user.getName().equals(name)){ // 삭제할 노드의 위치를 찾았음
            if (node.left != null) { // 왼쪽 서브트리에서 자신과 바꿀 노드를 찾아야함(가장 큰 값)
                Node child = findMaxNode(node.left);
                swapData(node, child);
                node.left = deleteNode(node.left, name);
            } else if (node.right != null) {
                Node child = findMinNode(node.right);
                swapData(node, child);
                node.right = deleteNode(node.right, name);
            }else{
                System.out.printf("'%s'이(가) 삭제되었습니다.\n", name);
                return null;
            }
        }
        return node;
    }

    // 트리에서 가장 큰 값을 가지는 노드 찾기
    private static Node findMaxNode(Node node) {
        if(node.right == null) return node;
        return findMaxNode(node.right);
    }

    // 트리에서 가장 작은 값을 가지는 노드 찾기
    private static Node findMinNode(Node node) {
        if(node.left == null) return node;
        return findMaxNode(node.left);
    }

    private static void swapData(Node node, Node child) {
        AddressBook tmp = node.user;
        node.user = child.user;
        child.user = tmp;
    }

    private static void save(String fileName) throws IOException{
        String fileDir = "src/assignment06/" + fileName;
        BufferedWriter file = new BufferedWriter(new FileWriter(fileDir));
        saveInorderTraversal(root, file);
        file.close();
        if (file != null) {
            System.out.printf("'%s'파일이 성공적으로 저장되었습니다. (exit 호출시 파일이 보여집니다.)\n", fileName);
        }else{
            System.out.println("ERROR: 파일 저장 실패");
        }
    }

    private static void saveInorderTraversal(Node node, BufferedWriter file) throws IOException {
        if(node == null) return;
        saveInorderTraversal(node.left, file);
        file.write(
                node.user.getName() + "\t"
                + node.user.getCompany() + "\t"
                + node.user.getAddress() + "\t"
                + node.user.getZip() + "\t"
                + node.user.getPhone() + "\t"
                + node.user.getEmail() + "\n"
        );
        saveInorderTraversal(node.right, file);
    }
}
