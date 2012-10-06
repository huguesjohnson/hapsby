/*
JTextAreaTableCellRenderer.java - a simple cell renderer that uses a JTextArea
Copyright  (C) 2008 Hugues Johnson

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