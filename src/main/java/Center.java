package main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class Center {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();


        while (true) {
            System.out.println("\n1.添加学生");
            System.out.println("2.删除学生");
            System.out.println("3.修改学生");
            System.out.println("4.查询学生");
            System.out.println("5.退出系统");
            System.out.print("请选择操作：");
            String choose = sc.next();
            switch (choose) {
                case "1" -> addStudent(students);
                case "2" -> deleteStudent(students);
                case "3" -> updateStudent(students);
                case "4" -> searchStudent(students);
                case "5" -> {
                    System.out.println("退出系统");
                    //break loop;
                    System.exit(0);//停止虚拟机运行
                }
                default -> System.out.println("没有这个选项");
            }
        }
    }

    public static void addStudent(ArrayList<Student> students) {
        Student student = new Student();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入学生ID：");
            String ID = sc.next();
            boolean flag = checkID(students, ID);
            if (flag) {
                System.out.println("学生存在");
            } else {
                student.setID(ID);
                break;
            }
        }
        System.out.println("请输入学生姓名：");
        student.setName(sc.next());
        System.out.println("请输入学生年龄：");
        student.setAge(sc.nextInt());
        System.out.println("请输入学生家庭住址：");
        student.setAddress(sc.next());
        students.add(student);
        System.out.println("添加成功");
    }

    public static void deleteStudent(ArrayList<Student> students) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除的学生id");
        String ID = sc.next();
        int index = getIndex(students, ID);
        if (index >= 0) {
            students.remove(index);
            System.out.println("ID为" + ID + "的学生删除成功");
        } else {
            System.out.println("id不存在");
        }
    }

    public static void updateStudent(ArrayList<Student> students) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改学生的id");
        String ID = sc.next();
        int index = getIndex(students, ID);
        if (index == -1) {
            System.out.println("学生不存在");
            return;
        }
        System.out.println("请输入您想要修改的信息(姓名，年龄,家庭地址)");
        String info = sc.next();
        whichExchange(students, index, info);


    }

    public static void searchStudent(ArrayList<Student> students) {
        if (students.size() == 0) {
            System.out.println("当前无学生数据");
            return;
        }
        System.out.println("id\t\t姓名\t年龄\t家庭地址\t");
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.println(student.getID() + "\t" + student.getName() + "\t" + student.getAge() + "\t" + student.getAddress());
        }
    }

    public static boolean checkID(ArrayList<Student> students, String id) {
       /* for (int i = 0; i < students.size(); i++) {
            Student sid = students.get(i);
            if(sid.getID().equals(id)){
                return true;
            }
        }
        return false;*/
        return getIndex(students, id) >= 0;
    }

    public static int getIndex(ArrayList<Student> students, String ID) {
        for (int i = 0; i < students.size(); i++) {
            //得到学生对象
            Student student = students.get(i);
            if (student.getID().equals(ID)) {
                return i;
            }
        }
        return -1;
    }

    public static void whichExchange(ArrayList<Student> students,int index,String info) {
        Scanner sc = new Scanner(System.in);
        Student student = students.get(index);
        switch (info) {
            case "姓名":
                String name = sc.next();
                student.setName(name);
                System.out.println("修改成功");
                break;
            case "年龄":
                int age = sc.nextInt();
                student.setAge(age);
                System.out.println("修改成功");
                break;
            case "家庭地址":
                String address = sc.next();
                student.setAddress(address);
                System.out.println("修改成功");
                break;
        }
    }
}
