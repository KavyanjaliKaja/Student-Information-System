package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.model.Course;
import entity.model.Teacher;
import exception.InvalidTeacherDataException;
import exception.TeacherNotFoundException;
import util.DBConnUtil;
import util.DBPropertyUtil;

public class TeacherDaoImpl implements TeacherDao {

	private static final String fileName="src/util/db.properties"; 
    private static final String URL = DBPropertyUtil.getConnectionString(fileName);
    
    
    //Get teacher Id By email
    public long getTeacherIdByEmail(Teacher teacher) throws  FileNotFoundException, ClassNotFoundException, IOException, TeacherNotFoundException {
        // Default value if course ID is not found
    	long teacherId=-1;
        try (Connection connection =DBConnUtil.getConnection(URL)) {
            String selectQuery = "SELECT teacher_id FROM teacher WHERE email = ?";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                selectStatement.setString(1, teacher.getEmail());
                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    if (resultSet.next()) {
                        teacherId = resultSet.getLong("teacher_id");
                    } else {
                        throw new TeacherNotFoundException("Teacher not found for given email " );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacherId;
    }

    
    public void updateTeacherInfo(Teacher teacher, String firstName, String lastName, String email) throws SQLException, TeacherNotFoundException, InvalidTeacherDataException, FileNotFoundException, ClassNotFoundException, IOException {
        try (Connection connection =DBConnUtil.getConnection(URL)) {
        	if (isInvalidTeacherData(firstName, lastName,  email)) {
                throw new InvalidTeacherDataException("Invalid data provided for updating teacher. At least one of Name or Email should be provided.");
            }
            String updateQuery = "UPDATE teacher SET first_name=?, last_name=?, email=?  WHERE teacher_id=?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                updateStatement.setString(1, firstName);     
                updateStatement.setString(2, lastName);
                updateStatement.setString(3, email);
                updateStatement.setLong(4, teacher.getTeacherId());
                int rowsAffected = updateStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Teacher information updated successfully!");
                } else {
                    throw new TeacherNotFoundException("Teacher not found for the given teacher ID. No updates were made.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private boolean isInvalidTeacherData(String firstName, String lastName, String email) {
        // Validate that no detail is left empty
        if (firstName == null || lastName == null ||  email == null) {
            return true;
        }
        return false;
    }


    
    
   
    public void displayTeacherInfo(Teacher teacher) throws TeacherNotFoundException, FileNotFoundException, ClassNotFoundException, IOException {
        try (Connection connection = DBConnUtil.getConnection(URL)) {
            String selectQuery = "SELECT * FROM teacher WHERE teacher_id = ?";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                selectStatement.setLong(1, teacher.getTeacherId());
                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    if (resultSet.next()) {
                        System.out.println("Teacher Information:");
                        System.out.println("Teacher ID: " + resultSet.getLong("teacher_id"));
                        System.out.println("First Name: " + resultSet.getString("first_name"));
                        System.out.println("Last Name: " + resultSet.getString("last_name"));
                        System.out.println("Email: " + resultSet.getString("email"));
                    } else {
                        throw new TeacherNotFoundException("Teacher not found for the given teacher ID.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
    
    public List<Course> getAssignedCourses(Teacher teacher) throws SQLException, FileNotFoundException, ClassNotFoundException, IOException {
        List<Course> assignedCourses = new ArrayList<>();

        try (Connection connection = DBConnUtil.getConnection(URL)) {
            String selectQuery = "SELECT course_id, course_name FROM courses WHERE teacher_id = ?";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                selectStatement.setLong(1, teacher.getTeacherId());

                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    while (resultSet.next()) {
                        long courseId = resultSet.getLong("course_id");
                        String courseName = resultSet.getString("course_name");
                        Course course = new Course(courseId, courseName);
                        assignedCourses.add(course);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return assignedCourses;
    }
    
    
    

    public static void main(String[] args) throws  TeacherNotFoundException, InvalidTeacherDataException,SQLException, FileNotFoundException, ClassNotFoundException, IOException {
        // Example usage
    	TeacherDaoImpl tce=new TeacherDaoImpl();
       
        // Assuming you have a Teacher object with a valid teacherId
        Teacher teacher = new Teacher(11L);

        System.out.println("***************************");
        
        try {
            // Test 1: Update teacher info with valid data
            tce.updateTeacherInfo(teacher, "Surya Krishna", "Karri", "updated.suryakrishna@gmail.com");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println("***************************");


        // Test 2: Display teacher info
        Teacher teacher2=new Teacher(12L);
		tce.displayTeacherInfo(teacher2);
		
		System.out.println("***************************");

        // Test 3: Get assigned courses
		List<Course> assignedCourses = tce.getAssignedCourses(teacher2);
		if (!assignedCourses.isEmpty()) {
		    System.out.println("Assigned Courses:");
		    for (Course course : assignedCourses) {
		        System.out.println("Course ID: " + course.getCourseId() + ", Course Name: " + course.getCourseName());
		    }
		} else {
		    System.out.println("No courses assigned to the teacher.");
		}
		
		System.out.println("***************************");
	
        Teacher teach=new Teacher("sarah.smith@example.com");
        
       
        try {
        	long tid=tce.getTeacherIdByEmail(teach);
        	System.out.println(tid);
        }
        catch(TeacherNotFoundException|IOException|ClassNotFoundException e) {
        	System.out.println(e.getMessage());
        }
    }

}
