/*
Hapsby - universal save game editor
SaveGameProperty.java - Save game property
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

import java.io.Serializable;

/**
 * Class to describe a property (value) of a save game.
 * 
 * @author Hugues Johnson
 */
public class SaveGameProperty implements Serializable{
	private static final long serialVersionUID=-6760189712493942479L;
	
	/**
	 * public enum ByteOrder { LOW_BYTE_FIRST, HIGH_BYTE_FIRST }
	 * <br>
	 * Whether values are stored high byte first (Big-Endian) or low byte first (Little-Endian).
	 */
	public enum ByteOrder { LOW_BYTE_FIRST, HIGH_BYTE_FIRST }
	/**
	 * public enum DataType { TYPE_INTEGER, TYPE_STRING }
	 * <br>
	 * Data type for the property.
	 */
	public enum DataType { TYPE_INTEGER, TYPE_STRING }
	
	/* name of property */
	private String name;
	/* description of property */
	private String description;
	/* byte order */
	private ByteOrder byteOrder;
	/* data type */
	private DataType dataType;
	/* address of property, in decimal format */
	private int address;
	/* length (number of bytes) of the property */
	private int length;
	/* maximum value of the property */
	private int maxValue;
	/* minimum value of the property */
	private int minValue;

	/**
	 * Default constructor.
	 */
	public SaveGameProperty(){ /* empty */ }
	
	/**
	 * Creates a new property.
	 * 
	 * @param name Name of the property.
	 * @param description Description of the property.
	 * @param byteOrder Byte order of the property.
	 * @param dataType Data type of the the property.
	 * @param address Address of new property, in decimal.
	 * @param length Length (number of bytes) of the property.
	 * @param maxValue Maximum value of the property, in decimal.
	 * @param minValue Minimum value of the property, in decimal.
	 */
	public SaveGameProperty(String name,String description,ByteOrder byteOrder,DataType dataType,int address,int length,int maxValue,int minValue){
		/*
		 * initialize these to zero to prevent errors when calling the set methods later on
		 */
		this.maxValue=0;
		this.minValue=0;
		/* use methods because they check if the parameters are valid */
		try{
			this.setName(name);
			this.setDescription(description);
			this.setByteOrder(byteOrder);
			this.setDataType(dataType);
			this.setAddress(address);
			this.setLength(length);
			this.setMaxValue(maxValue);
			this.setMinValue(minValue);
		} catch(Exception x){
			x.printStackTrace();
		}
	}

	/**
	 * Sets the name of the property.
	 * 
	 * @param name The new name of the property.
	 */
	public final void setName(String name){
		this.name=name;
	}

	/**
	 * Sets the description of the property.
	 * 
	 * @param description The new description of the property.
	 */
	public final void setDescription(String description){
		this.description=description;
	}

	/**
	 * Sets the address & checks if it's valid.
	 * 
	 * @param address The new address in decimal form, must be >= 0.
	 * @throws Exception Exception thrown if address is <=0.
	 */
	public final void setAddress(int address) throws Exception{
		try{
			if(address>=0){
				this.address=address;
			} else{
				this.address=0;
				throw(new Exception("Address must be >= 0"));
			}
		} catch(Exception x){
			x.printStackTrace();
		}
	}

	/**
	 * Sets the length property, in decimal format, must be > 0.
	 * 
	 * @param length The new length of the property.
	 * @throws Exception Exception thrown if length is <=0.
	 */
	public final void setLength(int length) throws Exception{
		try{
			if(length>0){
				this.length=length;
			} else{
				this.length=1;
				throw(new Exception("Length must be > 0"));
			}
		} catch(Exception x){
			x.printStackTrace();
		}
	}

	/**
	 * Sets the maximum value of the property, in decimal format.
	 * 
	 * @param maxValue The new maximum value of the property.
	 */
	public final void setMaxValue(int maxValue){
		this.maxValue=maxValue;
	}

	/**
	 * Sets the minimum value of the property, in decimal format.
	 * 
	 * @param minValue The new minimum value of the property.
	 */
	public final void setMinValue(int minValue){
		this.minValue=minValue;
	}

	/**
	 * Returns the name of the property.
	 * 
	 * @return The name of the property.
	 */
	public final String getName(){
		return(this.name);
	}

	/**
	 * Returns the description of the property.
	 * 
	 * @return The description of the property.
	 */
	public final String getDescription(){
		return(this.description);
	}

	/**
	 * Returns the data type of the property.
	 * 
	 * @return The data type of the property.
	 */
	public final DataType getDataType(){
		return(this.dataType);
	}

	/**
	 * Returns the address of the property in decimal.
	 * 
	 * @return The address of the property.
	 */
	public final int getAddress(){
		return(this.address);
	}

	/**
	 * Returns the length (number of bytes) of the property.
	 * 
	 * @return The length (number of bytes) of the property.
	 */
	public final int getLength(){
		return(this.length);
	}

	/**
	 * Returns the maximum value of the property, in decimal.
	 * 
	 * @return The maximum value of the property.
	 */
	public final int getMaxValue(){
		return(this.maxValue);
	}

	/**
	 * Returns the minimum value of the property, in decimal.
	 * 
	 * @return The minimum value of the property.
	 */
	public final int getMinValue(){
		return(this.minValue);
	}
	
	/**
	 * Returns the byte order of the property.
	 * 
	 * @return Byte order of the property.
	 */
	public ByteOrder getByteOrder(){
		return(this.byteOrder);
	}

	/**
	 * Sets the byte order of this property.
	 * 
	 * @param byteOrder The new byte order of this property.
	 */
	public void setByteOrder(ByteOrder byteOrder){
		this.byteOrder=byteOrder;
	}

	/**
	 * Sets the data type of this property.
	 * 
	 * @param dataType The new data type of this property.
	 */
	public void setDataType(DataType dataType){
		this.dataType=dataType;
	}	
	
	/**
	 * Returns the property name.
	 * <code>toString()</code> is implicitly called by <code>JList</code> and <code>JTable</code> components when complex objects are added.
	 * This needs to return the text that should be displayed in these controls. In this case, it's the property name.
	 * 
	 * @return The game title.
	 */
	public String toString(){
		return(this.getName());
	}	
}