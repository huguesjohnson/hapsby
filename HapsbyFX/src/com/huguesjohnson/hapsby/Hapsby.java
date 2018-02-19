/*
Hapsby - universal save game editor
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
