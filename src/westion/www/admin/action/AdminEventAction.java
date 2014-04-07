package westion.www.admin.action;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import westion.www.entity.Event;
import westion.www.service.EventService;
import westion.www.service.impl.EventServiceImpl;
import westion.www.utls.FileTool;
import westion.www.utls.TimeFormat;

/**
 * 
 * 事件请求类（管理员）
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 * @see westion.www.service.EventService
 */
public class AdminEventAction {

	/** 请求参数 */
	private Map<String, String[]> params = null;
	/** 网站全局的配置文件 */
	private Properties properties = null;
	/** 网站全局的返回状态文件 */
	private List<String> errorList = null;

	private EventService eventService = new EventServiceImpl();

	private HttpServletRequest request = null;

	/**
	 * 构造函数，从request中获取相应的属性，并对私有成员初始化
	 * 
	 * @param request
	 *            HttpServletRequest
	 */
	@SuppressWarnings("unchecked")
	public AdminEventAction(HttpServletRequest request) {
		super();
		this.params = (Map<String, String[]>) request.getAttribute("params");
		this.properties = (Properties) request.getServletContext()
				.getAttribute("pageConfig");
		this.errorList = (List<String>) request.getAttribute("errorList");
		this.request = request;
	}

	/**
	 * 增加一条事件
	 * */
	public void addEvent() {
		String filename = null;
		try {
			filename = FileTool.fileUpload(request,
					"/" + properties.getProperty("eventPhotoDir"));
		} catch (Exception e) {
			errorList.add(properties.getProperty("uploadError"));
			return;
		}
		try {
			String address = null;
			if (filename != null)
				address = properties.getProperty("eventPhotoDir") + filename;
			eventService.add(
					(String) request.getAttribute("econtent"),(String) request.getAttribute("etitle"),
					address,
					TimeFormat.changeToLongTime(
							(String) request.getAttribute("etime")).getTime(),
					null);
		} catch (Exception e) {
			e.printStackTrace();
			if (filename != null)
				FileTool.deleteFile(this.request.getServletContext()
						.getRealPath(
								"/" + properties.getProperty("eventPhotoDir")
										+ filename));
			errorList.add(properties.getProperty("uploadError"));
		}
	}

	/**
	 * 删除一条事件，并要删除图片
	 * */
	public void deleteEvent() {
		Event event = null;
		try {
			event = eventService
					.findById(Integer.parseInt(params.get("eid")[0]));
		} catch (Exception e) {
			errorList.add(properties.getProperty("sqlError"));
			return;
		}
		try {
			eventService.delete(event.getEid());
		} catch (Exception e) {
			errorList.add(properties.getProperty("sqlError"));
			return;
		}
		try {
			String filename = event.getEphoto_url();
			if (filename != null)
				FileTool.deleteFile(this.request.getServletContext()
						.getRealPath("/" + filename));
		} catch (Exception e) {
			eventService.add(event.getEcontent(), event.getEphoto_url(),event.getEtitle(),
					event.getEtime(), event.getCreate_time());
			errorList.add(properties.getProperty("sqlError"));
		}

	}

	/**
	 * 修改一条事件，并可修改图片
	 * */
	public void updateEvent() {
		Event event = null;
		String filename = null;
		try {
			event = eventService
					.findById(Integer.parseInt(params.get("eid")[0]));
		} catch (Exception e) {
			e.printStackTrace();
			errorList.add(properties.getProperty("sqlError"));
			return;
		}
		try {
			filename = event.getEphoto_url();
			if (filename != null)
				FileTool.deleteFile(this.request.getServletContext()
						.getRealPath("/" + filename));
		} catch (Exception e) {
			e.printStackTrace();
			errorList.add(properties.getProperty("sqlError"));
			return;
		}
		try {
			filename = FileTool.fileUpload(request,
					"/" + properties.getProperty("eventPhotoDir"));
		} catch (Exception e) {
			e.printStackTrace();
			errorList.add(properties.getProperty("sqlError"));
			return;
		}
		try {
			String address = null;
			if (filename != null)
				address = properties.getProperty("eventPhotoDir") + filename;
			eventService.update(
					event.getEid(),
					(String) request.getAttribute("econtent"),(String) request.getAttribute("etitle"),
					address,
					TimeFormat.changeToLongTime(
							(String) request.getAttribute("etime")).getTime(),
					null);
		} catch (Exception e) {
			e.printStackTrace();
			if (filename != null)
				FileTool.deleteFile(this.request.getServletContext()
						.getRealPath(
								"/" + properties.getProperty("eventPhotoDir")
										+ filename));
			errorList.add(properties.getProperty("sqlError"));
		}

	}

}
