/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goooey;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Patrick
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private TextField FilesTest1;
    @FXML private TextField NAgentsTest1;
    @FXML private TextField UAgentsTest1;
    @FXML private TextField DAgentsTest1;
    @FXML private TextField PAgentsTest1;
    
    @FXML private TextField FilesTest2;
    @FXML private TextField NAgentsTest2;
    @FXML private TextField UAgentsTest2;
    @FXML private TextField DAgentsTest2;
    @FXML private TextField PAgentsTest2;
    
    // Results screen TextFields below
    @FXML private TextField R_Files_T1;
    @FXML private TextField R_NAgents_T1;
    @FXML private TextField R_UAgents_T1;
    @FXML private TextField R_DAgents_T1;
    @FXML private TextField R_PAgents_T1;
    
    @FXML private TextField R_Files_T2;
    @FXML private TextField R_NAgents_T2;
    @FXML private TextField R_UAgents_T2;
    @FXML private TextField R_DAgents_T2;
    @FXML private TextField R_PAgents_T2;
    
    @FXML private TextField R_Files_TD;
    @FXML private TextField R_NAgents_TD;
    @FXML private TextField R_UAgents_TD;
    @FXML private TextField R_DAgents_TD;
    @FXML private TextField R_PAgents_TD;
    
    @FXML private TextField R_RqSent_T1;
    @FXML private TextField R_RqAccepted_T1;
    @FXML private TextField R_RqRejected_T1;
    
    @FXML private TextField R_RqSent_T2;
    @FXML private TextField  R_RqAccepted_T2;
    @FXML private TextField R_RqRejected_T2;
    
    @FXML private TextField R_RqSent_TD;
    @FXML private TextField  R_RqAccepted_TD;
    @FXML private TextField R_RqRejected_TD;
    
    @FXML private TextField R_UAGenerated_T1;
    @FXML private TextField R_FilesAdded_T1;
    
    @FXML private TextField R_UAGenerated_T2;
    @FXML private TextField R_FilesAdded_T2;
    
    @FXML private TextField R_UAGenerated_TD;
    @FXML private TextField R_FilesAdded_TD;
    
    @FXML private TextField R_PAGenerated_T1;
    @FXML private TextField R_WIssued_T1;
    @FXML private TextField R_TDIssued_T1;
    @FXML private TextField R_PADiscovered_T1;
    
    @FXML private TextField R_PAGenerated_T2;
    @FXML private TextField R_WIssued_T2;
    @FXML private TextField R_TDIssued_T2;
    @FXML private TextField R_PADiscovered_T2;
    
    @FXML private TextField R_PAGenerated_TD;
    @FXML private TextField R_WIssued_TD;
    @FXML private TextField R_TDIssued_TD;
    @FXML private TextField R_PADiscovered_TD;
     
    @FXML private TextField R_DAGenerated_T1;
    @FXML private TextField R_NewAgentsAdded_T1;
    
    @FXML private TextField R_DAGenerated_T2;
    @FXML private TextField R_NewAgentsAdded_T2;
    
    @FXML private TextField R_DAGenerated_TD;
    @FXML private TextField R_NewAgentsAdded_TD;
    
    @FXML private TextField R_RemainingAgents_T1;
    @FXML private TextField R_RequestSucessRate_T1;
    @FXML private TextField R_SystemGrowth_T1;
    
    @FXML private TextField R_RemainingAgents_T2;
    @FXML private TextField R_RequestSucessRate_T2;
    @FXML private TextField R_SystemGrowth_T2;
    
    @FXML private TextField R_RemainingAgents_TD;
    @FXML private TextField R_RequestSucessRate_TD;
    @FXML private TextField R_SystemGrowth_TD;
    
    Stage nextStage;
    private JavaFXApplication11 application;
    public void setNextStage(Stage stage){
    this.nextStage = stage;
}
     public void setApp(JavaFXApplication11 application){
        this.application = application;
    }
    
    public void handleButtonAction(ActionEvent event)throws IOException{
           int Files_Test1 = parseInt(FilesTest1.getText());
           int NAgents_Test1 = parseInt(NAgentsTest1.getText());
           int UAgents_Test1 = parseInt(UAgentsTest1.getText());
           int DAgents_Test1 = parseInt(DAgentsTest1.getText());
           int PAgents_Test1 = parseInt(PAgentsTest1.getText());
           
           int Files_Test2 = parseInt(FilesTest2.getText());
           int NAgents_Test2 = parseInt(NAgentsTest2.getText());
           int UAgents_Test2 = parseInt(UAgentsTest2.getText());
           int DAgents_Test2 = parseInt(DAgentsTest2.getText());
           int PAgents_Test2 = parseInt(PAgentsTest2.getText());
           
           Tester.Login(Files_Test1, NAgents_Test1, UAgents_Test1, DAgents_Test1, PAgents_Test1, Files_Test2, NAgents_Test2, UAgents_Test2, DAgents_Test2, PAgents_Test2);
           
        Stage stage = new Stage();
   
    Pane myPane = null;
    myPane = FXMLLoader.load(getClass().getResource("gui2.fxml"));
    Scene scene = new Scene(myPane);
    stage.setScene(scene);
    stage.show();
    // Need to make threads
    //Tester.RunTest1();
    //Tester.RunTest2();
    }
    
    public int setValues(int value){
        
        return value;
    }
     
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   public void handleButtonAction2(ActionEvent event)throws IOException{
            int Files_Test1 = parseInt(FilesTest1.getText());
           int NAgents_Test1 = parseInt(NAgentsTest1.getText());
           int UAgents_Test1 = parseInt(UAgentsTest1.getText());
           int DAgents_Test1 = parseInt(DAgentsTest1.getText());
           int PAgents_Test1 = parseInt(PAgentsTest1.getText());
           
           int Files_Test2 = parseInt(FilesTest2.getText());
           int NAgents_Test2 = parseInt(NAgentsTest2.getText());
           int UAgents_Test2 = parseInt(UAgentsTest2.getText());
           int DAgents_Test2 = parseInt(DAgentsTest2.getText());
           int PAgents_Test2 = parseInt(PAgentsTest2.getText());
           
           Tester.Login(Files_Test1, NAgents_Test1, UAgents_Test1, DAgents_Test1, PAgents_Test1, Files_Test2, NAgents_Test2, UAgents_Test2, DAgents_Test2, PAgents_Test2);
            Tester.RunTest1();
            System.out.print("");
            System.out.print("");
            Tester.RunTest2();
        Stage stage = new Stage();
   
    Pane myPane = null;
    myPane = FXMLLoader.load(getClass().getResource("ResultsPage.fxml"));
    Scene scene = new Scene(myPane);
    stage.setScene(scene);
    stage.show();
    Tester.Results();
    }
    
   @FXML public void handleButtonAction3(ActionEvent event) {
    R_Files_T1.setText(Integer.toString(Tester.InitialFiles1));
    R_NAgents_T1.setText(Integer.toString(Tester.InitialNAgents1));
    R_UAgents_T1.setText(Integer.toString(Tester.InitialUAgents1));
    R_DAgents_T1.setText(Integer.toString(Tester.InitialDAgents1));
    R_PAgents_T1.setText(Integer.toString(Tester.InitialPAgents1));
    
    R_Files_T2.setText(Integer.toString(Tester.InitialFiles2));
    R_NAgents_T2.setText(Integer.toString(Tester.InitialNAgents2));
    R_UAgents_T2.setText(Integer.toString(Tester.InitialUAgents2));
    R_DAgents_T2.setText(Integer.toString(Tester.InitialDAgents2));
    R_PAgents_T2.setText(Integer.toString(Tester.InitialPAgents2));
    
    R_Files_TD.setText(Integer.toString(Tester.InitialFiles2-Tester.InitialFiles1));
    R_NAgents_TD.setText(Integer.toString(Tester.InitialNAgents2-Tester.InitialNAgents1));
    R_UAgents_TD.setText(Integer.toString(Tester.InitialUAgents2-Tester.InitialUAgents1));
    R_DAgents_TD.setText(Integer.toString(Tester.InitialDAgents2-Tester.InitialDAgents1));
    R_PAgents_TD.setText(Integer.toString(Tester.InitialPAgents2-Tester.InitialPAgents1));
    
    R_RqSent_T1.setText(Integer.toString(Tester.Old_RequestsSent));
    R_RqAccepted_T1.setText(Integer.toString(Tester.Old_RequestsAccepted));
    R_RqRejected_T1.setText(Integer.toString(Tester.Old_RequestsRejected));
    
    R_RqSent_T2.setText(Integer.toString(ManagerAgent.RequestsSent));
    R_RqAccepted_T2.setText(Integer.toString(ManagerAgent.RequestsAccepted));
    R_RqRejected_T2.setText(Integer.toString(ManagerAgent.RequestsRejected));
    
    R_RqSent_TD.setText(Integer.toString(ManagerAgent.RequestsSent-Tester.Old_RequestsSent));
    R_RqAccepted_TD.setText(Integer.toString(ManagerAgent.RequestsAccepted-Tester.Old_RequestsAccepted));
    R_RqRejected_TD.setText(Integer.toString(ManagerAgent.RequestsRejected-Tester.Old_RequestsRejected));
    
    R_UAGenerated_T1.setText(Integer.toString(Tester.Old_UploadersGenerated));
    R_FilesAdded_T1.setText(Integer.toString(Tester.Old_FilesAdded));
    
    R_UAGenerated_T2.setText(Integer.toString(ManagerAgent.UploadersGenerated));
    R_FilesAdded_T2.setText(Integer.toString(ManagerAgent.FilesAdded));
    
    R_UAGenerated_TD.setText(Integer.toString(ManagerAgent.UploadersGenerated-Tester.Old_UploadersGenerated));
    R_FilesAdded_TD.setText(Integer.toString(ManagerAgent.FilesAdded-Tester.Old_FilesAdded));
    
    R_PAGenerated_T1.setText(Integer.toString(Tester.Old_PoliceGenerated));
    R_WIssued_T1.setText(Integer.toString(Tester.Old_WarningsIssued));
    R_TDIssued_T1.setText(Integer.toString(Tester.Old_TakedownsIssued));
    R_PADiscovered_T1.setText(Integer.toString(Tester.Old_PoliceAgentsDiscovered));
    
    R_PAGenerated_T2.setText(Integer.toString(ManagerAgent.PoliceGenerated));
    R_WIssued_T2.setText(Integer.toString(ManagerAgent.WarningsIssued));
    R_TDIssued_T2.setText(Integer.toString(ManagerAgent.TakedownsIssued));
    R_PADiscovered_T2.setText(Integer.toString(ManagerAgent.PoliceAgentsDiscovered));
    
    R_PAGenerated_TD.setText(Integer.toString(ManagerAgent.PoliceGenerated-Tester.Old_PoliceGenerated));
    R_WIssued_TD.setText(Integer.toString(ManagerAgent.WarningsIssued-Tester.Old_WarningsIssued));
    R_TDIssued_TD.setText(Integer.toString(ManagerAgent.TakedownsIssued-Tester.Old_TakedownsIssued));
    R_PADiscovered_TD.setText(Integer.toString(ManagerAgent.PoliceAgentsDiscovered-Tester.Old_PoliceAgentsDiscovered));
    
    R_DAGenerated_T1.setText(Integer.toString(Tester.Old_DownloadersGenerated));
    R_NewAgentsAdded_T1.setText(Integer.toString(Tester.Old_NewAgentsAdded));
    
    R_DAGenerated_T2.setText(Integer.toString(ManagerAgent.DownloadersGenerated));
    R_NewAgentsAdded_T2.setText(Integer.toString(ManagerAgent.NewAgentsAdded));
    
    R_DAGenerated_TD.setText(Integer.toString(ManagerAgent.DownloadersGenerated-Tester.Old_DownloadersGenerated));
    R_NewAgentsAdded_TD.setText(Integer.toString(ManagerAgent.NewAgentsAdded-Tester.Old_NewAgentsAdded));
    
    R_RemainingAgents_T1.setText(Integer.toString(Tester.Old_TotalRemainingAgents));
    R_RequestSucessRate_T1.setText(Double.toString(Tester.Old_SucessRate)+"%");
    R_SystemGrowth_T1.setText(Double.toString(Tester.Old_SystemGrowth)+"%");
    
    R_RemainingAgents_T2.setText(Integer.toString(ManagerAgent.TotalRemainingAgents));
    R_RequestSucessRate_T2.setText(Double.toString(Tester.SucessRate)+"%");
    R_SystemGrowth_T2.setText(Double.toString(Tester.SystemGrowth)+"%");
    
    R_RemainingAgents_TD.setText(Integer.toString(ManagerAgent.TotalRemainingAgents-Tester.Old_TotalRemainingAgents));
    R_RequestSucessRate_TD.setText(Double.toString(Tester.SucessRate-Tester.Old_SucessRate)+"%");
    R_SystemGrowth_TD.setText(Double.toString(Tester.SystemGrowth-Tester.Old_SystemGrowth)+"%");
    }
}
