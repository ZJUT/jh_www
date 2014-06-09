package westion.www.utls;

import com.google.gson.annotations.Expose;

/**
 * 返回生成json数据对象的类
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */
public class ReturnData {

	
	@Expose
	/** 返回值状态（值参考见表）*/
	String status = null;
	
	@Expose
	/**数据*/
	Object data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
