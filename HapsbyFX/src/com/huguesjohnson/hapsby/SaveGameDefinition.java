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