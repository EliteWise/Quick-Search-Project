/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *
 * @author Dell
 */
public class FXMLDocumentController implements Initializable {
    
    public static String main_product;
    public static String max_price;
    
    @FXML
    private TextArea linkA;
    
    @FXML
    private TextArea linkC;
    
    @FXML
    private TextArea avA;
    
    @FXML
    private TextArea avC;
    
    @FXML
    private TextField name;
    
    @FXML
    private TextArea price;
    
    @FXML
    private TextArea priceA;
    
    @FXML
    private TextArea priceC;
    private Button button;
    
    @FXML
    private TextArea test;
    
    @FXML
    private CheckBox aCheck;
    
    @FXML
    private CheckBox cCheck;
    
    public static String website;
    
    @FXML
    private void mainText(ActionEvent event) {
       System.out.println(name.getText());
        
    }
    
    private boolean isNumeric(String strNum) {
    try {
        int a = Integer.parseInt(strNum);
    } catch (NumberFormatException | NullPointerException nfe) {
        return false;
    }
    return true;
    
    }
    
    private void dialogAlert(String text, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Error");
        alert.setHeaderText(text);
 
        alert.showAndWait();
    }
    
    public void launchPage(String ressource, String title) {
        try {
            
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ressource));
                Parent root1 = (Parent) fxmlLoader.load();
                
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle(title);
                Scene scene = new Scene(root1);
                scene.getStylesheets().add("design_2.css");
                
                stage.setScene(scene);         
                stage.show();
                
            } catch (Exception e) {
                
            }
    }
     
    
    @FXML
    private void buttonAction(ActionEvent event) throws SQLException {
        if((!name.getText().isEmpty()) && (!(price.getText().isEmpty())) && (isNumeric(price.getText()))) {
            
        String dbName = "quicksearch";
        String userName = "flo";
        String password = "";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/quicksearch?useUnicode=true&characterEncoding=utf-8", userName, password);
            System.out.println("Good");
            
            Statement stmt = (Statement) connection.createStatement();
	    ResultSet rs = stmt.executeQuery("SELECT name, price, company FROM products WHERE name = '" + name.getText() + "' AND price = " + price.getText());
            
            main_product = name.getText();
            max_price = price.getText();
            
            //if (!rs.isBeforeFirst() ) {    
                //System.out.println("No data");
                
                //dialogAlert("Your product doesn't exist.", Alert.AlertType.ERROR);         
            //}
            
            while(rs.next()) {
                if(rs.getString("company").equalsIgnoreCase("amazon")) {
                    //avA.setText(rs.getString("available"));
                    //priceA.setText(rs.getString("price"));

                } else if(rs.getString("company").equalsIgnoreCase("cdiscount")){
                    //avC.setText(rs.getString("available"));
                    //priceC.setText(rs.getString("price"));
                }
                   
            }
            
            if(aCheck.isSelected() && cCheck.isSelected()) {
                System.out.println("Amazon & Cdiscount");
                website = "AC";
                
                launchPage("Page2.1.fxml", "Amazon & Cdiscount Products");
            
            } else if(cCheck.isSelected()) {
                System.out.println("Cdiscount");
                website = "C";
                
                launchPage("Page2.2.fxml", "Cdiscount Products");
            
            } else if(aCheck.isSelected()) {
                System.out.println("Amazon");
                website = "A";
                
                launchPage("Page2.2.fxml", "Amazon Products");
                
            } else {
                dialogAlert("You have to choose at least one website.", Alert.AlertType.INFORMATION);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        } else {
            dialogAlert("You have to enter the name and the price of a product.", Alert.AlertType.INFORMATION);
        }
   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    
}
