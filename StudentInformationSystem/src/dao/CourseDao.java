package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import entity.model.Course;
import entity.model.Teacher;
import exception.CourseNotFoundException;
import exception.InvalidCourseDataException;
import exception.StudentNotFoundException;
import exception.TeacherNotFoundException;

public interface CourseDao {

	public void assignTeacher(Course course, Teacher teacher) throws FileNotFoundException, ClassNotFoundException, IOException, TeacherNotFoundException, CourseNotFoundException;
	public void updateCourseInfo(Course course,String courseName, int credits, long teacherId) throws FileNotFoundException, ClassNotFoundException, IOException, CourseNotFoundException, InvalidCourseDataException;
	public void displayCourseInfo(Course course) throws FileNotFoundException, ClassNotFoundException, IOException, CourseNotFoundException;
	public List<String> getEnrolledStudentNames(Course course) throws FileNotFoundException, ClassNotFoundException, IOException, CourseNotFoundException, StudentNotFoundException;
	public Teacher getAssignedTeachersNames(Course course) throws FileNotFoundException, ClassNotFoundException, IOException, CourseNotFoundException;

}
