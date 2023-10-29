import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class University {
    private List<Student> students;
    private Admin admin;

    public static void main(String[] args) {
        (new University()).displayMenu();
    }

    // Constructor for University
    public University() {
        admin = new Admin(); // Calls the admin constructor
        Data.init(); // Initialize the database
        students = Data.readStudents(); // Empty if initialized or Data if file existed
    }

    // RETURN TRUE/FALSE depending if both email and password pattern is correct
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

    // Uses match function in student, return student if ID matches from list/ or
    // Null
    private Student findStudentbyID() {
        System.out.print("\tRemove by ID: ");
        String studentID = In.nextLine();
        students = Data.readStudents();

        for (Student s : students) {
            if (s.matchbyID(studentID)) {
                System.out.println(Colors.YELLOW + "\tRemoving Student " + studentID + " Account" + Colors.RESET);

                return s;
            }
        }
        System.out.println(Colors.RED + "\tStudent " + studentID + " does not exist" + Colors.RESET);
        return null;
    }

    // Return student only if there is entry with db which have same email AND
    // password, if just 1 of them match , null
    private Student findStudent(String email, String password) {
        students = Data.readStudents();
        for (Student s : students) {
            if (s.match(email, password)) {
                return s;
            }
        }
        return null;
    }

    // if Y - write and empty student list into the database and clear existing list
    public void clearDatabase() {
        System.out.print(Colors.RED + "\tAre you sure you want to clear the databse (Y)es/ (N)o : " + Colors.RESET);
        char charac = In.nextChar();

        while (charac != 'Y' || charac != 'N') {
            if (charac == 'Y') {
                students = Data.readStudents();
                System.out.println(Colors.YELLOW + "\tClearing Student Database" + Colors.RESET);
                Data.deleteAllStudentData(students);
                break;
            } else if (charac == 'N')
                break;
            else {
                System.out.println(Colors.YELLOW + "\tY - Yes | N - No" + Colors.RESET);
                System.out.println(Colors.RED + "\tAre you sure you want to clear the databse (Y)es/ (N)o"
                        + Colors.RESET);
                charac = In.nextChar();
            }
        }
    }

    public void groupByGrade() {
        List<Student> students = Data.readStudents();
        String[] gradeLetters = { "HD", "D", "CR", "P", "F" };

        ArrayList<String> gradeString = new ArrayList<>();

        if (students.isEmpty())
            System.out.println("\t\t<Nothing to display>");
        else {
            System.out.println(Colors.YELLOW + "\tGrade Grouping" + Colors.RESET);
            for (String g : gradeLetters) { // for each grade
                gradeString.clear();
                // gradeString.add(g);
                for (Student s : students) { // for each student inside each grade
                    if (s.getterAverageGrade().equals(g)) // current student grade matches grade
                        gradeString.add(s.nameGetter() + " :: " +
                                s.IDGetter() + " --> GRADE: " + g + " - MARKS: " + s.getterAverageMarks()); // adds to
                                                                                                            // the
                                                                                                            // string
                                                                                                            // list
                }
                if (gradeString.size() >= 1)
                    System.out.println("\t" + g + " --> " + gradeString.toString()); // this if condition is there so
                                                                                     // that only grades where there are
                                                                                     // student are printed
            }
        }
    }

    public void partitionPassFail() {
        List<Student> students = Data.readStudents();

        List<Student> studentPassList = new ArrayList<>();
        List<Student> studentFailList = new ArrayList<>();

        ArrayList<String> PassList = new ArrayList<>();// empty string list for printing like rubric
        ArrayList<String> FailList = new ArrayList<>();// empty string list for printing like rubric

        for (Student s : students) { // Partitioned as pass or fail
            if (s.getterAverageMarks() >= 50) {
                studentPassList.add(s);
            } else {
                if (s.getSubjectList().size() >= 1)
                    studentFailList.add(s);
            }
        }
        System.out.println(Colors.YELLOW + "\tPASS/FAIL Partition" + Colors.RESET);

        for (Student student : studentFailList) {
            FailList.add(student.nameGetter() + " :: " + student.IDGetter() + "--> GRADE: "
                    + student.getterAverageGrade() + " - MARK: " + student.getterAverageMarks() + ", "); // building our
                                                                                                         // string
        }

        for (Student student : studentPassList) {
            PassList.add(student.nameGetter() + " :: " + student.IDGetter() + "--> GRADE: "
                    + student.getterAverageGrade() + " - MARK: " + student.getterAverageMarks()); // building our string
        }
        System.out.println("\tFAIL --> " + FailList.toString());
        System.out.println("\tPASS --> " + PassList.toString());
    }

    // calls the find student by id and deletes it if returned
    public void removeStudentByID() {
        Student toRemove = findStudentbyID();
        if (toRemove != null) {
            // System.out.println("YAYYY reached inside here"); //debugging
            // students = Data.readStudents();
            students.remove(toRemove);
            Data.saveStudentData(students);
        }
    }

    // for each student - call overwritten tostring
    public void displayStudentsList() {
        students = Data.readStudents();
        if (students.isEmpty())
            System.out.println("\t\t<Nothing to display>");
        else {
            System.out.println(Colors.YELLOW + "\tStudent List" + Colors.RESET);
            for (Student s : students) {
                System.out.println(s.toString());
            }
        }
    }

    // bn 000001 - 999999 through string builder
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

    private void studentRegister() {
        System.out.print("\tEmail: ");
        String email = In.nextLine().toLowerCase();
        System.out.print("\tPassword: ");
        String password = In.nextLine();

        students = Data.readStudents();
        if (verifyCredentials(email, password)) {
            Student curStudent = findStudent(email, password);
            System.out.println(Colors.YELLOW + "\tEmail and password formats acceptable" + Colors.RESET);

            if (curStudent == null) { // proceed if no student is return after matching
                System.out.print("\tName: ");
                String name = In.nextLine();
                // Functionality which will generate random ID is also pending here
                System.out.println(Colors.YELLOW + "\tEnrolling student " + name + Colors.RESET);
                students.add(new Student(name, email, password, generateID()));
                Data.saveStudentData(students);
            } else // if STUDENT ALREADY EXISTS
                System.out
                        .println(
                                Colors.RED + "\tStudent " + curStudent.nameGetter() + " already exists" + Colors.RESET);
        } else {
            System.out.println(Colors.RED + "\tIncorrect email or password format" + Colors.RESET);
            studentRegister();
        }

    }

    private void studentLogin() {
        System.out.print("\tEmail: ");
        String email = In.nextLine().toLowerCase();
        System.out.print("\tPassword: ");
        String password = In.nextLine();

        if (verifyCredentials(email, password)) {
            System.out.println(Colors.YELLOW + "\tEmail and password formats acceptable" + Colors.RESET);
            Student curStudent = findStudent(email, password);
            if (curStudent != null) // proceed only if find method returns a student
                studentCourseMenu(curStudent);
            else // if find returned null
                System.out.println(Colors.RED + "\tStudent does not exist" + Colors.RESET);
        } else {
            System.out.println(Colors.RED + "\tIncorrect email or password format" + Colors.RESET);
            studentLogin();
        }
    }

    private void studentCourseMenu(Student s) {
        System.out.print(Colors.CYAN + "\t\tStudent Course Menu(c/e/r/s/x): " + Colors.RESET);
        char c;
        while ((c = In.nextChar()) != 'x') {
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
            System.out.print(Colors.CYAN + "\t\tStudent Course Menu(c/e/r/s/x): " + Colors.RESET);
        }
    }

    private void studentMenu() {
        System.out.print(Colors.CYAN + "\tStudent System (l/r/x): " + Colors.RESET);
        char c;
        while ((c = In.nextChar()) != 'x') {
            switch (c) {
                case 'l':
                    System.out.println(Colors.GREEN + "\tStudent Sign In" + Colors.RESET);
                    studentLogin();
                    break;
                case 'r':
                    System.out.println(Colors.GREEN + "\tStudent Sign Up" + Colors.RESET);
                    studentRegister();
                    break;
                default:
                    // Help menu
                    break;
            }
            System.out.print(Colors.CYAN + "\tStudent System (l/r/x): " + Colors.RESET);
        }
        // System.out.println(Colors.YELLOW + "Thank You" + Colors.RESET);
    }

    private void adminMenu(University uni) {
        // students = Data.readStudents();
        admin.adminMenu(uni);
    }

    // Function : Displays the (home) Menu
    public void displayMenu() {
        char c;
        System.out.print(Colors.CYAN + "University System: (A)dmin, (S)tudent or X: " + Colors.RESET);
        while ((c = In.nextChar()) != 'X') {
            switch (c) {
                case 'A':
                    adminMenu(this);
                    break;
                case 'S':
                    studentMenu();
                    break;
                default:
                    // Help menu
                    break;
            }
            System.out.print(Colors.CYAN + "University System: (A)dmin, (S)tudent or X: " + Colors.RESET);
        }
        System.out.println(Colors.YELLOW + "Thank You" + Colors.RESET);
    }
}
