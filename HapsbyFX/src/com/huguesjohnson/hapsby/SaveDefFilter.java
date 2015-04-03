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

import java.io.File;
import java.io.FilenameFilter;


public class SaveDefFilter implements FilenameFilter{
    public final static String EXTENTION=".hsd.json";

    @Override
    public boolean accept(File dir,String name){
        return(name.toLowerCase().endsWith(SaveDefFilter.EXTENTION));
    }
}