package com.project.models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.project.dbhandler.PostgresConnect;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Student extends Person {

    // private ObservableList<Student> studentList
    // =FXCollections.observableArrayList();
    private int level;

    /**
     * Generates the Staff Constructor.
     * 
     * @param id
     * @param firstName
     * @param lastName
     * @param birthDate
     * @param email
     * @param phoneNumber
     * @param level
     */

    public Student(String id, String firstName, String lastName, LocalDate birthDate, String email, String phoneNumber,
            int level) {
        super(id, firstName, lastName, birthDate, email, phoneNumber);
        this.level = level;
    }

    /**
     * Get the Student's level as integer.
     * 
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     * Set the Student's level as integer.
     * 
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.project.models.Person#print()
     */
    @Override
    public void print() {
        super.print();
        System.out.println("Level: " + this.level);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.project.models.Person#getFullName()
     */
    @Override
    public String getFullName() {
        return super.getFullName();
    }

    /**
     * It exposes the property of an object as a JAVAFX property
     * 
     * @return
     */
    public SimpleStringProperty idProperty() {
        return new SimpleStringProperty(this.getId());
    }

    /**
     * It exposes the property of an object as a JAVAFX property
     * 
     * @return
     */
    public SimpleStringProperty firstNameProperty() {
        return new SimpleStringProperty(this.getFirstName());
    }

    /**
     * It exposes the property of an object as a JAVAFX property
     * 
     * @return
     */
    public SimpleStringProperty lastNameProperty() {
        return new SimpleStringProperty(this.getLastName());
    }

    /**
     * It exposes the property of an object as a JAVAFX property
     * 
     * @return
     */
    public SimpleStringProperty emailProperty() {
        return new SimpleStringProperty(this.getEmail());
    }

    /**
     * It exposes the property of an object as a JAVAFX property
     * 
     * @return
     */
    public SimpleIntegerProperty ageProperty() {
        return new SimpleIntegerProperty(this.getAge());
    }

    /**
     * It exposes the property of an object as a JAVAFX property
     * 
     * @return
     */
    public SimpleIntegerProperty levelProperty() {
        return new SimpleIntegerProperty(this.getLevel());
    }

    /**
     * Load the student list
     */

    public static ObservableList<Student> loadStudents() {
        PostgresConnect pgConnect = new PostgresConnect();
        String sql = "SELECT student_id, student_firstname, student_lastname, student_birthdate, student_email, student_phone, student_level FROM students";
        ObservableList<Student> studentList = FXCollections.observableArrayList();

        try (Connection connection = pgConnect.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
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
            return studentList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Save data of the student in PostgreSQL
     */
    public void saveStudent() {
        final String sql = "INSERT INTO students" +
                " (student_id, student_firstname, student_lastname, student_birthdate, student_email, student_phone, student_level) VALUES "
                +
                " (?, ?, ?, ?, ?, ?, ?);";
        PostgresConnect pgConnect = new PostgresConnect();
        Connection connection = pgConnect.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, this.getId());
            preparedStatement.setString(2, this.getFirstName());
            preparedStatement.setString(3, this.getLastName());
            preparedStatement.setDate(4, Date.valueOf(getBirthDate()));
            preparedStatement.setString(5, this.getEmail());
            preparedStatement.setString(6, this.getPhoneNumber());
            preparedStatement.setInt(7, this.getLevel());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Let delet an student from the list and database
     * 
     * @param student
     */
    public static void deleteStudent(Student student) {
        PostgresConnect pgConnect = new PostgresConnect();
        Connection connection = pgConnect.getConnection();
        String sql = "DELETE FROM students WHERE student_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
