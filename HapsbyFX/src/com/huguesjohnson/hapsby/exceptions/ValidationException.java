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
package com.huguesjohnson.hapsby.exceptions;

public class ValidationException extends Exception{
    private String field;
    private String expectedValue;
    private String actualValue;
    
    public ValidationException(String field,String expectedValue,String actualValue){
        this.field=field;
        this.expectedValue=expectedValue;
        this.actualValue=actualValue;
    }
    
    public ValidationException(String field,int expectedValue,int actualValue){
        this.field=field;
        this.expectedValue=String.valueOf(expectedValue);
        this.actualValue=String.valueOf(actualValue);
    }
    
    @Override
    public String getMessage(){
        StringBuilder message=new StringBuilder();
        message.append("ValidationException\n");
        message.append(" [field=");
        message.append(this.field);
        message.append("]\n [expectedValue=");
        message.append(this.expectedValue);
        message.append("]\n [actualValue=");
        message.append(this.actualValue);
        message.append("]");
        return(message.toString());
    }

    @Override
    public String toString(){
        return(this.getMessage());
    } 
    
}