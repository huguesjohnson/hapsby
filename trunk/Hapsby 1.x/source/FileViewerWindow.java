/*
Hapsby - universal save game editor
FileViewer - simple multi-format file viewer
HexViewTest.java - test class for FileViewer components
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

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import AsciiViewPane;
import BinaryViewPane;
import DecimalViewPane;
import HexViewPane;
import OctalViewPane;
import FileViewerMenu;

public class FileViewerWindow extends JFrame{
     private final static String ASCII_PANE_CAPTION="Ascii";
     private final static String DECIMAL_PANE_CAPTION="Decimal";
     private final static String HEX_PANE_CAPTION="Hexadecimal";
     private final static String OCTAL_PANE_CAPTION="Octal";
     private final static String BINARY_PANE_CAPTION="Binary";
     private final static String WINDOW_CAPTION="File Viewer";
     private final static String VERSION="alpha version";
     private AsciiViewPane asciiPane;
     private DecimalViewPane decimalPane;
     private HexViewPane hexPane;
     private OctalViewPane octalPane;
     private BinaryViewPane binaryPane;
     private String resourcePath;
     private JProgressBar progress;
     
     public FileViewerWindow(){
          super(FileViewerWindow.WINDOW_CAPTION+" "+FileViewerWindow.VERSION);
          this.setDefaultResourcePath();
          this.addComponents();
     }
     
     public FileViewerWindow(String caption,String resourcePath){
          super(caption);
          this.resourcePath=resourcePath;
          this.addComponents();
     }
     
     final private void addComponents(){
          /* add menu */
          FileViewerMenu menu=new FileViewerMenu(){ public void actionPerformed(final ActionEvent e){ actionHandler(e);}};
          this.setJMenuBar(menu);
          Container contentPane=this.getContentPane();
          /* add tabbed pane */
          JTabbedPane tabbedPane=new JTabbedPane();
          this.asciiPane=new AsciiViewPane(){public void valueChanged(final ListSelectionEvent e){listSelectionHandler(e);}};
          this.hexPane=new HexViewPane(){public void valueChanged(final ListSelectionEvent e){listSelectionHandler(e);}};
          this.octalPane=new OctalViewPane(){public void valueChanged(final ListSelectionEvent e){listSelectionHandler(e);}};
          this.decimalPane=new DecimalViewPane(){public void valueChanged(final ListSelectionEvent e){listSelectionHandler(e);}};
          this.binaryPane=new BinaryViewPane(){public void valueChanged(final ListSelectionEvent e){listSelectionHandler(e);}};
          tabbedPane.addTab(FileViewerWindow.ASCII_PANE_CAPTION,this.asciiPane);
          tabbedPane.addTab(FileViewerWindow.DECIMAL_PANE_CAPTION,this.decimalPane);
          tabbedPane.addTab(FileViewerWindow.HEX_PANE_CAPTION,this.hexPane);
          tabbedPane.addTab(FileViewerWindow.OCTAL_PANE_CAPTION,this.octalPane);
          tabbedPane.addTab(FileViewerWindow.BINARY_PANE_CAPTION,this.binaryPane);
          contentPane.add(tabbedPane);
          /* add progress bar */
          this.progress=new JProgressBar();
          this.progress.setStringPainted(true);
          contentPane.add(this.progress,BorderLayout.SOUTH);
     }
     
     public void resetProgress(int minimum,int maximum,String caption){
          if((this.progress!=null)&&(minimum<=maximum)){
               this.progress.setMinimum(minimum);
               this.progress.setMaximum(maximum);
               this.progress.setString(caption);
          }
     }
     
     public void incrementProgress(int incrementAmount){
          if(this.progress!=null){
               int newValue=this.progress.getValue()+incrementAmount;
               if(newValue<this.progress.getMaximum()){
                    this.progress.setValue(newValue);
               }
          }
     }
     
     private final void setDefaultResourcePath(){
          /* under some IDEs (such as Forte and Visual Cafe) the next line will not work because System.getProperty("user.dir") returns a path to the IDE and not to the working folder */
          this.resourcePath=new String(System.getProperty("user.dir")+File.separator);
     }
     
     public void addLine(byte[] line){
          this.asciiPane.addLine(line);
          this.decimalPane.addLine(line);
          this.hexPane.addLine(line);
          this.octalPane.addLine(line);
          this.binaryPane.addLine(line);
     }
     
     /** toString returns a String representation of this object
      * @return String representation of this object
      */
     public String toString(){
          String tostring=new String(super.toString());
          return(tostring);
     }     
     
     public void actionHandler(final ActionEvent e){
     }
     
     public void listSelectionHandler(final ListSelectionEvent e){
     }
}