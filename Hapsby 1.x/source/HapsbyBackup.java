/*
Hapsby - universal save game editor
HapsbyBackup.java - Create and restore backup routines
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import java.io.File;
import javax.swing.JFileChooser;
import HapsbyBackupFilter;
import ZipCompressor;
import ZipDecompressor;

/** class HapsbyBackup contains all routines for creating and restoring backups
* @author Hugues Johnson
*/
public class HapsbyBackup{
     /** flag to create a backup */
     public final static int FLAG_CREATE=0;
     /** flag to restore a backup */
     public final static int FLAG_RESTORE=1;
     /** flag to browse for a file to restore */
     public final static int FLAG_SELECT_RESTORE=2;
     /* path to the backup directory */
     private String backupPath;
     
     /**
     * @param flag the action to perform
     * @file varies by value of flag:
          FLAG_CREATE: file is the file to create a backup of 
          FLAG_RESTORE: file is the file to restore
          FLAG_SELECT_RESTORE: file is the directory to initially browse in
     */
     public HapsbyBackup(int flag,String backupPath,String file){
          this.backupPath=backupPath+File.separator;
          switch(flag){
               case HapsbyBackup.FLAG_CREATE: this.createBackup(file); break;
               case HapsbyBackup.FLAG_RESTORE: this.restoreBackup(file); break;
               case HapsbyBackup.FLAG_SELECT_RESTORE: this.restoreBackup(this.selectBackup(file)); break;
          }
     }

     /* restoreBackup restores a backup file
     * @param fileName the name & path of the file to restore
     */
     private void restoreBackup(String fileName){
          if((fileName!=null)&&(this.backupPath!=null)){
               ZipDecompressor unzip=new ZipDecompressor(fileName);
          }
     }
     
     /* createBackup creates a Hapsby backup for a save game, the name of the backup file is determined by the save game name and current time.
     * @param file the name & path of file to be backed up
     */
     private void createBackup(String fileName){
          File file=new File(fileName);
          String fileTitle=file.getName().replace('.','_');
          String currentTime=Long.toString(System.currentTimeMillis());
          fileName=this.backupPath+fileTitle+"_"+currentTime+HapsbyBackupFilter.EXTENTION;
          String fileArray[]={ file.getPath() };
          ZipCompressor zip=new ZipCompressor(fileArray,fileName);
     }

     /* selectBackup creates a JFileChooser to browse for Hapsby backup to restore.
     */
     private String selectBackup(String backupPath){
          String selectedFile=null;
          final JFileChooser fc=new JFileChooser(backupPath);
          HapsbyBackupFilter filter=new HapsbyBackupFilter();
          fc.setFileFilter(filter);
          fc.setDialogTitle("Restore Backup");
          int returnValue=fc.showDialog(null,"Restore");
          if(returnValue==fc.APPROVE_OPTION){
               selectedFile=fc.getSelectedFile().getPath();
          }
          return(selectedFile);
     }
     
     /** toString returns a String representation of this object
      * @return String representation of this object
      */
     public String toString(){
          String tostring=new String(super.toString());
          return(tostring);
     }     
}
