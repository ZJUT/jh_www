package westion.www.exception;

/**
 * 
 * 调用函数的参数类型有误
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */
public class ArgumentTypeException extends RuntimeException {

	public ArgumentTypeException(Throwable cause) {
		super(cause);
	}
}
