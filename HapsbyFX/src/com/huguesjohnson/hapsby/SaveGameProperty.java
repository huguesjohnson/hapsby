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

public class SaveGameProperty implements Serializable{
    private String name;
    private String description;
    private ByteOrder byteOrder;
    private DataType dataType;
    private int address;
    private int length;
    private int maxValue;
    private int minValue;
    
    public SaveGameProperty(){ }
	
    public SaveGameProperty(String name,String description,ByteOrder byteOrder,DataType dataType,int address,int length,int maxValue,int minValue) throws Exception{
        //using the set methods because they check if the parameters are valid
        this.setName(name);
        this.setDescription(description);
        this.setByteOrder(byteOrder);
        this.setDataType(dataType);
        this.setAddress(address);
        this.setLength(length);
        this.setMaxValue(maxValue);
        this.setMinValue(minValue);
    }

    public final void setName(String name){
        this.name=name;
    }

    public final void setDescription(String description){
        this.description=description;
    }

    public final void setAddress(int address) throws Exception{
        if(address>=0){
            this.address=address;
        }else{
            this.address=0;
            throw(new Exception("Address must be >= 0"));
        }
    }

    public final void setLength(int length) throws Exception{
        if(length>0){
            this.length=length;
        }else{
            this.length=1;
            throw(new Exception("Length must be > 0"));
        }
    }

    public final void setMaxValue(int maxValue){
        this.maxValue=maxValue;
    }

    public final void setMinValue(int minValue){
        this.minValue=minValue;
    }

    public final String getName(){
        return(this.name);
    }

    public final String getDescription(){
        return(this.description);
    }

    public final DataType getDataType(){
        return(this.dataType);
    }

    public final int getAddress(){
        return(this.address);
    }

    public final int getLength(){
        return(this.length);
    }

    public final int getMaxValue(){
        return(this.maxValue);
    }

    public final int getMinValue(){
        return(this.minValue);
    }
	
    public final ByteOrder getByteOrder(){
        return(this.byteOrder);
    }

    public final void setByteOrder(ByteOrder byteOrder){
        this.byteOrder=byteOrder;
    }

    public final void setDataType(DataType dataType){
        this.dataType=dataType;
    }	

    @Override
    public String toString(){
        return(this.getName());
    }	
}