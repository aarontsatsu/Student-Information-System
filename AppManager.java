/**
 * @author Aaron Tsatsu Tamakloe, Princess Asante, Mercy Chimezie
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.StringTokenizer;

/**
 * The App Manager Class handles all the methods that can be implemented for the student app
 * The App Manager Class has an AVLTree field that initializes a default AVL tree
 * The App manager Class inherits from the AVLTree class to allow for the use of certain methods
 */
public class AppManager extends AVLTree{

    private static AVLTree newTree = new AVLTree();
    private static ArrayList<Student> newArray = new ArrayList<Student>();
    private static StackImp<Student> newStack = new StackImp<>();

    public AppManager() throws FileNotFoundException {
        readFromFile();
    }

    
    public void addStudent() throws Exception {
        String name = "";
        int age = 0;
        int studID = 0;
        int yearGrad = 0;
        double cumgpa = 0.0;

        Scanner askStdDataObj = new Scanner(System.in);
        System.out.println("Enter your name: ");
        name = askStdDataObj.nextLine();


        System.out.println("Enter your age: ");
        age = askStdDataObj.nextInt();

        System.out.println("Enter your student ID: ");
        int askStdID = askStdDataObj.nextInt();
        if(ifKeyExists(askStdID)){
            throw new Exception("Student with this ID already exists!!!");
        }
        studID = askStdID;

        System.out.println("Enter your Graduation year: ");
        yearGrad = askStdDataObj.nextInt();

        System.out.println("Enter your CGPA: ");
        cumgpa = askStdDataObj.nextDouble();
        Student studentObj = new Student(name, age, studID, yearGrad, cumgpa);
        newTree.root = insertNode(newTree.root, askStdID, studentObj);

        // Write to file
        try{
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("studentdata.txt", true));
            bw.write(studentObj.getName() + ",");
            bw.write(studentObj.getAge() + ",");
            bw.write(studentObj.getStudentID() + ",");
            bw.write(studentObj.getYearOfGrad() + ",");
            bw.write(String.valueOf(studentObj.getCumGpa()) + "\n");
            bw.close();
            System.out.println("Student successfully added.");
        } catch(Exception exc){
            return;
        }
    }

    public String editStudent() throws IOException {
        Scanner askIDObj = new Scanner(System.in);
        System.out.println("Enter the Student ID of the student you want to EDIT >>> ");
        int studentID = askIDObj.nextInt();


        System.out.println("Enter the edited information for the student (NB: Enter -1 if you do not want to change this information >>> ");
        System.out.println("Name >>> ");
        String editedName = askIDObj.next();
        if (! editedName.equalsIgnoreCase("-1")){
            newTree.root.studObj.setName(editedName);
        }

        System.out.println("Age >>> ");
        int editedAge = askIDObj.nextInt();
        if (! String.valueOf(editedAge).equalsIgnoreCase("-1")){
            newTree.root.studObj.setAge(editedAge);
        }


        System.out.println("Graduation Year >>> ");
        int editedGradYear = askIDObj.nextInt();
        if (! String.valueOf(editedGradYear).equalsIgnoreCase("-1")){
            newTree.root.studObj.setYearOfGrad(editedGradYear);
        }

        System.out.println("Cumulative GPA >>> ");
        double editedGPA = askIDObj.nextDouble();
        if (! String.valueOf(editedGPA).equalsIgnoreCase("-1")){
            newTree.root.studObj.setCumGpa(editedGPA);
        }

        newTree.root.studObj.setStudentID(studentID);


        //update file information
        File file = new File("studentdata.txt");
        PrintWriter newWrite = new PrintWriter(file);
        newWrite.print("");
        newWrite.close();
        writeStudentFile(newTree.root);
        return "Successfully edited Student with ID number: " + studentID;
    }

    public String findStudent(){
        Scanner askIDObj = new Scanner(System.in);
        System.out.println("Enter the Student ID of the student you want to FIND >>> ");
        int studentID = askIDObj.nextInt();

        Node s = findNode(studentID, newTree.root);

        if(s == null){
            return "Student not found";
        }

        newStack.push(s.studObj);

        return "ID number: " + studentID + " >>>\nName >>> " + s.studObj.getName() + "\nAge >>> "
                + s.studObj.getAge() + "\nYear of Graduation >>> " + s.studObj.getYearOfGrad()
                + "\nCumulative GPA >>>  " + s.studObj.getCumGpa();
    }

    public void writeStudentFile(Node node) throws FileNotFoundException {
        if (node == null){
            return;
        }

        try{
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("studentdata.txt", true));
            bw.write(node.studObj.getName() + ",");
            bw.write(node.studObj.getAge() + ",");
            bw.write(node.studObj.getStudentID() + ",");
            bw.write(node.studObj.getYearOfGrad() + ",");
            bw.write(String.valueOf(node.studObj.getCumGpa()) + "\n");
            bw.close();
        } catch(Exception exc){
            return;
        }
        writeStudentFile(node.left);
        writeStudentFile(node.right);

    }

    public void deleteStudent() throws FileNotFoundException {
        Scanner askIDObj = new Scanner(System.in);
        System.out.println("Enter the Student ID of the student you want to DELETE >>> ");
        int studentID = askIDObj.nextInt();
        newTree.root = deleteNode(newTree.root, studentID);

        //update file information
        File file = new File("studentdata.txt");
        PrintWriter newWrite = new PrintWriter(file);
        newWrite.print("");
        newWrite.close();

        writeStudentFile(newTree.root);
        System.out.println("Successfully deleted Student with ID number: " + studentID);
    }

    public void listStudents() throws IOException{
        //loop through text file and return all student names.
        File file = new File("studentdata.txt");
        Scanner readFile = new Scanner(file);
        StringTokenizer token = null;

        String name = "";
        int age = 0;
        int studID = 0;
        int  yearGrad = 0;
        double cumgpa = 0.0;

        // reads data from the file
        while(readFile.hasNextLine()){
            token = new StringTokenizer(readFile.nextLine(), ",");


            name = token.nextToken();
            age = Integer.parseInt(token.nextToken());
            studID = Integer.parseInt(token.nextToken());
            yearGrad = Integer.parseInt(token.nextToken());
            cumgpa = Double.parseDouble(token.nextToken());

            Student fileStudent = new Student(name, age, studID, yearGrad, cumgpa);
            newArray.add(fileStudent);
        }
        readFile.close();

        System.out.println("The Students found in the Database are: \n");

        for(Student stud : newArray){
            System.out.println(stud.getName() + ":\n\t\t" +
                    "Age = " + stud.getAge() + "\n\t\tID = " + stud.getStudentID() + "\n\t\tYear of Graduation = " + stud.getYearOfGrad() + "\n\t\tCumulative GPA = " + stud.getCumGpa() + "\n\n");
        }
    }

    public void lastSearch(){

        if (newStack.isEmpty()){
            System.out.println("You have not searched for any data yet.");
        }
        System.out.println("Your last search found student with the following data:\n" +
                "Name: " + newStack.top().getName() + "\nAge: " + newStack.top().getAge()
                + "\nStudent ID: " + newStack.top().getStudentID() +
                "\nGraduation Year: " + newStack.top().getYearOfGrad() +
                "\nCum. GPA: " + newStack.top().getCumGpa());
    }

    public void readFromFile() throws FileNotFoundException {
        File file = new File("studentdata.txt");
        Scanner readFile = new Scanner(file);
        StringTokenizer token = null;

        String name = "";
        int age = 0;
        int studID = 0;
        int  yearGrad = 0;
        double cumgpa = 0.0;

        while(readFile.hasNextLine()){
            token = new StringTokenizer(readFile.nextLine(), ",");


            name = token.nextToken();
            age = Integer.parseInt(token.nextToken());
            studID = Integer.parseInt(token.nextToken());
            yearGrad = Integer.parseInt(token.nextToken());
            cumgpa = Double.parseDouble(token.nextToken());

            Student fileStudent = new Student(name, age, studID, yearGrad, cumgpa);
            newTree.root = insertNode(newTree.root, fileStudent.getStudentID(), fileStudent);
        }
        readFile.close();
    }



    public static void main(String[] args) throws Exception {

        Scanner askerObj = new Scanner(System.in);
        System.out.println("|..............................Hello Instructor, Welcome to our Student Management App..............................|\n\n" +
                "Enter START to start the program or STOP to end it.\n>>> ");



        String check = askerObj.nextLine();
        while (check.equalsIgnoreCase("START")){


            System.out.println("\n\nPlease enter a digit to perform the corresponding action: \n" +
                    "1. Add Student\n2. Edit Student\n3. Delete Student\n4. Find Student\n5. List Students\n6. View Last Search\n7. Stop the program\n\n>>> ");

            int askOption = askerObj.nextInt();

            AppManager methodImp = new AppManager();


            if (askOption == 1){
                methodImp.addStudent();

            }

            else if (askOption == 2){
                System.out.println(methodImp.editStudent());
            }

            else if (askOption == 3){
                methodImp.deleteStudent();
            }

            else if (askOption == 4){
                System.out.println(methodImp.findStudent());
            }

            else if (askOption == 5){
                methodImp.listStudents();
            }

            else if (askOption == 6){
                methodImp.lastSearch();
            }

            else if (askOption == 7){
                break;
            }

            else{
                System.out.println("Please check your input.");
            }
        }
    }
}