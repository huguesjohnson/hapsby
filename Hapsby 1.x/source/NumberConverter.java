/*
NumberConverter - simple number format converter
NumberConverter.java - main program and window
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import java.awt.Cursor;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import NumberConverterPanel;
import NumberConversions;

/** class NumberConverter
* @author HuguesJohnson
*/
public class NumberConverter extends JFrame{
     /* caption for the window */
     private final static String windowCaption="Number Converter";
     /* program version */
     private final static String programVersion="0.1a";
     /* panel to enter & display decimal value */
     private NumberConverterPanel decimalPanel;
     /* panel to enter & display ascii value */
     private NumberConverterPanel asciiPanel;
     /* panel to enter & display hexadecimal value */
     private NumberConverterPanel hexPanel;
     /* panel to enter & display octal value */
     private NumberConverterPanel octalPanel;
     /* panel to enter & display binary value */
     private NumberConverterPanel binaryPanel;
     
     /** constructor for NumberConverter, create and show the window
     */
     public NumberConverter(){
          super(windowCaption+" "+programVersion);
          this.addComponents();
          this.pack();
          /* force window to stay in fixed size */
          this.setResizable(false);
          /* show window */
          this.setVisible(true);
     }
     
     /* addComponents, add panels to the window
     */
     private void addComponents(){
          this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
          this.addDecimalPanel();
          this.addAsciiPanel();
          this.addHexPanel();
          this.addOctalPanel();
          this.addBinaryPanel();
     }
     
     /* addDecimalPanel, adds the decimal panel
     */
     private void addDecimalPanel(){
          this.decimalPanel=new NumberConverterPanel("Decimal: "){
               public void keyReleased(final KeyEvent ke){
                    decimalUpdate();
               }
          };
          this.getContentPane().add(this.decimalPanel);
     }
     
     /* addAsciiPanel, adds the ascii panel
     */
     private void addAsciiPanel(){
          this.asciiPanel=new NumberConverterPanel("Ascii: "){
               public void keyReleased(final KeyEvent ke){
                    asciiUpdate();
               }
          };
          this.getContentPane().add(this.asciiPanel);
     }     
     
     /* addHexPanel, adds the hexadecimal panel
     */
     private void addHexPanel(){
          this.hexPanel=new NumberConverterPanel("HexaDecimal: "){
               public void keyReleased(final KeyEvent ke){
                    hexUpdate();
               }
          };
          this.getContentPane().add(this.hexPanel);
     }     
     
     /* addOctalPanel, adds the octal panel
     */
     private void addOctalPanel(){
          this.octalPanel=new NumberConverterPanel("Octal: "){
               public void keyReleased(final KeyEvent ke){
                    octalUpdate();
               }
          };
          this.getContentPane().add(this.octalPanel);
     }     
     
     /* addBinaryPanel, adds the binary panel
     */
     private void addBinaryPanel(){
          this.binaryPanel=new NumberConverterPanel("Binary: "){
               public void keyReleased(final KeyEvent ke){
                    binaryUpdate();
               }
          };
          this.getContentPane().add(this.binaryPanel);
     }
     
     /* decimalUpdate, fires when decimalPanel is typed in
     */
     private void decimalUpdate(){
          this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
          String text=this.decimalPanel.getText().trim();
          this.asciiPanel.setText(NumberConversions.decimalStringToAsciiString(text));
          this.hexPanel.setText(NumberConversions.decimalStringToHexString(text));
          this.octalPanel.setText(NumberConversions.decimalStringToOctalString(text));
          this.binaryPanel.setText(NumberConversions.decimalStringToBinaryString(text));
          this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
     }

     /* asciiUpdate, fires when asciiPanel is typed in
     */
     private void asciiUpdate(){
          this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
          /* first convert ascii value to decimal value */
          String text=this.asciiPanel.getText().trim();
          this.decimalPanel.setText(NumberConversions.asciiStringToDecimalString(text));
          /* now use decimal convertions to handle the rest */
          text=this.decimalPanel.getText().trim();
          this.hexPanel.setText(NumberConversions.decimalStringToHexString(text));
          this.octalPanel.setText(NumberConversions.decimalStringToOctalString(text));
          this.binaryPanel.setText(NumberConversions.decimalStringToBinaryString(text));
          this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
     }
     
     /* hexUpdate, fires when hexPanel is typed in
     */
     private void hexUpdate(){
          this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
          /* first convert hex value to decimal value */
          String text=this.hexPanel.getText().trim();
          this.decimalPanel.setText(NumberConversions.hexStringToDecimalString(text));
          /* now use decimal convertions to handle the rest */
          text=this.decimalPanel.getText().trim();
          this.asciiPanel.setText(NumberConversions.decimalStringToAsciiString(text));
          this.octalPanel.setText(NumberConversions.decimalStringToOctalString(text));
          this.binaryPanel.setText(NumberConversions.decimalStringToBinaryString(text));
          this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
     }
     
     /* octalUpdate, fires when octalPanel is typed in
     */
     private void octalUpdate(){
          this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
          /* first convert octal value to decimal value */
          String text=this.octalPanel.getText().trim();
          this.decimalPanel.setText(NumberConversions.octalStringToDecimalString(text));
          /* now use decimal convertions to handle the rest */
          text=this.decimalPanel.getText().trim();
          this.asciiPanel.setText(NumberConversions.decimalStringToAsciiString(text));
          this.binaryPanel.setText(NumberConversions.decimalStringToBinaryString(text));     
          this.hexPanel.setText(NumberConversions.decimalStringToHexString(text));
          this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
     }
     
     /* binaryUpdate, fires when decimalPanel is typed in
     */
     private void binaryUpdate(){
          this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
          /* first convert octal value to decimal value */
          String text=this.binaryPanel.getText().trim();
          this.decimalPanel.setText(NumberConversions.binaryStringToDecimalString(text));
          /* now use decimal convertions to handle the rest */
          text=this.decimalPanel.getText().trim();
          this.asciiPanel.setText(NumberConversions.decimalStringToAsciiString(text));
          this.hexPanel.setText(NumberConversions.decimalStringToHexString(text));
          this.octalPanel.setText(NumberConversions.decimalStringToOctalString(text));
          this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
     }
     
     /** toString returns a String representation of this object
      * @return String representation of this object
      */
     public String toString(){
          String tostring=new String(super.toString());
          return(tostring);
     }     
}
