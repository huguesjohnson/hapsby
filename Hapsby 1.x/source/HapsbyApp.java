/*
Hapsby - universal save game editor
HapsbyApp.java
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import java.io.File;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.lang.Thread;
import java.util.Arrays;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
/* imports for JFC 1.0.1
import com.sun.java.swing.JOptionPane;
import com.sun.java.swing.preview.JFileChooser;
 */
import SaveGameDefinition;
import SaveDefFilter;
import SaveGame;
import HapsbyBackup;
import HapsbyBackupFilter;
import HapsbyProgressCaptions;
import IniFile;
import FileViewer;
import NumberConverter;

/** Class HapsbyApp
 * @author Hugues Johnson
 */
public class HapsbyApp extends Thread{
     /* begin private shared members */
      /* a reference to the parent window is needed for some swing components (ie. JFileChooser, JOptionPane) */
     private Component parentWindow;
     /* pointer to current game definition */
     private SaveGameDefinition gameDef;
     /* pointer to current save game */
     private SaveGame saveGame;
     /* path to the \def directory */
     private String defFilePath;
     /* path to \backup directory */
     private String backupPath;
     /* path to \settings directory */
     private String settingsPath;
      /* autobackup mode */
     private int autoBackupFlag;
     /* flag for sorting property list */
     private boolean sortPropertyListFlag;
     /* flag for sorting save game definitions */
     private boolean sortSaveDefinitionListFlag;
     /* directory that open save game file chooser is in */
     private String openSaveGamePath;
     /* FileViewer window */
     private FileViewer fileViewer;
     /* NumberConverter window */
     private NumberConverter numberConverter;
     /* end private shared members */
     
     /* start program info */
     /** Program name */
     public final static String PROGRAM_NAME ="Hapsby";
     /** Program version */
     public final static String PROGRAM_VERSION="Version 0.4";
     /** Program author(s) */
     public final static String PROGRAM_AUTHOR="Hugues Johnson";
     /** Program copyright */
     public final static String PROGRAM_COPYRIGHT="Copyright (C) 2000-2001 Hugues Johnson";
     /* end program info */
     
     /* begin constants */
     /** auto-backup on */
     public static final int FLAG_AUTOBACKUP_ON=0;
     /** auto-backup off */
     public static final int FLAG_AUTOBACKUP_OFF=1;
     /** prompt before making backups */
     public static final int FLAG_AUTOBACKUP_PROMPT=2;
     /* end constants */
     
     /** Constructor for HapsbyCore, sets paths and error stream.
      * @param parentWindow pointer to parent window component
      */
     public HapsbyApp(Component parentWindow){
          this.parentWindow=parentWindow;
          /* in certain IDEs (such as Forte & Visual Cafe) the next three lines will not work because the result of System.getProperty("user.dir") will be a path to the IDE and not the working folder */
          this.defFilePath=new String(System.getProperty("user.dir")+File.separator+"def");
          this.backupPath=new String(System.getProperty("user.dir")+File.separator+"backup");
          this.settingsPath=new String(System.getProperty("user.dir")+File.separator+"settings");
          /* set error stream */
          this.setErrorStream();
          /* load saved settings */
          this.applySavedSettings();
     }
     
     /* setErrorStream creates a text file to log run-time errors.
      */
     private void setErrorStream(){
          try{
               System.setErr(new PrintStream(new FileOutputStream(this.settingsPath+File.separator+this.PROGRAM_NAME+".err",true)));
               /* start error log session */
               System.err.println(this.PROGRAM_NAME+" "+this.PROGRAM_VERSION+" execution started at "+Long.toString(System.currentTimeMillis()));
          } catch(Exception x){
               x.printStackTrace();
          }
     }
     
     /* errorMessage displays an error dialog in the parent window.
      * @param message the error message to display in the body of the dialog
      */
     private final void errorMessage(String message){
          JOptionPane.showMessageDialog(this.parentWindow,message,"Error",JOptionPane.ERROR_MESSAGE);
     }
     
     /* showAboutDialog displays a program information dialog in the parent window.
      */
     public final void showAboutDialog(){
          String title="About "+this.PROGRAM_NAME;
          String prompt=this.PROGRAM_NAME+"\n"+this.PROGRAM_VERSION+"\n\n"+this.PROGRAM_COPYRIGHT;
          JOptionPane.showMessageDialog(this.parentWindow,prompt,title,JOptionPane.INFORMATION_MESSAGE);
     }
     
