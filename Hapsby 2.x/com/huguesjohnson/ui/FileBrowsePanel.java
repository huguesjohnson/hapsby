/*
FileBrowsePanel.java - panel to browse for a file
 
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.huguesjohnson.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;

/**
 * Panel to browse for a file.
 * 
 * @author Hugues Johnson
 */
public class FileBrowsePanel extends JPanel{
	private static final long serialVersionUID=-4285415441734931402L;
	// the last path opened
	private static String lastPath;
	// filters to use when browsing
	private FileFilter[] filters;
	/** Default value for showing the browse button. */
	public final static boolean DEFAULT_SHOW_BROWSE_BUTTON=true;
	//controls
	private JButton buttonBrowse;
	private JLabel labelPath;
	private JTextField textFieldPath;
	private TitledBorder border;

	/**
	 * Creates a new FileBrowsePanel.
	 * 
	 * @param title The title of the panel.
	 * @param filters A list of file filters to accept.
	 */
	public FileBrowsePanel(String title,FileFilter[] filters){
		this.init(title,filters,DEFAULT_SHOW_BROWSE_BUTTON);
	}

	/**
	 * Creates a new FileBrowsePanel with a single file filter.
	 * 
	 * @param title The title of the panel.
	 * @param filter The file filter to accept.
	 */
	public FileBrowsePanel(String title,FileFilter filter){
		FileFilter[] filters=new FileFilter[1];
		filters[0]=filter;
		this.init(title,filters,DEFAULT_SHOW_BROWSE_BUTTON);
	}

	/**
	 * Creates a new FileBrowsePanel.
	 * 
	 * @param title The title of the panel.
	 * @param filters A list of file filters to accept.
	 * @param showBrowseButton Whether or not to show the browse button.
	 */
	public FileBrowsePanel(String title,FileFilter[] filters,boolean showBrowseButton){
		this.init(title,filters,showBrowseButton);
	}

	/**
	 * Creates a new FileBrowsePanel with a single file filter.
	 * 
	 * @param title The title of the panel.
	 * @param filter The file filter to accept.
	 * @param showBrowseButton Whether or not to show the browse button.
	 */
	public FileBrowsePanel(String title,FileFilter filter,boolean showBrowseButton){
		FileFilter[] filters=new FileFilter[1];
		filters[0]=filter;
		this.init(title,filters,showBrowseButton);
	}

	/**
	 * Creates a new FileBrowsePanel, accepts (*.*).
	 * 
	 * @param title The title of the panel.
	 */
	public FileBrowsePanel(String title){
		this.init(title,(new FileFilter[0]),DEFAULT_SHOW_BROWSE_BUTTON);
	}

	/**
	 * Creates a new FileBrowsePanel, accepts (*.*).
	 * 
	 * @param title The title of the panel.
	 * @param showBrowseButton Whether or not to show the browse button.
	 */
	public FileBrowsePanel(String title,boolean showBrowseButton){
		this.init(title,(new FileFilter[0]),showBrowseButton);
	}

	private void init(String title,FileFilter[] filters,boolean showBrowseButton){
		initComponents();
		// set the title
		this.border=(TitledBorder)this.getBorder();
		this.border.setTitle(title);
		// set file filters
		this.filters=filters;
		// look for the last path
		String spLastPath=System.getProperty("FileBrowsePanel.lastPath");
		if((spLastPath==null)||(spLastPath.length()<1)){
			lastPath=System.getProperty("user.dir");
		} else{
			lastPath=spLastPath;
		}
		// hide the browse button
		if(!showBrowseButton){
			this.buttonBrowse.setVisible(false);
		}
	}

