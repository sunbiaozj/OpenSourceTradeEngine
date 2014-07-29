package trade;

import java.io.Serializable;

public class Order implements Serializable
{
	protected boolean buysell;	// Holds whether the trade is a buy (1) or a sell (0)
	
	public Order(boolean BuySell)
	{
		buysell = BuySell;
	}
	
	public boolean getBuySell()	// Returns whether the trade is a buy (1) or a sell (0)
	{
		return buysell;
	}
}
