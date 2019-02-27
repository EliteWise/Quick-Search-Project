/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static javafxapplication2.FXMLDocumentController.main_product;

/**
 *
 * @author Dell
 */
public class AmazonProducts implements Initializable {
    
    @FXML
    private AnchorPane paneProductName;
    
    @FXML
    private AnchorPane panePrice;
    
    @FXML
    private Text amazon_or_cdiscount;
    
    @FXML
    private Text main_product_name;
    
    @FXML
    private Text allprices;
    
    @FXML
    private Text pdn1;
    
    @FXML
    private Text pdn2;
    
    @FXML
    private Text pdn3;
    
    @FXML
    private Text pdn4;
    
    @FXML
    private Text p1;
    
    @FXML
    private Text p2;
    
    @FXML
    private Text p3;
    
    @FXML
    private Text p4;
    
    @FXML
    public ComboBox<String> combobox;
    
    ObservableList<String> list = FXCollections.observableArrayList("Asus", "Razer", "Dell", "Acer");
    
    private String a_or_c;
    
    private String dbName = "quicksearch";
    private String userName = "flo";
    private String password = "";
    
    @FXML
    private void list(ActionEvent event) {
        
        if(combobox.getValue().equals("Asus")) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/quicksearch?useUnicode=true&characterEncoding=utf-8", userName, password);
            
            Statement stmt = (Statement) connection.createStatement();
	    ResultSet rs = stmt.executeQuery("SELECT name, price, company FROM products WHERE name LIKE '%" + FXMLDocumentController.main_product + "%'"
                                            + " AND name LIKE '%asus%' AND company = '" + a_or_c + "' AND price < " + FXMLDocumentController.max_price);    
                
                for (Node node : paneProductName.getChildren()) {
                    if(node instanceof Text) {
                        if(rs.next()) {
                            ((Text) node).setText(rs.getString("name"));
                        
                    }
                }  
                
               //pdn1.setText(rs.getString("name"));
            }
                         
             
        
        } catch (Exception e) {
            e.printStackTrace();
            
            }
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/quicksearch?useUnicode=true&characterEncoding=utf-8", userName, password);
            
            Statement stmt = (Statement) connection.createStatement();
	    ResultSet rs = stmt.executeQuery("SELECT name, price, company FROM products WHERE name LIKE '%" + FXMLDocumentController.main_product + "%'"
                                            + " AND name LIKE '%asus%' AND company = '" + a_or_c + "' AND price < " + FXMLDocumentController.max_price);    
                
                for (Node node : panePrice.getChildren()) {
                    if(node instanceof Text) {
                        if(rs.next()) {
                            ((Text) node).setText(rs.getString("price") + " €");
                        
                    }
                }  
                
               //pdn1.setText(rs.getString("name"));
            }
                         
             
        
        } catch (Exception e) {
            e.printStackTrace();
            
            }
        
        } else if(combobox.getValue().equals("Razer")) {
            pdn1.setText("Ecran Razer 1");
            pdn2.setText("Ecran Razer 2");
            pdn3.setText("Ecran Razer 3");
            pdn4.setText("Ecran Razer 4");
            
            p1.setText("200 €");
            p2.setText("240 €");
            p3.setText("285 €");
            p4.setText("320 €");
        }
    }
    
    public void getMinMax() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/quicksearch?useUnicode=true&characterEncoding=utf-8", userName, password);
            
            Statement stmt = (Statement) connection.createStatement();
	    ResultSet rs = stmt.executeQuery("SELECT name, MIN(price), MAX(price) FROM products WHERE name = '" + FXMLDocumentController.main_product + "'");    
            
            while(rs.next()) {
               allprices.setText(rs.getString("MIN(price)") + " - " + FXMLDocumentController.max_price + " €");
            }
        
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getMinMax();
        combobox.setItems(list);
        main_product_name.setText(FXMLDocumentController.main_product.toUpperCase());
        
        if(FXMLDocumentController.website.equals("C")) {
            a_or_c = "cdiscount";
            amazon_or_cdiscount.setText("Cdiscount");
            amazon_or_cdiscount.layoutXProperty().setValue(239);
        } else if(FXMLDocumentController.website.equals("A")){
            a_or_c = "amazon";
            amazon_or_cdiscount.setText("Amazon");
        } else {

        }
    }
    
}
