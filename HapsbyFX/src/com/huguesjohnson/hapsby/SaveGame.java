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

import com.huguesjohnson.hapsby.exceptions.SaveGameIOException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveGame{
    private static final Logger logger=Logger.getLogger(SaveGame.class.getName());
    /* pointer to file */
    private RandomAccessFile saveGame;
    /* (2^8) - java only defines a max signed byte */
    private final static int MAX_UNSIGNED_BYTE=256;
    /* read-write mode of the file */
    private final static String READ_WRITE_MODE="rw";
    /* length of a single byte */
    private final static int BYTE_LENGTH=8;

    public SaveGame(String filePath) throws SaveGameIOException{
        this.openSaveGame(new File(filePath));
    }

    public SaveGame(File file) throws SaveGameIOException{
        this.openSaveGame(file);
    }

    public void close(){
        try{
            this.saveGame.close();
        }catch(Exception x){
            logger.log(Level.SEVERE,x.getMessage(),x);
        }
    }
	
    private void openSaveGame(File file) throws SaveGameIOException{
        try{
            this.saveGame=new RandomAccessFile(file,SaveGame.READ_WRITE_MODE);
        }catch(IOException x){
            logger.log(Level.SEVERE,x.getMessage(),x);
            throw(new SaveGameIOException(x));
        }
    }

    public int getIntValue(int offset,int length,ByteOrder byteOrder) throws SaveGameIOException{
        int value=0;
            try{
                if(this.saveGame!=null){
                    for(int index=0;index<length;index++){
                        this.saveGame.seek(offset+index);
                        if(byteOrder==ByteOrder.LOW_BYTE_FIRST){
                            value+=((this.saveGame.read()))*(Math.pow(MAX_UNSIGNED_BYTE,index));
                        }else if(byteOrder==ByteOrder.HIGH_BYTE_FIRST){
                            value+=((this.saveGame.read()))*(Math.pow(MAX_UNSIGNED_BYTE,(length-index-1)));
                        }
                    }
                }
            }catch(IOException x){
                logger.log(Level.SEVERE,x.getMessage(),x);
                throw(new SaveGameIOException(x));
            }
            return(value);
        }

        public void setIntValue(int offset,int length,int value,ByteOrder byteOrder) throws SaveGameIOException{
            int currentByte;
            try{
                if(this.saveGame!=null){
                    for(int index=0;index<length;index++){
                        currentByte=((value>>(index*BYTE_LENGTH))%(MAX_UNSIGNED_BYTE));
                        if(byteOrder==ByteOrder.LOW_BYTE_FIRST){
                            this.saveGame.seek(offset+index);
                        }else if(byteOrder==ByteOrder.HIGH_BYTE_FIRST){
                            this.saveGame.seek(offset+(length-index-1));
                        }
                        this.saveGame.write(currentByte);
                    }
                }
            }catch(IOException x){
                logger.log(Level.SEVERE,x.getMessage(),x);
                throw(new SaveGameIOException(x));
            }
        }
	
    public void setStringValue(int offset,int length,String value,ByteOrder byteOrder) throws SaveGameIOException{
        try{
            if(this.saveGame!=null){
                StringBuffer buffer=new StringBuffer(value);
                if(buffer.length()>length){
                    buffer.delete(length,buffer.length());
                }else{
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
        }catch(IOException x){
            logger.log(Level.SEVERE,x.getMessage(),x);
            throw(new SaveGameIOException(x));
        }
    }
	
    public String getStringValue(int offset,int length,ByteOrder byteOrder) throws SaveGameIOException{
        String value="";
        try{
            if(this.saveGame!=null){
                byte[] bytes=new byte[length];
                this.saveGame.seek(offset);
                this.saveGame.read(bytes,0,length);
                if(byteOrder==ByteOrder.LOW_BYTE_FIRST){
                    value=new String(bytes);
                }else{
                    value=new StringBuffer(new String(bytes)).reverse().toString();
                }
            }
        }catch(IOException x){
            logger.log(Level.SEVERE,x.getMessage(),x);
            throw(new SaveGameIOException(x));
        }
        return(value);
    }
    
}