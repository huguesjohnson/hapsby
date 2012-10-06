/*
IniFile.java - Routines to read/write Windows style .ini files
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

package com.huguesjohnson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Reads/writes old-school Windows style .ini files. 
 * <p>
 * Recommended usage:
 * <p>
 * <code><pre>
 * 	private void readSavedSettings(){<br>
 * 		IniFile iniFile=new IniFile(this.settingsPath+File.separator+"YourAppName.ini");<br>
 * 		try{<br>
 * 		 		iniFile.read();<br>
 * 		} catch(IOException x){ <br>
 * 		 		//something bad happened<br>
 * 		}<br>
 * 		final String defaultString=new String("YourDefaultValue");<br>
 * 		String property1=new String(iniFile.getProperty("YourAppProperty",defaultString));<br>
 * 		String property2=new String(iniFile.getProperty("AnotherAppProperty",defaultString));<br>
 * 		//and so on..<br>
 * 		}<br>
 * 	}<br>
 * 	private void saveSettings(){<br>
 *  	IniFile iniFile=new IniFile(this.settingsPath+File.separator+"YourAppName.ini");<br>
 * 		if(iniFile!=null){<br>
 * 			iniFile.setProperty("YourAppProperty",this.someVariable);<br>
 * 			iniFile.setProperty("AnotherAppProperty",this.anotherVariable);<br>
 * 			iniFile.save("some header");<br>
 * 		}<br>
 * 	}
 * </pre></code>
 * 
 * @author Hugues Johnson
 */
public class IniFile{
	/* the name (path) of the physical ini file */
	private String fileName;
	/* collection of properties stored in the file */
	private Properties properties;

	/**
	 * Create a new instance of IniFile. For existing files, need to call <code>read()</code> method to load the contents from the file.
	 * 
	 * @param fileName Full path of ini file to read/write.
	 */
	public IniFile(String fileName){
		this.properties=new Properties();
		this.fileName=fileName;
	}

	/**
	 * Reads the file specified in the constuctor.
	 * 
	 * @throws IOException Exception thrown if the file does not exist or another I/O error occurs.
	 */
	public void read() throws IOException{
		/*
		 * erase current contents of properties, if the file is not found the contents of properties should be empty
		 */
		this.properties=new Properties();
		FileInputStream fIn=new FileInputStream(this.fileName);
		this.properties.load(fIn);
		fIn.close();
	}

	/**
	 * Saves settings to file specified in the constuctor. Overwrites old file if it exists. Creates the directory if the specified directory does not exist.
	 * 
	 * @param header New header for ini file.
	 * @throws IOException Exception thrown if an I/O error occurs.
	 */
	public void save(String header) throws IOException{
		/*
		 * does the directory this file should reside in exist? if it doesn't, a FileNoteFoundException will be thrown
		 */
		File targetDirectory=new File(this.fileName.substring(0,this.fileName.lastIndexOf(File.separator)));
		if(!targetDirectory.exists()){
			targetDirectory.mkdir();
		}
		FileOutputStream fOut=new FileOutputStream(this.fileName);
		this.properties.store(fOut,header);
		fOut.close();
	}

	/**
	 * Clears out all loaded properties, does not modify the underlying file.
	 */
	public void clear(){
		if(this.properties!=null){
			this.properties.clear();
		}
	}

	/**
	 * Sets a property in memory - use <code>save()</code> to write all properties back to the file.
	 * 
	 * @param name The name (key) of the setting.
	 * @param value The value of the setting.
	 */
	public void setProperty(String name,String value){
		this.properties.setProperty(name.trim(),value.trim());
	}

	/**
	 * Sets a property in memory - use <code>save()</code> to write all properties back to the file.
	 * 
	 * @param name The name (key) of the setting.
	 * @param value The value of the setting.
	 */
	public void setProperty(String name,int value){
		this.setProperty(name,String.valueOf(value));
	}

	/**
	 * Sets a property in memory - use <code>save()</code> to write all properties back to the file.
	 * 
	 * @param name The name (key) of the setting.
	 * @param value The value of the setting.
	 */
	public void setProperty(String name,boolean value){
		this.setProperty(name,String.valueOf(value));
	}

	/**
	 * Sets a property in memory - use <code>save()</code> to write all properties back to the file.
	 * 
	 * @param name The name (key) of the setting.
	 * @param value The value of the setting.
	 */
	public void setProperty(String name,long value){
		this.setProperty(name,String.valueOf(value));
	}

	/**
	 * Sets a property in memory - use <code>save()</code> to write all properties back to the file.
	 * 
	 * @param name The name (key) of the setting.
	 * @param value The value of the setting.
	 */
	public void setProperty(String name,float value){
		this.setProperty(name,String.valueOf(value));
	}

	/**
	 * Sets a property in memory - use <code>save()</code> to write all properties back to the file.
	 * 
	 * @param name The name (key) of the setting.
	 * @param value The value of the setting.
	 */
	public void setProperty(String name,double value){
		this.setProperty(name,String.valueOf(value));
	}

	/**
	 * Sets a property in memory - use <code>save()</code> to write all properties back to the file.
	 * 
	 * @param name The name (key) of the setting.
	 * @param value The value of the setting.
	 */
	public void setProperty(String name,char value){
		this.setProperty(name,String.valueOf(value));
	}

