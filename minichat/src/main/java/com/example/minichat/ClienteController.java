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

public class ClienteController implements Observer, Initializable {

    @FXML
    private TextArea text_area_cliente;

    @FXML
    private TextField text_field_cliente;

    @FXML
    private Button boton_enviar;
    private Servidor servidor;

    @FXML
    void Enviar(ActionEvent event) {
        String mensagem = "2: " + text_field_cliente.getText() + "\n";
        text_area_cliente.appendText(mensagem);

        Cliente cliente = new Cliente(5010, mensagem);
        Thread t = new Thread(cliente);
        t.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        text_area_cliente.appendText((String) arg);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        servidor = new Servidor(6000);
        servidor.addObserver(this);
        Thread t = new Thread(servidor);
        t.start();
    }
}
