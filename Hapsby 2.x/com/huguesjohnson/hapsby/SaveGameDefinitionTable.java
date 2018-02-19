/*
Hapsby - universal save game editor
SaveGameDefinitionTable.java - Table to display a list of save game definitions
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.huguesjohnson.hapsby;

import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.huguesjohnson.ui.JTextAreaTableCellRenderer;

/**
 * Displays a list of save game definitions.
 * 
 * @author Hugues Johnson
 */
public class SaveGameDefinitionTable extends JTable{
	private static final long serialVersionUID=-2670717996938962898L;

	/**
	 * Creates the table and adds data.
	 * 
	 * @param saveGameDefinitions The data to display in the table.
	 */
	@SuppressWarnings("unchecked") 
	public SaveGameDefinitionTable(ArrayList<SaveGameDefinition> saveGameDefinitions){
		// setup the table
		DefaultTableModel model=new DefaultTableModel(){
			private static final long serialVersionUID=8502702869421332319L;

			public boolean isCellEditable(int row,int column){
				return(false);
			}
		};
		TableSorter sorter=new TableSorter(model){
			private static final long serialVersionUID=-2844124849693527847L;
			@Override 
			public void notifySortingStatusChanged(){
				resizeAllRows();
			}
		};
		this.setModel(sorter);
		sorter.setTableHeader(this.getTableHeader());
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.appendColumn("Game Title");
		this.appendColumn("Description");
		this.appendColumn("File Pattern");
		int columnCount=this.getColumnCount();
		for(int column=0;column<columnCount;column++){
			this.getColumnModel().getColumn(column).setCellRenderer(new JTextAreaTableCellRenderer());
		}
		// add stuff
		int length=saveGameDefinitions.size();
		for(int index=0;index<length;index++){
			SaveGameDefinition sgd=saveGameDefinitions.get(index);
			Vector<Object> rowData=new Vector<Object>();
			rowData.add(sgd);
			rowData.add(sgd.getGameDescription().replaceAll("\\<.*?>",""));
			rowData.add(sgd.getSaveFilePattern());
			rowData.add(sgd);
			model.addRow(rowData);
		}
		columnCount=this.getColumnCount();
	}

	/**
	 * Attempts to resize all the rows and columns to fit contents.
	 */
	public void resizeStuff(){
		int width=this.getWidth();
		this.getColumnModel().getColumn(0).setPreferredWidth((int)(width*.4));
		this.getColumnModel().getColumn(1).setPreferredWidth((int)(width*.4));
		this.getColumnModel().getColumn(2).setPreferredWidth((int)(width*.2));
		this.resizeAllRows();
	}
	
	/**
	 * Attempts to resize all the rows to fit contents.
	 */
	public void resizeAllRows(){
		int rowCount=this.getModel().getRowCount();
		for(int row=0;row<rowCount;row++){
			int rowHeight=this.getRowHeight(row);
			int preferredHeight=rowHeight;
			int columnCount=this.getColumnCount();
			for(int column=0;column<columnCount;column++){
				JTextAreaTableCellRenderer cellRenderer=(JTextAreaTableCellRenderer)this.getCellRenderer(row,column);
				String value=this.getModel().getValueAt(row,column).toString();
				FontMetrics fm=cellRenderer.getFontMetrics(cellRenderer.getFont());
				int charsWidth=fm.charsWidth(value.toCharArray(),0,value.length());				
				//check if this needs to wrap
				if(charsWidth>cellRenderer.getWidth()){
					int textRows=(int)Math.ceil(charsWidth/this.getColumnModel().getColumn(column).getWidth());
					int newHeight=textRows*fm.getHeight();
					if(newHeight>preferredHeight){
						preferredHeight=newHeight;
					}
				}
			}
			if(preferredHeight>rowHeight){
				this.setRowHeight(row,preferredHeight);
			}
		}
	}
	
	private void appendColumn(String caption){
		DefaultTableModel model=(DefaultTableModel)((TableSorter)this.getModel()).getTableModel();
		TableColumn column=new TableColumn(model.getColumnCount());
		column.setHeaderValue(caption);
		this.addColumn(column);
		model.addColumn(caption);
	}
}
