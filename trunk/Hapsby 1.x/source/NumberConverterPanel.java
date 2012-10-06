/*
NumberConverter - simple number format converter
NumberConverter.java - main program
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
import java.awt.Dimension;  
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.String;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/** class NumberConverterPanel
* @author Hugues Johnson
*/
public class NumberConverterPanel extends JPanel implements KeyListener{
     private JLabel label;
     private JTextField textField;
     private final static Dimension panelSize=new Dimension(300,20);
     private final static Dimension labelSize=new Dimension(100,20);
     private final static Dimension textSize=new Dimension(200,20);
     
     /** constructor for NumberConverterPanel
     */
     public NumberConverterPanel(String labelCaption){
          /* set size for panel */
          this.setSize(this.panelSize);
          this.setPreferredSize(this.panelSize);
          this.setMinimumSize(this.panelSize);
          this.setMaximumSize(this.panelSize);
          /* ste layout */
          this.setLayout(new BorderLayout());
          this.label=new JLabel(labelCaption,SwingConstants.RIGHT);
          this.label.setAlignmentX(SwingConstants.RIGHT);
          /* set size for label */
          this.label.setSize(this.labelSize);
          this.label.setPreferredSize(this.labelSize);
          this.label.setMaximumSize(this.labelSize);
          this.label.setMinimumSize(this.labelSize);
          this.add(this.label,BorderLayout.WEST);
          this.textField=new JTextField();
          /* set size for textField */
          this.textField.setSize(this.textSize);
          this.textField.setPreferredSize(this.textSize);
          this.textField.setMaximumSize(this.textSize);
          this.textField.setMinimumSize(this.textSize);
          this.textField.addKeyListener(this);
          this.add(this.textField,BorderLayout.EAST); 
     }

     /** toString returns a String representation of this object
      * @return String representation of this object
      */
     public String toString(){
          String tostring=new String(super.toString());
          return(tostring);
     }     
     
     /** setText
     * @param text
     */
     public void setText(String text){
          this.textField.setText(text);
     }
     
     /** getText
     * @return text
     */
     public String getText(){
          String text=new String();
          if(this.textField.getText()!=null){
               text=this.textField.getText();
          }
          return(text);
     }
     
     /** keyReleased
     * @param ke
     */
     public void keyReleased(final KeyEvent ke){
     }
     
     /** keyPressed
     * @param ke
     */
     public void keyPressed(final KeyEvent ke){
     }
     
     /** keyTyped
     * @param ke
     */
     public void keyTyped(final KeyEvent ke){
     }
}