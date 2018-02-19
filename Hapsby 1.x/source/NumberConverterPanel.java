/*
NumberConverter - simple number format converter
NumberConverter.java - main program
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
