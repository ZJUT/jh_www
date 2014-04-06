package westion.www.entity;

import com.google.gson.annotations.Expose;


/**
 * 
 * ��ʷ�¼����ݿ�ʵ����
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */
public class Event {

	/**�¼����(����)*/
	@Expose
	private Integer eid;
	/**�¼�����*/
	@Expose
	private String econtent;
	/**�¼�ͼƬ*/
	@Expose
	private String ephoto_url;
	/**��ʽ�����¼�ʱ��*/
	@Expose
	private String formatTime_etime;
	/**��ʽ�����¼�����ʱ��*/
	@Expose
	private String formatTime_create_time;
	/**����1970��ʽ���¼�ʱ��*/
	private Long etime;
	/**����1970��ʽ���¼�����ʱ��*/
	private Long create_time;


	public String getFormatTime_etime() {
		return formatTime_etime;
	}

	public void setFormatTime_etime(String formatTime_etime) {
		this.formatTime_etime = formatTime_etime;
	}

	public String getFormatTime_create_time() {
		return formatTime_create_time;
	}

	public void setFormatTime_create_time(String formatTime_create_time) {
		this.formatTime_create_time = formatTime_create_time;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEcontent() {
		return econtent;
	}

	public void setEcontent(String econtent) {
		this.econtent = econtent;
	}

	public Long getEtime() {
		return etime;
	}

	public void setEtime(Long etime) {
		this.etime = etime;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	public String getEphoto_url() {
		return ephoto_url;
	}

	public void setEphoto_url(String ephoto_url) {
		this.ephoto_url = ephoto_url;
	}

}
