import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class University implements Serializable {
    private List<Student> students;
    private List<Subject> subjects;
    private Admin admin;

    public static void main(String[] args) {
        (new University()).displayMenu();
    }

    // Constructor for University
    public University() {
        admin = new Admin();
        // subjects = new ArrayList<>();
        Data.init();
        students = Data.readStudents();
    }

    private static final String Chars = "0123456789";

    // private String generateID() {
    // {
    // // Define the minimum length of the ID
    // int minLength = 6;
    // // Generate the random ID
    // StringBuilder randomID = new StringBuilder();
    // Random random = new Random();
    // // Ensure the ID has at least 6 characters
    // while (randomID.length() < minLength) {
    // int index = random.nextInt(Chars.length());
    // randomID.append(Chars.charAt(index));
    // }

    // return randomID;

    // }
    // }

    private void deleteStudentData(Student s) {
        students = Data.readStudents();
        students.remove(s);
    }

    private void deleteAllStudentData() {
        students = Data.readStudents();
        Data.deleteAllStudentData(students);
    }

    // Return character entered by user
    private char readChoice() {
        return In.nextChar();
    }

    // return student object if there is a match or null if no match.
    private Student findStudent(String email, String password) {
        students = Data.readStudents();
        for (Student s : students) {
            if (s.match(email, password)) {
                return s;
            }
        }
        return null;
    }

    // RETURN TRUE/FALSE depending if pattern is correct
    private boolean verifyCredentials(String email, String password) {
        return isValidEmail(email) && isValidPassword(password);
    }

    // This Function returns true if the email is correctly formatted
    private static boolean isValidEmail(String email) {
        String emailPattern = "^[a-zA-Z]+\\.[a-zA-Z]+@university\\.com$";
        return email.matches(emailPattern);
    }

    // This function returns true if the password is correctly formatted
    private static boolean isValidPassword(String password) {
        String passwordPattern = "^[A-Z][a-zA-Z]{5,}\\d{3,}$";
        return password.matches(passwordPattern);
    }

    // private static void groupByGrade() {
    // List<Student> students = Data.readStudents();
    // String[] gradeLetters = { "HD", "D", "C", "P", "F" };

    // System.out.println("Grade Grouping");
    // for (String gradeLetter : gradeLetters) { // Grouped by grade ("HD", "D",
    // "C", "P", "F")
    // for (Student student : students) {
    // if (getLetter(student.getGrade()).equals(gradeLetter)) {
    // System.out.println(gradeLetter + " --> [" + student.nameGetter() + " :: " +
    // student.IDGetter()
    // + " --> GRADE: " + gradeLetter + " - MARK: " + student.getGrade() + "]");
    // }
    // }
    // }
    // }

    // private static String getLetter(double grade) {
    // if (grade >= 85)
    // return "HD";
    // if (grade >= 75)
    // return "D";
    // if (grade >= 65)
    // return "C";
    // if (grade >= 50)
    // return "P";
    // return "F";
    // }

    // private static void partitionPassFail() {
    // List<Student> students = Data.readStudents();
    // List<Student> studentPassList = new ArrayList<>();
    // List<Student> studentFailList = new ArrayList<>();

    // for (Student student : students) { // Partitioned as pass or fail
    // if (student.getGrade() >= 50) {
    // studentPassList.add(student);
    // } else {
    // studentFailList.add(student);
    // }
    // }
    // System.out.println("PASS/FAIL Partition");
    // System.out.print("FAIL --> ");
    // for (Student student : studentFailList) {
    // System.out.print("[" + student.nameGetter() + " :: " + student.IDGetter() + "
    // --> GRADE: "
    // + getLetter(student.getGrade()) + " - MARK: " + student.getGrade() + "], ");
    // }
    // System.out.println();
    // System.out.print("PASS --> ");
    // for (Student student : studentPassList) {
    // System.out.print("[" + student.nameGetter() + " :: " + student.IDGetter() + "
    // --> GRADE: "
    // + getLetter(student.getGrade()) + " - MARK: " + student.getGrade() + "], ");
    // }
    // System.out.println();
    // }

    private void studentRegister() {
        System.out.println("Student Sign Up");
        System.out.print("Email: ");
        String email = In.nextLine();
        System.out.print("Password: ");
        String password = In.nextLine();

        students = Data.readStudents();
        if (verifyCredentials(email, password)) {
            // Functionality which will generate random ID is also pending here, for now its
            // 111111
            Student curStudent = findStudent(email, password);
            if (curStudent == null) {
                System.out.println("Email and password form acceptable");
                System.out.print("Name: ");
                String name = In.nextLine();
                System.out.println("Enrolling student - add name here");
                students.add(new Student(name, email, password, "111111"));
                Data.saveStudentData(students);
            } else
                System.out.println("Already exists");

        } else {
            System.out.println("Incorrect email or password format");
        }

    }

    public void studentLogin() {
        System.out.println("Student Sign In");
        System.out.print("Email: ");
        String email = In.nextLine();
        System.out.print("Password: ");
        String password = In.nextLine();

        if (verifyCredentials(email, password)) {
            Student curStudent = findStudent(email, password);
            if (curStudent != null)
                studentCourseMenu(curStudent);
            else
                System.out.println("Student not found");
        } else
            System.out.println("Password / email format issue");
    }

    public void studentCourseMenu(Student s) {
        System.out.print(Colors.BLUE + "Student Course Menu(c/e/r/s/x): " + Colors.RESET);
        char c;
        while ((c = readChoice()) != 'x') {
            switch (c) {
                case 'c':
                    s.updatePassword();
                    Data.saveStudentData(students);
                    break;
                case 'e':
                    s.enrolSubject();
                    Data.saveStudentData(students);
                    break;
                case 'r':
                    s.withdrawSubject();
                    Data.saveStudentData(students);
                    break;
                case 's':
                    s.viewEnrollment();
                    break;
                default:
                    // Help menu
                    break;
            }
            System.out.print(Colors.BLUE + "Student Course Menu(c/e/r/s/x): " + Colors.RESET);
        }
    }

    // This Function is incomplete & might move to student class
    private void studentMenu() {
        System.out.print(Colors.BLUE + "Student System (l/r/x): " + Colors.RESET);
        char c;
        while ((c = readChoice()) != 'x') {
            switch (c) {
                case 'l':
                    studentLogin();
                    break;
                case 'r':
                    studentRegister();
                    break;
                default:
                    // Help menu
                    break;
            }
            System.out.print(Colors.BLUE + "Student System (l/r/x): " + Colors.RESET);
        }
        System.out.println(Colors.YELLOW + "Thank You" + Colors.RESET);

    }

    // This Function is incomplete & later to be moved to admin class
    private void adminMenu() {
        System.out.print(Colors.BLUE + "Admin System (c/g/p/r/s/x): " + Colors.RESET);
        char c;
        while ((c = readChoice()) != 'x') {
            switch (c) {
                case 'c':
                    deleteAllStudentData();
                    Data.saveStudentData(students);
                    break;
                case 'g':
                    // groupByGrade();
                    break;
                case 'p':
                    // partitionPassFail();
                    break;
                case 'r':
                    // Remove student
                    break;
                case 's':
                    // show
                    break;
                default:
                    // Help menu
                    break;
            }
            System.out.print(Colors.BLUE + "Admin System (c/g/p/r/s/x): " + Colors.RESET);
        }
    }
    // System.out.println(Colors.YELLOW + "Thank You" + Colors.RESET);

    // Function : Displays the (home) Menu
    public void displayMenu() {
        System.out.println(students);
        char c;
        System.out.print(Colors.BLUE + "University System: (A)dmin, (S)tudent or X: " + Colors.RESET);
        while ((c = readChoice()) != 'X') {
            switch (c) {
                case 'A':
                    adminMenu();
                    break;
                case 'S':
                    studentMenu();
                    break;
                default:
                    // Help menu
                    break;
            }
            System.out.print(Colors.BLUE + "University System: (A)dmin, (S)tudent or X: " + Colors.RESET);
        }
        System.out.println(Colors.YELLOW + "Thank You" + Colors.RESET);
    }
}
