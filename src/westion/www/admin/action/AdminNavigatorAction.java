package westion.www.admin.action;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import westion.www.exception.AddException;
import westion.www.exception.DeleteException;
import westion.www.exception.UpdateException;
import westion.www.service.NavigatorService;
import westion.www.service.impl.NavigatorServiceImpl;

/**
 * 
 * ���������ࣨ����Ա��
 * 
 * @version 1.0, 2014-4-6
 * @author westion
 * @since JDK1.7
 * @see westion.www.service.NavigatorService
 */

public class AdminNavigatorAction {

	/** ������� */
	private Map<String, String[]> params = null;
	
	/** ��վȫ�ֵ������ļ� */
	private Properties properties = null;
	/** ��վȫ�ֵķ���״̬�ļ� */
	private List<String> errorList = null;

	private NavigatorService navigatorService = new NavigatorServiceImpl();


	/**
	 * ���캯������request�л�ȡ��Ӧ�����ԣ�����˽�г�Ա��ʼ��
	 * 
	 * @param request
	 *            HttpServletRequest
	 */
	@SuppressWarnings("unchecked")
	public AdminNavigatorAction(HttpServletRequest request) {
		super();
		this.params = (Map<String, String[]>) request.getAttribute("params");
		this.properties = (Properties) request.getServletContext()
				.getAttribute("pageConfig");
		this.errorList = (List<String>) request.getAttribute("errorList");

	}

	/**
	 * ����һ������
	 * 
	 * 
	 */
	public void addNavigator() {
		try {
			String[] naname = params.get("naname");
			String[] destination_url = params.get("destination_url");
			String[] weight = params.get("weight");
			navigatorService.add(naname[0], destination_url[0],Integer.parseInt(weight[0]));
		} catch (AddException e) {
			e.printStackTrace();
			errorList.add(properties.getProperty("sqlError"));
		} catch (Exception e) {
			e.printStackTrace();
			errorList.add(properties.getProperty("unknownError"));
		}
	}
	

	/**
	 *ɾ��һ������
	 * 
	 * 
	 */
	public void deleteNavigator() {
		try {
			String[] nnid = params.get("naid");
			navigatorService.delete(Integer.parseInt(nnid[0]));
		} catch (DeleteException e) {
			errorList.add(properties.getProperty("sqlError"));
		} catch (Exception e) {
			errorList.add(properties.getProperty("unknownError"));
		}
	}
	
	/**
	 *�޸�һ������
	 * 
	 * 
	 */
	public void updateNavigator() {
		try {
			String[] naname = params.get("naname");
			String[] destination_url = params.get("destination_url");
			String[] naid = params.get("naid");
			String[] weight = params.get("weight");
			navigatorService.update(Integer.parseInt(naid[0]), naname[0], destination_url[0],Integer.parseInt(weight[0]));
		} catch (UpdateException e) {
			errorList.add(properties.getProperty("sqlError"));
		} catch (Exception e) {
			errorList.add(properties.getProperty("unknownError"));
		}
	}
}
