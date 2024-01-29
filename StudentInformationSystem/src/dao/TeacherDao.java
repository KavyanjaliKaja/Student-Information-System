package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import entity.model.Course;
import entity.model.Teacher;
import exception.InvalidTeacherDataException;
import exception.TeacherNotFoundException;

public interface TeacherDao {

	

	public void updateTeacherInfo(Teacher teacher, String firstName, String lastName, String email) throws SQLException, TeacherNotFoundException, InvalidTeacherDataException, FileNotFoundException, ClassNotFoundException, IOException;

	public void displayTeacherInfo(Teacher teacher) throws TeacherNotFoundException, FileNotFoundException, ClassNotFoundException, IOException;
	public List<Course> getAssignedCourses(Teacher teacher) throws SQLException, FileNotFoundException, ClassNotFoundException, IOException;

}