     /* openSaveGame opens a save game for editing.
      * @param defFile the definition file for the save game
      * @return success (FileChooser.APPROVE_OPTION) or failure (FC.CANCEL_OPTION) of routine
      */
     public boolean openSaveGame(String defFile){
          /* replace blank spaces with underscores */
          defFile=defFile.replace(' ','_');
          if(!defFile.toLowerCase().endsWith(SaveDefFilter.EXTENTION.toLowerCase())){
               defFile=defFile+SaveDefFilter.EXTENTION;
          }
          this.gameDef=new SaveGameDefinition(defFilePath+File.separator+defFile);
          /* set up file chooser */
          final JFileChooser fc=new JFileChooser(this.openSaveGamePath);
          fc.setDialogTitle("Open "+this.gameDef.getGameTitle()+" Save Game");
          /* add SaveGameFilter */
          SaveGameFilter filter=new SaveGameFilter(this.gameDef.getSaveFileName(),this.gameDef.getGameTitle());
          fc.setFileFilter(filter);
          int returnValue=fc.showOpenDialog(parentWindow);
          if(returnValue==fc.APPROVE_OPTION){
               /* set the path */
               this.openSaveGamePath=fc.getCurrentDirectory().getPath();
               this.saveGame=new SaveGame(fc.getSelectedFile());
               this.autoBackup(fc.getSelectedFile());
          }
          return(returnValue==fc.APPROVE_OPTION);
     }
     
     /* endProgram terminates program exectution.
      * @TODO see if anything else should go here
      */
     public void endProgram(){
          this.updateStatus(HapsbyProgressCaptions.PROGRESS_EXIT);
          if(this.fileViewer!=null){
               this.fileViewer=null;
          }
          /* save settings */
          this.saveSettings();
          /* end error logging session */
          System.err.println("terminated execution at "+Long.toString(System.currentTimeMillis()));
          /* end execution */
          System.exit(0);
     }
     
     /** Retrieves a String array representing all save game definitions.
      * @return list of save game definitions
      */
     public String[] getDefList(){
          String defList[]={""};
          try{
               File f=new File(defFilePath);
               defList=new String[f.list().length];
               defList=f.list(new SaveDefFilter());
               this.updateStatus(HapsbyProgressCaptions.PROGRESS_CREATE_DEF_LIST);
               for(int q=0;q<defList.length;q++){
                    /* redundant test */
                    if(defList[q].toLowerCase().endsWith(SaveDefFilter.EXTENTION.toLowerCase())){
                         defList[q]=defList[q].substring(0,defList[q].length()-SaveDefFilter.EXTENTION.length());
                    }
                    defList[q]=defList[q].replace('_',' ');
               }
          } catch(Exception x){
               x.printStackTrace();
               errorMessage(x.toString());
          }
          /* sort defList */
          if(this.sortSaveDefinitionListFlag){
               this.updateStatus(HapsbyProgressCaptions.PROGRESS_SORT_DEF_LIST);
               Arrays.sort(defList);
          }
          this.updateStatus(HapsbyProgressCaptions.PROGRESS_READY);
          return(defList);
     }
     
     /** getPropertyList retrieves an array representing all properties in the current save game definition.
      * @return String array of property names
      */
     public String[] getPropertyList(){
          String[] list=gameDef.getPropertyList();
          /* sort list */
          if(this.sortPropertyListFlag){
               this.updateStatus(HapsbyProgressCaptions.PROGRESS_SORT_PROPERTY_LIST);
               Arrays.sort(list);
               this.updateStatus(HapsbyProgressCaptions.PROGRESS_READY);
          }
          return(list);
     }
     
     /** getProperty retrieves a specific SaveGameProperty from the current save game definition.
      * @param name the name of the property to retrieve
      * @return the SaveGameProperty if it is found, otherwise returns null
      */
     public SaveGameProperty getProperty(String name){
          SaveGameProperty p=new SaveGameProperty();
          if(this.gameDef!=null){
               p=this.gameDef.getSaveGameProperty(name);
          }
          return(p);
     }
     
     
     /** getCurrentValue retrieves a value from the active save game.
      * @param address the address (in decimal format) of the value
      * @param length the length (number of bytes) of the value
      * @param dataType the data type (as defined in SaveGameProperty.class) of the value
      */
     public String getCurrentValue(int address,int length,int dataType){
          String value="";
          switch(dataType){
               case (SaveGameProperty.DATA_TYPE_INTEGER_LOW):
                    value=Integer.toString(saveGame.getIntValue(address,length,saveGame.LOW_BYTE_FIRST));
                    break;
               case (SaveGameProperty.DATA_TYPE_INTEGER_HIGH):
                    value=Integer.toString(saveGame.getIntValue(address,length,saveGame.HIGH_BYTE_FIRST));
                    break;
          }
          return(value);
     }
     
