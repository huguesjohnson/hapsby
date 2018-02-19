/*
GzipCompressor.java - Gzip compressor
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
