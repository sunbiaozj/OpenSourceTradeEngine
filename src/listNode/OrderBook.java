package listNode;

import java.util.ArrayList;	// To create the Array list of orders
import java.util.*;

import trade.*;	// Object to populate the ArrayList

public class OrderBook {
	
	private ArrayList <LimitTrade> OrderList = new ArrayList <LimitTrade>();
	private ArrayList <LimitTrade> MatchedTrades = new ArrayList <LimitTrade>();
	private boolean buysell;	//Holds whether the order book is a buy or sell order book, true is buy, false is sell
	
	public OrderBook(boolean bs)
	{
		buysell = bs;
	}
	
	public void addTrade(LimitTrade newTrade)
	{
		OrderList.add(0, newTrade);
		Collections.sort(OrderList);
	}
	
	public void addTrade(MarketTrade marketTrade)
	{
		int tradeSize = marketTrade.getAmount();
		ArrayList<Trade> split;
		if (tradeSize > OrderList.get(0).getAmount())	// Market Order is bigger than the best bid/ask
		{
			 split = marketTrade.splitTrade(marketTrade, OrderList.get(0).getAmount());
			 MatchedTrades.add(0,OrderList.get(0));
			 OrderList.remove(0);
			 MatchedTrades.get(0).Match((MarketTrade)split.get(0));
			 addTrade((MarketTrade)split.get(1));
		}
		else if (tradeSize < OrderList.get(0).getAmount())	// Market Order is smaller than the best bid/ask
		{
			split = OrderList.get(0).splitTrade(OrderList.get(0), tradeSize);
			MatchedTrades.add(0,(LimitTrade)split.get(0));
			OrderList.remove(0);
			OrderList.add((LimitTrade)split.get(1));
			MatchedTrades.get(0).Match(marketTrade);
			
		}
		else	// Market Order matches the size of the best bid/ask
		{
			MatchedTrades.add(0,OrderList.get(0));
			OrderList.remove(0);
			MatchedTrades.get(0).Match(marketTrade);
		}
	}
	
	public boolean getBuySell()
	{
		return buysell;
	}
	
	public String toStringTemp()
	{
		String ret = "";
		
		for( int i = 0; i < OrderList.size(); i++)
		{
			ret = ret + "[Price = " + OrderList.get(i).getPrice() + ", Amount = " + OrderList.get(i).getAmount() + "]";
		}
		
		return ret;
	}

}
