// tag::sample[]
package hello.test;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "course")
@NamedEntityGraph(name = "graph.Course.students", attributeNodes = @NamedAttributeNode(value = "students")) 
@NamedEntityGraph(name = "graph.Course.teachers", attributeNodes = @NamedAttributeNode(value = "teachers")) 
@NamedEntityGraph(name = "graph.Course.both", attributeNodes = { @NamedAttributeNode(value = "teachers"), @NamedAttributeNode(value = "students")}) 
public class Course  implements Serializable{

	private static final long serialVersionUID = 2165900900917717356L;
	
	private int id;
    private String name;
    private Set<Student> students;
    private Set<Teacher> teachers;

    public Course(){

    }

    public Course(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "course", cascade = CascadeType.ALL)
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        String result = String.format(
                "Course[id=%d, name='%s']%n",
                id, name);
        if (students != null) {
            for(Student student : students) {
                result += String.format(
                        "Student[id=%d, name='%s']%n",
                        student.getId(), student.getName());
            }
        }
        if (teachers != null) {
            for(Teacher teacher : teachers) {
                result += String.format(
                        "Teacher[id=%d, name='%s']%n",
                        teacher.getId(), teacher.getName());
            }
        }

        return result;
    }
}
		
