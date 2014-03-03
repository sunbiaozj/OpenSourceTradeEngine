package listNode;

import java.util.*;
import trade.*;	// Object to populate the ArrayList

public class OrderBook {
	
	private LinkedList <LimitTrade> OrderList;
	private ArrayList <LimitTrade> MatchedTrades;
	private ArrayList <CloseLimitTrade> CancelledTrades;
	private boolean buysell;	//Holds whether the order book is a buy or sell order book, true is buy, false is sell
	
	public OrderBook(boolean bs)
	{
		OrderList = new LinkedList <LimitTrade>();
		MatchedTrades = new ArrayList <LimitTrade>();
		buysell = bs;
	}
	
	public void addTrade(LimitTrade newTrade)
	{
		ListIterator<LimitTrade> itr = OrderList.listIterator();
		if( OrderList.size() == 0) {OrderList.add(newTrade);}
		else {
			while(itr.hasNext())
			{
				LimitTrade nxt = itr.next();
				if(nxt.compareTo(newTrade)==0 && newTrade.getSplit())
				{
					itr.previous();
					itr.add(newTrade);
					break;
				}
				else if (nxt.compareTo(newTrade)==0 && (!newTrade.getSplit()))
				{
					itr.add(newTrade);
					break;
				}
				else if (nxt.compareTo(newTrade)==1)
				{
					itr.previous();
					itr.add(newTrade);
					break;
				}
				if(!itr.hasNext())
				{
					itr.add(newTrade);
					break;
				}
			}
		}
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
			addTrade((LimitTrade)split.get(1));
			MatchedTrades.get(0).Match(marketTrade);
			
		}
		else	// Market Order matches the size of the best bid/ask
		{
			MatchedTrades.add(0,OrderList.get(0));
			OrderList.remove(0);
			MatchedTrades.get(0).Match(marketTrade);
		}
	}
	
	public void addTrade(CloseLimitTrade closeLimitTrade)
	{
		ListIterator<LimitTrade> itr = OrderList.listIterator();
		while(itr.hasNext())
		{
			LimitTrade next = itr.next();
			if (next.gettxID() == closeLimitTrade.gettxID())
			{
				itr.remove();
				CancelledTrades.add(closeLimitTrade)
				break;
			}
		}
	}
	
	public boolean getBuySell()
	{
		return buysell;
	}
	
	public String toStringOB()
	{
		String ret = "";
		
		for( int i = 0; i < OrderList.size(); i++)
		{
			ret = ret + "[Price = " + OrderList.get(i).getPrice() + ", Amount = " + OrderList.get(i).getAmount() + "]";
		}
		
		return ret;
	}
	
	public String toStringMatched()
	{
		String ret = "";
		
		for( int i = 0; i < MatchedTrades.size(); i++)
		{
			ret = ret + "[Trade 1 = " + MatchedTrades.get(i).gettxID();
			
			for (int j = 0; j < MatchedTrades.get(i).getMatched().size();j++)
			{
				int p = j + 2;
				ret = ret + ", Trade " + p + " = " + MatchedTrades.get(i).getMatched().get(j).gettxID();
			}
		}
		ret = ret + "]";
		return ret;
	}

}
