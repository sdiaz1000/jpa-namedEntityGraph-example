package hello.test;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Teacher implements Serializable{
		
	private static final long serialVersionUID = -3295618803288063735L;
	private int id;
    private String name;
//    private Course course;
 
    public Teacher() {
 
    }
 
    public Teacher(String name) {
        this.name = name;
    }
 
//    public Teacher(String name, Course course) {
//        this.name = name;
//        this.course = course;
//    }
 
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
 
//    @ManyToOne
//    @JoinColumn(name = "course_id")
//    public Course getCourse() {
//        return course;
//    }
// 
//    public void setCourse(Course course) {
//        this.course = course;
//    }
}
