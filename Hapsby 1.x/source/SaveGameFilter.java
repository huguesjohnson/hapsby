/*
Hapsby - universal save game editor
SaveGameFilter.java - Customizable filter for save game files
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

/** Class SaveGameFilter, customizable filter for save game files
 * @author Hugues Johnson
*/
public class SaveGameFilter extends FileFilter{
     /* begin private shared strings */
     private String filePattern;
     private String description;
     /* end private shared strings */
     
     /** Constructor for class SaveGameFilter. Creates a filter based on file pattern passed.
     * Presently, only leading and trailing wildcard characters are accepted.
     * For example: *.txt, name.*, and *.da* are all acceptible, but qw*.ert is not.
     * @param filePattern pattern or specific name to apply to filter
     * @param gameTitle title of game the pattern is for, used to set the description
     */
     public SaveGameFilter(String filePattern,String gameTitle){
          /** @TODO support wildcards in any position */
          String wildcard="*";
          /* set description */
          this.description=gameTitle+" Save Games ("+filePattern+")";
          /* parse out leading & trailing wildcards */
          filePattern=filePattern.toLowerCase();
          /** @TODO find a faster way to do this */
          if(filePattern.startsWith(wildcard)){ filePattern=filePattern.substring(1,filePattern.length()); }
          if(filePattern.endsWith(wildcard)){ filePattern=filePattern.substring(0,filePattern.length()-1); }
          this.filePattern=filePattern;
     }
     
     /** accept tests if a file fits the filter defined in the constructor.
     * @param f file to check
     * @return true if the file fits the defined filter, false if it does not
     */
     public boolean accept(File f){
          boolean returnValue=false; /* initialize to something or javac will complain */
          /** @TODO find a faster way to do this */
          String fileName=(f.getName().toLowerCase());
          if(f.isDirectory()){
               returnValue=true;
          } else{
               returnValue=(fileName.indexOf(this.filePattern)>0);
          }
          return(returnValue);
     }
     
     /** getDescription returns the description of file accepted by the filter.
     * @return description of files accepted by the current filter
     */
     public String getDescription(){
          return(description);
     }
}