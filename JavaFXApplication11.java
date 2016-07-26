/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goooey;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author chris
 */


public class JavaFXApplication11 extends Application {
     private Stage stage;
         private final double MINIMUM_WINDOW_WIDTH = 390.0;
    private final double MINIMUM_WINDOW_HEIGHT = 500.0;
    
     @Override
       public void start(Stage primaryStage)throws Exception{
           stage = primaryStage;
//                  stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
//            stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        primaryStage.setTitle("MultiAgent File Sharing");  
         goToLogin();
            primaryStage.show();
       //     primaryStage.show();
      //  FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
       // Pane myPane = (Pane)loader.load();
       // FXMLDocumentController controller = (FXMLDocumentController) loader.getController();
        
//        controller.setNextStage(primaryStage);
//        Scene myScene = new Scene(myPane);
//        primaryStage.setScene(myScene);
//        primaryStage.show();
          
    }
       public void goToLogin(){
            try {
           FXMLDocumentController login = (FXMLDocumentController) replaceSceneContent("login.fxml");
            login.setApp(this);
            }
            catch (Exception ex) {
                    System.out.print(ex);
                    }
          // FXMLDocumentController change = (FXMLDocumentController) replaceSceneContent("login.fxml");
       }
       
       
         public void gotoResults() {

        try {
            ChartController profile = (ChartController) replaceSceneContent("Results.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            System.out.print("ERROR");
        }
    }

       public Initializable replaceSceneContent(String fxml)throws Exception{
           FXMLLoader loader = new FXMLLoader();
          
           
        InputStream in = JavaFXApplication11.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(JavaFXApplication11.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        } 
        Scene scene = new Scene(page, 800, 600);
        stage.setScene(scene);
       // stage.sizeToScene();
       // loader.<FXMLDocumentController>getController();
      //  loader.passValue(value);
        return (Initializable) loader.getController();
       }
       
        public static void main(String[] args) {
        launch(args);
    }
}
//    
//    @Override
//    public void start(Stage stage) throws Exception {
//        
//        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
//        
//        Scene scene = new Scene(root);
//        
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        launch(args);
//    }
//    
//}




//public class JavaFXApplication11 extends Application {
//       private Stage stage;
//    private Details loggedUser;
//    private final double MINIMUM_WINDOW_WIDTH = 390.0;
//    private final double MINIMUM_WINDOW_HEIGHT = 500.0;
//    
//    
//     public static void main(String[] args){
//            Application.launch(JavaFXApplication11.class, (java.lang.String[])null);
//    }
//    
//    @Override
//    public void start(Stage primaryStage) {
//        try {
//            stage = primaryStage;
//            stage.setTitle("FXML Login Sample");
//            stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
//            stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
//           
//            primaryStage.show();
//        } catch (Exception ex) {
//            Logger.getLogger(JavaFXApplication11.class.getName()).log(Level.SEVERE, null, ex);
//        }
  //  }
//
//    public Details getLoggedUser() {
//        return loggedUser;
//    }
//        
//    public boolean userLogging(String userId, String password){
//        if (Authenticator.validate(userId, password)) {
//            loggedUser = User.of(userId);
//            gotoProfile();
//            return true;
//        } else {
//            return false;
//        }
//    }
    
//    void userLogout(){
//        loggedUser = null;
//        gotoLogin();
//    }
//    
//    private void gotoProfile() {
//        try {
//            ProfileController profile = (ProfileController) replaceSceneContent("profile.fxml");
//            profile.setApp(this);
//        } catch (Exception ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    private void gotoLogin() {
//        try {
//            LoginController login = (LoginController) replaceSceneContent("login.fxml");
//            login.setApp(this);
//        } catch (Exception ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

//    private Initializable replaceSceneContent(String fxml) throws Exception {
//        FXMLLoader loader = new FXMLLoader();
//        InputStream in = Main.class.getResourceAsStream(fxml);
//        loader.setBuilderFactory(new JavaFXBuilderFactory());
//        loader.setLocation(Main.class.getResource(fxml));
//        AnchorPane page;
//        try {
//            page = (AnchorPane) loader.load(in);
//        } finally {
//            in.close();
//        } 
//        Scene scene = new Scene(page, 800, 600);
//        stage.setScene(scene);
//        stage.sizeToScene();
//        return (Initializable) loader.getController();
//    }
//}

     
//    @Override
//    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
//        
//        Scene scene = new Scene(root);
//        
//        stage.setScene(scene);
//        stage.show();
//    }

    /**
     * @param args the command line arguments
     */
   
//}
