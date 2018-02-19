/*
Hapsby - universal save game editor
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.huguesjohnson.hapsbyfx;

import com.huguesjohnson.hapsby.DataType;
import com.huguesjohnson.hapsby.Hapsby;
import com.huguesjohnson.hapsby.SaveGameDefinition;
import com.huguesjohnson.hapsby.SaveGameProperty;
import com.huguesjohnson.hapsby.exceptions.SaveGameIOException;
import com.huguesjohnson.hapsby.exceptions.ValidationException;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class HapsbyFXController implements Initializable{
    private Hapsby hapsby;
    private SaveGameProperty activeProperty;
    private ResourceBundle bundle;

    @FXML
    private Label labelDataType;

    @FXML
    private Label labelDataTypeValue;
    
    @FXML
    private Label labelMinimumValue;

    @FXML
    private Label labelMinimumValueValue;

    @FXML
    private Label labelAddress;

    @FXML
    private Label labelAddressValue;

    @FXML
    private Label labelSaveGame;

    @FXML
    private Label labelSelectedValueDescriptionHeader;

    @FXML
    private Button buttonOpenSaveGame;

    @FXML
    private Label labelMaximumValue;

    @FXML
    private Label labelMaximumValueValue;

    @FXML
    private Label labelCurrentValue;

    @FXML
    private Label labelSaveGameDescriptionHeader;

    @FXML
    private ListView<SaveGameProperty> listProperties;

    @FXML
    private Label labelBytes;

    @FXML
    private Label labelBytesValue;
    
    @FXML
    private Label labelByteOrder;
    
    @FXML
    private Label labelByteOrderValue;
    
    @FXML
    private Label labelSaveGameDefinition;

    @FXML
    private TextField textSaveGame;

    @FXML
    private Label labelSelectValue;

    @FXML
    private Button buttonSave;

    @FXML
    private TextField textCurrentValue;

    @FXML
    private ChoiceBox<SaveGameDefinition> choiceSaveGameDefinition;

    @FXML
    private Label labelSaveGameDescription;

    @FXML
    private Label labelSelectedValueDescription;

    @FXML
    private Label labelStatus;
    
    @Override
    public void initialize(URL url, ResourceBundle bundle){
        this.bundle=bundle;
        this.hapsby=new Hapsby();
        //setup save game definition list
        this.choiceSaveGameDefinition.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends SaveGameDefinition> observableValue, SaveGameDefinition oldDef, SaveGameDefinition newDef) -> {
            setSaveGameDefinition(newDef);
        });
        ObservableList<SaveGameDefinition> defs=FXCollections.observableArrayList(this.hapsby.getSaveGameDefinitions());
        this.choiceSaveGameDefinition.setItems(defs);
        //setup save game properties list
        this.listProperties.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.listProperties.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends SaveGameProperty> observableValue, SaveGameProperty oldProperty, SaveGameProperty newProperty) -> {
            setSaveGameProperty(newProperty);
        });
        //setup listener for text changed
        this.textCurrentValue.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            validate(newValue);
        });
        //setup save button
        this.buttonSave.setDisable(true);
    }    
    
    @FXML
    void onSave(ActionEvent event){
        try{
            String currentValue=this.textCurrentValue.getText();
            this.validate(currentValue);
            if(this.activeProperty.getDataType()==DataType.TYPE_INTEGER){
                this.hapsby.saveProperty(this.activeProperty,Integer.valueOf(currentValue));
            }else if(this.activeProperty.getDataType()==DataType.TYPE_STRING){
                this.hapsby.saveProperty(this.activeProperty,currentValue);
            }
            this.labelStatus.setText(this.bundle.getString("message_saved")+this.activeProperty.getName());
            this.buttonSave.setDisable(true);
        }catch(SaveGameIOException | ValidationException | NumberFormatException x){
            this.labelStatus.setText(this.bundle.getString("message_exception")+x.getMessage());
        }
    }

    @FXML
    void onOpenSaveGame(ActionEvent event){
        FileChooser fc=new FileChooser();
        fc.setInitialDirectory(this.getInitialDirectory());
        fc.setTitle(this.bundle.getString("dialog_opensavegame"));
        Stage stage=(Stage)this.buttonOpenSaveGame.getScene().getWindow();
        File f=fc.showOpenDialog(stage);
        if(f!=null){
            try{
                this.hapsby.openSaveGame(f.getAbsolutePath());
                this.textSaveGame.setText(f.getName());
                this.saveInitialDirectory(f.getParent());
                if(this.listProperties.getItems().size()>0){
                    if(this.listProperties.getSelectionModel().getSelectedIndex()>=0){
                        this.setSaveGameProperty(this.listProperties.getSelectionModel().getSelectedItems().get(0));
                    }
                }
                this.labelStatus.setText(this.bundle.getString("message_opened")+f.getAbsolutePath());
            }catch(SaveGameIOException x){
                this.labelStatus.setText(this.bundle.getString("message_exception")+x.getMessage());
            }
        }
    }
 
    private void setSaveGameDefinition(SaveGameDefinition def){
        if(def==null){
            this.labelSaveGameDescription.setText(this.bundle.getString("message_opensavegame"));
            this.listProperties.getItems().clear();
            this.setSaveGameProperty(null);
        }else{
            this.labelSaveGameDescription.setText(def.getGameDescription());
            ObservableList<SaveGameProperty> properties=FXCollections.observableArrayList(def.getProperties());
            this.listProperties.setItems(properties);
            this.listProperties.getSelectionModel().select(0);
        }
    }
    
    private void setSaveGameProperty(SaveGameProperty property){
        this.activeProperty=property;
        if(property==null){
            this.labelMinimumValueValue.setText("");
            this.labelAddressValue.setText("");
            this.labelMaximumValueValue.setText("");
            this.labelBytesValue.setText("");
            this.labelByteOrderValue.setText("");
            this.labelSelectedValueDescription.setText("");
            this.textCurrentValue.setText("");
        }else{
            this.labelDataTypeValue.setText(property.getDataType().toString());
            this.labelMinimumValueValue.setText(String.valueOf(property.getMinValue()));
            this.labelAddressValue.setText(String.valueOf(property.getAddress()));
            this.labelMaximumValueValue.setText(String.valueOf(property.getMaxValue()));
            this.labelBytesValue.setText(String.valueOf(property.getLength()));
            this.labelByteOrderValue.setText(String.valueOf(property.getByteOrder()));
            this.labelSelectedValueDescription.setText(property.getDescription());
            if(this.hapsby.hasActiveSaveGame()){
                //disable until the value is changed
                this.buttonSave.setDisable(true);
                try{
                    this.textCurrentValue.setText(this.hapsby.getPropertyValue(property));
                    this.textCurrentValue.setDisable(false);
                }catch(SaveGameIOException x){
                    this.textCurrentValue.setText("");
                    this.textCurrentValue.setDisable(true);
                    this.labelStatus.setText(this.bundle.getString("message_exception")+x.getMessage());
                }
            }else{
                this.textCurrentValue.setText("");
                this.textCurrentValue.setDisable(true);
            }
        }
    }
    
    private void validate(String newValue){
        if(this.activeProperty==null){
            this.buttonSave.setDisable(true);
        }else{
            if(this.activeProperty.getDataType()==DataType.TYPE_STRING){
                if(newValue.length()>this.activeProperty.getLength()){
                    this.buttonSave.setDisable(true);
                }else{
                    this.buttonSave.setDisable(false);
                }
            }else if(this.activeProperty.getDataType()==DataType.TYPE_INTEGER){
                try{
                    int v=Integer.valueOf(newValue);
                    if((v>this.activeProperty.getMaxValue())||(v<this.activeProperty.getMinValue())){
                        this.buttonSave.setDisable(true);
                    }else{
                        this.buttonSave.setDisable(false);
                    }
                }catch(NumberFormatException x){
                    this.buttonSave.setDisable(true);
                }
            }
        }
    }
    
    private File getInitialDirectory(){
        String defaultDirectory=System.getProperty("user.home");
        try{
            Preferences p=Preferences.userRoot().node(Hapsby.class.getName());
            String directory=p.get("directory",defaultDirectory);
            File f=new File(directory);
            if(f.exists()){
                return(f);
            }
            return(new File(defaultDirectory));
        }catch(Exception x){
            return(new File(defaultDirectory));
        }
    }
    
    private void saveInitialDirectory(String initialDirectory){
        try{
            Preferences p=Preferences.userRoot().node(Hapsby.class.getName());
            p.put("directory",initialDirectory);
        }catch(Exception x){/* not implemented */}
    }
    
}
