import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
/**
*	This GUI assumes that you are using a 52 card deck and that you have 13 sets in the deck.
*	The GUI is simulating a playing table
	@author Patti Ordonez
*/
public class Table extends JFrame implements ActionListener
{
	final static int numDealtCards = 9;

/**
*	Variables creating for keeping count of each players turn
*
*/

	int playerturn = 0;
	int playerlayStack = 0;
	int playerlay = 0;


	JPanel player1;
	JPanel player2;
	JPanel deckPiles;
	JLabel deck;
	JLabel stack;
	JList p1HandPile;
	JList p2HandPile;
	Deck cardDeck;
	Stack stackDeck;

	SetPanel [] setPanels = new SetPanel[13];
	JLabel topOfStack;
	JLabel deckPile;
	JButton p1Stack;
	JButton p2Stack;

	JButton p1Deck;
	JButton p2Deck;

	JButton p1Lay;
	JButton p2Lay;

	JButton p1LayOnStack;
	JButton p2LayOnStack;


	Hand p1Hand;//I edited this
	Hand p2Hand;//I edited this




	private void deal(Card [] cards)
	{
		for(int i = 0; i < cards.length; i ++)
			cards[i] = (Card)cardDeck.dealCard();
	}

	public Table()
	{
		super("HLL Project 2 - Hector Carrion :)");

		setLayout(new BorderLayout());
		setSize(1200,700);


		cardDeck = new Deck();
		/**
		 * Iterate over arrays of rank and suit from card class.
		 * Creates every card for the deck, adds them and shuffles them.
		 */
		for(int i = 0; i < Card.suit.length; i++){
			for(int j = 0; j < Card.rank.length; j++){
				Card card = new Card(Card.suit[i],Card.rank[j]);
				cardDeck.addCard(card);
			}
		}
		cardDeck.shuffle();
		stackDeck = new Stack(52);

		JPanel top = new JPanel();

		for (int i = 0; i < Card.rank.length;i++)
			setPanels[i] = new SetPanel(Card.getRankIndex(Card.rank[i]));


		top.add(setPanels[0]);
		top.add(setPanels[1]);
		top.add(setPanels[2]);
		top.add(setPanels[3]);

		player1 = new JPanel();

		player1.add(top);




		add(player1, BorderLayout.NORTH);
		JPanel bottom = new JPanel();


		bottom.add(setPanels[4]);
		bottom.add(setPanels[5]);
		bottom.add(setPanels[6]);
		bottom.add(setPanels[7]);
		bottom.add(setPanels[8]);

		player2 = new JPanel();




		player2.add(bottom);
		add(player2, BorderLayout.SOUTH);


		JPanel middle = new JPanel(new GridLayout(1,3));

		/**
		 * creates the buttons to play
		 *
		 */
		p1Stack = new JButton("Stack");
		p1Stack.addActionListener(this);
		p1Deck = new JButton("Deck ");
		p1Deck.addActionListener(this);
		p1Lay = new JButton("Lay   ");
		p1Lay.addActionListener(this);
		p1LayOnStack = new JButton("Lay on stack");
		p1LayOnStack.addActionListener(this);
		//LayOnSet

		Card [] cardsPlayer1 = new Card[numDealtCards];
		deal(cardsPlayer1);
		p1Hand = new Hand();
		for(int i = 0; i < cardsPlayer1.length; i++)
			p1Hand.addCard(cardsPlayer1[i]);
		p1HandPile = new JList(p1Hand.hand);


		middle.add(new HandPanel("Player 1", p1HandPile, p1Stack, p1Deck, p1Lay, p1LayOnStack));

		deckPiles = new JPanel();
		deckPiles.setLayout(new BoxLayout(deckPiles, BoxLayout.Y_AXIS));
		deckPiles.add(Box.createGlue());
		JPanel left = new JPanel();
		left.setAlignmentY(Component.CENTER_ALIGNMENT);

		//Pile stack created
		stack = new JLabel("Stack");
		stack.setAlignmentY(Component.CENTER_ALIGNMENT);

		left.add(stack);
		topOfStack = new JLabel();
		topOfStack.setIcon(new ImageIcon(Card.directory + "blank.gif"));
		topOfStack.setAlignmentY(Component.CENTER_ALIGNMENT);
		left.add(topOfStack);
		deckPiles.add(left);
		deckPiles.add(Box.createGlue());

		JPanel right = new JPanel();
		right.setAlignmentY(Component.CENTER_ALIGNMENT);

		//deck created
		deck = new JLabel("Deck");

		deck.setAlignmentY(Component.CENTER_ALIGNMENT);
		right.add(deck);
		deckPile = new JLabel();
		deckPile.setIcon(new ImageIcon(Card.directory + "cardback.png"));
		deckPile.setAlignmentY(Component.CENTER_ALIGNMENT);
		right.add(deckPile);
		deckPiles.add(right);
		deckPiles.add(Box.createGlue());
		middle.add(deckPiles);


		p2Stack = new JButton("Stack");
		p2Stack.addActionListener(this);
		p2Deck = new JButton("Deck ");
		p2Deck.addActionListener(this);
		p2Lay = new JButton("Lay  ");
		p2Lay.addActionListener(this);
		p2LayOnStack = new JButton("Lay on stack");
		p2LayOnStack.addActionListener(this);

		Card [] cardsPlayer2 = new Card[numDealtCards];
		deal(cardsPlayer2);
		p2Hand = new Hand();

		for(int i = 0; i < cardsPlayer2.length; i++)
			p2Hand.addCard(cardsPlayer2[i]);

		p2HandPile = new JList(p2Hand.hand);

		middle.add(new HandPanel("Player 2", p2HandPile, p2Stack, p2Deck, p2Lay, p2LayOnStack));

		add(middle, BorderLayout.CENTER);

		JPanel leftBorder = new JPanel(new GridLayout(2,1));


		setPanels[9].setLayout(new BoxLayout(setPanels[9], BoxLayout.Y_AXIS));
		setPanels[10].setLayout(new BoxLayout(setPanels[10], BoxLayout.Y_AXIS));
		leftBorder.add(setPanels[9]);
		leftBorder.add(setPanels[10]);
		add(leftBorder, BorderLayout.WEST);

		JPanel rightBorder = new JPanel(new GridLayout(2,1));

		setPanels[11].setLayout(new BoxLayout(setPanels[11], BoxLayout.Y_AXIS));
		setPanels[12].setLayout(new BoxLayout(setPanels[12], BoxLayout.Y_AXIS));
		rightBorder.add(setPanels[11]);
		rightBorder.add(setPanels[12]);
		add(rightBorder, BorderLayout.EAST);


		p1Hand.sort();
		p2Hand.sort();
	}


