package westion.www.exception;


/**
 * 
 * ���ݿ�ɾ����¼ʧ��
 * @version 1.0, 2014-4-6
 * @author westion
 * @since JDK1.7
 */
public class DeleteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DeleteException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public DeleteException() {
		super("can not delete the object");
	}

	
}
