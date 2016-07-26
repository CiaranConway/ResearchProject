/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goooey;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;


    public class ChartController{
        private JavaFXApplication11 application;
        @FXML private TextField R_Files_T1;
        
           @FXML 
    private Label success;

         public void setApp(JavaFXApplication11 application){
        this.application = application;
            }
        
        public void seeFinalResults(ActionEvent event) {
            R_Files_T1.setText("Hi");
         //animateMessage();
           // return;
        }
        
       public void DisplayResults() {
        R_Files_T1.setText("Hi");
    }
        
          private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), success);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
//        public Stage 
    }
          
    }