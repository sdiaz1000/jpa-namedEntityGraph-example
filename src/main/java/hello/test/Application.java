package hello.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	
//	  @Autowired
//	    private CourseRepository studentCourseRepository;
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(CourseRepository studentCourseRepository, EntityManager em) {
		return (args) -> {

			// save a couple of courses
	        final Course courseA = new Course("Course A");
	        Set studentASet = new HashSet<Student>(){{
	            add(new Student("Student A1", courseA));
	            add(new Student("Student A2", courseA));
	        }};
	        courseA.setStudents(studentASet);

	        Set teacherASet = new HashSet<Teacher>(){{
//	            add(new Teacher("Mr Pants", courseA));
//	            add(new Teacher("Mr FRED", courseA));
	            add(new Teacher("Mr Pants"));
	            add(new Teacher("Mr FRED"));
	        }};
	        courseA.setTeachers(teacherASet);
	        
	        final Course courseB = new Course("Course B");
	        Set studentBSet = new HashSet<Student>(){{
	            add(new Student("Student B1", courseB));
	            add(new Student("Student B2", courseB));
	        }};
	        courseB.setStudents(studentBSet);
	        //persit entities to database
	        
	        studentCourseRepository.save(courseA);
	        studentCourseRepository.save(courseB);
	 
	        EntityGraph<?> entityGraph = em.getEntityGraph("graph.Course.both");
	        
	        Map hints = new HashMap();
	        hints.put("javax.persistence.fetchgraph", entityGraph);

//	        return this.em.find(Order.class, orderId, hints);
	        Object course = em.find(Course.class, 1, hints);
	        
	        
	        
	        // fetch all courses
//	        for (Course course : studentCourseRepository.findAll()) {
	            System.out.println(course.toString());
//	        }
		};
	}
}
