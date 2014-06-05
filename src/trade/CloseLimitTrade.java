package trade;

public class CloseLimitTrade extends Order
{
	private String receivedtxID;
	private LimitTrade matchedClose;
	
	public CloseLimitTrade(String txID, boolean BuySell)
	{
		super(BuySell);
		receivedtxID = txID;
	}
	
	public void Match(LimitTrade match)
	{
		matchedClose = match;
	}
	
	public String gettxID()
	{
		return receivedtxID;
	}
	public LimitTrade getMatchedClose()
	{
		return matchedClose;
	}
}
