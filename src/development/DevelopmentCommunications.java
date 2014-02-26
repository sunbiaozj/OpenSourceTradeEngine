package development;

import java.util.ArrayList;

import trade.*;

public class DevelopmentCommunications {
	
	private ArrayList<Trade> devTrade;
	
	public DevelopmentCommunications()
	{
		devTrade = new ArrayList<Trade>();
		devTrade.add(new LimitTrade(100,2, true));
		devTrade.add(new LimitTrade(180,3, true));
		devTrade.add(new LimitTrade(100,1, true));
		devTrade.add(new MarketTrade(1,false));
		devTrade.add(new LimitTrade(20,20, true));
		devTrade.add(new LimitTrade(80,10, true));
	}
	
	public void getBuffer(ArrayList<Trade> ret)
	{
		if (devTrade.size() > 0)
		{
			ret.add(devTrade.get(0));
			devTrade.remove(0);
		}
	}
}