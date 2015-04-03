/*
Hapsby - universal save game editor
Copyright  (C) 2000-2015 Hugues Johnson

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
the GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software 
Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */

package com.huguesjohnson.hapsby;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.huguesjohnson.hapsby.exceptions.SaveGameIOException;
import com.huguesjohnson.hapsby.exceptions.ValidationException;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Hapsby{
    private static final Logger logger=Logger.getLogger(Hapsby.class.getName());
    private ArrayList<SaveGameDefinition> gameDefinitions;
    private SaveGame activeSaveGame;

    public Hapsby(){
        try{
            this.loadDefinitions();
        }catch(Exception x){
            logger.log(Level.SEVERE,x.getMessage(),x);
        }
    }

    private void loadDefinitions() throws Exception{
        String defFilePath=System.getProperty("user.dir")+File.separator+"hsd";
        File f=new File(defFilePath);
        String[] defList=f.list(new SaveDefFilter());
        int length=defList.length;
        this.gameDefinitions=new ArrayList<>();
        Gson gson=new Gson();
        for(int index=0;index<length;index++){
            try(JsonReader reader=new JsonReader(new FileReader(defFilePath+File.separator+defList[index]))){
                this.gameDefinitions.add((SaveGameDefinition)gson.fromJson(reader,SaveGameDefinition.class));
            }
        }
    }
    
    public ArrayList<SaveGameDefinition> getSaveGameDefinitions(){
        return(this.gameDefinitions);
    }
	
    public final void closeSaveGame(){
        try{
            if(this.activeSaveGame!=null){
                this.activeSaveGame.close();
            }
        }catch(Exception x){
            logger.log(Level.SEVERE,x.getMessage(),x);
        }
    }

    public final void saveProperty(SaveGameProperty property,int newValue) throws SaveGameIOException, ValidationException{
        if(this.activeSaveGame==null){throw(new SaveGameIOException("A save game is not open for editing."));}
        this.validate(property,newValue);
        this.activeSaveGame.setIntValue(property.getAddress(),property.getLength(),newValue,property.getByteOrder());
    }

    public final void saveProperty(SaveGameProperty property,String newValue) throws SaveGameIOException, ValidationException{
        if(this.activeSaveGame==null){throw(new SaveGameIOException("A save game is not open for editing."));}
        this.validate(property,newValue);
        this.activeSaveGame.setStringValue(property.getAddress(),property.getLength(),newValue,property.getByteOrder());
    }

    public final String getPropertyValue(SaveGameProperty property) throws SaveGameIOException{
        if(this.activeSaveGame==null){throw(new SaveGameIOException("A save game is not open for editing."));}
        if(property.getDataType()==DataType.TYPE_INTEGER){
            return(String.valueOf(this.activeSaveGame.getIntValue(property.getAddress(),property.getLength(),property.getByteOrder())));
        }
        return(this.activeSaveGame.getStringValue(property.getAddress(),property.getLength(),property.getByteOrder()));
    }

    public final void openSaveGame(String saveGamePath) throws SaveGameIOException{
        if(this.activeSaveGame!=null){
            this.activeSaveGame.close();
        }
        this.activeSaveGame=new SaveGame(saveGamePath);
    }

    public boolean hasActiveSaveGame(){
        return(this.activeSaveGame!=null);
    }
    
    private void validate(SaveGameProperty property,String value) throws ValidationException{
        int l=value.length();
        if(l>property.getLength()){
            throw(new ValidationException("Length",property.getLength(),l));
        }
    }

    private void validate(SaveGameProperty property,int value) throws ValidationException{
        if(value<property.getMinValue()){
            throw(new ValidationException("MinimumValue",property.getMinValue(),value));
        }
        if(value>property.getMaxValue()){
            throw(new ValidationException("MaximumValue",property.getMaxValue(),value));
        }
    }

}