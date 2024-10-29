package output;

import models.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentWriter extends CollectionWriter<Student> {
    public void toFile (List<Student> students) {
        ArrayList<String> studentsOutput = new ArrayList<>();
        for (Student student : students) {
            String studentString = student.getGroupNumber()
                    +delimiter
                    +student.getAvgScore()
                    +delimiter
                    +student.getRecordBookNumber();
            studentsOutput.add(studentString);
        }
        writeFile("students", studentsOutput);
    }
}