/*
Hapsby - universal save game editor
SplashScreen.java - simple splash screen
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