package com.example.uebung_c;

import Data.Denomination;
import Data.Patient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    @javafx.fxml.FXML
    private ListView<Patient> listView;
    static Stage stage;
    static Parent root;

    /**
     * This methode opens a new window to add a new patient into the database.
     * @param actionEvent
     */
    @javafx.fxml.FXML
    public void AddMethode(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            root = fxmlLoader.load();
            stage = new Stage();
            stage.setScene(new Scene(root));

            stage.getScene().getWindow().setOnHidden(windowEvent -> {
                stage.close();
                getFromDatabase();
            });
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @javafx.fxml.FXML
    public void ChangeMethode(ActionEvent actionEvent) {
    }

    /**
     * With this methode the user is capable of deleting a patient from the database.
     * @param actionEvent
     */
    @javafx.fxml.FXML
    public void DeleteMethode(ActionEvent actionEvent) {

        try {
            Connection c = DriverManager.getConnection("jdbc:derby://localhost:1527/Arztpraxis", "Arztpraxis", "Arztpraxis");

            PreparedStatement s = c.prepareStatement("DELETE FROM patient WHERE ID = ?");
            s.setInt(1, listView.getSelectionModel().getSelectedItem().getId());
            System.out.println(listView.getSelectionModel().getSelectedItem().getId());
            s.executeUpdate();
            c.close();
            getFromDatabase();




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * With this methode you close the program.
     * @param actionEvent
     */
    @javafx.fxml.FXML
    public void CloseMethode(ActionEvent actionEvent) {

        Platform.exit();
    }

    /**
     * This methode starts at the beginning of the program.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getFromDatabase();
    }

    /**
     * This methode displays the database into the ListView.
     */
    public void getFromDatabase(){
            try {
            Connection c = DriverManager.getConnection("jdbc:derby://localhost:1527/Arztpraxis", "Arztpraxis", "Arztpraxis");
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //TYPE_SCROLL_SENSITIVE kann man zurück springen, CONCUR_UPDATABLE kann die Table verändern außerhalb des des SQL-Befehls
            ResultSet rs = s.executeQuery("SELECT * FROM patient");

            listView.getItems().clear();
            while(rs.next()){
                listView.getItems().add(new Patient(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5)));
            }
            c.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
