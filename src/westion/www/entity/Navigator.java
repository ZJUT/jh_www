package westion.www.entity;

import com.google.gson.annotations.Expose;


/**
 * 
 * �����������ݿ�ʵ����
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */
public class Navigator {
	
	/**���(����)*/
	@Expose
	private Integer naid;
	/**��������*/
	@Expose
	private String naname;
	/**������Ӧ����*/
	@Expose
	private String destination_url;
	
	
	
	public Integer getNaid() {
		return naid;
	}
	public void setNaid(Integer naid) {
		this.naid = naid;
	}
	public String getNaname() {
		return naname;
	}
	public void setNaname(String naname) {
		this.naname = naname;
	}
	public String getDestination_url() {
		return destination_url;
	}
	public void setDestination_url(String destination_url) {
		this.destination_url = destination_url;
	}
	
	
}
