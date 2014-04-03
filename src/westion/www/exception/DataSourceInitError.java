package westion.www.exception;


/**
 * 
 * 数据源初始化错误
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */


public class DataSourceInitError extends ExceptionInInitializerError {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6404544141010408735L;

	public DataSourceInitError(Throwable thrown) {
		super(thrown);
	}

	
	
	
}

