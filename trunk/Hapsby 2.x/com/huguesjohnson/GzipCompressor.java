/*
GzipCompressor.java - Gzip compressor
Copyright  (C) 2000-2009 Hugues Johnson
 
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

package com.huguesjohnson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Class to create a gzip archive.
 * 
 * @author Hugues Johnson
 */
public class GzipCompressor{
	/* default buffer length to use */
	private final static int BUFFER_LENGTH=1024;

	/**
	 * Creates a gzip archive containing a single file.
	 * 
	 * @param sourceFile The full path to source file to compress.
	 * @param destinationFile The full path to the destination gzip file.
	 */
	public GzipCompressor(String sourceFile,String destinationFile){
		try{
			GZIPOutputStream zout=new GZIPOutputStream(new FileOutputStream(new File(destinationFile)));
			byte buffer[]=new byte[BUFFER_LENGTH];
			int length;
			FileInputStream fin=new FileInputStream(sourceFile);
			/* write entry to gz file */
			while((length=fin.read(buffer))>-1){
				zout.write(buffer,0,length);
			}
			fin.close();
			zout.finish();
			zout.close();
		} catch(Exception x){
			x.printStackTrace();
		}
	}
}