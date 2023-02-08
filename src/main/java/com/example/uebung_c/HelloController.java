package com.example.uebung_c;

import Data.Country;
import Data.Denomination;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.LinkedList;

import java.util.ResourceBundle;

public class HelloController implements Initializable {

    LinkedList<Denomination> llreligion = new LinkedList<>();
    LinkedList<Country> llnationality = new LinkedList<>();
    Connection c = null;
    @FXML
    private ChoiceBox genderbox;
    @FXML
    private ComboBox<Denomination> denomination;
    @FXML
    private ComboBox<Country> nationality;
    @FXML
    private TextField lastName;
    @FXML
    private TextField firstName;
    @FXML
    private TextField pat_id;
    @FXML
    private Label nationalityCode;

    /**
     * This Methode is clearing the Window, to enter a new Patient
     * @param actionEvent
     */
    @FXML
    public void CancelButtonPressed(ActionEvent actionEvent) {
        pat_id.clear();
        firstName.clear();
        lastName.clear();
        denomination.setValue(null);
        nationality.setValue(null);
        nationalityCode.setText("____");
    }

    /**
     * This methode closes this window
     * @param actionEvent
     */
    @FXML
    public void ExitButtonPressed(ActionEvent actionEvent) {
        firstName.getScene().getWindow().hide();

    }

    /**
     * This methode is saving the attributes from the window into our sql database. Afterwards clears the input fields.
     * @param actionEvent
     * @throws SQLException
     */
    @FXML
    public void SaveButtonPressed(ActionEvent actionEvent) throws SQLException {
        try {
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/Arztpraxis", "Arztpraxis", "Arztpraxis");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        PreparedStatement ps  = c.prepareStatement("INSERT INTO patient values(?, ?, ?, ?, ?)");
        ps.setInt(1, Integer.parseInt(pat_id.getText()));
        ps.setString(2, firstName.getText());
        ps.setString(3, lastName.getText());

        for (int i = 0; i < llreligion.size(); i++) {
            if (llreligion.get(i).getKonfession().equals(denomination.getValue().getKonfession())) {
                ps.setInt(4, llreligion.get(i).getCode());
            }
        }

        for (int i = 0; i < llnationality.size(); i++) {
            if(llnationality.get(i).getCode().equals(nationality.getValue().getCode())){
                ps.setString(5, llnationality.get(i).getCode());
            }
        }
        ps.execute();


        pat_id.clear();
        firstName.clear();
        lastName.clear();
        denomination.setValue(null);
        nationality.setValue(null);
        nationalityCode.setText("____");
    }

    /**
     * This Methode will be executed in a matter of seconds after the start of the program.
     * Its job is to get the denominations and countries from our database and puts them separately
     * into a LinkedList and into our ComboBoxes. So that our users can modify from different countries and denominations
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/Arztpraxis", "Arztpraxis", "Arztpraxis");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Statement s = null;
        try {
            s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //TYPE_SCROLL_SENSITIVE kann man zurück springen, CONCUR_UPDATABLE kann die Table verändern außerhalb des des SQL-Befehls
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try {
            ResultSet rs = s.executeQuery("SELECT * FROM religious_denomination");
            /*
            rs.last();
            Denomination d = new Denomination(rs.getInt(1), rs.getString(2));
            System.out.println(d.toString());
            rs.previous();
            Denomination d2 = new Denomination(rs.getInt(1), rs.getString(2));
            System.out.println(d2.toString());
             */

            while(rs.next()){
                llreligion.add(new Denomination(rs.getInt(1), rs.getString(2)));
            }
            for (int i = 0; i < llreligion.size(); i++) {
                denomination.getItems().add(llreligion.get(i));
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            ResultSet rs = s.executeQuery("SELECT * FROM nationality");

            while(rs.next()){
                llnationality.add(new Country(rs.getString(1), rs.getString(2),
                        rs.getString(3) ));
            }

            for (int i = 0; i < llnationality.size(); i++) {
                nationality.getItems().add(llnationality.get(i));
            }
            c.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * This methode adds the Nationality code to the label
     * @param event
     */
    @FXML
    public void NationalityCode(Event event) {
        if(nationality.getValue()==null){
            return;
        }
        for (int i = 0; i < llnationality.size(); i++) {
            if(llnationality.get(i).getCountry().equals(nationality.getValue().getCountry())){
                nationalityCode.setText("" + llnationality.get(i).getCode());
            }
        }

    }








}