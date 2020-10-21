package sample;

import com.sun.speech.freetts.VoiceManager;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class Controller {
    ObservableList<Word> list = FXCollections.observableArrayList();

    @FXML
    private TextField search;

    @FXML
    private TextField wordTarget;

    @FXML
    private TextField definition;

    @FXML
    private TextArea meaning;

    @FXML
    private ListView<String> listView;

    @FXML
    private ActionEvent actionEvent;
    DictionaryManagement dictionaryManagement = new DictionaryManagement();

    public void pressToAdd(MouseEvent mouseEvent) {
        list.removeAll(list);
        if (wordTarget.getText() != null && definition.getText() != null) {
            if (dictionaryManagement.dictionaryLookup(wordTarget.getText()).equals("Not found")) {
                dictionaryManagement.addToFile(wordTarget.getText(), definition.getText());
            }
        }
        wordTarget.setText(null);
        definition.setText(null);
        list = FXCollections.observableArrayList(dictionaryManagement.getWordArrayList());
    }

    public void pressToDelete(MouseEvent mouseEvent) {
        list.removeAll(list);
        String word = search.getText();
        if (wordTarget.getText() != null && definition.getText() != null) {
            if (!dictionaryManagement.dictionaryLookup(wordTarget.getText()).equals("Not found")) {
                dictionaryManagement.deleteWord(word);
                dictionaryManagement.dictionaryExportToFile();
            }
        }
        wordTarget.setText(null);
        definition.setText(null);
        search.setText(null);
        list = FXCollections.observableArrayList(dictionaryManagement.getWordArrayList());

    }

    public void voice(MouseEvent mouseEvent) {
        String word = search.getText();
        VoiceManager voiceManager = VoiceManager.getInstance();
        com.sun.speech.freetts.Voice synthesizer = voiceManager.getVoice("kevin16");
        synthesizer.allocate();
        synthesizer.speak(word);
        synthesizer.deallocate();
    }

    //Change scene
    public void goBack(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
    }


}