	public void actionPerformed(ActionEvent e)
	{	//Adds cards to players hand by clicking deck button
		Object src = e.getSource();

		if(p1Deck == src && ((playerturn % 2) == 0) && (playerlayStack == playerturn)){
			playerturn++;
			Card card = cardDeck.dealCard();

			if (card != null){
				if(src == p1Deck)
					p1Hand.addCard(card);
			}
			if(cardDeck.getSizeOfDeck() == 0)
				deckPile.setIcon(new ImageIcon(Card.directory + "blank.gif"));
			p1Hand.sort();

			//Endgame Deck
			if(cardDeck.isEmpty()){
				if(p1Hand.compareTo(p2Hand) > 0){
					System.out.println("Player 1 Wins!");
				}
				else if(p1Hand.compareTo(p2Hand) == 0){
					System.out.println("It's a tie!");
				}
				else{
					System.out.println("Player 2 Wins!");
				}

			}

		}

		if(p2Deck == src && ((playerturn % 2) == 1) && (playerlayStack == playerturn)){
			playerturn++;
			Card card = cardDeck.dealCard();

			if(card != null){
				if(src == p2Deck)
					p2Hand.addCard(card);

			}
			if(cardDeck.getSizeOfDeck() == 0)
				deckPile.setIcon(new ImageIcon(Card.directory + "blank.gif"));
			p2Hand.sort();
		}

		if(p1Stack == src && (playerturn % 2 == 0) ){

			Card card = stackDeck.Peek();

			if(card != null){
				playerturn++;
				Card topCard = stackDeck.Pop();
				if (stackDeck.isEmpty() != true)
					topOfStack.setIcon(topCard.getCardImage());
				else
					topOfStack.setIcon(new ImageIcon(Card.directory + "blank.gif"));

				if(p1Stack == src)
					p1Hand.addCard(card);
			}
			p1Hand.sort();

		}

		if(p2Stack == src && playerturn % 2 == 1){


			Card card = stackDeck.Peek();

			if(card != null){
				playerturn++;
				Card topCard = stackDeck.Pop();
				if (stackDeck.isEmpty() != true)
					topOfStack.setIcon(topCard.getCardImage());
				else
					topOfStack.setIcon(new ImageIcon(Card.directory + "blank.gif"));

				if(p2Stack == src)
					p2Hand.addCard(card);


			}
			p2Hand.sort();

		}

		if(p1Lay == src){
			Object [] cards = p1Hand.findSet();
			if (cards != null){
				for(int i = 0; i < cards.length; i++)
				{
					Card card = (Card)cards[i];
					layCard(card);
					p1Hand.removeCard(card);
				}
			}
			if(p1Hand.GameOver()){
				if(p1Hand.compareTo(p2Hand) > 0){
					System.out.println("Player 1 Wins!");
				}
				else if(p1Hand.compareTo(p2Hand) == 0){
					System.out.println("It's a tie!");
				}
				else{
					System.out.println("Player 2 Wins!");
				}
			}

			if(cardDeck.isEmpty()){
				if(p1Hand.compareTo(p2Hand) > 0){
					System.out.println("Player 1 Wins!");
				}
				else if(p1Hand.compareTo(p2Hand) == 0){
					System.out.println("It's a tie!");
				}
				else{
					System.out.println("Player 2 Wins!");
				}

			}
		}


		if(p2Lay == src){
			Object [] cards = p2Hand.findSet();
			if (cards != null){
				for(int i = 0; i < cards.length; i++)
				{
					Card card = (Card)cards[i];
					layCard(card);
					p2Hand.removeCard(card);
				}
			}
			//Endgame Lay
			if(p2Hand.GameOver()){
				if(p1Hand.compareTo(p2Hand) > 0){
					System.out.println("Player 1 Wins!");
				}
				else if(p1Hand.compareTo(p2Hand) == 0){
					System.out.println("It's a tie!");
				}
				else{
					System.out.println("Player 2 Wins!");
				}
			}

		}


		if(p1LayOnStack == src && (playerlayStack % 2 == 0) && (playerlayStack < playerturn)){
			playerlayStack++;
			int [] num  = p1HandPile.getSelectedIndices();
			if (num.length == 1)
			{
				Object obj = p1HandPile.getSelectedValue();
				if (obj != null)
				{
					p1Hand.removeObj(obj);
					Card card = (Card)obj;
					stackDeck.Push(card);
					topOfStack.setIcon(card.getCardImage());
				}

				//CPU

				//CPU Draw
				p2Hand.sort();
				playerturn++;
				playerlayStack++;
				Card CPUcard = cardDeck.dealCard();
				if (CPUcard != null){
					p2Hand.addCard(CPUcard);
				}
				if(cardDeck.getSizeOfDeck() == 0){
					deckPile.setIcon(new ImageIcon(Card.directory + "blank.gif"));
				}
				p2Hand.sort();

				//CPU Lay Sets
				Object [] CPUcards = p2Hand.findSet();
				if (CPUcards != null){
					for(int i = 0; i < CPUcards.length; i++)
					{
						Card CPUlaycard = (Card)CPUcards[i];
						layCard(CPUlaycard);
						p2Hand.removeCard(CPUlaycard);
					}
				}

				//CPU Discard

				Card ccard = p2Hand.getCard(0);
				stackDeck.Push(ccard);
				topOfStack.setIcon(ccard.getCardImage());
				p2Hand.removeCard(ccard);


			}

		}
			//Endgame Lay
			if(p1Hand.GameOver()){
				if(p1Hand.compareTo(p2Hand) < 0){
					System.out.println("Player 1 Wins!");
				}
				else if(p1Hand.compareTo(p2Hand) == 0){
					System.out.println("It's a tie!");
				}
				else{
					System.out.println("Player 2 Wins!");
				}
			}
			//Endgame Deck
			if(cardDeck.isEmpty()){
				if(p1Hand.compareTo(p2Hand) < 0){
					System.out.println("Player 1 Wins!");
				}
				else if(p1Hand.compareTo(p2Hand) == 0){
					System.out.println("It's a tie!");
				}
				else{
					System.out.println("Player 2 Wins!");
				}

			}



		if(p2LayOnStack == src && (playerlayStack % 2 == 1) && (playerlayStack < playerturn)){
			playerlayStack++;
			int [] num  = p2HandPile.getSelectedIndices();
			if (num.length == 1)
			{
				Object obj = p2HandPile.getSelectedValue();
				if (obj != null)
				{

					p2Hand.removeObj(obj);
					Card card = (Card)obj;
					stackDeck.Push(card);
					topOfStack.setIcon(card.getCardImage());
				}
			}
			//Endgame Lay
			if(p2Hand.GameOver()){
				if(p1Hand.compareTo(p2Hand) > 0){
					System.out.println("Player 1 Wins!");
				}
				else if(p1Hand.compareTo(p2Hand) == 0){
					System.out.println("It's a tie!");
				}
				else{
					System.out.println("Player 2 Wins!");
				}
			}
			//Endgame Deck
			if(cardDeck.isEmpty()){
				if(p1Hand.compareTo(p2Hand) > 0){
					System.out.println("Player 1 Wins!");
				}
				else if(p1Hand.compareTo(p2Hand) == 0){
					System.out.println("It's a tie!");
				}
				else{
					System.out.println("Player 2 Wins!");
				}

			}
		}
	}

