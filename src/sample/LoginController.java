package sample;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import java.awt.*;
import java.util.concurrent.ExecutionException;

import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
public class LoginController {
    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label txtLabel;
    @FXML
    public void login(ActionEvent event){
        try {
            if (txtUserName.getText().equals("user") && txtPassword.getText().equals("pass")) {
                txtLabel.setText("Success");
            } else {
                txtLabel.setText("Failure");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
