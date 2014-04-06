package westion.www.exception;



/**
 * 
 * Êý¾Ý¿â²éÑ¯Ê§°Ü
 * @version 1.0, 2014-4-6
 * @author westion
 * @since JDK1.7
 */
public class QueryException extends RuntimeException {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QueryException() {
		super("can not query find");
		
	}

	public QueryException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
