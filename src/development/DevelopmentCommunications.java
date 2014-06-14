package development;

import java.util.ArrayList;
import java.io.*;

import trade.*;

public class DevelopmentCommunications {
	
	private ArrayList<Order> devTrade;
	
	public DevelopmentCommunications()
	{
		devTrade = new ArrayList<Order>();
		devTrade.add(new LimitTrade(100,2, true));
		devTrade.add(new LimitTrade(180,3, true));
		devTrade.add(new LimitTrade(100,1, true));
		devTrade.add(new MarketTrade(1,false));
		devTrade.add(new LimitTrade(20,20, true));
		devTrade.add(new LimitTrade(80,10, true));
		
		LimitTrade tx =(LimitTrade) devTrade.get(0);
		String txID = tx.gettxID();
		boolean bs = tx.getBuySell();
		
		devTrade.add(new CloseLimitTrade(txID, bs));
	}
	
	public void getBuffer(ArrayList<Order> ret)
	{
		if (devTrade.size() > 0)
		{
			ret.add(devTrade.get(0));
			devTrade.remove(0);
		}
	}
	
	public void writeBuffer(ArrayList<Order> matchedTrades, ArrayList<Order> cancelledTrades) // Should for now output to a file.
	{
		
	}
}