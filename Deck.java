import java.util.*;

public class Deck {
    private int top;
    private ArrayList<Card> deck;

    /**
    * Creates an empty deck of cards.
    */
    public Deck(){
        this.deck = new ArrayList<Card>();
    }
    /**
    *  returns next Card in a deck that is facing down without removing it
    */
    public Card peek(){
        this.top = deck.size();
        return deck.get(top-1);
    }
    /**
    * this method is used to add Cards to a Deck.
		* since the Deck is completely empty when it is initialized.
    */

    public void addCard(Card card){
        deck.add(card);
    }

    /**
    * returns number of cards on the deck
    * @return int
    */
    public int getSizeOfDeck(){
        return deck.size();
    }

    /**
    * removes first card on a deck so equivalent to flipping
		* the card off of a deck that is faced down
    * @return <code>null</code> if there are no cards left on the Stack.
		* Otherwise returns Card
    */
    public Card dealCard(){
        this.top = deck.size();
        return deck.remove(deck.size() - 1);

    }

    /**
    * removes Card last card placed on a deck so equivalent to removing
		* card from deck that is faced up
    * @return <code>null</code> if there are no cards left on the deck.
		* Otherwise removes Card
    */
    public Card removeCard(){
        this.top = deck.size();
        return deck.remove(deck.size() - 1);
    }

    /**
    * Shuffles the cards present in the deck.
    */
    public void shuffle(){
        Collections.shuffle(deck);
    }
    /**
    * Looks for an empty deck.
    * @return <code>true</code> if there are no cards left to be dealt from the deck.
    */
    public boolean isEmpty(){
        return deck.isEmpty();
    }

    /**
    * Restores the deck to being empty and ready to add Cards to
    */
    public void restoreDeck(){
        deck.clear();
    }


}
