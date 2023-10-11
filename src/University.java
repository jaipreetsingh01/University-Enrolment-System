import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Student> students;
    private List<Subject> subjects;
    private Admin admin;

    public static void main(String[] args) {
        (new University()).displayMenu();

    }

    public University() {
        admin = new Admin();
        students = new ArrayList<>();
        subjects = new ArrayList<>();
    } // Contructor

    private void registerStudentData() {
        // verify against data & verify pattern
        // add student through contrcutor
    }

    private void generateID() {
    }

    private void saveStudentData() {
    }

    private boolean verifyCredentials(String email, String password) {
        return true; // FOR NOW
    }

    private void updatePassword(String newPass) {
    }

    private void updateStudentData() {

    }

    private void deleteStudentData() {

    }

    private void deleteAllStudentData() {

    }

    private char readChoice() {
        // if (menu == "main") {
        // System.out.print("University System: (A)dmin, (S)tudent or X");
        // return In.nextChar();
        // } else if (menu == "admin") {
        // System.out.print("University System: (A)dmin, (S)tudent or X");
        // return In.nextChar();
        // } else if (menu == "student") {
        // System.out.print("University System: (A)dmin, (S)tudent or X");
        // return In.nextChar();
        // } else
        return In.nextChar();
    }

    private String readEmail() {
        System.out.print("Email: ");
        return In.nextLine();
        // verify pattern & return error if does not patch
    }

    private String readPassword() {
        System.out.print("Password: ");
        return In.nextLine();
        // verify pattern & return error if does not patch
    }

    private Student findStudent() {
        return new Student("ab", "ab", "ab", 1);
    }

    private void studentMenu() {

    }

    private void adminMenu() {
        System.out.print("Admin System (c/g/p/r/s/x): ");
        char c;
        while ((c = readChoice()) != 'x') {
            switch (c) {
                case 'c':
                    // clear database file
                    break;
                case 'g':
                    // group students
                    break;
                case 'p':
                    // partition students
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
            System.out.print("Admin System (c/g/p/r/s/x): ");
        }
        System.out.println(Colors.YELLOW + "Thank You" + Colors.RESET);

    }

    // Author : xyz , Function : Displays the (home) Menu
    public void displayMenu() {
        System.out.println();
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