     /** getGameDescription retrieves the description of the current save game definition.
      * @return description of the current save game definition
      */
     public String getGameDesc(){
          return(this.gameDef.getGameDescription());
     }
     
     /** getGameTitle retrieves the title of the current save game definition
      * @return title of the current save game definition
      */
     public String getGameTitle(){
          return(this.gameDef.getGameTitle());
     }
     
     /* save saves the value current property.
      * @param parameter the name of the property to save and the value in the form "propertyName|value".
      * @return success of operation
      */
     public boolean save(String name,String value){
          boolean success=true;
          int intValue;
          try{
               SaveGameProperty p=this.getProperty(name);
               switch(p.getDataType()){
                    case (SaveGameProperty.DATA_TYPE_INTEGER_LOW):
                         intValue=Integer.valueOf(value).intValue();
                         if(intValue>p.getMaxValue()||intValue<p.getMinValue()){
                              throw(new Exception(name+" must be greater than "+p.getMinValue()+" and less than "+p.getMaxValue()));
                         } else{
                              saveGame.setIntValue(p.getAddress(),p.getLength(),intValue,saveGame.LOW_BYTE_FIRST);
                         }
                         break;
                    case (SaveGameProperty.DATA_TYPE_INTEGER_HIGH):
                         intValue=Integer.valueOf(value).intValue();
                         if(intValue>p.getMaxValue()||intValue<p.getMinValue()){
                              throw(new Exception(name+" must be greater than "+p.getMinValue()+" and less than "+p.getMaxValue()));
                         } else{
                              saveGame.setIntValue(p.getAddress(),p.getLength(),intValue,saveGame.HIGH_BYTE_FIRST);
                         }
                         break;
               }
          } catch(Exception x){
               x.printStackTrace();
               success=false;
               errorMessage(x.toString());
          }
          return(success);
     }
     
