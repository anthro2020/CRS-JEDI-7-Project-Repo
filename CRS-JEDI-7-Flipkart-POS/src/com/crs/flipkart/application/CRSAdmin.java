
package com.crs.flipkart.application;

import java.util.*;

import com.crs.flipkart.bean.*;
import com.crs.flipkart.business.*;

/**
 * @author jasan
 *Admin Application
 */
public class CRSAdmin {

	AdminInterface adminService = new AdminService();
	Scanner sc=new Scanner(System.in);
	
	/**
     * method for displaying admin dashboard and selecting the available choices.
     */
	public void create_menu()
	{
		Scanner sc = new Scanner(System.in);
		int choice;
		do {

				System.out.println("\n\n___________________________________________________________________");
				System.out.println("");
				System.out.println("                            ADMIN DASHBOARD                        ");          
				System.out.println("___________________________________________________________________\n");
				System.out.println("1. View Courses");
				System.out.println("2. Add Courses");
				System.out.println("3. Drop Courses");
				System.out.println("4. Approve Student");
				System.out.println("5. Add Professor");
				System.out.println("6. Drop Professor");
				System.out.println("7. View Professor List");
				System.out.println("8. Generate Grade Card");
				System.out.println("9. Logout");
				System.out.print("Option : ");
			
				choice = sc.nextInt();
			
				switch (choice) {
				
				case 1:
					// View Courses
					viewCourses();
					break;
					
				case 2: 
					// Add Course;
					addCourse();
					break;
					
				case 3:
					// Drop Course
					dropCourse();
					break;
					
				case 4:
					approveStudent();
					break;
					
				case 5:
					addProfessor();
					break;
					
				case 6:
					// View Professor List
					dropProfessor();
					break;
					
				case 7:
					viewProfessorList();
					break;
				
				case 8:
					// Generate Grade Card
					generateGradeCard();
					break;
					
				case 9:
					CRSApplication.loggedIn = false;
					break;			
					
				default:
					System.out.println("Incorrect Choice!");
			}
			
		}while(CRSApplication.loggedIn);
	}
	
	 /**
     * method for viewing the courses
     */
	public void viewCourses() {
		// TODO Auto-generated method stub
		List<Course>courseList=adminService.viewCourse();
		for(Course course: courseList)
		{
			
				System.out.println(course.getCourseId() +" : "+ course.getCourseName()+" taught by : "+course.getProfessorId());
		}
		
	}
	
	/**
     * method for adding a course
     */
	public void addCourse() {
		// TODO Auto-generated method stub
		Course course=new Course();
		System.out.print("Enter the course Id :");
		course.setCourseId(sc.nextLine());
		if(adminService.verifyCourse(course.getCourseId()))
		{
			System.out.println("Course already exists");
			return;
		}
		System.out.print("Enter the course name :");
		course.setCourseName(sc.nextLine());
		System.out.print("Enter the course fee :");
		course.setCourseFee(sc.nextFloat());
		sc.nextLine();
		System.out.print("Enter the department name :");
		course.setDepartmentName(sc.nextLine());
		course.setProfessorId(null);
		course.setStudentCount(0);
		System.out.println(adminService.addCourse(course));
	}
	
	/**
     * method for removing a course
     */
	public void dropCourse() {
		// TODO Auto-generated method stub
		System.out.print("Enter the course Id :");
		String courseId=sc.nextLine();
		System.out.println(adminService.dropCourse(courseId));
	}
	
	 /**
     * method for approving student registration
     */
	public void approveStudent() {
		// TODO Auto-generated method stub
		System.out.println("List of students to be approved : ");
		List<Student> studentList = adminService.getPendingStudents();
		int i=1;
		for(Student student:studentList)
		{
			System.out.println(i+". ID : "+student.getStudentEnrollmentId()+"     Name : "+student.getName());
			i++;
		}
		System.out.print("Serial number of student to approve or 0 : ");
		int studentIndex=sc.nextInt();
		if(studentIndex>0)
		{
			System.out.println(adminService.approveStudent(studentList.get(studentIndex-1)));
		}
	}
	
	 /**
     * method for adding a professor
     */
	public void addProfessor() {
		// TODO Auto-generated method stub
		Professor newProfessor = new Professor();
		System.out.print("Enter the professor name :");
		newProfessor.setName(sc.nextLine());
		sc.nextLine();
		System.out.print("Enter email : ");
		newProfessor.setEmail(sc.nextLine());
		System.out.print("Enter password : ");
		newProfessor.setPassword(sc.nextLine());
		System.out.print("Enter the professor salary :");
		newProfessor.setSalary(sc.nextFloat());
		sc.nextLine();
		System.out.print("Enter the department name :");
		newProfessor.setDepartment(sc.nextLine());
		System.out.print("Enter the doj :");
		newProfessor.setDoj(sc.nextLine());
		System.out.print("Enter the contact number:");
		newProfessor.setContactNumber(sc.nextLine());
		System.out.println(adminService.addProfessor(newProfessor));
	}
	
	 /**
     * method for removing a professor
     */
	public void dropProfessor() {
		// TODO Auto-generated method stub
		viewProfessorList();
		List<Professor> professorList = adminService.viewProfessorList();
		System.out.print("Enter the serial number :");
		int index=sc.nextInt();
		System.out.println(adminService.dropProfessor(professorList.get(index-1).getProfessorId()));
		
	}
	
	 /**
     * method for viewing all professors
     */
	public void viewProfessorList() {
		// TODO Auto-generated method stub
		List<Professor> professorList = adminService.viewProfessorList();
		int i=1;
		for(Professor professor:professorList)
		{
			System.out.println(i+". ID : "+professor.getProfessorId()+"     Name : "+professor.getName()+"    Department : "+professor.getDepartment());
			i++;
		}
	}
	
	 /**
     * method for generating the student grade card
     */
	public void generateGradeCard() {
		// TODO Auto-generated method stub
		System.out.print("Enter the student Id :");
		String studentId=sc.next();
		System.out.print("Enter the semester :");
		String semester=sc.next();
		System.out.println(adminService.generateGradeCard(studentId, semester));
	}
	
	
}
