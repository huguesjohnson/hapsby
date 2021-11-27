/*
Hapsby - universal save game editor
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.huguesjohnson.hapsby;

import java.io.Serializable;

public class SaveGameProperty implements Serializable{
	private static final long serialVersionUID=666L;
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
