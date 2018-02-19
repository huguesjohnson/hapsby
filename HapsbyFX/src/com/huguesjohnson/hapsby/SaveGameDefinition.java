/*
Hapsby - universal save game editor
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.huguesjohnson.hapsby;

import java.io.Serializable;
import java.util.ArrayList;

public class SaveGameDefinition implements Serializable{
    private static final long serialVersionUID=4560725891622337985L;
    private ArrayList<SaveGameProperty> properties;
    private String gameTitle;
    private String gameDescription;
    private String saveFilePattern;

    public SaveGameDefinition(){ }

    public final String getGameTitle(){
        return(this.gameTitle);
    }

    public final String getGameDescription(){
        return(this.gameDescription);
    }

    public final String getSaveFilePattern(){
        return(this.saveFilePattern);
    }

    public ArrayList<SaveGameProperty> getProperties(){
        return(this.properties);		
    }
	
    public SaveGameProperty getSaveGameProperty(int index){
        return(this.properties.get(index));
    }

    public void setProperties(ArrayList<SaveGameProperty> properties){
        this.properties=properties;
    }

    public void setGameTitle(String gameTitle){
        this.gameTitle=gameTitle;
    }

    public void setGameDescription(String gameDescription){
        this.gameDescription=gameDescription;
    }

    public void setSaveFilePattern(String saveFilePattern){
        this.saveFilePattern=saveFilePattern;
    }

    @Override
    public String toString(){
        return(this.getGameTitle());
    }
}
