import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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

    // private String generateID() {
    // String randomID = UUID.randomUUID().toString(); // create random ID in string
    // format
    // return randomID;
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
                    break;
                case 'e':
                    // Enroll
                    break;
                case 'r':
                    // Remove
                    break;
                case 's':
                    // Show
                    break;
                default:
                    // Help menu
                    break;
            }
            System.out.print(Colors.BLUE + "Admin System (c/g/p/r/s/x): " + Colors.RESET);
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
