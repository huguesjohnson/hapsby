/*
Hapsby - universal save game editor
HapsbyBackupFilter.java - Filter for save game backups
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
import javax.swing.filechooser.FileFilter;
/* imports for JFC 1.0.1
import com.sun.java.swing.preview.filechooser.FileFilter;
*/

/**
* Class HapsbyBackupFilter, filter for Hapsby save game backup files (*.hbk)
* @author Hugues Johnson
*/
public class HapsbyBackupFilter extends FileFilter{
     /** extention for Hapsby backup files */
     public final static String EXTENTION=".hbk";
     /* descrition used in JFileChooser */
     private final static String description="Hapsby Backup Files (*.hbk)";
     
     /** accept tests if a file is a valid save game definition.
     * @param f file to check
     * @return if the name parameter is a valid Hapsby backup
     */
     public boolean accept(File f){
          if(f.isDirectory()){
               return(true);
          } else{
               /* convert name to lower case before comparing */
               String name=f.getName().toLowerCase();
               return(name.endsWith(this.EXTENTION));
          }
     }
     
     /** Retrieves the description of the filter, typically used in a JFileChooser.
      * @return description of filter
      */
     public String getDescription(){
          return(this.description);
     }
}