	/**
	 * Sets a property in memory - use <code>save()</code> to write all properties back to the file.
	 * 
	 * @param name The name (key) of the setting.
	 * @param value The value of the setting.
	 */
	public void setProperty(String name,byte value){
		this.setProperty(name,String.valueOf(value));
	}

	/**
	 * Returns a property from memory.
	 * 
	 * @param name The name (key) of the property to retrieve.
	 * @param defaultValue The value to return if property is not found.
	 * @return The value of the property if found, else the default value.
	 */
	public String getProperty(String name,String defaultValue){
		return(this.properties.getProperty(name,defaultValue).trim());
	}

	/**
	 * Returns a property from memory.
	 * 
	 * @param name The name (key) of the property to retrieve.
	 * @param defaultValue The value to return if property is not found.
	 * @return The value of the property if found, else the default value.
	 */
	public int getProperty(String name,int defaultValue){
		String property=this.properties.getProperty(name,String.valueOf(defaultValue)).trim();
		try{
			int returnValue=Integer.valueOf(property).intValue();
			return(returnValue);
		} catch(NumberFormatException nfx){
			return(defaultValue);
		}
	}

	/**
	 * Returns a property from memory.
	 * 
	 * @param name The name (key) of the property to retrieve.
	 * @param defaultValue The value to return if property is not found.
	 * @return The value of the property if found, else the default value.
	 */
	public boolean getProperty(String name,boolean defaultValue){
		String property=this.properties.getProperty(name,String.valueOf(defaultValue)).trim();
		try{
			boolean returnValue=Boolean.valueOf(property).booleanValue();
			return(returnValue);
		} catch(NumberFormatException nfx){
			return(defaultValue);
		}
	}

	/**
	 * Returns a property from memory.
	 * 
	 * @param name The name (key) of the property to retrieve.
	 * @param defaultValue The value to return if property is not found.
	 * @return The value of the property if found, else the default value.
	 */
	public double getProperty(String name,double defaultValue){
		String property=this.properties.getProperty(name,String.valueOf(defaultValue)).trim();
		try{
			double returnValue=Integer.valueOf(property).doubleValue();
			return(returnValue);
		} catch(NumberFormatException nfx){
			return(defaultValue);
		}
	}

	/**
	 * Returns a property from memory.
	 * 
	 * @param name The name (key) of the property to retrieve.
	 * @param defaultValue The value to return if property is not found.
	 * @return The value of the property if found, else the default value.
	 */
	public byte getProperty(String name,byte defaultValue){
		String property=this.properties.getProperty(name,String.valueOf(defaultValue)).trim();
		try{
			byte returnValue=Byte.valueOf(property).byteValue();
			return(returnValue);
		} catch(NumberFormatException nfx){
			return(defaultValue);
		}
	}

	/**
	 * Returns a property from memory.
	 * 
	 * @param name The name (key) of the property to retrieve.
	 * @param defaultValue The value to return if property is not found.
	 * @return The value of the property if found, else the default value.
	 */
	public long getProperty(String name,long defaultValue){
		String property=this.properties.getProperty(name,String.valueOf(defaultValue)).trim();
		try{
			long returnValue=Long.valueOf(property).longValue();
			return(returnValue);
		} catch(NumberFormatException nfx){
			return(defaultValue);
		}
	}

	/**
	 * Returns a property from memory.
	 * 
	 * @param name The name (key) of the property to retrieve.
	 * @param defaultValue The value to return if property is not found.
	 * @return The value of the property if found, else the default value.
	 */
	public float getProperty(String name,float defaultValue){
		String property=this.properties.getProperty(name,String.valueOf(defaultValue)).trim();
		try{
			float returnValue=Float.valueOf(property).floatValue();
			return(returnValue);
		} catch(NumberFormatException nfx){
			return(defaultValue);
		}
	}

	/**
	 * Returns a property from memory.
	 * 
	 * @param name The name (key) of the property to retrieve.
	 * @param defaultValue The value to return if property is not found.
	 * @return The value of the property if found, else the default value.
	 */
	public char getProperty(String name,char defaultValue){
		String property=this.properties.getProperty(name,String.valueOf(defaultValue)).trim();
		char c;
		if(property.length()>1){
			c=defaultValue;
		} else{
			c=property.charAt(0);
		}
		return(c);
	}

	/**
	 * Returns the path to the file specified in the constructor and all properties currently in memory.
	 * 
	 * @return The path to the file specified in the constructor and all properties currently in memory.
	 */
	public String toString(){
		StringBuffer tostring=new StringBuffer(super.toString());
		tostring.append("\nfileName=");
		tostring.append(this.fileName);
		tostring.append("\nproperties=");
		Enumeration<?> propertyNames=this.properties.propertyNames();
		while(propertyNames.hasMoreElements()){
			Object propertyName=propertyNames.nextElement();
			Object property=this.properties.get(propertyName);
			tostring.append("\n[");
			tostring.append(propertyName.toString());
			tostring.append("=");
			tostring.append(property.toString());
			tostring.append("]");
		}
		return(tostring.toString());
	}
}