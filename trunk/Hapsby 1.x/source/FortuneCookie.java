/*
Hapsby - universal save game editor
FortuneCookie.java
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

import java.lang.Math;
import java.lang.String;

/** class FortuneCookie retrieves a random fortune from a list.
* All fortunes are from actual fortune cookies, spelling and grammatical errors were left intact.
* The array is stored in alphabetical order to help avoid duplicates.
*/
public abstract class FortuneCookie{
     final private static String[] fortunes={
          "A thrilling time is in your immediate future.",
          "A well-directed imagination is the source of great deeds.",
          "Back away from individuals who are impulsive.",
          "Be careful in whom you share your confidence",
          "Depart from the path which fate has you assigned.",
          "Do not do to others what you do not want done to yourself",
          "Don't be hasty, prosperity will knock on your door soon.",
          "Fame & fortune are in your stars.",
          "Find release from your cares, have a good time.",
          "From now on your kindness will lead you to success.",
          "Good news will come to you from far away.",
          "Hang in there, within sight is the rainbow",
          "It is better to be envied than to be pitied.",
          "Keep your feet on the ground even though friends flatter you.",
          "Many ideals are becoming real.",
          "More money and travel is in your future.",
          "No one conquers who doesn't fight.",
          "Now is a good time to finish up old tasks.",
          "Now is the time to try something new.",
          "Pennies from heaven find their way to your doorstep this year!",
          "Shopping expeditions should prove all you'd hoped for.",
          "Simplicity and clarity should be your theme in dress.",
          "Soon you will encounter a whole new world of opportunity.",
          "Strike iron while it is hot.",
          "That long-sought opportunity will soon arise.",
          "The heart is wiser than the intellect.",
          "The only good is knowledge and the only evil ignorance.",
          "The philosophy of one century is the common sense of the next.",
          "The tide of change approaches.",
          "The time is right to make new friends.",
          "Traveling with your family can be an enjoyment.",
          "Wise men learn more from fools, than fools from the wise.",
          "You are not one to make quick, rash decisions.",
          "You are the center of every group's attention.",
          "You believe in the goodness of mankind.",
          "You display the wonderful traits of charm and courtesy.",
          "You emerge victorious from the maze you've been traveling in.",
          "You have an important new business development shaping up.",
          "You love peace.",
          "You love sports, horses, and gambling but not to excess.",
          "You may attend a party where strange customs prevail.",
          "You will be fortunate in everything you put your hands to.",
          "You will be singled out for promotion.",
          "You will be unusually successful in business",
          "You will conquer obstacles to achieve success.",
          "You will have an enjoyable experience in the future.",
          "You will inherit a large sum of money.",
          "You will inherit some money or a small piece of land.",
          "You will never need to worry about a steady income.",
          "You will never regret the present, you live life to its fulliest!",
          "You will soon be crossing the great waters.",
          "You will travel far and wide, both pleasure and business.",
          "You will witness a special ceremony.",
          "Your charm and courtesy will be advantageous in goal.",
          "Your emotional nature is strong and sensitive.",
          "Your happiness is intertwined with your outlook on life.",
          "Your life will be happy and peaceful.",
          "Your long forgotten kindness to someone will bring a substantial sum of money.",
          "Your luck has been completely changed today.",
          "Your present plans are going to succeed.",
          "Your principles mean more to you than money or success."
     };

     /** getFortune retrieves a random fortune from a static array of fortunes.
     * @return fortune
     */      
     public final static String getFortune(){
          int max=fortunes.length-1; 
          int min=0; 
          int randomIndex=(int) Math.round(Math.random()*(max-min)+min);
          return(fortunes[randomIndex]);
     }
}