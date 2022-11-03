/**
 * The Student class represents a student object that has fields:
 * name, age, id number, graduation year, cumulative year.
 */
public class Student {
    private String name;
    private Integer age;
    private Integer studentID;
    private Integer yearOfGrad;
    private Double cumGpa;

    public Student(){}

    /**
     * Student constructs a Student object
     * @param name Student's name
     * @param stAge Student's age
     * @param stId Student's ID number
     * @param stGradYear Student's Year of Graduation
     * @param stCumGpa Student's Cumulative GPA
     */
    public Student(String name, Integer stAge, Integer stId, Integer stGradYear, Double stCumGpa){
        this.name = name;
        age = stAge;
        studentID = stId;
        yearOfGrad = stGradYear;
        cumGpa = stCumGpa;
    }

    /**
     *
     * @return returns the name of the student.
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @param name sets the name of a student.
     */
    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {this.studentID = studentID;}

    public Integer getYearOfGrad() {
        return yearOfGrad;
    }

    public void setYearOfGrad(Integer yearOfGrad) {
        this.yearOfGrad = yearOfGrad;
    }

    public Double getCumGpa() {
        return cumGpa;
    }

    public void setCumGpa(Double cumGpa) {
        this.cumGpa = cumGpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", studentID=" + studentID +
                ", yearOfGrad=" + yearOfGrad +
                ", cumGpa=" + cumGpa +
                '}';
    }
}
