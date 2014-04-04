package westion.www.admin.action;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import westion.www.service.EventService;
import westion.www.service.impl.EventServiceImpl;
import westion.www.utls.FileUploadTool;

public class AdminEventAction {

	/** ������� */
	private Map<String, String[]> params = null;
	/** �����û���session */
	private HttpSession session = null;
	/** ��վȫ�ֵ������ļ� */
	private Properties properties = null;
	/** ��վȫ�ֵķ���״̬�ļ� */
	private List<String> errorList = null;

	private EventService eventService = new EventServiceImpl();

	private HttpServletRequest request = null;

	/**
	 * ���캯������request�л�ȡ��Ӧ�����ԣ�����˽�г�Ա��ʼ��
	 * 
	 * @param request
	 *            HttpServletRequest
	 */
	@SuppressWarnings("unchecked")
	public AdminEventAction(HttpServletRequest request) {
		super();
		this.params = (Map<String, String[]>) request.getAttribute("params");
		this.session = request.getSession();
		this.properties = (Properties) request.getServletContext()
				.getAttribute("pageConfig");
		this.errorList = (List<String>) request.getAttribute("errorList");
		this.request = request;
	}

	/**
	 * ����һ���¼�
	 * 
	 * @author westion �ļ��ϴ� ���岽�裺 1����ô����ļ���Ŀ���� DiskFileItemFactory Ҫ���� 2�� ����
	 *         request ��ȡ ��ʵ·�� ������ʱ�ļ��洢���� �����ļ��洢 ���������洢λ�ÿɲ�ͬ��Ҳ����ͬ 3����
	 *         DiskFileItemFactory ��������һЩ ���� 4����ˮƽ��API�ļ��ϴ�����
	 *         ServletFileUploadupload = new ServletFileUpload(factory);
	 *         Ŀ���ǵ���parseRequest��request������ ��� FileItem ����list �� 5���� FileItem
	 *         ������ ��ȡ��Ϣ�� ������ �ж� ���ύ��������Ϣ �Ƿ��� ��ͨ�ı���Ϣ �������� 6�� ��һ��. �õ����� �ṩ��
	 *         item.write( new File(path,filename) ); ֱ��д�������� �ڶ���. �ֶ�����
	 * 
	 * @param econtent
	 *            String �¼�����
	 * @param ephoto_url
	 *            String �¼�ͼƬ
	 * @param etime
	 *            Integer �¼���ʱ��
	 * @param create_time
	 *            Integer �¼�����ʱ��
	 * 
	 * 
	 * */
	public void addEvent() {
		try {
			String filename = FileUploadTool.fileUpload(request, "/"
					+ properties.getProperty("eventPhotoDir"));
			eventService.add((String) request.getAttribute("econtent"),
					properties.getProperty("eventPhotoDir") + filename,
					(String) request.getAttribute("etime"));
		} catch (Exception e) {
			errorList.add(properties.getProperty("uploadError"));
		}

	}

}
