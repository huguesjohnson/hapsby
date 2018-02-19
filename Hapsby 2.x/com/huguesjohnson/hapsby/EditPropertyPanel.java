/*
Hapsby - universal save game editor
EditPropertyPanel.java - UI for editing a save game property
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.huguesjohnson.hapsby;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.huguesjohnson.hapsby.SaveGameProperty.DataType;

/**
 * Panel to display and edit a <code>SaveGameProperty</code>.
 * 
 * @author Hugues Johnson
 */
public abstract class EditPropertyPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID=1669846851666693676L;
	// ui controls
	private JLabel nameDescLabel;
	private JLabel addressLabel;
	private JLabel dataTypeLabel;
	private JLabel lengthLabel;
	private JLabel maxValueLabel;
	private JLabel minValueLabel;
	private JLabel propertyDescLabel;
	private JTextField currentValue;

	/**
	 * Default constructor, just adds the UI components.
	 */
	public EditPropertyPanel(){
		this.addContents();
	}

	/*
	 * addContents adds all controls onto the panel
	 */
	private void addContents(){
		// create border and layout
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Edit Property"));
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		Dimension minSize=new Dimension(200,20);
		Dimension descMinSize=new Dimension(400,60);
		// save game description label
		JPanel saveGameDescriptionLabelPanel=new JPanel();
		saveGameDescriptionLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel saveGameDescriptionLabel=new JLabel("Save Game Description");
		saveGameDescriptionLabel.setAlignmentX(LEFT_ALIGNMENT);
		saveGameDescriptionLabel.setMinimumSize(minSize);
		saveGameDescriptionLabel.setPreferredSize(minSize);
		saveGameDescriptionLabelPanel.add(saveGameDescriptionLabel);
		this.add(saveGameDescriptionLabelPanel);
		// save game description
		JPanel saveGameDescriptionPanel=new JPanel();
		saveGameDescriptionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.nameDescLabel=new JLabel("<html><p><i>Please open a save game</i></p></html>");
		this.nameDescLabel.setAlignmentX(LEFT_ALIGNMENT);
		this.nameDescLabel.setAlignmentY(TOP_ALIGNMENT);
		this.nameDescLabel.setMinimumSize(descMinSize);
		this.nameDescLabel.setPreferredSize(descMinSize);
		saveGameDescriptionPanel.add(this.nameDescLabel);
		this.add(saveGameDescriptionPanel);
		// property description label
		JPanel propertyDescriptionLabelPanel=new JPanel();
		propertyDescriptionLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel propertyDescriptionLabel=new JLabel("Selected Property Description");
		propertyDescriptionLabel.setAlignmentX(LEFT_ALIGNMENT);
		propertyDescriptionLabel.setMinimumSize(minSize);
		propertyDescriptionLabel.setPreferredSize(minSize);
		propertyDescriptionLabelPanel.add(propertyDescriptionLabel);
		this.add(propertyDescriptionLabelPanel);
		// property description
		JPanel propertyDescriptionPanel=new JPanel();
		propertyDescriptionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.propertyDescLabel=new JLabel("<html><p><i>No property selected</i></p></html>");
		this.propertyDescLabel.setAlignmentX(LEFT_ALIGNMENT);
		this.propertyDescLabel.setAlignmentY(TOP_ALIGNMENT);
		this.propertyDescLabel.setMinimumSize(descMinSize);
		this.propertyDescLabel.setPreferredSize(descMinSize);
		propertyDescriptionPanel.add(this.propertyDescLabel);
		this.add(propertyDescriptionPanel);
		// data type
		JPanel dataTypePanel=new JPanel();
		dataTypePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		dataTypePanel.add(new JLabel("Data Type: "));
		this.dataTypeLabel=new JLabel();
		this.dataTypeLabel.setAlignmentX(LEFT_ALIGNMENT);
		this.dataTypeLabel.setMinimumSize(minSize);
		this.dataTypeLabel.setPreferredSize(minSize);
		dataTypePanel.add(this.dataTypeLabel);
		this.add(dataTypePanel);
		// address
		JPanel addressPanel=new JPanel();
		addressPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		addressPanel.add(new JLabel("Address: "));
		this.addressLabel=new JLabel();
		this.addressLabel.setAlignmentX(LEFT_ALIGNMENT);
		this.addressLabel.setMinimumSize(minSize);
		this.addressLabel.setPreferredSize(minSize);
		addressPanel.add(this.addressLabel);
		this.add(addressPanel);
		// length
		JPanel lengthPanel=new JPanel();
		lengthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		lengthPanel.add(new JLabel("Length: "));
		this.lengthLabel=new JLabel();
		this.lengthLabel.setAlignmentX(LEFT_ALIGNMENT);
		this.lengthLabel.setMinimumSize(minSize);
		this.lengthLabel.setPreferredSize(minSize);
		lengthPanel.add(this.lengthLabel);
		this.add(lengthPanel);
		// max value
		JPanel maxValuePanel=new JPanel();
		maxValuePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		maxValuePanel.add(new JLabel("Maximum Value: "));
		this.maxValueLabel=new JLabel();
		this.maxValueLabel.setAlignmentX(LEFT_ALIGNMENT);
		this.maxValueLabel.setMinimumSize(minSize);
		this.maxValueLabel.setPreferredSize(minSize);
		maxValuePanel.add(this.maxValueLabel);
		this.add(maxValuePanel);
		// min value
		JPanel minValuePanel=new JPanel();
		minValuePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		minValuePanel.add(new JLabel("Minimum Value: "));
		this.minValueLabel=new JLabel();
		this.minValueLabel.setAlignmentX(LEFT_ALIGNMENT);
		this.minValueLabel.setMinimumSize(minSize);
		this.minValueLabel.setPreferredSize(minSize);
		minValuePanel.add(this.minValueLabel);
		this.add(minValuePanel);
		//current value
		JPanel currentValuePanel=new JPanel();
		currentValuePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		currentValuePanel.add(new JLabel("Current Value:" ));
		this.currentValue=new JTextField("");
		this.currentValue.setMinimumSize(minSize);
		this.currentValue.setPreferredSize(minSize);
		this.currentValue.setInputVerifier(
				new InputVerifier(){
					@SuppressWarnings({"synthetic-access","unqualified-field-access"}) 
					@Override 
					public boolean verify(JComponent input){
						if(dataTypeLabel.getText().equals("Integer")){
							try{
								int minValue=Integer.parseInt(minValueLabel.getText());
								int maxValue=Integer.parseInt(maxValueLabel.getText());
								int newValue=Integer.parseInt(currentValue.getText());
								if((newValue<minValue)||(newValue>maxValue)){
									JOptionPane.showMessageDialog(getParent(),"New value must be >="+minValue+" and <="+maxValue,"Validation error",JOptionPane.ERROR_MESSAGE);
									return(false);
								}
							} catch(Exception x){
								JOptionPane.showMessageDialog(getParent(),"Value is not numeric","Validation error",JOptionPane.ERROR_MESSAGE);
								return(false);
							}
						} else if(dataTypeLabel.getText().equals("String")){
							try{
								int length=Integer.parseInt(lengthLabel.getText());
								if(currentValue.getText().length()>length){
									JOptionPane.showMessageDialog(getParent(),"Text length must be <="+length,"Validation error",JOptionPane.ERROR_MESSAGE);
									return(false);
								}
							} catch(Exception x){
								JOptionPane.showMessageDialog(getParent(),"Value is not numeric","Validation error",JOptionPane.ERROR_MESSAGE);
								return(false);
							}							
						}
						return(true);
					}
				}
		);
		currentValuePanel.add(this.currentValue);
		this.add(currentValuePanel);
	}

	/**
	 * Returns the text contained in the "Current Value" <code>JTextField</code>.
	 * 
	 * @return The text contained in the "Current Value" <code>JTextField</code>.
	 */
	public String getCurrentValue(){
		return(this.currentValue.getText());
	}

	/**
	 * Sets the value for the "Save Game Description" field. The description may be plain text or html format.
	 * 
	 * @param caption New value for the "Save Game Description" field.
	 */
	public void setNameDesc(String caption){
		this.nameDescLabel.setText(caption);
	}

	/**
	 * Sets the value for the "Address" field.
	 * 
	 * @param address New value for the "Address" field.
	 */
	public void setAddress(String address){
		this.addressLabel.setText(address);
	}

	/**
	 * Sets the value for the "Length" field.
	 * 
	 * @param length New value for the "Length" field.
	 */
	public void setLength(String length){
		this.lengthLabel.setText(length);
	}

	/**
	 * Sets the value for the "Minimum Value" field.
	 * 
	 * @param minValue New value for the "Minimum Value" field.
	 */
	public void setMinValue(String minValue){
		this.minValueLabel.setText(minValue);
	}

	/**
	 * Sets the value for the "Maximum Value" field.
	 * 
	 * @param maxValue New value for the "Maximum Value" field.
	 */
	public void setMaxValue(String maxValue){
		this.maxValueLabel.setText(maxValue);
	}

	/**
	 * Sets the value for the "Data Type" field.
	 * 
	 * @param dataType New value for the "Data Type" field.
	 */
	public void setDataType(DataType dataType){
		if(dataType==DataType.TYPE_INTEGER){
			this.dataTypeLabel.setText("Integer");
		} else{
			this.dataTypeLabel.setText("String");
		}
	}
	
	/**
	 * Sets the value for the "Current Value" field.
	 * 
	 * @param value New value for the "Current Value" field.
	 */
	public void setCurrentValue(String value){
		this.currentValue.setText(value);
	}

	/**
	 * Sets the value for the "Selected Property Description" field. The description may be plain text or html format.
	 * 
	 * @param description New value for the "Selected Property Description" field.
	 */
	public void setPropertyDesc(String description){
		this.propertyDescLabel.setText(description);
	}

	/**
	 * Sets the caption for the panel.
	 * 
	 * @param caption New caption for the panel.
	 */
	public void setCaption(String caption){
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),caption));
	}
}
