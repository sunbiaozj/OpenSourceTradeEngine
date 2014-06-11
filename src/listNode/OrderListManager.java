package listNode;

import trade.*; //imports the trades package
import customExceptions.*;
import java.util.ArrayList;

public class OrderListManager {
	
	private static Communications com = new Communications();
	private static ArrayList<Order> buff = new ArrayList<Order>(); 
	
	public static void main(String[] args) 
	{
		OrderBook buyBook = new OrderBook(false);
		
		boolean t = true;
		while (t)
		{
			com.getBuffer(buff);
			for(int i = 0; i < buff.size();i++)
			{
				try 
				{
					if(buff.get(i) instanceof MarketTrade)
					{
						buyBook.addTrade((MarketTrade) buff.get(i));
					}
					else if(buff.get(i) instanceof LimitTrade)
					{
						buyBook.addTrade((LimitTrade) buff.get(i));
					}
					else if(buff.get(i) instanceof CloseLimitTrade)
					{
						buyBook.addTrade((CloseLimitTrade) buff.get(i));
					}
					buff.remove(i);
				}
				catch (TradeNotFoundException ex)
				{
					System.out.println(ex.getMessage());
					System.exit(0);
				}
			}
			System.out.println("Buy Book:" + buyBook.toStringOB());
			System.out.println("Matched Trades:" + buyBook.toStringMatched());
			
		}
	}
}