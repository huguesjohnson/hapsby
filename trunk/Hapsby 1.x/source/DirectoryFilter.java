/*
Hapsby - universal save game editor
DirectoryFilter.java - Filter for save game definition files
Copyright  (C) 2000-2001 Hugues Johnson

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

import java.io.FilenameFilter;
import java.io.File;

/** Class DirectoryFilter, filter for Hapsby save game definition files (*.hsd)
* @author Hugues Johnson
*/
public class DirectoryFilter implements FilenameFilter{
     
     /** accept tests if a file is a valid save game definition.
     * @param dir directory file is in
     * @param name the name of the name to test
     * @return if the name parameter is a directory
     */
     public boolean accept(File dir,String name){
          File f=new File(dir.getAbsolutePath()+File.separator+name);  
          return(f.isDirectory());
     }
}