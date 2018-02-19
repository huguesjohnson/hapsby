/*
Hapsby - universal save game editor
SaveGameFilter.java - Customizable filter for save game files
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
