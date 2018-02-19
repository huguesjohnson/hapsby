/*
Hapsby - universal save game editor
ZipDecompressor.java - Zip decompressor
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
