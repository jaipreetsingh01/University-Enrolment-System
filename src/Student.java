import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {

    private String name;
    private String email;
    private String password;
    private String studentID;
    private List<Subject> enrolledSubjects;
    private float averageMarks;
    private String averageGrade;

    public Student(String name, String email, String password, String studentID) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.studentID = studentID;
        this.enrolledSubjects = new ArrayList<>();
        this.averageMarks = 0;
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

    public void updatePassword() {
        System.out.print(Colors.YELLOW + "Updating Password: " + Colors.RESET);
        System.out.print("New Password: ");
        String newPass = In.nextLine();

        System.out.print("Confirm Password: ");
        String confrimNewPass = In.nextLine();

        while (!newPass.equals(confrimNewPass)) {
            System.out.println(Colors.RED + "Password does not match - try again" + Colors.RESET);
            System.out.print("Confirm Password: ");
            confrimNewPass = In.nextLine();
        }

        this.password = newPass;
    }

    public void enrolSubject() {
        if (this.enrolledSubjects.size() < 4) {
            Subject subject = new Subject();
            this.enrolledSubjects.add(subject);
            System.out.println(Colors.YELLOW + "Enrolling in subject-" + subject.getID() + Colors.RESET);
            System.out.println(Colors.YELLOW + "You are now enrolled in " + this.enrolledSubjects.size()
                    + " out of 4 subjects" + Colors.RESET);
            this.calculateAverageMarks();
            this.calculateAverageGrade();
        } else
            System.out.println(Colors.RED + "Students are allowed to enroll in 4 subjects only" + Colors.RESET);

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

    public boolean match(String email, String password) {
        return (this.email.equals(email) && this.password.equals(password));
    }

    public void withdrawSubject() {
        if (this.enrolledSubjects.size() > 1) {
            System.out.print("Remove subject by ID: ");
            String ID = In.nextLine();

            Subject subjectToRemove = this.findSubject(ID);
            if (subjectToRemove != null) {
                this.enrolledSubjects.remove(subjectToRemove);
                System.out.println(Colors.YELLOW + "Dropping Subject-" + ID + Colors.RESET);
                System.out.println(Colors.YELLOW + "You are now enrolled in " + this.enrolledSubjects.size()
                        + " out of 4 Subjects" + Colors.RESET);
                this.calculateAverageMarks();
                this.calculateAverageGrade();
            } else
                System.out.println(Colors.RED + "Subject with that ID does not exist" + Colors.RESET);

        } else
            System.out
                    .println(Colors.RED + "Students are required to be enrolled in minimum one subject" + Colors.RESET);
    }

    public void viewEnrollment() {
        if (enrolledSubjects.isEmpty()) {
            System.out.println(Colors.YELLOW + "Showing 0 Subjects" + Colors.RESET);
        } else {
            System.out.println(Colors.YELLOW + "Showing " + this.enrolledSubjects.size() + " Subjects" + Colors.RESET);
            for (Subject subject : enrolledSubjects) {
                System.out.println(subject.toString());
            }
        }
    }

    public boolean matchbyID(String ID) {
        return (this.studentID.equals(ID));
    }

    public String toString() {
        calculateAverageGrade();
        calculateAverageMarks();
        return String.format("%s :: %s --> Email: %s", this.name, this.studentID,
                this.email);
    }
}