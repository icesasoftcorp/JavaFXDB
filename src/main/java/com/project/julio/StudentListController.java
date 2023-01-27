package com.project.julio;

import java.io.IOException;

import com.project.models.Student;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentListController {
    DataSingleton data  = DataSingleton.getInstance();

    @FXML private TableView studentsTableView;
    @FXML private TableColumn idCol;
    @FXML private TableColumn firstNameCol;
    @FXML private TableColumn lastNameCol;
    @FXML private TableColumn emailCol;
    @FXML private TableColumn ageCol;
    @FXML private TableColumn levelCol;

    /**
     * Initialize the values in the student table
     */
    @FXML
    public void initialize() {
        // TODO Auto-generated method stub
        this.idCol.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
        this.firstNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        this.lastNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        this.emailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        this.ageCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("age"));
        this.levelCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("level"));
        this.studentsTableView.setItems(data.getStudents());
    }

    /**
     * Let change to student form display
     * @throws IOException
     */
    @FXML
    private void displayStudentForm() throws IOException {
        App.setRoot("studentForm");
    }

    /**
     * Stop software
     * @throws IOException
     */
    @FXML
    private void exitButton() throws IOException {
        System.exit(0);
    }

    /**
     * Delete the selected student
     */
    @FXML
    private void deleteButton() {
        Student selectedStudent = (Student) studentsTableView.getSelectionModel().getSelectedItem();
        if(selectedStudent != null) {
            String studentID = selectedStudent.getId();
        }
        DataSingleton.getInstance().deleteStudent(selectedStudent);
    }
}
