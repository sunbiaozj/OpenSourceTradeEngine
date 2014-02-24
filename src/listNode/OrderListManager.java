package listNode;

import trade.*; //imports the trades package
import java.util.ArrayList;

public class OrderListManager {
	
	private static Communications com = new Communications();
	private static ArrayList<Trade> buff = new ArrayList<Trade>(); 
	
	public static void main(String[] args) 
	{
		OrderBook buyBook = new OrderBook(true);
		
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
				buff.remove(i);
			}
			System.out.println("Buy Book" + buyBook.toStringTemp());
		}
	}
}