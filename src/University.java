import java.util.List;

public class University {
    private List<Student> students;
    private List<Subject> subjects;
    private Admin admin;

    public static void main(String[] args) {

    }

    public University() {
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
        System.out.print("University System: (A)dmin, (S)tudent or X");
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
        // returns student after matching
    }

    private void studentLogin() {

    }

    private void adminLogin() {
    }

    public void displayMenu() {
        System.out.println();
        char c;
        while ((c = readChoice()) != 'X') {
            switch (c) {
                case 'A':
                    adminLogin();
                    break;
                case 'S':
                    studentLogin();
                    break;
                default:
                    // Help menu
                    break;
            }

        }
    }
}
