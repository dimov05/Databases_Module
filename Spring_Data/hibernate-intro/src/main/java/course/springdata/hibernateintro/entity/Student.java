package course.springdata.hibernateintro.entity;

import java.util.Date;

public class Student {
    private Long id;
    private String name;
    private Date registrationDate = new Date();

    public Student(String name) {
        this.name = name;
    }

    public Student(Long id, String name, Date registrationDate) {
        this.id = id;
        this.name = name;
        this.registrationDate = registrationDate;
    }

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return id.equals(student.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
