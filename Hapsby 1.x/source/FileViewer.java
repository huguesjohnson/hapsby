/*
Hapsby - universal save game editor
FileViewer - simple multi-format file viewer
FileViewer.java - entry point for file viewer
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.lang.Thread;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import FileViewerActionCommands;
import FileViewerWindow;

public class FileViewer extends Thread{
     private final static int LINE_LENGTH=16;
     private String resourcePath;
     private String currentDirectory;
     FileViewerWindow window;
     
     public FileViewer(){
     }
     
     public void run(){
          this.window=this.createWindow();
          this.showWindow(this.window);
     }
     
     final public void move(int x,int y){
          if(this.window!=null){
               this.window.setLocation(x,y);
          }
     }
     
     final private FileViewerWindow createWindow(){
          FileViewerWindow window=new FileViewerWindow(){
               public void actionHandler(final ActionEvent e){
                    actionEvent(e);
               }
               public void listSelectionHandler(final ListSelectionEvent e){
                    listEvent(e);
               }
          };
          /* override window closing event */
          window.addWindowListener(new WindowAdapter(){ public void windowClosing(WindowEvent e){closeWindow();}});
          return(window);
     }
     
     final private void showWindow(JFrame window){
          if(window!=null){
               window.pack();
               window.setVisible(true);
          }
     }

     private void openFile(){
          this.readFile(this.selectFile());
     }
     
     private File selectFile(){
          File selectedFile=null;
          JFileChooser fc=new JFileChooser();
          fc.setFileFilter(fc.getAcceptAllFileFilter());
          if(fc.showOpenDialog(this.window)==JFileChooser.APPROVE_OPTION){
               selectedFile=fc.getSelectedFile();
               this.currentDirectory=selectedFile.getPath();
          }
          return(selectedFile);
     }

     private void readFile(File file){
          try{
               FileInputStream fStream=new FileInputStream(file);
               byte[] byteArray=new byte[FileViewer.LINE_LENGTH];
               int lineCount=0;
               int readSuccess;
               this.window.resetProgress(0,(int)file.length(),"Opening");
               while((readSuccess=fStream.read(byteArray))!=-1){
                    this.window.addLine(byteArray);
                    this.window.incrementProgress(FileViewer.LINE_LENGTH);
               }
               fStream.close();
               this.window.resetProgress(0,0,"Done");
          } catch(Exception x){
               x.printStackTrace();
          }
     }
     
     private final void setDefaultPaths(){
          /* under some IDEs (such as Forte and Visual Cafe) the next line will not work because System.getProperty("user.dir") returns a path to the IDE and not to the working folder */
          this.resourcePath=new String(System.getProperty("user.dir")+File.separator);
          this.currentDirectory=resourcePath;
     }
     
     /* if this is not being run as a main class, this method must be overridden */
     public void closeWindow(){
          System.exit(0);
     }
     
     /** toString returns a String representation of this object
      * @return String representation of this object
      */
     public String toString(){
          String tostring=new String(super.toString());
          return(tostring);
     }     
     
     public void listEvent(final ListSelectionEvent e){
     }
     
     public void actionEvent(final ActionEvent e){
          String command=e.getActionCommand().toLowerCase();
          if(command.equals(FileViewerActionCommands.CMD_OPEN)){
               this.openFile();
          } else if(command.equals(FileViewerActionCommands.CMD_EXIT)){
               this.window.hide();
               this.closeWindow();
          }
     }
}
