package westion.www.exception;


/**
 * 
 * 未登录错误，获取需要登录的资源
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */
public class NoLoginException extends RuntimeException {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoLoginException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
