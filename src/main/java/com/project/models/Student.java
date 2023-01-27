package com.project.models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import com.project.dbhandler.PostgresConnect;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student extends Person {

    private int level;

    /**
     * Generates the Staff Constructor.
     * @param id
     * @param firstName
     * @param lastName
     * @param birthDate
     * @param email
     * @param phoneNumber
     * @param level
     */

     public Student (String id, String firstName, String lastName, LocalDate birthDate, String email, String phoneNumber, int level) {
        super(id, firstName, lastName, birthDate, email, phoneNumber);
        this.level = level;
     }

    /**
     * Get the Student's level as integer.
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     * Set the Student's level as integer.
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }
    
    /* (non-Javadoc)
     * @see com.project.models.Person#print()
     */
    @Override
    public void print() {
        super.print();
        System.out.println("Level: "+this.level);
    }

    /* (non-Javadoc)
     * @see com.project.models.Person#getFullName()
     */
    @Override
    public String getFullName() { 
        return super.getFullName();
    }

    /**
     * It exposes the property of an object as a JAVAFX property
     * @return
     */
    public SimpleStringProperty idProperty(){
        return new SimpleStringProperty(this.getId());
    }

    /**
     * It exposes the property of an object as a JAVAFX property
     * @return
     */
    public SimpleStringProperty firstNameProperty(){
        return new SimpleStringProperty(this.getFirstName());
    }

    /**
     * It exposes the property of an object as a JAVAFX property
     * @return
     */
    public SimpleStringProperty lastNameProperty(){
        return new SimpleStringProperty(this.getLastName());
    }

    /**
     * It exposes the property of an object as a JAVAFX property
     * @return
     */
    public SimpleStringProperty emailProperty(){
        return new SimpleStringProperty(this.getEmail());
    }
    
    /**
     * It exposes the property of an object as a JAVAFX property
     * @return
     */
    public SimpleIntegerProperty ageProperty(){
        return new SimpleIntegerProperty(this.getAge());
    }

    /**
     * It exposes the property of an object as a JAVAFX property
     * @return
     */
    public SimpleIntegerProperty levelProperty(){
        return new SimpleIntegerProperty(this.getLevel());
    }

    /**
     * Save data of the student in PostgreSQL
     */
    public void saveStudent() {
        final String sql = "INSERT INTO students" + 
        " (student_id, student_firstname, student_lastname, student_birthdate, student_email, student_phone, student_level) VALUES " + 
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
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
