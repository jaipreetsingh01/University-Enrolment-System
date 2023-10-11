import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Student> students;
    private List<Subject> subjects;
    private Admin admin;

    public static void main(String[] args) {
        (new University()).displayMenu();
    }

    // CONSTRUCTOR FOR UNIVERSITY, HAVE TO ADD FILE STUFF HERE
    public University() {
        admin = new Admin();
        students = new ArrayList<>();
        subjects = new ArrayList<>();
    }

    private void registerStudentData() {
        // verify against data & verify pattern
        // add student through contrcutor
    }

    private void generateID() {
    }

    private void saveStudentData() {
    }

    private void updatePassword(String newPass) {
    }

    private void updateStudentData() {

    }

    private void deleteStudentData() {
    }

    private void deleteAllStudentData() {

    }

    // Return character entered by user
    private char readChoice() {
        return In.nextChar();
    }

    // Read the email and call verify pattern to match pattern
    // return student object if there is a match
    private Student findStudent() {
        return new Student("ab", "ab", "ab", 1);
    }

    // RETURN TRUE/FALSE depending if pattern is correct
    private boolean verifyCredentials(String email, String password) {
        return true; // For now to avoid error
    }

    // INCOMPLETE FUCNTION - DISPLAY STUDENT MENU
    private void studentMenu() {
        System.out.print(Colors.BLUE + "Student System (l/r/x): " + Colors.RESET);
        char c;
        while ((c = readChoice()) != 'x') {
            switch (c) {
                case 'l':
                    // implement student login
                    break;
                case 'r':
                    // implement student register
                    break;
                default:
                    // Help menu
                    break;
            }
            System.out.print(Colors.BLUE + "Student System (l/r/x): " + Colors.RESET);
        }
        System.out.println(Colors.YELLOW + "Thank You" + Colors.RESET);

    }

    // INCOMPLETE FUNCTION - DISPAYS THE MENU FOR ADMINS (LATER TO BE MOVED TO ADMIN
    // CLASS)
    private void adminMenu() {
        System.out.print(Colors.BLUE + "Admin System (c/g/p/r/s/x): " + Colors.RESET);
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
            System.out.print(Colors.BLUE + "Admin System (c/g/p/r/s/x): " + Colors.RESET);
        }
        System.out.println(Colors.YELLOW + "Thank You" + Colors.RESET);

    }

    // Function : Displays the (home) Menu
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
