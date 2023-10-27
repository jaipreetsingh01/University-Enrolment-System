import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {

    private String name;
    private String email;
    private String password;
    private String studentID;
    private List<Subject> enrolledSubjects;
    private float averageMarks = 0;
    private String averageGrade;

    public Student(String name, String email, String password, String studentID) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.studentID = studentID;
        this.enrolledSubjects = new ArrayList<>();
        calculateAverageGrade();
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

    public List<Subject> getSubjectList() {
        return this.enrolledSubjects;
    }

    public float getterAverageMarks() {
        return this.averageMarks;
    }

    public String getterAverageGrade() {
        return this.averageGrade;
    }

    private void calculateAverageMarks() {
        float average = 0;
        for (Subject s : this.enrolledSubjects) {
            average += s.getMark();
        }
        this.averageMarks = average / this.enrolledSubjects.size();
    }

    public void updatePassword() {
        System.out.print("New Password: ");
        String newPass = In.nextLine();

        System.out.print("Confirm Password: ");
        String confrimNewPass = In.nextLine();

        while (newPass != confrimNewPass) {
            System.out.println("Password does not match - try again");
            System.out.print("Confirm Password: ");
            confrimNewPass = In.nextLine();
        }

        this.password = newPass;
    }

    private void calculateAverageGrade() {
        this.calculateAverageMarks();
        if (this.averageMarks >= 85) {
            this.averageGrade = "HD"; // High Distinction
        } else if (this.averageMarks >= 75) {
            this.averageGrade = "D"; // Distinction
        } else if (this.averageMarks >= 65) {
            this.averageGrade = "CR"; // Credit
        } else if (this.averageMarks >= 50) {
            this.averageGrade = "P"; // Pass
        } else {
            this.averageGrade = "F"; // Fail
        }
    }

    public void enrolSubject() {
        if (this.enrolledSubjects.size() < 4) {
            Subject subject = new Subject();
            this.enrolledSubjects.add(subject);
            System.out.println("Enrolled in the subject-: " + subject.getID());
            System.out.println("Enrolled in " + this.enrolledSubjects.size() + " out of 4 subjects");
            this.calculateAverageMarks();
            this.calculateAverageGrade();
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
                System.out.println("Dropping Subject " + ID);
                System.out.println("You are now enrolled in " + this.enrolledSubjects.size() + " out of 4 Subjects");
                this.calculateAverageMarks();
                this.calculateAverageGrade();
            } else
                System.out.println("Subject with that ID does not exist");

        } else
            System.out.println("Minimum 1 Subject");
    }

    public void viewEnrollment() {
        if (enrolledSubjects.isEmpty()) {
            System.out.println("Showing 0 Subjects");
        } else {
            System.out.println("Showing " + this.enrolledSubjects.size() + " Subjects");
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

    public boolean matchbyID(String ID) {
        return (this.studentID.equals(ID));
    }

    public String toString() {
        calculateAverageGrade();
        calculateAverageMarks();
        return String.format("Student name :: %s --> Email: %s", this.name,
                this.email);
    }
}