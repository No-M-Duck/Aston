package models;

public class Student {
    private final int groupNumber;
    private final double averageScore;
    private final int recordBookNumber;

    public static class StudentBuilder {
        private int groupNumber;
        private double averageScore;
        private int recordBookNumber;

        public StudentBuilder groupNumber(int groupNumber) {
            this.groupNumber = groupNumber;
            return this;
        }

        public StudentBuilder avgScore(double averageScore) {
            this.averageScore = averageScore;
            return this;
        }

        public StudentBuilder recordBookNumber(int recordBookNumber) {
            this.recordBookNumber = recordBookNumber;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }

    private Student(StudentBuilder builder) {
        this.groupNumber = builder.groupNumber;
        this.averageScore = builder.averageScore;
        this.recordBookNumber = builder.recordBookNumber;
    }
    public int getGroupNumber(){
        return groupNumber;
    }
    public double getAvgScore(){
        return averageScore;
    }
    public int getRecordBookNumber() {
        return recordBookNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "groupNumber=" + groupNumber +
                ", averageScore=" + averageScore +
                ", recordBookNumber='" + recordBookNumber + '\'' +
                '}';
    }
}

