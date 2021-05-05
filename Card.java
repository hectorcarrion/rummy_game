import javax.swing.*;

public class Card implements Comparable {

   // instance variables for the card
   private char suitValue;
   private char rankValue;
   final static char[] suit = {'c','d','h','s'};
   final static char [] rank = {'a','2','3','4','5','6','7','8','9','t','j','q','k'};


   private ImageIcon cardImage;

   static String directory = "cards/";


  /**
   * Creates a new playing card.
   * @param suit the suit value of this card.
   * @param rank the rank value of this card.
   * @param cardFace the face image of this card.
   */
   public Card( char suit, char rank, ImageIcon cardFace ) {
      cardImage = cardFace;
      suitValue = suit;
      rankValue = rank;
	  }


   public Card(char suit,char rank)
   {
	   suitValue = suit;
	   rankValue = rank;
	   cardImage = new ImageIcon(getImageFile());
   }

   public static int getSuitIndex(char suit)
   {

					switch(suit){
					case 'c':
						return 0;

					case 'd':
						return 1;

					case 'h':
						return 2;

					case 's':
						return 3;
				}
				return -1;

	}

	public static int getRankIndex(char rank)
	{
					switch (rank){
					case 'a':
						return 0;
					case '2':
					case '3':
					case '4':
					case '5':
					case '6':
					case '7':
					case '8':
					case '9':
						return rank - '1';
					case 't':
						return 9;
					case 'j':
						return 10   ;
					case 'q':
						return 11;
					case 'k':
						return 12;
					default:
						return -1;

				}
	}


	public String getImageFile(){
		return directory + toString() + ".gif";
	}


  /**
   * Returns the suit of the card.
   * @return a Suit constant representing the suit value of the card.
   */
   public char getSuit() {
      return suitValue;
   }


  /**
   * Returns the rank of the card.
   * @return a Rank constant representing the rank value of the card.
   */
   public char getRank() {
      return rankValue;
   }


  /**
   * Returns the graphic image of the card.
   * @return an icon containing the graphic image of the card.
   */
   public ImageIcon getCardImage() {
      return cardImage;
   }


  /**
   * Returns a description of this card.
   * @return the name of the card.
   */
   public String toString() {
		return "" + getRank() + getSuit();
   }


  /**
   * Compares two cards for the purposes of sorting.
   * Cards are ordered by their
   * rank value.
   * @param otherCardObject the other card
   * @return a negative integer, zero, or a positive integer is this card is
   * less than, equal to, or greater than the referenced card.
   */
   public int compareTo( Object otherCardObject ) {
     Card otherCard = (Card) otherCardObject;
	  int rankDiff = getRankIndex(rankValue) - getRankIndex(otherCard.rankValue) ;
	  return rankDiff;
   }



}