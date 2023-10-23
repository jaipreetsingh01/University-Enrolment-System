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

    public void withdrawSubject() { // MG

    }

    public void viewEnrollment() { // MG

    }

    }

    /*
     * public void enrolSubject(Subject subject) {
     * if (enrolledSubjects.size() < 4) {
     * enrolledSubjects.add(subject);
     * System.out.println("You have enrolled in the subject");
     * // System.out.println("You are enrolled in " +
     * toString(enrolledSubjects.size()));
     * // need to insert subject name code
     * } else 
     * {
     * System.out.println("Students are allowed to enrol in 4 subjects only");
     * }
     * }
     */

    /* 

    public void viewEnrollment() {​​
        if (enrolledSubjects.isEmpty()) {​​
            System.out.println(name + " is not enrolled in any subjects.");
        }​​ else {​​
            System.out.println(name + " is enrolled in the following subjects:");
            for (Subject subject : enrolledSubjects) {​​
                System.out.println("- " + subject.getSubjectName());
            }​​
        }​​
    }​ ​

    public void withdrawSubject(Subject subject) {​​
        // add Remove Subject by ID: and input ID
        enrolledSubjects.remove(subject);
    }

    class Subject {
        private String subjectName;
   
        public Subject(String subjectName) {
            this.subjectName = subjectName;
        }
   
        public String getSubjectName() {
            return subjectName;
        }
    }​ */
​