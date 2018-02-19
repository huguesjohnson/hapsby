/*
Hapsby - universal save game editor
SaveGameDefinition.java - Save game defintion
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.huguesjohnson.hapsby;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class to define the attributes and editable fields of a save game.
 * 
 * @author Hugues Johnson
 */
public class SaveGameDefinition implements Serializable{
	private static final long serialVersionUID=4560725891622337985L;
	
	/* array to hold all properties */
	private ArrayList<SaveGameProperty> properties;
	/* title of the game */
	private String gameTitle;
	/* description of save game */
	private String gameDescription;
	/* file name or pattern of save game */
	private String saveFilePattern;


	/**
	 * Constructor, no logic implemented.
	 */
	public SaveGameDefinition(){ /* empty */ }

	/**
	 * Returns the game title.
	 * 
	 * @return The game title.
	 */
	public final String getGameTitle(){
		return(this.gameTitle);
	}

	/**
	 * Returns the description of the game.
	 * 
	 * @return The description of the game.
	 */
	public final String getGameDescription(){
		return(this.gameDescription);
	}

	/**
	 * Returns the name, or pattern of the save game.
	 * 
	 * @return The file name (ie. save.dat) or pattern (ie. *.dat) of the game.
	 */
	public final String getSaveFilePattern(){
		return(this.saveFilePattern);
	}

	/**
	 * Returns all properties in this save game definition.
	 * 
	 * @return All properties in this save game definition.
	 */
	public ArrayList<SaveGameProperty> getProperties(){
		return(this.properties);		
	}
	
	/**
	 * Accesses a specific save game property.
	 * 
	 * @param index Index of the element to retrieve.
	 * @return The <code>SaveGameProperty</index> at the specified index.
	 */
	public SaveGameProperty getSaveGameProperty(int index){
		return(this.properties.get(index));
	}

	/**
	 * Sets the property list.
	 * 
	 * @param properties The new properties for the save game.
	 */
	public void setProperties(ArrayList<SaveGameProperty> properties){
		this.properties=properties;
	}

	/**
	 * Sets the game title.
	 * 
	 * @param gameTitle The new title for the save game.
	 */
	public void setGameTitle(String gameTitle){
		this.gameTitle=gameTitle;
	}

	/**
	 * Sets the game description.
	 * 
	 * @param gameDescription The new game description.
	 */
	public void setGameDescription(String gameDescription){
		this.gameDescription=gameDescription;
	}

	/**
	 * Sets the save file pattern.
	 * 
	 * @param saveFilePattern The new file name (ie. save.dat) or pattern (ie. *.dat) of the game.
	 */
	public void setSaveFilePattern(String saveFilePattern){
		this.saveFilePattern=saveFilePattern;
	}

	/**
	 * Returns the game title.
	 * <code>toString()</code> is implicitly called by <code>JList</code> and <code>JTable</code> components when complex objects are added.
	 * This needs to return the text that should be displayed in these controls. In this case, it's the game title.
	 * 
	 * @return The game title.
	 */
	public String toString(){
		return(this.getGameTitle());
	}
}
