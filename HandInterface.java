// Details for these methods can be found on Hand.java

public interface HandInterface extends Comparable {

   public void addCard( Card card );

   public Card getCard( int index ) ;

   public Card removeCard( Card card );

   public Card removeCard( int index );

   public int getNumberOfCards();

   public void sort();

   public boolean isEmpty();

   public boolean containsCard( Card card ) ;

   public int findCard( Card card ) ;

   public Card [] findSet( );

   public int compareTo( Object otherHandObject );

   public int evaluateHand();

   public String toString();

}
