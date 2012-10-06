/*
Hapsby - universal save game editor
SaveDefFilter.java - Filter for save game definition files
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

import java.io.File;
import java.io.FilenameFilter;

/** Class SaveDefFilter, filter for Hapsby save game definition files (*.hsd)
* @author Hugues Johnson
*/
public class SaveDefFilter implements FilenameFilter{
     /* extention of save game definitions */
     public final static String EXTENTION=".hsd";
     
     /** accept tests if a file is a valid save game definition.
     * @param dir not used but required to implement FilenameFilter
     * @param name file name to check
     * @return if the name parameter is a valid save game definition
     */
     public boolean accept(File dir,String name){
          /* convert to lowercase before testing */
          name=name.toLowerCase();
          return(name.endsWith(this.EXTENTION));
     }
}