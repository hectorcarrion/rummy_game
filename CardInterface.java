// Details for these methods can be found on Card.java

import javax.swing.*;

public interface CardInterface extends Comparable {


   final static char[] suit = {'c','d','h','s'};
   final static char [] rank = {'a','2','3','4','5','6','7','8','9','t','j','q','k'};
   final public static String directory = "cards/";

	public String getImageFile();

   public char getSuit();

   public char getRank();

   public ImageIcon getCardImage();

   public String toString();

   public int compareTo( Object otherCardObject );


}
