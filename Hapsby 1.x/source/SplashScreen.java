/*
Hapsby - universal save game editor
SplashScreen.java - simple splash screen
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;

/** class SplashScreen loads a borderless window containing a single image.
* @author Hugues Johnson
*/
public class SplashScreen extends Window{
     /* the image to display */
     private Image splashImage;
     /** Constructor for SplashSceen creates a borderless window and paints a specified image.
     * @param parent the parent window, required to extend java.awt.Window
     * @param splashImageName name (path) of the image to load
     */
     public SplashScreen(Frame parent, String splashImageName){
          super(parent);
          /* use MediaTracker to make sure image loads before trying to display */
          MediaTracker mt = new MediaTracker(this);
          /* load splash image */
          this.splashImage = Toolkit.getDefaultToolkit().getImage(splashImageName);
          /* add image to media tracker */
          mt.addImage(this.splashImage,0);
          /* wait for image to load */
          try{
               mt.waitForID(0);
          } catch(InterruptedException x){
               System.err.println(x.toString());
          }
          /* set the size of the window to the size of the image */
          int height=this.splashImage.getHeight(this);
          int width=this.splashImage.getWidth(this);
          this.setSize(width,height);
          /* center the window */
          Dimension screenDimension=Toolkit.getDefaultToolkit().getScreenSize();
          Rectangle windowDimension=this.getBounds();
          this.setLocation((screenDimension.width-windowDimension.width)/2,(screenDimension.height-windowDimension.height)/2);
          /* show the window */
          this.setVisible(true);
     }

     /** toString returns a String representation of this object
      * @return String representation of this object
      */
     public String toString(){
          String tostring=new String(super.toString());
          return(tostring);
     }     
     
     /** paint draws the image onto the window
     * @param g graphics object to paint
     */
     public void paint(Graphics g){
          if (this.splashImage!=null){
               g.drawImage(this.splashImage,0,0,this);
          }
     }
}
