package westion.www.exception;



/**
 * 
 * ʵ���ȡ����
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */
public class QueryException extends RuntimeException {

	
	
	public QueryException() {
		super("get entity error");
		
	}

	public QueryException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
