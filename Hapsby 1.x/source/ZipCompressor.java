/*
Hapsby - universal save game editor
ZipCompressor.java - Zip compressor
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/** Class ZipCompressor, very basic class to create a .zip archive.
 * @author Hugues Johnson
 */
public class ZipCompressor{
     /* default buffer length to use */
     private final static int BUFFER_LENGTH=1024;
     /** Constructor creates a zip archive containing a number of files. Highest compression level is used.
      * @param fileNames an array of all files to include in archive
      * @param destinationFile the destination archive
      */
     public ZipCompressor(String fileNames[],String destinationFile){
          try{
               ZipOutputStream zout=new ZipOutputStream(new FileOutputStream(new File(destinationFile)));
               zout.setMethod(ZipOutputStream.DEFLATED);
               for(int q=0;q<fileNames.length;q++){
                    File file=new File(fileNames[q]);
                    byte buffer[]=new byte[BUFFER_LENGTH];
                    int length;
                    /* generate CRC */
                    CRC32 crc=new CRC32();
                    FileInputStream fin=new FileInputStream(file);
                    while((length=fin.read(buffer))>-1){
                         crc.update(buffer,0,length);
                    }
                    fin.close();
                    /* create zip entry */
                    ZipEntry entry=new ZipEntry(fileNames[q]);
                    entry.setSize(file.length());
                    entry.setTime(file.lastModified());
                    entry.setCrc(crc.getValue());
                    zout.putNextEntry(entry);
                    fin=new FileInputStream(file);
                    /* write entry to zip file */
                    while((length=fin.read(buffer))>-1){
                         zout.write(buffer,0,length);
                    }
                    fin.close();
                    zout.closeEntry();
               }
               zout.close();
          } catch(Exception x){
               x.printStackTrace();
          }
     }
     
      /** toString returns a String representation of this object
      * @return String representation of this object
      */
     public String toString(){
          String tostring=new String(super.toString());
          return(tostring);
     }
}
