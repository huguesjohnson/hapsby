/*
Hapsby - universal save game editor
ZipCompressor.java - Zip compressor
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