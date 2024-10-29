package output;

import models.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentWriter extends CollectionWriter<Student> {
    public void toFile (List<Student> students) throws IOException {
        ArrayList<String> studentsOutput = new ArrayList<>();
        for (Student student : students) {
            String studentString = getString(student);
            studentsOutput.add(studentString);
        }
        writeCollection("students", studentsOutput);
    }

    public void toFile (Student student) throws IOException {
        String studentString = getString(student);
        writeElement("students_found", studentString);
    }

    private String getString (Student student) {
        return student.getGroupNumber()
                +delimiter
                +student.getAvgScore()
                +delimiter
                +student.getRecordBookNumber();
    }
}