package com.example.minichat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class ServidorController implements Observer, Initializable {

    @FXML
    private Button botton_enviar;

    @FXML
    private TextArea texto_area_chat;

    @FXML
    private TextField text_fiels_chat;

    private Servidor servidor;

    @FXML
    void Enviar(ActionEvent event) {
        String mensagem = "1: " + text_fiels_chat.getText() + "\n";
        texto_area_chat.appendText(mensagem);

        Cliente cliente = new Cliente(6000, mensagem);
        Thread t = new Thread(cliente);
        t.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        texto_area_chat.appendText((String) arg);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        servidor = new Servidor(5010);
        servidor.addObserver(this);
        Thread t = new Thread(servidor);
        t.start();
    }
}
