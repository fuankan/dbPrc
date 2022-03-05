package com.example.dbFirstPrc.Controller;

import com.example.dbFirstPrc.Connection.ConnectionClass;
import com.example.dbFirstPrc.Model.Department;
import com.example.dbFirstPrc.Repository.DepartmentRepository;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Dep {

    final DepartmentRepository repository = new DepartmentRepository();

    @FXML
    private DatePicker Bdate;

    @FXML
    private TextField Fname;

    @FXML
    private TextField Lname;

    @FXML
    private TableColumn<Department, String> NameColumn;

    @FXML
    private TableColumn<Department, String> NumColumn;

    @FXML
    private TextField Ssn;

    @FXML
    private TableColumn<Department, Date> dateColumn;

    @FXML
    private TableView<Department> employeeTable;

    @FXML
    private TableColumn<Department, String> ssnColumn;

    @FXML
    private Button submitButton;

    public TableColumn<Department, Department> actionColumn;

    public TableColumn<Department, Department> actionColumn1;



    @FXML
    private void initialize(){


        NameColumn.setCellValueFactory(cellData -> cellData.getValue().NameProperty());
        ssnColumn.setCellValueFactory(cellData -> cellData.getValue().ssnProperty());
        NumColumn.setCellValueFactory(cellData -> cellData.getValue().NumProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().DateProperty());
        actionColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        actionColumn1.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));


        initializeTableValues();

        actionColumn.setCellFactory(param -> new TableCell<Department, Department>() {
            private final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(Department department, boolean empty) {
                super.updateItem(department, empty);

                if (department == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);

                deleteButton.setOnAction(event -> removePerson(department.getNum()));
            }
        });

        actionColumn1.setCellFactory(param -> new TableCell<Department, Department>() {
            private final Button changeButton = new Button("Change");

            @Override
            protected void updateItem(Department department, boolean empty) {
                super.updateItem(department, empty);

                if (department == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(changeButton);

                changeButton.setOnAction(event -> changePerson(department.getDate()));
            }
        });
    }

    public void changePerson(Date num) {
        try {
            Statement statement=connection.createStatement();
            String sql = "UPDATE department set Mgr_start_date = '2000-01-01' where Mgr_start_date = '" + num +"'";
            statement.executeUpdate(sql);

            System.out.println("Success!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        initializeTableValues();
    }

    public void removePerson(String num) {
        try {
            Statement statement=connection.createStatement();
            String sql = "DELETE FROM department  WHERE dnumber = '" + num + "'";
            statement.executeUpdate(sql);

            System.out.println("Success!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        initializeTableValues();
    }

    /**
     * Следующие строки предназначены для создания объекта класса -ConnectionClass-
     * и вызова его метода -getConnection()-
     * Переменная -connection- далее используется для отправки запросов на базу данных
     */
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();

    public ObservableList<Department> employeeList = FXCollections.observableArrayList();

    /**
     * Метод -insertPerson(ActionEvent actionEvent)- привязан к кнопке -Создать- на главной странице
     * при нажатии которой из текста полей -Fname, Lname, Ssn...- формируется
     * запрос добавления(INSERT) в базу данных.
     *
     * Далее значения полей опустошаются и вызывается метод
     * -initializeTableValues();- для заполнения таблицы новыми данными из Базы данных.
     *
     * @param actionEvent
     */
    public void insertPerson(ActionEvent actionEvent) {
        try {
            Statement statement=connection.createStatement();
            String sql = "INSERT INTO " +
                    "department(Dname, Dnumber, Mgr_ssn, Mgr_start_date) " +
                    "VALUES ('"+Fname.getText()+"','" +
                    ""+Integer.parseInt(Lname.getText())+"','" +
                    ""+Ssn.getText()+"','" +
                    ""+Bdate.getValue()+"')";
            statement.executeUpdate(sql);

            System.out.println("Success!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Fname.setText("");
        Lname.setText("");
        Ssn.setText("");

        initializeTableValues();
    }




    /**
     * Данный метод делает запрос -SELECT- в базу данных и из полученных данных формирует список
     * типа -ObservableList<Employee>-, с помощью которого заполняет таблицу -personTable-
     */
    public void initializeTableValues(){
        ObservableList<Department> personList = repository.getList();
        if(personList.size() > 0){
            employeeTable.setItems(personList);
        }
    }
}

