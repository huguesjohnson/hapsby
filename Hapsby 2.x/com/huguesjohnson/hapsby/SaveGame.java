/*
Hapsby - universal save game editor
SaveGame.java - Save game file
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
import java.io.RandomAccessFile;

import com.huguesjohnson.hapsby.SaveGameProperty.ByteOrder;

/**
 * Class to read & write values to a file.
 * 
 * @author Hugues Johnson
 */
public class SaveGame{
	/* pointer to file */
	private RandomAccessFile saveGame;
	/* (2^8) - java only defines a max signed byte */
	private int MAX_UNSIGNED_BYTE=256;
	/* read-write mode of the file */
	private final static String READ_WRITE_MODE="rw";
	/* length of a single byte */
	private final static int BYTE_LENGTH=8;

	/**
	 * Create a new <code>SaveGame</code>, opens the file passed in the constructor.
	 * 
	 * @param filePath Full path to the save game to open.
	 */
	public SaveGame(String filePath){
		this.openSaveGame(new File(filePath));
	}

	/**
	 * Create a new <code>SaveGame</code>, opens the file passed in the constructor.
	 * 
	 * @param file Save game file to open.
	 */
	public SaveGame(File file){
		this.openSaveGame(file);
	}

	/**
	 * Close the save game.
	 */
	public void close(){
		try{
			this.saveGame.close();
		} catch(Exception x){
			x.printStackTrace();
		}
	}
	
	/*
	 * openSaveGame opens a save game for reading and writing.
	 * 
	 * @param file the save game to open
	 */
	private void openSaveGame(File file){
		try{
			this.saveGame=new RandomAccessFile(file,SaveGame.READ_WRITE_MODE);
		} catch(Exception x){
			x.printStackTrace();
		}
	}

	/**
	 * Returns an integer value from the save game.
	 * 
	 * @param offset Decimal value pointing to the first byte of the value to retrieve.
	 * @param length Length (number of bytes) of the value.
	 * @param byteOrder The byte order of the value.
	 * @return The value stored at offset X (offset*length) in decimal format.
	 */
	public int getIntValue(int offset,int length,ByteOrder byteOrder){
		int value=0;
		try{
			if(this.saveGame!=null){
				for(int index=0;index<length;index++){
					this.saveGame.seek(offset+index);
					if(byteOrder==ByteOrder.LOW_BYTE_FIRST){
						value+=((this.saveGame.read()))*(Math.pow(this.MAX_UNSIGNED_BYTE,index));
					} else if(byteOrder==ByteOrder.HIGH_BYTE_FIRST){
						value+=((this.saveGame.read()))*(Math.pow(this.MAX_UNSIGNED_BYTE,(length-index-1)));
					}
				}
			}
		} catch(Exception x){
			x.printStackTrace();
		}
		return(value);
	}

	/**
	 * Writes an integer value to the save game.
	 * 
	 * @param offset Decimal value pointing to the first byte of the value to store.
	 * @param length Length (number of bytes) of the value.
	 * @param value The decimal number to be stored at offset X (offset*length).
	 * @param byteOrder The byte order of the value to store.
	 */
	public void setIntValue(int offset,int length,int value,ByteOrder byteOrder){
		int currentByte=0;
		try{
			if(this.saveGame!=null){
				for(int index=0;index<length;index++){
					currentByte=((value>>(index*SaveGame.BYTE_LENGTH))%(this.MAX_UNSIGNED_BYTE));
					if(byteOrder==ByteOrder.LOW_BYTE_FIRST){
						this.saveGame.seek(offset+index);
					} else if(byteOrder==ByteOrder.HIGH_BYTE_FIRST){
						this.saveGame.seek(offset+(length-index-1));
					} else{
						throw new Exception("invalid byte order");
					}
					this.saveGame.write(currentByte);
				}
			}
		} catch(Exception x){
			x.printStackTrace();
		}
	}
	
	/**
	 * Writes a string value to the save game.
	 * 
	 * @param offset Decimal value pointing to the first byte of the string to store.
	 * @param length Length (number of bytes) of the string.
	 * @param value The string to be stored at offset.
	 * @param byteOrder The byte order of the string (just in case there's some oddball save game where strings are stored in reverse).
	 */
	public void setStringValue(int offset,int length,String value,ByteOrder byteOrder){
		try{
			if(this.saveGame!=null){
				StringBuffer buffer=new StringBuffer(value);
				if(buffer.length()>length){
					buffer.delete(length,buffer.length());
				} else{
					while(buffer.length()<length){
						buffer.append(" ");
					}
				}
				if(byteOrder==ByteOrder.HIGH_BYTE_FIRST){
					buffer=buffer.reverse();
				}
				byte[] bytes=buffer.toString().getBytes();
				this.saveGame.seek(offset);
				this.saveGame.write(bytes,0,length);
			}
		} catch(Exception x){
			x.printStackTrace();
		}
	}
	
	/**
	 * Returns a string value from a save game.
	 * 
	 * @param offset Decimal value pointing to the first byte of the string to retrieve.
	 * @param length Length (number of bytes) of the string.
	 * @param byteOrder The byte order of the string (just in case there's some oddball save game where strings are stored in reverse).
	 * @return Value stored at offset X (offset*length) in string format.
	 */
	public String getStringValue(int offset,int length,ByteOrder byteOrder){
		String value="";
		try{
			if(this.saveGame!=null){
				byte[] bytes=new byte[length];
				this.saveGame.seek(offset);
				this.saveGame.read(bytes,0,length);
				if(byteOrder==ByteOrder.LOW_BYTE_FIRST){
					value=new String(bytes);
				} else{
					value=new StringBuffer(new String(bytes)).reverse().toString();
				}
			}
		} catch(Exception x){
			x.printStackTrace();
		}
		return(value);
	}
}