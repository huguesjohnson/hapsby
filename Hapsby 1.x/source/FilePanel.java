/*
Hapsby - universal save game editor
FilePanel.java - UI for editing a save game
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
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
/* imports for JFC 1.0.1
import com.sun.java.swing.JPanel;
import com.sun.java.swing.JLabel;
import com.sun.java.swing.JTextField;
import com.sun.java.swing.JButton;
import com.sun.java.swing.JComboBox;
import com.sun.java.swing.BoxLayout;
import com.sun.java.swing.BorderFactory;
import com.sun.java.swing.ImageIcon;
*/
import HapsbyActionCommands;

/** Class FilePanel, save game editing interface for Hapsby program
* @author Hugues Johnson
*/
public class FilePanel extends JPanel implements ActionListener{
     /* begin captions for controls */
     /* caption for the panel */
     private final static String PANEL_CAPTION="Current Save Game";
     /* caption for address label */
     private final static String ADDRESS_LABEL="Address: ";
     /* caption for length label */
     private final static String LENGTH_LABEL="    Length: ";
     /* caption for maximum value label */
     private final static String MAXVALUE_LABEL="Maximum Value: ";
     /* caption for minimum value label */
     private final static String MINVALUE_LABEL="    Minimum Value: ";
     /* caption for current value label */
     private final static String CURRENTVALUE_LABEL="Current Value: ";
     /* caption for save button */
     private final static String SAVEBUTTON_LABEL="Save";
     /* caption for game name & description label */
     private final static String NAMEDESC_LABEL="Please Open a Save Game";
     /* caption for property combo box */
     private final static String PROPERTY_LABEL="Property: ";
     /* end captions for controls */
     
     /* begin default values */
     /* default for all integer fields */
     private final static String DEFAULT_VALUE="0";
     /* default for property list combo box */
     private final static String[] EMPTY_LIST={" "};
     /* end default values */
     
     /* begin shared objects */
     /* label to store game description */
     private JLabel nameDescLabel;
     /* label to store address */
     private JLabel addressLabel;
     /* label to store length */
     private JLabel lengthLabel;
     /* label to store maximum value */
     private JLabel maxValueLabel;
     /* label to store minimum value */
     private JLabel minValueLabel;
     /* label to store property name & description */
     private JLabel propertyDescLabel;
     /* text field to edit current value */
     private JTextField currentValue;
     /* combo box to store property list */
     private JComboBox propertyList;
     /* save button */
     private JButton saveButton;
     /* resource path (where images are stored) */
     private String resourcePath;
     /* end shared objects */
     
     /** Default constructor for FilePanel class.
     * This is pretty large as there are many controls in this panel.
     * @TODO (long term) create several sub-panel classes.
     */
     public FilePanel(){
          /* set the resource path
          under some IDEs (such as Forte and Visual Cafe) the next line will not work because System.getProperty("user.dir") returns a path to the IDE and not to the working folder */
          String defaultResourcePath=new String(System.getProperty("user.dir")+File.separator+"themes"+File.separator+"default"+File.separator);
          this.setResourcePath(defaultResourcePath);
          this.addContents();
     }
     
     /** Default constructor for FilePanel class.
     * This is pretty large as there are many controls in this panel.
     * @TODO (long term) create several sub-panel classes.
     */
     public FilePanel(String resourcePath){
          this.setResourcePath(resourcePath);
          this.addContents();
     }
     