	private void initComponents(){
		this.labelPath=new JLabel();
		this.textFieldPath=new JTextField();
		this.buttonBrowse=new JButton();

		this.setLayout(new javax.swing.BoxLayout(this,javax.swing.BoxLayout.X_AXIS));

		this.setBorder(new javax.swing.border.TitledBorder(""));
		this.labelPath.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		this.labelPath.setText("Path: ");
		this.labelPath.setToolTipText("Path to the file");
		this.labelPath.setMaximumSize(new java.awt.Dimension(40,15));
		this.labelPath.setMinimumSize(new java.awt.Dimension(40,15));
		this.labelPath.setPreferredSize(new java.awt.Dimension(40,15));
		this.add(this.labelPath);

		this.textFieldPath.setEditable(false);
		this.textFieldPath.setToolTipText("Path to the file");
		this.textFieldPath.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE,21));
		this.textFieldPath.setMinimumSize(new java.awt.Dimension(280,21));
		this.textFieldPath.setPreferredSize(new java.awt.Dimension(280,21));
		this.add(this.textFieldPath);

		this.buttonBrowse.setText("Browse");
		this.buttonBrowse.setToolTipText("Browse for a file");
		this.buttonBrowse.setMaximumSize(null);
		this.buttonBrowse.setMinimumSize(null);
		this.buttonBrowse.setPreferredSize(null);
		this.buttonBrowse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				buttonBrowseActionPerformed();
			}
		});
		this.add(this.buttonBrowse);

	}

	/**
	 * Action executed when the browse button is pressed. Opens a <code>JFileChooser</code> and calls <code>onBrowseComplete()</code> if a file was selected.
	 */
	public final void buttonBrowseActionPerformed(){
		if(this.browse()){
			this.onBrowseComplete();
		}
	}
	
	/**
	 * Opens a <code>JFileChooser</code> with the supported filters.
	 * 
	 * @return True if a file was selected and the selected file is not the same as the file currently listed in the "Path:" text field.
	 */
	public boolean browse(){
		boolean success=true;
		JFileChooser fileChooser=new JFileChooser();
		// do not allow multiple selections
		fileChooser.setMultiSelectionEnabled(false);
		// add filters
		int filterCount=this.filters.length;
		if(filterCount<1){
			// no filters specified, accept everything
			fileChooser.setAcceptAllFileFilterUsed(true);
		} else{
			fileChooser.setAcceptAllFileFilterUsed(false);
			for(int index=0;index<filterCount;index++){
				fileChooser.setFileFilter(this.filters[index]);
			}
		}
		// set the current directory
		String filePath=this.getFilePath();
		if(filePath.length()>0){
			fileChooser.setCurrentDirectory(new File(filePath));
		} else{
			fileChooser.setCurrentDirectory(new File(FileBrowsePanel.lastPath));
		}
		// show the file chooser
		int returnValue=fileChooser.showOpenDialog(this);
		// was 'OK' selected?
		if(returnValue==JFileChooser.APPROVE_OPTION){
			this.setFilePath(fileChooser.getSelectedFile().getPath());
			// save the last path
			String currentDirectoryPath=fileChooser.getCurrentDirectory().getPath();
			FileBrowsePanel.lastPath=currentDirectoryPath;
			System.setProperty("FileBrowsePanel.lastPath",currentDirectoryPath);
		} else{
			success=false;
		}
		return(success);
	}

	/**
	 * Event that fires after a browse has completed successfully.
	 * No method implementation, not abstract because overriding this is optional.
	 */
	public void onBrowseComplete(){ /* not implemented */ }

	/**
	 * Sets the "Path:" text field.
	 * 
	 * @param filePath The new path.
	 */
	public void setFilePath(String filePath){
		this.textFieldPath.setText(filePath);
	}

	/**
	 * Returns the file path in the "Path:" text field.
	 * 
	 * @return Path in the text field.
	 */
	public String getFilePath(){
		return(this.textFieldPath.getText());
	}

	/**
	 * Returns the full directory of the current path, no file name.
	 * 
	 * @return The full directory of the current path, no file name.
	 */
	public String getDirectory(){
		String path=(this.getFilePath());
		if(!path.endsWith(File.separator)){
			int lastIndexOf=path.lastIndexOf(File.separator);
			if((lastIndexOf>0)&&(lastIndexOf<path.length())){
				path=path.substring(0,lastIndexOf+1);
			}
		}
		return(path);
	}

	/**
	 * Clears the "Path:" text field.
	 */
	public void clear(){
		this.textFieldPath.setText("");
	}

	/**
	 * Sets the file filters.
	 * 
	 * @param filters The new filters to use.
	 */
	public void setFilters(FileFilter[] filters){
		this.filters=filters;
	}

	/**
	 * Sets filters to a single filter.
	 * 
	 * @param filter The single filter to use.
	 */
	public void setFilters(FileFilter filter){
		FileFilter[] filters=new FileFilter[1];
		filters[0]=filter;
		this.filters=filters;
	}
	
	/**
	 * Sets the title on the panel border.
	 * 
	 * @param title The new title for the panel border.
	 */
	public void setTitle(String title){
		this.border.setTitle(title);
	}
}
