import java.util.ArrayList;
import java.util.List;

public class Student {

    private String name;
    private String email;
    private String password;
    private int studentID;
    private List<Subject> enrolledSubjects;

    public Student(String name, String email, String password, int studentID) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.studentID = studentID;
        this.enrolledSubjects = new ArrayList<>();
    }

    public void enrolSubject() {

    }

    public void withdrawSubject() {

    }

    public void viewEnrollment() {

    }
}
