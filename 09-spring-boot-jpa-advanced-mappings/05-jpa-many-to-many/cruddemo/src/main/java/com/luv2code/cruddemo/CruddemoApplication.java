package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);}


	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner->{
			//createCourseAndStudents(appDAO);
			//findCourseAndStudents(appDAO);
			//findStudentAndCourses(appDAO);
			//addMoreCoursesForStudent(appDAO);
			//deleteCourse(appDAO);
			//deleteStudent(appDAO);
		};

	}

	private void deleteStudent(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting id: " + theId);
		appDAO.deleteStudentById(theId);
		System.out.println("Done!");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {
		int theId = 2;
		Student tempStudent = appDAO.findStudentAndCourseByStudentId(theId);

		Course course1 = new Course("Rubik's Cube - How To Speed Cube");
		Course course2 = new Course("Atari 2600 - Game Development");

		//add course to student
		tempStudent.addCourse(course1);
		tempStudent.addCourse(course2);

		System.out.println("Updating student: " + tempStudent);
		System.out.println("Associated courses: " + tempStudent.getCourses());

		appDAO.update(tempStudent);

		System.out.println("Done!");

	}

	private void findStudentAndCourses(AppDAO appDAO) {
		int theId=2;
		Student tempStudent = appDAO.findStudentAndCourseByStudentId(theId);

		System.out.println("Loaded student: " + tempStudent);
		System.out.println("Courses: " + tempStudent.getCourses());

		System.out.println("Done!");
	}

	private void findCourseAndStudents(AppDAO appDAO) {
		int theId =10;
		Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);
		System.out.println("Loaded course: " + tempCourse);
		System.out.println("Students: " + tempCourse.getStudents());

		System.out.println("Done!");


	}

	private void createCourseAndStudents(AppDAO appDAO) {
		//create a course
		Course tempCourse = new Course("Pacman - How To Score One Million Points");

		//create a student
		Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
		Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");

		//add students to the course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);

		//save the course and associated students
		System.out.println("Saving the course: " + tempCourse);
		System.out.println("Associated students: " + tempCourse.getStudents());

		appDAO.save(tempCourse);
		System.out.println("Done!");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Deleting course id: " + theId);
		appDAO.deleteCourseById(theId);

		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		//get the course and reviews
		int theId = 10;
		Course tempCourse = appDAO.findCourseAndReviewByCourseId(theId);

		//print the course
		System.out.println(tempCourse);

		//print reviews
		System.out.println(tempCourse.getReviews());

		System.out.println("Done!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		//create a course
		Course tempCourse = new Course("Pacman - How To Score One Million Point");

		//add some review
		tempCourse.addReview(new Review("Great Course...loved it!"));
		tempCourse.addReview(new Review("Cool Course, job well done."));
		tempCourse.addReview(new Review("What a dump course, you are an idiot."));

		//save the course and leverage cascade all
		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.save(tempCourse);

		System.out.println("Done!");

	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Deleting course id: " + theId);
		appDAO.deleteCourseById(theId);

		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 10;

		//find the course
		System.out.println("Finding course id: " + theId);
		Course tempCourse = appDAO.findCourseById(theId);

		//update course
		System.out.println("Updating course id: " + theId);
		tempCourse.setTitle("Enjoy the Simple Things");
		appDAO.update(tempCourse);

		System.out.println("Done!");
	}

	private void updateInstructor(AppDAO appDAO) {
		int tempId = 1;
		System.out.println("Finding instructor id: " + tempId);
		Instructor tempInstructor = appDAO.findInstructorById(tempId);
		//update instructor
		System.out.println("Updating instructor id: " + tempId);
		tempInstructor.setLastName("TESTER");
		appDAO.update(tempInstructor);
		System.out.println("Done");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId =1;
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);
		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associate courses: " + tempInstructor.getCourses() );
		System.out.println("Done!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId =1;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);

		//find courser for instructor
		System.out.println("Finding courses for instructor id: " + theId);

		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		tempInstructor.setCourses(courses);

		System.out.println("The associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}


	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId =1;
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		//create the instructor
		Instructor tempInstructor =
				new Instructor("Susan", "Public", "susan@luv2code.com");

		//create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail("http://www.youtube.com",
						"Video Games");
		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//create some courses
		Course tempCourse= new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2= new Course("The Pinball Masterclass");

		//add courses instructor
		tempInstructor.add(tempCourse);
		tempInstructor.add(tempCourse2);

		//save the instructor
		//Note: this will also save the courses
		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("The courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);
		System.out.println("Done!");


	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId =3;
		System.out.println("Deleting instructor detail id: " + theId);
		appDAO.deleteInstructorDetailById(theId);
		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		//get instructor detail object
		int theId=2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);
		//print the instructor detail
		System.out.println("tempInstructorDetail: " + tempInstructorDetail);

		//print the associated instructor
		System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId =1;
		System.out.println("Deleting instructor id: " + theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Done!");

	}

	private void findInstructor(AppDAO appDAO) {
		int theId=2;
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associate instructor detail only: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		/*
		//create the instructor
		Instructor tempInstructor =
				new Instructor("Chad", "Darby", "darby@luv2code.com");

		//create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail("http://www.luv2code.com/youtube",
						"Luv 2 code!!!");
						
		 */

		//create the instructor
		Instructor tempInstructor =
				new Instructor("Madhu", "Patel", "madhu@luv2code.com");

		//create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail("http://www.luv2code.com/youtube",
						"Guitar");
		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//save the instructor
		// this also will save details object
		//because of CascadeType.All
		//
		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);
	}
}
