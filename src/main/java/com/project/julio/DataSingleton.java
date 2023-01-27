package com.project.julio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.project.dbhandler.PostgresConnect;
import com.project.models.Student; 

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataSingleton {
    
    private static final DataSingleton instance = new DataSingleton();

    private ObservableList<Student> studentList =FXCollections.observableArrayList();

    /**
     * Load the student list 
     */
    private DataSingleton() {        
        this.loadStudents();
    }

    /**
     * Get an instance of DataSingleton
     * @return
     */
    public static DataSingleton getInstance() { 
        return instance; 
    }

    /**
     * Return the student list to the instance
     * @return
     */
    public ObservableList<Student> getStudents() {
        return this.studentList;
    }

    /**
     * Update list of students when someone is registered
     * @param student
     */
    public void addStudent(Student student) {
        this.studentList.add(student);
    }
    
    /**
     * Get database connection and return the values fron the table students
     */
    private void loadStudents() {
        PostgresConnect pgConnect = new PostgresConnect();
        String sql = "SELECT student_id, student_firstname, student_lastname, student_birthdate, student_email, student_phone, student_level FROM students";
        
        try (Connection connection = pgConnect.getConnection(); Statement statement =connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()) {
                String id = resultSet.getString("student_id");
                String firstName = resultSet.getString("student_firstname");
                String lastName = resultSet.getString("student_lastname");
                LocalDate birthDate = resultSet.getDate("student_birthdate").toLocalDate();
                String email = resultSet.getString("student_email");
                String phone = resultSet.getString("student_phone");
                int level = resultSet.getInt("student_level");
                studentList.add(new Student(id, firstName, lastName, birthDate, email, phone, level));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Let delet an student from the list and database
     * @param student
     */
    public void deleteStudent(Student student) {
        PostgresConnect pgConnect = new PostgresConnect();
        String sql = "DELETE FROM students WHERE student_id = ?";

        try (Connection connection = pgConnect.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, student.getId());
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        studentList.remove(student);
    }
}
