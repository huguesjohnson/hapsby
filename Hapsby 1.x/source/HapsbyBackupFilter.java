/*
Hapsby - universal save game editor
HapsbyBackupFilter.java - Filter for save game backups
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
