package trade;

import java.util.Random; //Needed to create transaction ID
import java.util.ArrayList;

/*	This class is the superclass of all trades and at this point only has the 
*	amount of the trade in it and a method for subclasses to retrieve 
*/
public class Trade {
	
	private int amount; 	// Holds the size of the trade
	private String txID;	// Holds the trade ID in txID
	private boolean buysell;	// Holds whether the trade is a buy (1) or a sell (0)
	private boolean split;	// Holds whether a trade was split (and therefore has a sibling with the same txID or not (0) no, (1) yes.
	
	public Trade(int Amount, boolean BuySell) //Constructor sets the Amount and creates the txID
	{
		amount = Amount;
		buysell = BuySell;
		settxID(Amount);	// Calls private settxID method
		split = false;
	}
	
	// This method takes in a trade and an amount and returns an ArrayList of Trade with the first element
	// being a new trade (with the same txID) of the amount passed, and the second element of the ArrayList
	// being a new trade (with the same txID) with it's amount being what is left over
	public ArrayList<Trade> splitTrade(MarketTrade trade, int amount)
	{
		Trade trade1 = new MarketTrade(amount, trade.getBuySell());
		Trade trade2 = new MarketTrade(trade.getAmount()-amount, trade.getBuySell());
		trade1.settxID(trade.gettxID());
		trade2.settxID(trade.gettxID());
		ArrayList<Trade> ret = new ArrayList<Trade>();
		ret.add(trade1);
		ret.add(trade2);
		return ret;
	}
	
	public ArrayList<Trade> splitTrade(int price, LimitTrade trade, int amount)
	{
		Trade trade1 = new LimitTrade(price ,amount, trade.getBuySell());
		Trade trade2 = new LimitTrade(price ,trade.getAmount()-amount, trade.getBuySell());
		trade1.settxID(trade.gettxID());
		trade2.settxID(trade.gettxID());
		ArrayList<Trade> ret = new ArrayList<Trade>();
		ret.add(trade1);
		ret.add(trade2);
		return ret;
	}
	
	private void settxID(int Amount) 	// Generates a random number to pad the amount for the txID
	{
		txID = String.valueOf(Amount); 	// Puts the amount at the beginning of the txID
		
		Random rand = new Random();
		while (txID.length() < 100)		// Loops until the length of the txID is 100
		{
			String buff = String.valueOf(rand.nextInt(9));
			txID = txID + buff; // Concatenates the new random int to the txID
		}
	}
	
	private void settxID(String newID)	// Sets the txID to the string passed
	{
		txID = newID;
		split = true;
		
	}
	
	public void setAmount(int Amount)
	{
		amount = Amount;
	}
	
	//	The Next methods are for giving access to the private variables of this class
	
	public String gettxID() //Returns the txID
	{
		return txID;
	}
	
	public int getAmount() //Returns the amount of the trade
	{
		return amount;
	}
	
	public boolean getBuySell()	// Returns whether the trade is a buy (1) or a sell (0)
	{
		return buysell;
	}
	
	public boolean getSplit()
	{
		return split;
	}
}
