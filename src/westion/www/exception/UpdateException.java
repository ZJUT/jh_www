package westion.www.exception;


/**
 * 
 * 数据库更新记录失败
 * @version 1.0, 2014-4-6
 * @author westion
 * @since JDK1.7
 */
public class UpdateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UpdateException() {
		super("can not update");
	}

	public UpdateException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
