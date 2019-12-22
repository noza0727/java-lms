package Library;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.naming.spi.InitialContextFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static Library.Configs.*;

public class StudentsTable implements Initializable {

    private static Connection conn;
    private static Statement stmt;
    private ResultSet rs;


    @FXML
    private TableView<StudentDetails> tableStudent;

    @FXML
    private TableColumn<StudentDetails, String> col_id_student;
    @FXML
    private TableColumn<StudentDetails, String> col_fname_student;
    @FXML
    private TableColumn<StudentDetails, String> col_lname_student;
    @FXML
    private TableColumn<StudentDetails, String> col_year_student;
    @FXML
    private TableColumn<StudentDetails, String> col_department;
    @FXML
    private TableColumn<StudentDetails, CheckBox> col_select_student;

    private ObservableList<StudentDetails> list_student = FXCollections.observableArrayList();;

    public void createConnection(){
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + dbHost +":"+dbPort+"/"+dbPath,
                    dbUser, dbPass);
            stmt = conn.createStatement();

        }catch(SQLException ex){
            System.err.println(ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            createConnection();
            String sqlData = "SELECT `ID`, `FirstName`, `LastName`, `Year`, `Department` FROM `students`";
            rs = conn.createStatement().executeQuery(sqlData);
            int i = 0;
            while (rs.next()) {
                CheckBox ch2 = new CheckBox("" + (i++));
               StudentDetails student = new StudentDetails(rs.getString("ID"),
                       rs.getString("FirstName"), rs.getString("LastName"),
                       rs.getString("Year"), rs.getString("Department"));
                list_student.add(student);
            }

            col_id_student.setCellValueFactory(new PropertyValueFactory<>("studentID"));
            col_fname_student.setCellValueFactory(new PropertyValueFactory<>("studentFname"));
            col_lname_student.setCellValueFactory(new PropertyValueFactory<>("studentLname"));
            col_year_student.setCellValueFactory(new PropertyValueFactory<>("studentYear"));
            col_department.setCellValueFactory(new PropertyValueFactory<>("studentDepartment"));
            //col_select_student.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
            tableStudent.setItems(list_student);
            tableStudent.setEditable(true);

            col_id_student.setCellFactory(TextFieldTableCell.forTableColumn());
            col_fname_student.setCellFactory(TextFieldTableCell.forTableColumn());
            col_lname_student.setCellFactory(TextFieldTableCell.forTableColumn());
            col_year_student.setCellFactory(TextFieldTableCell.forTableColumn());
            col_department.setCellFactory(TextFieldTableCell.forTableColumn());


        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }


    public void editID(TableColumn.CellEditEvent editid) {
        StudentDetails studentselected = tableStudent.getSelectionModel().getSelectedItem();

        TablePosition pos =tableStudent.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        StudentDetails item = tableStudent.getItems().get(row);
        TableColumn col = pos.getTableColumn();

        String data1 = (String) col.getCellObservableValue(item).getValue();
        System.out.println(data1);

        studentselected.setStudentID(editid.getNewValue().toString());

        String data2 = (String) col.getCellObservableValue(item).getValue();
        System.out.println(data2);

        try {
            Connection con = Database.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE students set ID='"+data2+"' where ID like '"
                    +data1+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editFname(TableColumn.CellEditEvent editfname) {
        StudentDetails studentselected = tableStudent.getSelectionModel().getSelectedItem();

        TablePosition pos =tableStudent.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        StudentDetails item = tableStudent.getItems().get(row);
        TableColumn col = pos.getTableColumn();

        String data1 = (String) col.getCellObservableValue(item).getValue();
        System.out.println(data1);

        studentselected.setStudentFname(editfname.getNewValue().toString());

        String data2 = (String) col.getCellObservableValue(item).getValue();
        System.out.println(data2);

        try {
            Connection con = Database.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE students set FirstName='"+data2+"' where FirstName like '"
                    +data1+"' and ID='"+item.getStudentID()+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editLname(TableColumn.CellEditEvent editlname) {
        StudentDetails studentselected = tableStudent.getSelectionModel().getSelectedItem();

        TablePosition pos =tableStudent.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        StudentDetails item = tableStudent.getItems().get(row);
        TableColumn col = pos.getTableColumn();

        String data1 = (String) col.getCellObservableValue(item).getValue();
        System.out.println(data1);

        studentselected.setStudentLname(editlname.getNewValue().toString());

        String data2 = (String) col.getCellObservableValue(item).getValue();
        System.out.println(data2);

        try {
            Connection con = Database.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE students set LastName='"+data2+"' where LastName like '"
                    +data1+"' and ID='"+item.getStudentID()+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editDepartment(TableColumn.CellEditEvent editdep){
        StudentDetails studentselected = tableStudent.getSelectionModel().getSelectedItem();

        TablePosition pos =tableStudent.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        StudentDetails item = tableStudent.getItems().get(row);
        TableColumn col = pos.getTableColumn();

        String data1 = (String)col.getCellObservableValue(item).getValue();
        System.out.println(data1);

        studentselected.setStudentDepartment(editdep.getNewValue().toString());

        String data2 = (String) col.getCellObservableValue(item).getValue();
        System.out.println(data2);

        try {
            Connection con = Database.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = con.createStatement().executeQuery("select ID from students");
            while(rs.next()){
               if(rs.getString(1).equals(item.getStudentID())){
                   stmt.executeUpdate("UPDATE students set Department ='"+data2+"' where ID = '"
                           +item.getStudentID()+"'");
               }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editYear(TableColumn.CellEditEvent edityear){
        StudentDetails studentselected = tableStudent.getSelectionModel().getSelectedItem();

        TablePosition pos =tableStudent.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        StudentDetails item = tableStudent.getItems().get(row);
        TableColumn col = pos.getTableColumn();

        String data1 = (String) col.getCellObservableValue(item).getValue();
        System.out.println(data1);

        studentselected.setStudentYear(edityear.getNewValue().toString());

        String data2 = (String) col.getCellObservableValue(item).getValue();
        System.out.println(data2);

        try {
            Connection con = Database.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE students set `Year` ='"+data2+"' where ID = '"
                    +item.getStudentID()+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewStudent() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddStudent.fxml"));
        Scene scene3 = new Scene(pane);
        scene3.setFill(Color.TRANSPARENT);
        Stage stage3 = new Stage();
        stage3.setScene(scene3);
        stage3.initStyle(StageStyle.TRANSPARENT);
        stage3.setOnCloseRequest(e-> Platform.exit());
        stage3.show();

    }

    public void deleteLib(){
        StudentDetails libselected = tableStudent.getSelectionModel().getSelectedItem();
        TablePosition pos =tableStudent.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        StudentDetails item = tableStudent.getItems().get(row);
        try {
            if(libselected!=null){
                PreparedStatement statement = conn.prepareStatement("DELETE FROM students WHERE ID = ?");
                statement.setString(1, item.getStudentID());
                statement.executeUpdate();
            }
            else
                System.out.println("its null awe");

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }
}