     /* autoBackup determines checks the auto backup flag and calls the appropriate operation.
      * @param file the file to backup
      */
     private void autoBackup(File file){
          if((file!=null)&&(this.autoBackupFlag!=this.FLAG_AUTOBACKUP_OFF)){
               int selectedValue;
               if(this.autoBackupFlag==this.FLAG_AUTOBACKUP_ON){
                    selectedValue=JOptionPane.YES_OPTION;
               } else{
                    selectedValue=JOptionPane.showConfirmDialog(this.parentWindow,"Create backup for "+this.getGameTitle()+"?","Auto-Backup",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
               }
               if(selectedValue==JOptionPane.YES_OPTION){
                    new HapsbyBackup(HapsbyBackup.FLAG_CREATE,this.backupPath,file.getAbsolutePath());
               }
          }
     }
     
     /* createBackup creates a Hapsby backup for a save game, the name of the backup file is determined by the save game name and current time.
      * @param file the file to be backed up
      */
     private void createBackup(File file){
          this.updateStatus(HapsbyProgressCaptions.PROGRESS_CREATE_BACKUP_FOR+file.getName());
          String fileName=backupPath+File.separator+file.getName();
          new HapsbyBackup(HapsbyBackup.FLAG_CREATE,this.backupPath,fileName);
          this.updateStatus(HapsbyProgressCaptions.PROGRESS_READY);
     }
     
     /* set whether or not to sort the property list
      * @param flag the new flag value
      */
     protected void setPropertySortFlag(boolean flag){
          this.sortPropertyListFlag=flag;
     }
     
     /* set whether or not to sort the save game definition list
      * @param flag the new flag value
      */
     protected void setDefListSortFlag(boolean flag){
          this.sortSaveDefinitionListFlag=flag;
     }
     
     /** showFileViewer
      */
     protected void showFileViewer(){
          if(this.fileViewer==null){
               this.fileViewer=new FileViewer(){
                    public void closeWindow(){
                         disposeFileViewer();
                    }
               };
          }
          this.fileViewer.run();
          this.fileViewer.move(this.parentWindow.getLocation().x,this.parentWindow.getLocation().y);
     }
     
     /** disposeFileViewer
      */
     protected void disposeFileViewer(){
          if(this.fileViewer!=null){
               this.fileViewer=null;
          }
     }
     
     /** showNumberConverter
      */
     protected void showNumberConverter(){
          if(this.numberConverter==null){
               this.numberConverter=new NumberConverter(){
                    public void closeWindow(){
                         disposeNumberConverter();
                    }
               };
          }
          this.numberConverter.setLocation(this.parentWindow.getLocation());
     }
     
     /** disposeNumberConverter
      */
     protected void disposeNumberConverter(){
          if(this.numberConverter!=null){
               this.numberConverter=null;
          }
     }
     
     /* setBackupFlag, sets the autobackup flag
      * @param flag, new value
      */
     public void setBackupFlag(int flag){
          this.autoBackupFlag=flag;
     }
     
     /** restoreBackup, restores a Hapsby backup to its original location
      * @param backupFile, the file to restore - passing null loads a file chooser
      */
     public void restoreBackup(String backupFile){
          if(backupFile==null){
               new HapsbyBackup(HapsbyBackup.FLAG_SELECT_RESTORE,this.backupPath,this.backupPath);
          } else{
               new HapsbyBackup(HapsbyBackup.FLAG_RESTORE,this.backupPath,backupFile);
          }
     }
     
    /** applySavedSettings, loads and applies saved settings
     */
     private void applySavedSettings(){
          IniFile iniFile=new IniFile(this.settingsPath+File.separator+"HapsbyApp.ini");
          if(iniFile!=null){
               iniFile.read();
               String property=new String();
               final String defaultString=new String("__dEfAulT__");
               property=iniFile.getProperty("autoBackupFlag",defaultString);
               if(property.equals(defaultString)){
                    this.autoBackupFlag=this.FLAG_AUTOBACKUP_ON;
               } else{
                    this.autoBackupFlag=Integer.valueOf(property).intValue();
               }
               property=iniFile.getProperty("sortPropertyListFlag",defaultString);
               if(property.equals(defaultString)){
                    this.sortPropertyListFlag=true;
               } else{
                    this.sortPropertyListFlag=Boolean.valueOf(property).booleanValue();
               }               
               property=iniFile.getProperty("sortSaveDefinitionListFlag",defaultString);
               if(property.equals(defaultString)){
                    this.sortSaveDefinitionListFlag=true;
               } else{
                    this.sortSaveDefinitionListFlag=Boolean.valueOf(property).booleanValue();
               }                  
               property=iniFile.getProperty("openSaveGamePath",defaultString);
               if(property.equals(defaultString)){
                    this.openSaveGamePath=System.getProperty("user.dir");
               } else{
                    this.openSaveGamePath=new String(property);
               }                               
               iniFile.clear();
               iniFile=null;
          }
     }
     
    /** saveSettings, save program settings to ini file
     */
     private void saveSettings(){
          IniFile iniFile=new IniFile(this.settingsPath+File.separator+"HapsbyApp.ini");
          if(iniFile!=null){
               iniFile.setProperty("autoBackupFlag",this.autoBackupFlag);
               iniFile.setProperty("sortPropertyListFlag",this.sortPropertyListFlag);
               iniFile.setProperty("sortSaveDefinitionListFlag",this.sortSaveDefinitionListFlag);
               iniFile.setProperty("openSaveGamePath",this.openSaveGamePath);
               iniFile.save(this.PROGRAM_NAME+" "+this.PROGRAM_VERSION);
               iniFile=null;
          }
     }

     /** toString returns a String representation of this object
      * @return String representation of this object
      */
     public String toString(){
          String tostring=new String(super.toString());
          if(this.parentWindow!=null){
               tostring+='\n'+this.parentWindow.toString();
          }
          if(this.gameDef!=null){
               tostring+='\n'+this.gameDef.toString();
          }
          if(this.saveGame!=null){
               tostring+='\n'+this.saveGame.toString();
          }
          if(this.fileViewer!=null){
               tostring+='\n'+this.fileViewer.toString();
          }
          if(this.numberConverter!=null){
               tostring+='\n'+this.numberConverter.toString();
          }
          tostring+="\ndefFilePath="+this.defFilePath;
          tostring+="\nbackupPath="+this.backupPath;
          tostring+="\nsettingsPath="+this.settingsPath;
          tostring+="\nopenSaveGamePath="+this.openSaveGamePath;
          return(tostring);
     }     
     
    /** update status
     *  @param message
     */
     public void updateStatus(String message){
        /* override this */
     }
}
