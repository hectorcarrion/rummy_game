/**
 * Represents the basic functionality of a hand of cards.
 * Extensions of this class will provide the
 * definition of what constitutes a hand for that game and how hands are compared
 * to one another by overriding the <code>compareTo</code> method.
 */

import javax.swing.*;

public class Hand implements Comparable<Object>{
    public DefaultListModel<Card> hand;

    public Hand(){
        this.hand = new DefaultListModel<>();
    }

		/**
		 * Adds a card to this hand.
		 * @param card card to be added to th e current hand.
		 */
    public void addCard(Card card){
        hand.addElement(card);
    }


	  /**
	   * Obtains the card stored at the specified location in the hand.  Does not
	   * remove the card from the hand.
	   * @param index position of card to be accessed.
	   * @return the card of interest, or the null reference if the index is out of
	   * bounds.
	   */
    public Card getCard(int index){
        return hand.get(index);
    }

		/**
	   * Removes the specified card from the current hand.
	   * @param card the card to be removed.
	   * @return the card removed from the hand, or null if the card
	   * was not present in the hand.
	   */
    public Card removeCard(Card card){
        Card c = card;
        hand.removeElement(card);
        return c;

    }

		/**
	   * Removes the card at the specified index from the hand.
	   * @param index poisition of the card to be removed.
	   * @return the card removed from the hand, or the null reference if
	   * the index is out of bounds.
	   */
    public Card removeCard(int index){
        return hand.remove(index);
    }

		// Skip the BS and remove the object directly
		public void removeObj(Object obj) {
        hand.removeElement(obj);
    }

		/**
	   * The number of cards held in the hand.
	   * @return number of cards currently held in the hand.
	   */
    public int getNumberOfCards(){
        return hand.size();
    }

		/**
	   * Sorts the card in the hand.
	   * Sort is performed according to the order specified in the {@link Card} class.
	   */
    public void sort(){

        int n = hand.size();
        for (int i = 0; i < n-1; i++){
            for (int j = 0; j < n-i-1; j++){
                if (hand.get(j).compareTo(hand.get(j+1)) > 0)
                {

                    Card temp = hand.get(j);
                    hand.set(j, hand.get(j+1));
                    hand.set(j+1, temp);
                }
            }

        }
        System.out.print(hand.toString() + " ");
        System.out.println();

    }

		/**
	   * Determines whether or not the hand contains the specified card.
	   * @param card the card being searched for in the hand.
	   * @return <code>true</code> if the card is present in the hand.
	   */
    public boolean containsCard(Card card){
        return hand.contains(card);
    }

		/**
	   * Searches for the first instance of the specified card in the hand.
	   * @param card card being searched for.
	   * @return position index of card if found, or <code>-1</code> if not found.
	   */
    public int findCard(Card card){
        return hand.indexOf(card);
    }

    public int cardRank(int index){
        return hand.get(index).getRankIndex(hand.get(index).getRank());
    }

    public boolean GameOver(){
        return hand.isEmpty();
    }

		/**
	   * Searches for the first instance of a set (3 or 4 Cards of the same rank) in the hand.
	   * @return  returns Card [] of Cards found in hand or <code>-null </code> if not found.
	   */
    public Card [] findSet(){
        for (int i = 0; i < hand.size(); i++) {
            for (int j = i+1; j < hand.size(); j++) {
                if(j + 1 < hand.size()){
                    if((this.cardRank(i) == this.cardRank(j)) && (this.cardRank(i) == this.cardRank(j+1))){
                        Card [] cardSet = new Card[3];
                        cardSet[0] = hand.get(i);
                        cardSet[1] = hand.get(j);
                        cardSet[2] = hand.get(j+1);

                        if(j + 2 < hand.size()){
                            if(this.cardRank(i) == this.cardRank(j+2)){
                                cardSet = new Card[4];
                                cardSet[0] = hand.get(i);
                                cardSet[1] = hand.get(j);
                                cardSet[2] = hand.get(j+1);
                                cardSet[3] = hand.get(j+2);

                                return cardSet;
                            }
                        }
                        return cardSet;
                    }
                }
            }
        }
        return null;
    }

		/**
	   *  Compares two hands.
	   *  @param otherHandObject the hand being compared.
	   *  @return < 0 if this hand is less than the other hand, 0 if the two hands are
	   *  the same, or > 0 if this hand is greater then the other hand.
	   */
    public int compareTo(Object otherHandObject){
        Hand otherHand = (Hand) otherHandObject;
        Card P1, P2;
        int pointsP1 = 0, pointsP2 = 0;
        for (int i = 0; i < hand.size(); i++) {
            P1 = hand.get(i);
            pointsP1 = pointsP1 + P1.getRankIndex(P1.getRank());
        }
        for (int i = 0; i < otherHand.getNumberOfCards(); i++) {
            P2 = otherHand.getCard(i);
            pointsP2 = pointsP2 + P2.getRankIndex(P2.getRank());
        }
       System.out.println(pointsP1 - pointsP2);
       return pointsP1 - pointsP2;

    }

		/**
			* Returns a description of the hand.
			* @return a list of cards held in the hand.
			*/
    public String toString(){
        Card c;
        String cards = "";

        for (int i = 0; i < hand.size(); i++) {
            c = hand.get(i);
            cards = cards + "" + c.getRank() + c.getSuit();
        }
        return cards;
    }

}
