/*
Hapsby - universal save game editor
HapsbyMainWindow.java - main UI for Hapsby
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.huguesjohnson.hapsby;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
 
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;

/**
 * User interface for Hapsby application.
 * 
 * @author Hugues Johnson
 */
public abstract class HapsbyMainWindow extends JFrame{
	private static final long serialVersionUID=2499333826039263783L;
	private static final String RESOURCE_PATH="/com/huguesjohnson/hapsby/images/";
	private PropertyListPanel leftPane;
	private EditPropertyPanel rightPane;
	private JProgressBar progress;
	private JSplitPane splitPane;
	private HapsbyMenu menu;
	private HapsbyToolbar toolbar;
	private String currentLookAndFeel;

	/**
	 * Creates the window and adds components.
	 */
	public HapsbyMainWindow(String caption){
		super(caption);
		this.setIconImage((new ImageIcon(this.getClass().getResource(RESOURCE_PATH+"hapsby.gif"))).getImage());
		this.addComponents();
	}

	/*
	 * addComponents() creates and add components to the window
	 */
	private void addComponents(){
		/* create content pane */
		Container contentPane=this.getContentPane();
		/* initialize panels & override actionPerformed event */
		this.leftPane=new PropertyListPanel(){
			private static final long serialVersionUID=1253551531276902747L;

			public void actionPerformed(final ActionEvent e){
				actionListener(e);
			}
			
			public void valueChanged(final ListSelectionEvent e){
				listSelectionListener(e);
			}
		};
		this.rightPane=new EditPropertyPanel(){
			private static final long serialVersionUID=-1589261374967073731L;

			public void actionPerformed(final ActionEvent e){
				actionListener(e);
			}
		};
		/* add menu */
		this.menu=new HapsbyMenu(RESOURCE_PATH){
			private static final long serialVersionUID=-1580121091388429632L;

			public void actionPerformed(final ActionEvent e){
				actionListener(e);
			}
		};
		this.setJMenuBar(this.menu);
		/* add toolbar */
		this.toolbar=new HapsbyToolbar(RESOURCE_PATH){
			private static final long serialVersionUID=514696041870203305L;

			public void actionPerformed(final ActionEvent e){
				actionListener(e);
			}
		};
		contentPane.add(this.toolbar,BorderLayout.NORTH);
		/* add status bar */
		this.progress=new JProgressBar();
		this.progress.setStringPainted(true);
		contentPane.add(this.progress,BorderLayout.SOUTH);
		/* create split pane to hold components */
		this.splitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,this.leftPane,this.rightPane);
		this.splitPane.setOneTouchExpandable(true);
		/* size components */
		this.splitPane.setDividerLocation(200);
		this.leftPane.setMinimumSize(new Dimension(200,200));
		this.rightPane.setMinimumSize(new Dimension(400,200));
		/* add split pane */
		contentPane.add(this.splitPane);
		/* add listener for close event */
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				closeWindow();
			}
		});
	}

	/**
	 * Changes the look and feel of the window.
	 * 
	 * @param newLookAndFeel Class name of the new look & feel to use.
	 */
	public void setLookAndFeel(String newLookAndFeel){
		if(!newLookAndFeel.equals(this.currentLookAndFeel)){
			try{
				UIManager.setLookAndFeel(newLookAndFeel);
				SwingUtilities.updateComponentTreeUI(this);
				this.currentLookAndFeel=new String(newLookAndFeel);
			} catch(Exception x){
				x.printStackTrace();
			}
		}
	}

	/**
	 * Sets the value for the "Save Game Description" field. The description may be plain text or html format.
	 * 
	 * @param description New value for the "Save Game Description" field.
	 */
	public void setGameDescription(String description){
		this.rightPane.setNameDesc(description);
	}
	
	/**
	 * Refreshes the edit property panel (rightPane) based on a <code>SaveGameProperty</code> and value from a save game.
	 * 
	 * @param sgp New <code>SaveGameProperty</code> to display.
	 * @param currentValue New current value for the property.
	 */
	public void setCurrentProperty(SaveGameProperty sgp,int currentValue){
		this.setCurrentProperty(sgp,String.valueOf(currentValue));
	}

	/**
	 * Refreshes the edit property panel (rightPane) based on a <code>SaveGameProperty</code> and value from a save game.
	 * 
	 * @param sgp New <code>SaveGameProperty</code> to display.
	 * @param currentValue New current value for the property.
	 */
	public void setCurrentProperty(SaveGameProperty sgp,String currentValue){
		this.rightPane.setPropertyDesc(sgp.getDescription());
		this.rightPane.setDataType(sgp.getDataType());
		this.rightPane.setAddress(String.valueOf(sgp.getAddress()));
		this.rightPane.setLength(String.valueOf(sgp.getLength()));
		this.rightPane.setMinValue(String.valueOf(sgp.getMinValue()));
		this.rightPane.setMaxValue(String.valueOf(sgp.getMaxValue()));
		this.rightPane.setCurrentValue(currentValue);
	}	
	
	/**
	 * Returns the selected property from the properties list (leftPane).
	 * 
	 * @return Selected property from property list (leftPane).
	 */
	public SaveGameProperty getSelectedProperty(){
		return(this.leftPane.getSelectedItem());
	}

	/**
	 * Populates the property list (leftPane).
	 * 
	 * @param properties <code>ArrayList</code> of properties to go into the property list (leftPane).
	 */
	public void setPropertyList(ArrayList<SaveGameProperty> properties){
		this.leftPane.setPropertyList(properties);
	}
	
	
	/**
	 * Returns the text contained in the "Current Value" <code>JTextField</code>.
	 * 
	 * @return The text contained in the "Current Value" <code>JTextField</code>.
	 */
	public String getCurrentPropertyValue(){
		return(this.rightPane.getCurrentValue());
	}

	/**
	 * Method invoked when an action is performed. Override this routine when creating a new <code>HapsbyMainWindow</code>.
	 * 
	 * @param e <code>ActionEvent</code> received by UI
	 */
	public abstract void actionListener(ActionEvent e);

	/**
	 * Method invoked when the property list selection is changed. Override this routine when creating a new <code>HapsbyMainWindow</code>.
	 * 
	 * @param e <code>ListSelectionEvent</code> received by UI
	 */
	public abstract void listSelectionListener(ListSelectionEvent e);
	
	/**
	 * Method invoked when the window is closed. Override this routine when creating a new <code>HapsbyMainWindow</code>.
	 */
	public abstract void closeWindow();

	/**
	 * Sets the text and percent complete of the status bar.
	 * 
	 * @param message The new message to display in the status bar.
	 * @param percent The new percent complete to display in the status bar.
	 */
	public void setProgress(String message,int percent){
		if(this.progress!=null){
			this.progress.setString(message);
			this.progress.setValue(percent);
		}
	}

	/**
	 * Centers the window in the middle of the screen.
	 */
	public void centerWindow(){
		Dimension screenDimension=Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle windowDimension=this.getBounds();
		this.setLocation((screenDimension.width-windowDimension.width)/2,(screenDimension.height-windowDimension.height)/2);
	}
	
	/**
	 * Returns the class name of the current look and feel.
	 * 
	 * @return The class name of the current look and feel.
	 */
	public String getCurrentLookAndFeelName(){
		if(this.currentLookAndFeel==null){
			return(UIManager.getLookAndFeel().getClass().getName());
		}
		return(this.currentLookAndFeel);
	}
}
