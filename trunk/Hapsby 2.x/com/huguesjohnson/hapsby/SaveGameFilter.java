/*
Hapsby - universal save game editor
SaveGameFilter.java - Customizable filter for save game files
Copyright  (C) 2000-2009 Hugues Johnson

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