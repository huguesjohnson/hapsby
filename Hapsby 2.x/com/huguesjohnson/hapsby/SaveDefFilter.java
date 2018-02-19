/*
Hapsby - universal save game editor
SaveDefFilter.java - Filter for save game definition files
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.huguesjohnson.hapsby;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Filter for Hapsby save game definition files (.hsd.xml).
 * 
 * @author Hugues Johnson
 */
public class SaveDefFilter implements FilenameFilter{
	/** Extention of Hapsby save game definitions. */
	public final static String EXTENTION=".hsd.xml";

	/**
	 * Tests if a file is a valid save game definition.
	 * 
	 * @param dir Not used but required to implement FilenameFilter.
	 * @param name The file name to check.
	 * @return True if the name parameter matches <code>EXTENTION</code>.
	 */
	public boolean accept(File dir,String name){
		/* convert to lowercase before testing */
		return(name.toLowerCase().endsWith(SaveDefFilter.EXTENTION));
	}
}
