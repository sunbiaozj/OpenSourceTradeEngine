package trade;
import java.util.ArrayList;

public class LimitTrade extends Trade implements Comparable<LimitTrade>{
	
	private int price;	// Holds the entry price for the trade
	private ArrayList<MarketTrade> matched;
	
	public LimitTrade(int Price,int Amount, boolean buysell )	// Constructor calls the superclass constructor and sets the price
	{
		super(Amount, buysell);
		matched = new ArrayList<MarketTrade>();
		price = Price;
	}
	
	public void Match(MarketTrade marketTrade)
	{
		matched.add(marketTrade);
	}
	
	public ArrayList<Trade> splitTrade(LimitTrade trade, int amount)
	{
		ArrayList<Trade> ret = super.splitTrade(getPrice(),trade, amount);
		return ret;
	}
	
	/* Part of implementing the Comparable interface, this only directs the comparson to another two functions
	** If it is a buy trade it uses compareBuy() and if it is a sell trade it uses compareSell()
	*/
	public int compareTo(LimitTrade o) 
	{
		if (this.getBuySell())
		{
			return compareBuy(o);
		}
		else
		{
			return compareSell(o);
		}
	}
	
	/* These two methods compare the LimitTrade objects in the way that if it is a buy it considers it as lower, the larger the price is
	** And if it is a sell it considers it lower, the smaller the price is 
	*/
	private int compareBuy(LimitTrade o)
	{
		if (this.price < o.getPrice()){return -1;}
		else if (this.price > o.getPrice()){return 1;}
		else {return 0;}
	}
	private int compareSell(LimitTrade o)
	{
		if (this.price > o.getPrice()){return -1;}
		else if (this.price < o.getPrice()){return 1;}
		else {return 0;}
	}
	
	public ArrayList<MarketTrade> getMatched()
	{
		return matched;
	}
	
	public int getPrice()	// Returns the price
	{
		return price;
	}
}
