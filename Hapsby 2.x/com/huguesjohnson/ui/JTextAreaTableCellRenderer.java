/*
JTextAreaTableCellRenderer.java - a simple cell renderer that uses a JTextArea
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.huguesjohnson.ui;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/**
 * A simple cell renderer that extends <code>JTextArea</code>.
 * 
 * @author Hugues Johnson
 */
public class JTextAreaTableCellRenderer extends JTextArea implements TableCellRenderer{
	private static final long serialVersionUID=-8810415752643341962L;

	/**
	 * Sets the following properties of <code>JTextArea</code>:
	 * <ul>
	 * <li><code>setOpaque(true)</code></li>
	 * <li><code>setLineWrap(true)</code></li>
	 * <li><code>setWrapStyleWord(true)</code></li>
	 * </ul>
	 */
	public JTextAreaTableCellRenderer(){
		this.setOpaque(true);
	    this.setLineWrap(true);
	    this.setWrapStyleWord(true);
	}

	/** 
	 * Sets the colors, text, and tooltip.
	 * 
	 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	@Override 
	public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){
		if(isSelected){
			this.setBackground(UIManager.getColor("Label.foreground"));
			this.setForeground(UIManager.getColor("Label.background"));
		} else{
			this.setBackground(UIManager.getColor("Label.background"));
			this.setForeground(UIManager.getColor("Label.foreground"));
		}
		this.setText(value.toString());
		this.setToolTipText(value.toString());
		return(this);
	}
}
