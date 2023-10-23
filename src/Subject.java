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
        return ID;

    }

    public int getMark() {
        return mark;

    }

    public String getGrade() {
        return grade;
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

    @Override
    public String toString() {

        return String.format("Subject ID: %03d\nMark: %d\nGrade: %s", ID, mark, grade);

    }

}
