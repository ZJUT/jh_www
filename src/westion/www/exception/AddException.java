package westion.www.exception;



/**
 * 
 * ���ݿ���Ӽ�¼ʧ��
 * @version 1.0, 2014-4-6
 * @author westion
 * @since JDK1.7
 */
public class AddException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddException(Throwable arg0) {
		super(arg0);
	}

	public AddException() {
		super("can not add to sql");
	}
	
	

}