	public static void main(String args[])
	{
		Table t = new Table();
		t.setVisible(true);
	}
	void layCard(Card card)
	{
		char rank = card.getRank();
		char suit = card.getSuit();
		int suitIndex =  Card.getSuitIndex(suit);
		int rankIndex =  Card.getRankIndex(rank);
		//setPanels[rankIndex].array[suitIndex].setText(card.toString());
		System.out.println("laying " + card);
		setPanels[rankIndex].array[suitIndex].setIcon(card.getCardImage());
	}

}

class HandPanel extends JPanel
{

	public HandPanel(String name,JList hand, JButton stack, JButton deck, JButton lay, JButton layOnStack)
	{
		//model = hand.createSelectionModel();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//		add(Box.createGlue());
		JLabel label = new JLabel(name);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(label);
		stack.setAlignmentX(Component.CENTER_ALIGNMENT);
//		add(Box.createGlue());
		add(stack);
		deck.setAlignmentX(Component.CENTER_ALIGNMENT);
//		add(Box.createGlue());
		add(deck);
		lay.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lay);
		layOnStack.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(layOnStack);
		add(Box.createGlue());
		add(hand);
		add(Box.createGlue());
	}

}
class SetPanel extends JPanel
{
	private Set data;
	JButton [] array = new JButton[4];

	public SetPanel(int index)
	{
		super();
		data = new Set(Card.rank[index]);

		for(int i = 0; i < array.length; i++){
			array[i] = new JButton("   ");
			add(array[i]);
		}
	}

}
