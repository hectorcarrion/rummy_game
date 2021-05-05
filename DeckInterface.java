// Details for these methods can be found on Deck.java

public interface DeckInterface {

  public Card Deck();

	public Card peek();

  public void addCard( Card card ) ;

  public int getSizeOfDeck() ;

  public Card dealCard() ;

  public Card removeCard() ;

  public void shuffle() ;

  public boolean isEmpty();

  public void restoreDeck() ;

}