     /* addContents adds all controls onto the panel
     */
     private void addContents(){
          /* controls that are re-used in this method - but don't need to be shared in the class */
          JPanel panel;
          JLabel label;
          /* create border and layout */
          this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),PANEL_CAPTION));
          this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
          /** @TODO store size setting externally */
          /** @TODO work on layout so stuff looks better */
          Dimension minSize=new Dimension(400,20);
          Dimension maxSize=new Dimension(1000,40);
          Dimension preferredSize=new Dimension(400,20);
          Dimension descMinSize=new Dimension(400,80);
          Dimension descMaxSize=new Dimension(1000,200);
          Dimension descPreferredSize=new Dimension(400,80);
          /* set up panel to hold name & description */
          panel=new JPanel(new BorderLayout());
          panel.setMinimumSize(descMinSize);
          panel.setPreferredSize(descPreferredSize);
          panel.setMaximumSize(descMaxSize);
          this.nameDescLabel=new JLabel(NAMEDESC_LABEL);
          panel.add(this.nameDescLabel);
          this.add(panel);
          /* set up panel to hold property list*/
          panel=new JPanel(new BorderLayout());
          panel.setMinimumSize(minSize);
          panel.setPreferredSize(preferredSize);
          panel.setMaximumSize(maxSize);
          label=new JLabel(this.PROPERTY_LABEL);
          panel.add(label,BorderLayout.WEST);
          this.propertyList=new JComboBox(this.EMPTY_LIST);
          this.propertyList.addActionListener(this);
          this.propertyList.setActionCommand(HapsbyActionCommands.ACTION_SELECT);
          panel.add(this.propertyList,BorderLayout.CENTER);
          this.add(panel);
          /* set up panel to hold property description */
          panel=new JPanel(new BorderLayout());
          panel.setMinimumSize(descMinSize);
          panel.setPreferredSize(descPreferredSize);
          panel.setMaximumSize(descMaxSize);
          this.propertyDescLabel=new JLabel();
          panel.add(this.propertyDescLabel);
          this.add(panel);
          /* set up panel to hold address & length */
          panel=new JPanel(new BorderLayout());
          panel.setMinimumSize(minSize);
          panel.setPreferredSize(minSize);
          panel.setMaximumSize(maxSize);
          this.addressLabel=new JLabel(this.ADDRESS_LABEL+this.DEFAULT_VALUE);
          panel.add(addressLabel,BorderLayout.WEST);
          this.lengthLabel=new JLabel(this.LENGTH_LABEL+this.DEFAULT_VALUE);
          panel.add(this.lengthLabel,BorderLayout.CENTER);
          this.add(panel);
          /* set up panel to hold max & min values */
          panel=new JPanel(new BorderLayout());
          panel.setMinimumSize(minSize);
          panel.setPreferredSize(minSize);
          panel.setMaximumSize(maxSize);
          this.maxValueLabel=new JLabel(this.MAXVALUE_LABEL+this.DEFAULT_VALUE);
          panel.add(this.maxValueLabel,BorderLayout.WEST);
          this.minValueLabel=new JLabel(this.MINVALUE_LABEL+this.DEFAULT_VALUE);
          panel.add(minValueLabel,BorderLayout.CENTER);
          this.add(panel);
          /* set up panel to hold current value & save button */
          panel=new JPanel(new BorderLayout());
          panel.setMinimumSize(minSize);
          panel.setPreferredSize(minSize);
          panel.setMaximumSize(maxSize);
          label=new JLabel(this.CURRENTVALUE_LABEL);
          panel.add(label,BorderLayout.WEST);
          this.currentValue=new JTextField(this.DEFAULT_VALUE);
          panel.add(this.currentValue,BorderLayout.CENTER);
          this.saveButton=new JButton(this.SAVEBUTTON_LABEL,new ImageIcon(resourcePath+"save.gif"));
          this.saveButton.setActionCommand(HapsbyActionCommands.ACTION_SAVE);
          this.saveButton.addActionListener(this);
          panel.add(saveButton,BorderLayout.EAST);
          this.add(panel);
     }
     
     /** actionPerformed - empty method. Meant to be overidden by parent window.
     * @param e ActionEvent received in panel 
     */
     public void actionPerformed(final ActionEvent e){
     }
     
     /** getCurrentValue gets the text contained in JTextField currentValue
     * @return text contained in the current value field
     */
     public String getCurrentValue(){ 
          return(this.currentValue.getText()); 
     }
     
     /* the remaining methods are used to set the captions on labels from an external class */
     /** setNameDesc sets the text in nameDescLabel
     * @param caption new name & description
     */
     public void setNameDesc(String caption){ 
          this.nameDescLabel.setText(caption); 
     }
     
     /** setAddress sets the text in addressLabel
     * @param address new address
     */
     public void setAddress(String address){ 
          this.addressLabel.setText(this.ADDRESS_LABEL+address); 
     }

     /** setLength sets the text in lengthLabel
     * @param length new length
     */
     public void setLength(String length){ 
          this.lengthLabel.setText(this.LENGTH_LABEL+length); 
     }
     
     /** setMinValue sets the text in minValueLabel
     * @param minValue new minimum value
     */
     public void setMinValue(String minValue){ 
          this.minValueLabel.setText(this.MINVALUE_LABEL+minValue); 
     }
     
     /** setMaxValue sets the text in maxValueLabel
     * @param maxValue new maximum value
     */
     public void setMaxValue(String maxValue){ 
          this.maxValueLabel.setText(this.MAXVALUE_LABEL+maxValue); 
     }
     
     /** setCurrentValue sets the text in currentValue
     * @param value new value
     */
     public void setCurrentValue(String value){
          this.currentValue.setText(value); 
     }

     /** Sets the description of the current property.
     * The description may be plain text or html format.
     * @param description new description, either text or html
     */
    public void setPropertyDesc(String description) {
      this.propertyDescLabel.setText(description);
    }
     
     /** Sets caption for the panel.
     * @param caption new caption, must be plain text
     */
    public void setCaption(String caption) {
      this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),caption));
    }
     
     /** setPropertyList creates a new list for the property list combo box
     * @param list new list of properties
     */
     public void setPropertyList(String[] list){
          /* clear list */
          this.propertyList.removeAllItems();
          /* add all items from list to propertyList */
          for(int q=0;q<list.length;q++){
               this.propertyList.addItem(list[q]);
          }
     }
     
     /** Gets a String representation of the selected property.
     * @return name of selected property
     */
    public String getSelectedProperty(){
      return((String)this.propertyList.getSelectedItem());
    }

     /** toString returns a String representation of this object
      * @return String representation of this object
      */
     public String toString(){
          String tostring=new String(super.toString());
          tostring+="\nresourcePath="+this.resourcePath;
          return(tostring);
     }    
    
     /** setResourcePath sets the path to the resource (image) directory
     * @param resourcePath a path to the resource (image) directory 
     */
     public void setResourcePath(String resourcePath){
          /* test if resource path is a valid directory */
          File temp=new File(resourcePath);
          if(temp.isDirectory()){
               this.resourcePath=resourcePath;
          }
     }
}