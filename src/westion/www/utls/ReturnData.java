package westion.www.utls;

import com.google.gson.annotations.Expose;

/**
 * ��������json���ݶ������
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */
public class ReturnData {

	
	@Expose
	/** ����ֵ״̬��ֵ�ο�����*/
	String status = null;
	
	@Expose
	/**����*/
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
