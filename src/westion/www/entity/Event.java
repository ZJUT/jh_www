package westion.www.entity;

import com.google.gson.annotations.Expose;


/**
 * 
 * 历史事件数据库实体类
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */
public class Event {

	/**事件编号(主键)*/
	@Expose
	private Integer eid;
	/**事件内容*/
	@Expose
	private String econtent;
	/**事件图片*/
	@Expose
	private String ephoto_url;
	/**格式化的事件时间*/
	@Expose
	private String formatTime_etime;
	/**格式化的事件创建时间*/
	@Expose
	private String formatTime_create_time;
	/**距离1970格式的事件时间*/
	private Long etime;
	/**距离1970格式的事件创建时间*/
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
