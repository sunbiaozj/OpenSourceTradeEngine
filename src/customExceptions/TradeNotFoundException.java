package customExceptions;

public class TradeNotFoundException extends Exception 
{
	public TradeNotFoundException()
	{
		super();
	}
	
	public TradeNotFoundException(String mSg)
	{
		super(mSg);
	}
}
