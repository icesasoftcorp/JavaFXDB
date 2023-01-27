package com.project.julio;

import java.io.IOException;

import com.project.models.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class StudentFormController {
    
    DataSingleton data = DataSingleton.getInstance();
    
    @FXML TextField txtStudentID;
    @FXML TextField txtStudentName;
    @FXML TextField txtStudentLastName;
    @FXML DatePicker dpStudentBirthDate;
    @FXML TextField txtStudentEmail;
    @FXML TextField txtStudentPhone;
    @FXML ComboBox cbStudentLevel;

    ObservableList<Integer> levelItems = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6);
 
    /**
     * Initialize the items of combobox
     */
    @FXML
    public void initialize() {
        cbStudentLevel.setItems(levelItems);
    }

    /**
     * Let save a new student
     * @throws IOException
     */
    @FXML
    private void saveStudent() throws IOException {
        Student student = new Student(
            txtStudentID.getText(), 
            txtStudentName.getText(),
            txtStudentLastName.getText(),
            dpStudentBirthDate.getValue(),
            txtStudentEmail.getText(),
            txtStudentPhone.getText(),
            Integer.parseInt(cbStudentLevel.getValue().toString()));
            
        student.saveStudent();
        data.addStudent(student);
        App.setRoot("studentList");
    }

    /**
     * Let cancel a student registration
     * @throws IOException
     */
    @FXML
    private void cancel() throws IOException {
        App.setRoot("studentList");
    }
}
