/*
Hapsby - universal save game editor
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
