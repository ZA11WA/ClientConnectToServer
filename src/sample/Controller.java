package sample;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Controller {

    private InetAddress address;

    @FXML
    public TextField nick;
    @FXML
    public TextField answer;

    @FXML
    public void initialize(){
        try{
            address = InetAddress.getByName("localhost");

        }catch (UnknownHostException e){
            e.printStackTrace();
        }
    }

    public void pressButton(ActionEvent event){
        try(Socket socket = new Socket(address,8000)) {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(new Answer(answer.getText(), nick.getText()));
            this.answer.clear();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}