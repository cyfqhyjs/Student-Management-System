package main.java;

import java.util.Scanner;

public class Center {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Student[] arr = new Student[3];
        int i = 0;

        while (true) {
            System.out.println("\n1.添加学生");
            System.out.println("2.查看学生");
            System.out.println("3.删除学生");
            System.out.println("4.修改学生");
            System.out.println("5.退出系统");
            System.out.print("请选择操作：");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent(arr);
                    break;
                case 2:
                    if (containsArr(arr) && arr[i] != null) {
                        printArr(arr);
                    } else {
                        System.out.println("暂无学生信息");
                    }
                    break;
                case 3:
                    deleteStudent(arr);
                    break;
                case 4:
                    exchangeStudent(arr);
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        }
    }
    //添加学生
    public static void addStudent(Student[] arr) {
        Scanner sc = new Scanner(System.in);
        boolean flag = containsArr(arr);
        int i = 0;
        if (flag && i < arr.length) {
            System.out.println("请输入学生ID：");
            int id = sc.nextInt();
            System.out.println("请输入学生姓名：");
            String name = sc.next();
            System.out.println("请输入学生年龄：");
            int age = sc.nextInt();

            arr[i] = new Student(id, name, age);
            System.out.println("添加成功！当前学生信息：");
            printArr(arr);
            i++;
        } else {
            System.out.println("数组已满,正在创建新数组");
            Student[] newArr = creatArr(arr);
            System.out.println("请输入学生信息：");
            System.out.println("ID：");
            int id = sc.nextInt();
            System.out.println("姓名：");
            String name = sc.next();
            System.out.println("年龄：");
            int age = sc.nextInt();

            newArr[i] = new Student(id, name, age);
            arr = newArr; // 更新数组引用
            System.out.println("添加成功！当前学生信息：");
            printArr(arr);
            i++;
        }
    }

    //通过ID删除学生
    public static void deleteStudent(Student[] arr) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除的学生ID;");
        int id = sc.nextInt();
        int index = getIndex(arr, id);
        if (index >= 0) {
            arr[index] = null;
            System.out.println("学生已删除");
            for (int i = 0; i < arr.length - 1; i++) {
                arr[i] = arr[i + 1];
            }
        } else {
            System.out.println("查无此人");
        }
    }

    //通过ID修改学生信息
    public static void exchangeStudent(Student[] arr) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要修改的学生ID:");
        int id = sc.nextInt();
        int index = getIndex(arr, id);
        if (index >= 0) {
            Student stu = arr[index];
            System.out.println("请输入您想要修改的信息(姓名，年龄)");
            String info = sc.next();
            whichExchange(arr, index, info);
        }
    }

    //修改的内容
    public static void whichExchange(Student[] arr, int index, String info) {
        Scanner sc = new Scanner(System.in);
        Student stu = arr[index];
        switch (info) {
            case "姓名":
                String name = sc.next();
                stu.setName(name);
                break;
            case "年龄":
                int age = sc.nextInt();
                stu.setAge(age);
                break;
        }
    }


    //如果数组装满，创建新数组
    public static Student[] creatArr(Student[] arr) {
        Student[] newArr = new Student[arr.length + 1];
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    //判断数组是否装满
    public static boolean containsArr(Student[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                return true;
            }
        }
        return false;
    }

    //遍历学生信息
    public static void printArr(Student[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                System.out.println(arr[i].getID() + "," + arr[i].getName() + "," + arr[i].getAge());
            }
        }
    }

    //获取学生ID在数组中的索引
    public static int getIndex(Student[] arr, int ID) {
        for (int i = 0; i < arr.length; i++) {
            Student stu = arr[i];
            if (stu != null) {
                int id = stu.getID();
                if (ID == id) {
                    return i;
                }
            }
        }
        return -1;
    }
}