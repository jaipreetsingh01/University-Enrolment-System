//NOTE : This class could later be converted to StudentData.java if need need seperate databasr fir subjects - to discuss

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Data {
    private static String filename = "students.data";

    public static void init() {
        try {
            List<Student> students = new ArrayList<>();
            File file = new File(filename);

            if (!file.exists()) {
                file.createNewFile();
                FileOutputStream fileOut = new FileOutputStream(filename);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(students);
                objectOut.close();
                fileOut.close();
                // System.out.println(
                // file.createNewFile() ? "File " + filename + " Initialized"
                // : "Cannot initialize file: " + filename);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveStudentData(List<Student> students, File file) {
        try (FileOutputStream fileOut = new FileOutputStream(file)) {
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(students);
            objOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Student> readStudents(File file) {
        try (FileInputStream fileIn = new FileInputStream(file)) {
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            List<Student> students = (List<Student>) objIn.readObject();
            objIn.close();

            return students;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteAllStudentData(List<Student> students, File file) {
        try (FileOutputStream fileOut = new FileOutputStream(file)) {
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(new ArrayList<>());
            students.clear();
            objOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
 * Hey Guys ignore this block on commented code, i was following professor's
 * lecture
 * but i think this part is unneessary for scope of this assignment
 */

// try {
// // This allows us to placed data where source code of student class is
// located.
// File bin = new
// File(Student.class.getProtectionDomain().getCodeSource().getLocation().toURI());
// String directory = bin.getAbsolutePath();

// // To point to University folder where bin is located
// File folder = new File(Student.class.getPackageName());

// String filePath = directory + File.separator + folder;

// file.createNewFile();

// FileOutputStream fileOut = new FileOutputStream(file);
// ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
// objOut.writeObject(students);
// } catch (IOException | URISyntaxException e) {
// e.printStackTrace();
// }
// }