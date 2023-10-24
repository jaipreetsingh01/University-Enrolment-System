import java.io.Serializable;
import java.util.Random;
import java.util.Formatter;

public class Subject implements Serializable {
    private String ID;
    private int mark;
    private String grade;

    public Subject() {
        this.ID = generateRandomID();
        this.mark = generateRandomMark();
        this.grade = determineGrade(mark);

    }

    public String getID() {
        return this.ID;

    }

    public int getMark() {
        return this.mark;

    }

    public String getGrade() {
        return this.grade;
    }

    private String generateRandomID() {
        Random random = new Random();
        int ID = random.nextInt(900) + 100;
        return String.format("%03d", ID);
    }

    private int generateRandomMark() {
        Random random = new Random();
        return random.nextInt(76) + 25;
    }

    private String determineGrade(int mark) {
        if (mark >= 85) {
            grade = "HD"; // High Distinction
        } else if (mark >= 75) {
            grade = "D"; // Distinction
        } else if (mark >= 65) {
            grade = "CR"; // Credit
        } else if (mark >= 50) {
            grade = "P"; // Pass
        } else {
            grade = "F"; // Fail
        }
        return grade;

    }

    public boolean match(String ID) {
        return (this.ID.equals(ID));
    }

    @Override
    public String toString() {
        // System.out.printf("Subject ID: %s -- Mark = %d -- Grade = %s", this.ID,
        // this.mark, this.grade);
        return String.format("Subject ID: %s -- Mark = %d -- Grade = %s", this.ID, this.mark, this.grade);
    }
}
