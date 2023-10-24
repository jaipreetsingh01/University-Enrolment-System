import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {

    private String name;
    private String email;
    private String password;
    private String studentID;
    private List<Subject> enrolledSubjects;

    public Student(String name, String email, String password, String studentID) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.studentID = studentID;
        this.enrolledSubjects = new ArrayList<>();
    }

    public String studentEmailGetter() {
        return this.email;
    }

    public String studentPasswordGetter() {
        return this.password;
    }

    public String nameGetter() {
        return this.name;
    }

    public String IDGetter() {
        return this.studentID;

    }

    public float getAverageMarks() {
        float average = 0;
        for (Subject s : this.enrolledSubjects) {
            average += s.getMark();
        }
        return average;
    }

    public void updatePassword() {
        System.out.print("New Password: ");
        String newPass = In.nextLine();
        this.password = newPass;
    }

    public void enrolSubject() {
        if (this.enrolledSubjects.size() < 4) {
            Subject subject = new Subject();
            this.enrolledSubjects.add(subject);
            System.out.println("Enrolled in the subject-: " + subject.getID());
            System.out.println("Enrolled in " + this.enrolledSubjects.size() + " out of 4 subjects");
        } else
            System.out.println("Students are allowed to enroll in 4 subjects only");

        // REMEBER TO CALL DATA SAVE FROM UNIVERSITY
    }

    private Subject findSubject(String ID) {
        for (Subject sub : this.enrolledSubjects) {
            if (sub.match(ID)) {
                return sub;
            }
        }
        return null;
    }

    public void withdrawSubject() {
        if (this.enrolledSubjects.size() > 1) {
            System.out.print("ID: ");
            String ID = In.nextLine();

            Subject subjectToRemove = this.findSubject(ID);
            if (subjectToRemove != null) {
                this.enrolledSubjects.remove(subjectToRemove);
                System.out.println("Removed");
            } else
                System.out.println("No subject with that ID");

        } else
            System.out.println("Minimum 1 Subject");
    }

    public void viewEnrollment() {
        if (enrolledSubjects.isEmpty()) {
            System.out.println("You are not enrolled in any subjects.");
        } else {
            System.out.println("Enrolled in the following subjects:");
            for (Subject subject : enrolledSubjects) {
                System.out.println(subject.toString());
            }
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
     * 
     * public void viewEnrollment() {​​
     * if (enrolledSubjects.isEmpty()) {​​
     * System.out.println(name + " is not enrolled in any subjects.");
     * }​​ else {​​
     * System.out.println(name + " is enrolled in the following subjects:");
     * for (Subject subject : enrolledSubjects) {​​
     * System.out.println("- " + subject.getSubjectName());
     * }​​
     * }​​
     * }​ ​
     * 
     * public void withdrawSubject(Subject subject) {​​
     * // add Remove Subject by ID: and input ID
     * enrolledSubjects.remove(subject);
     * }
     * 
     * class Subject {
     * private String subjectName;
     * 
     * public Subject(String subjectName) {
     * this.subjectName = subjectName;
     * }
     * 
     * public String getSubjectName() {
     * return subjectName;
     * }
     * }​
     */

    public boolean match(String email, String password) {
        return (this.email.equals(email) && this.password.equals(password));
    }
}