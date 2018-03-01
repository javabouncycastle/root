package cn.com.sure.common;

public class KmApplicationexception extends Exception{

	/**
	 * application�쳣
	 */
	private static final long serialVersionUID = -1441961842085031455L;
	Exception underlyingException;
    int errorCode = -1;
    

    
    public KmApplicationexception(String message){
    	super(message);
    }
    
    public KmApplicationexception(String message, Exception e){
    	
    	super(message);
        underlyingException = e;
    }
    
    public KmApplicationexception(int errorCode){
        super("Error - "+errorCode);
		this.errorCode = errorCode;
	}
    
    public KmApplicationexception(int errorCode,String message){
        super(message);
		this.errorCode = errorCode;
	}
    
    public KmApplicationexception(int errorCode,String message, Exception e)
    {
        super(message);
		this.errorCode = errorCode;
        underlyingException = e;
    }
    
    public Exception getUnderlyingException()
    {
        return underlyingException;
    }

    public Throwable getCause()
    {
        return underlyingException;
    }

	public int getErrorCode() {
		return errorCode;
	}
	
	  public static void throwException(int errCode,Object[] args) throws KmApplicationexception{
	    	String message = ResourceBundleErrorMessage.getInstance().getMessage(errCode, args);
	    	throw new KmApplicationexception(errCode,message);
	    }

	 
}
