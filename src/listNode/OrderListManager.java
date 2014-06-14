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
					try
					{
						buyBook.addTrade((CloseLimitTrade) buff.get(i));
					}
					catch(TradeNotFoundException ex)
					{
						System.out.println(ex.getMessage());
						System.exit(0);
					}
				}
				buff.remove(i);
			}
			System.out.println("Buy Book:" + buyBook.toStringOB());
			System.out.println("Matched Trades:" + buyBook.toStringMatched());
			
		}
	}
}