//NOTE : This class could later be converted to StudentData.java if need need seperate databasr fir subjects - to discuss

import java.io.*;
import java.io.File;
import java.util.*;

public class Data {
    private static String filename = "students.data";

    public static void init() {
        try {
            List<Student> students = new ArrayList<>();
            File file = new File(filename);

            if (!file.exists()) {
                file.createNewFile(); // creates the empty file with name specified in line 13-> line 8
                // For writing raw data to the file
                FileOutputStream fileOut = new FileOutputStream(filename);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(students); // write students to the stream
                objectOut.close();
                fileOut.close();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveStudentData(List<Student> students) {
        try (FileOutputStream fileOut = new FileOutputStream(filename)) {
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(students);
            objOut.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Student> readStudents() {
        List<Student> students = null;
        try (FileInputStream fileIn = new FileInputStream(filename)) { // READ data as stream of bytes
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            students = (List<Student>) objIn.readObject(); // CAST as list of students
            objIn.close();
            fileIn.close();
            return students;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteAllStudentData(List<Student> students) {
        try (FileOutputStream fileOut = new FileOutputStream(filename)) {
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
 * Hey Guys ignore this block on commented code
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