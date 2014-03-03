package trade;

public class CloseLimitTrade extends Order
{
	private String receivedtxID;
	
	public CloseLimitTrade(String txID, boolean BuySell)
	{
		super(BuySell);
		receivedtxID = txID;
	}
	
	public void Match(LimitTrade match)
	{
		
	}
	
	public String gettxID()
	{
		return receivedtxID;
	}
}
