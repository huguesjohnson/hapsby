/*
Hapsby - universal save game editor
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.huguesjohnson.hapsbyfx;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HapsbyFX extends Application{
    private final static String DEFAULT_LOCALE="eng";
    private final static String BUNDLE_BASE="com.huguesjohnson.hapsby.bundles.hapsby_";
    
    @Override
    public void start(Stage stage) throws Exception{
        ResourceBundle resources=null;
        String locale=Locale.getDefault().getISO3Language();
        String bundleName=BUNDLE_BASE+locale;
        try{
            resources=ResourceBundle.getBundle(bundleName);
        }catch(MissingResourceException mrx){ 
            resources=ResourceBundle.getBundle(BUNDLE_BASE+DEFAULT_LOCALE);
        }
        Parent root=FXMLLoader.load(getClass().getResource("HapsbyFX.fxml"),resources);
        Scene scene=new Scene(root);
        try{
            stage.getIcons().add(new Image(HapsbyFX.class.getResourceAsStream("res/icon.png"))); 
        }catch(Exception x){/* not implemented - just here to prevent application from crashing if for some reason the icon can't be loaded */}
        stage.setScene(scene);
        stage.setTitle(resources.getString("app_title"));
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
