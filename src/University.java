import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class University implements Serializable {
    private List<Student> students;
    // private List<Subject> subjects;
    private Admin admin;

    public static void main(String[] args) {
        (new University()).displayMenu();
    }

    // Constructor for University
    public University() {
        admin = new Admin();
        Data.init();
        students = Data.readStudents();
    }

    private String generateID() {
        {
            String Chars = "0123456789";

            // Define the minimum length of the ID
            int minLength = 6;
            // Generate the random ID
            StringBuilder randomID = new StringBuilder();
            Random random = new Random();
            // Ensure the ID has at least 6 characters
            while (randomID.length() < minLength) {
                int index = random.nextInt(Chars.length());
                randomID.append(Chars.charAt(index));
            }
            return randomID.toString();

        }
    }

    private void deleteStudentData(Student s) {
        students.remove(s);
        Data.saveStudentData(students);
    }

    private Student findStudentbyID() {
        System.out.print("id: ");
        String studentID = In.nextLine();
        students = Data.readStudents();

        for (Student s : students) {
            if (s.matchbyID(studentID)) {
                System.out.println("Removing Student " + studentID + " Account");
                return s;
            } else
                System.out.println("Student " + studentID + " does not exist");
        }
        return null;
    }

    private void deleteAllStudentData() {
        students = Data.readStudents();
        Data.deleteAllStudentData(students);
        System.out.println("Students data cleared");
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

    private static void groupByGrade() {
        List<Student> students = Data.readStudents();
        String[] gradeLetters = { "HD", "D", "CR", "P", "F" };

        if (students.isEmpty())
            System.out.println("Nothing to display");
        else {
            System.out.println("Grade Grouping");
            for (String g : gradeLetters) {
                for (Student s : students) {
                    if (s.getterAverageGrade().equals(g))
                        System.out.println(g + " --> [" + s.nameGetter() + " :: " +
                                s.IDGetter() + " --> GRADE: " + g + " - MARKS: " + s.getterAverageMarks() + "]");
                }

            }
        }
    }

    private static void partitionPassFail() {
        List<Student> students = Data.readStudents();
        List<Student> studentPassList = new ArrayList<>();
        List<Student> studentFailList = new ArrayList<>();
        ArrayList<String> PassList = new ArrayList<>();
        ArrayList<String> FailList = new ArrayList<>();
        for (Student s : students) { // Partitioned as pass or fail
            if (s.getterAverageMarks() >= 50) {
                studentPassList.add(s);
            } else {
                if (s.getSubjectList().size() >= 1)
                    studentFailList.add(s);
            }
        }
        System.out.println("PASS/FAIL Partition");

        for (Student student : studentFailList) {
            FailList.add(student.nameGetter() + " :: " + student.IDGetter() + "--> GRADE: "
                    + student.getterAverageGrade() + " - MARK: " + student.getterAverageMarks() + ", ");
        }
        System.out.println();

        for (Student student : studentPassList) {
            PassList.add(student.nameGetter() + " :: " + student.IDGetter() + "--> GRADE: "
                    + student.getterAverageGrade() + " - MARK: " + student.getterAverageMarks() + ", ");
        }
        System.out.println("FAIL --> " + FailList.toString());
        System.out.println("PASS --> " + PassList.toString());
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
            System.out.println("Email and password form acceptable");
            System.out.print("Name: ");
            String name = In.nextLine();
            if (curStudent == null) {

                System.out.println("Enrolling student " + name);
                students.add(new Student(name, email, password, generateID()));
                Data.saveStudentData(students);
            } else
                System.out.println("Student " + name + " already exists");
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
                System.out.println("Student does not exist");
        } else
            System.out.println("Incorrect email or password format");
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
                    System.out.println("Are you sure you want to clear the databse (Y)es/ (N)o");
                    char charac = In.nextChar();

                    while (charac != 'Y' || charac != 'N') {
                        if (charac == 'Y')
                            deleteAllStudentData();
                        else if (charac == 'N')
                            break;
                        else {
                            System.out.println("Y - Yes | N - No");
                            System.out.println("Are you sure you want to clear the databse (Y)es/ (N)o");
                            charac = In.nextChar();
                        }
                    }
                    // Data.saveStudentData(students);
                    break;
                case 'g':
                    groupByGrade();
                    break;
                case 'p':
                    partitionPassFail();
                    break;
                case 'r':
                    Student toRemove = findStudentbyID();
                    deleteStudentData(toRemove);
                    break;
                case 's':
                    students = Data.readStudents();
                    if (students.isEmpty())
                        System.out.println("Nothing to display");
                    else {
                        System.out.println("Student List");
                        for (Student s : students) {
                            System.out.println(s.toString());
                        }
                    }
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
