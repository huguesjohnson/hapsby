/*
    Hapsby - universal save game editor
    Copyright (C) 2000-2015 Hugues Johnson

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
