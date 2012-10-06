/*
Hapsby - universal save game editor
ZipDecompressor.java - Zip decompressor
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
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;

/** Class ZipDecompressor, very basic class to extract a .zip archive.
* @author Hugues Johnson
*/
public class ZipDecompressor{
     /* default buffer length */
     private final int BUFFER_LENGTH=1024;
     /** Constructor extracts all files from a .zip archive.
     * @param fileName the archive to decompress
     */
     public ZipDecompressor(String fileName){
          try{
               ZipInputStream zin=new ZipInputStream(new FileInputStream(new File(fileName)));
               ZipEntry entry=zin.getNextEntry();
               while(entry!=null){
                    byte buffer[]=new byte[BUFFER_LENGTH];
                    int length;
                    /* write file */
                    FileOutputStream fout=new FileOutputStream(new File(entry.getName()));
                    while((length=zin.read(buffer))>-1){
                         fout.write(buffer,0,length);
                    }
                    zin.closeEntry();
                    fout.close();
                    entry=zin.getNextEntry();
               }
               zin.close();
          } catch(Exception x){
               x.printStackTrace();
          }
     }
}