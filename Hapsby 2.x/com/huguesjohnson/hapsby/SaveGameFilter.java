/*
Hapsby - universal save game editor
SaveGameFilter.java - Customizable filter for save game files
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.huguesjohnson.hapsby;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * Customizable filter for save game files.
 * 
 * @author Hugues Johnson
 */
public class SaveGameFilter extends FileFilter{
	private String filePattern;
	private String description;

	/**
	 * Creates a filter based on file pattern passed. Only leading and trailing wildcard characters are accepted. For example: *.txt, name.*, and *.da* are all acceptible, but qw*.ert is not.
	 * 
	 * @param filePattern Pattern, or specific name, to apply to filter.
	 * @param gameTitle Title of game the pattern is for, used to set the description.
	 */
	public SaveGameFilter(String filePattern,String gameTitle){
		/* set description */	
		this.description=gameTitle+" Save Games ("+filePattern+")";
		/* parse out leading & trailing wildcards */
		this.filePattern=filePattern.toLowerCase().replace("*","");
	}

	/**
	 * Tests if a file fits the filter defined in the constructor.
	 * 
	 * @param f The file to check.
	 * @return True if the file path matches the defined filter or is a directory, false if it does not match the filter.
	 */
	public boolean accept(File f){
		if(f.isDirectory()){
			return(true);
		} 
		return((f.getName().toLowerCase().indexOf(this.filePattern))>0);
	}

	/**
	 * Returns the description of file accepted by the filter.
	 * 
	 * @return The description of file accepted by the filter.
	 */
	public String getDescription(){
		return(this.description);
	}
}
