package westion.www.admin.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import westion.www.service.EventService;
import westion.www.service.impl.EventServiceImpl;

public class AdminEventAction {

	/** 请求参数 */
	private Map<String, String[]> params = null;
	/** 请求用户的session */
	private HttpSession session = null;
	/** 网站全局的配置文件 */
	private Properties properties = null;
	/** 网站全局的返回状态文件 */
	private List<String> errorList = null;

	private EventService eventService = new EventServiceImpl();

	private HttpServletRequest request = null;
	private String encodeToString;

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
		this.session = request.getSession();
		this.properties = (Properties) request.getServletContext()
				.getAttribute("pageConfig");
		this.errorList = (List<String>) request.getAttribute("errorList");
		this.request = request;
	}

	/**
	 * 增加一条事件
	 * 
	 * @author Administrator 文件上传 具体步骤： 1）获得磁盘文件条目工厂 DiskFileItemFactory 要导包 2）
	 *         利用 request 获取 真实路径 ，供临时文件存储，和 最终文件存储 ，这两个存储位置可不同，也可相同 3）对
	 *         DiskFileItemFactory 对象设置一些 属性 4）高水平的API文件上传处理 ServletFileUpload
	 *         upload = new ServletFileUpload(factory); 目的是调用
	 *         parseRequest（request）方法 获得 FileItem 集合list ，
	 * 
	 *         5）在 FileItem 对象中 获取信息， 遍历， 判断 表单提交过来的信息 是否是 普通文本信息 另做处理 6） 第一种.
	 *         用第三方 提供的 item.write( new File(path,filename) ); 直接写到磁盘上 第二种. 手动处理
	 * 
	 * @param econtent
	 *            String 事件内容
	 * @param ephoto_url
	 *            String 事件图片
	 * @param etime
	 *            Integer 事件的时间
	 * @param create_time
	 *            Integer 事件发布时间
	 * @throws UnsupportedEncodingException
	 * 
	 * */
	public void addEvent() throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8"); // 设置编码
		DiskFileItemFactory factory = new DiskFileItemFactory();
		String path = request.getServletContext().getRealPath("/upload/image/");

		factory.setRepository(new File(path));
		// 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
		factory.setSizeThreshold(1024 * 1024);

		// 高水平的API文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);

		String filename = null;

		try {
			// 可以上传多个文件
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);

			for (FileItem item : list) {
				// 获取表单的属性名字
				String name = item.getFieldName();

				// 如果获取的 表单信息是普通的 文本 信息
				if (item.isFormField()) {
					// 获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的
					String value = item.getString();

					request.setAttribute(name, value);
				}
				// 对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些
				else {
					String value = item.getName();
					// 索引到最后一个反斜杠
					int start = value.lastIndexOf(".");
					String type = value.substring(start); // 带一个点

					// 用时间和session编码，保证唯一性
					encodeToString = Base64
							.encodeBase64String((session.getId() + new Date()
									.getTime()).getBytes());
					filename = encodeToString + type;
					// 真正写到磁盘上
					// 它抛出的异常 用exception 捕捉
					// item.write( new File(path,filename) );//第三方提供的
					// 手动写的
					File file = new File(path, filename);
					OutputStream out = new FileOutputStream(file);
					InputStream in = item.getInputStream();

					int length = 0;
					byte[] buf = new byte[1024];

					System.out.println("获取上传文件的总共的容量：" + item.getSize());

					// in.read(buf) 每次读到的数据存放在 buf 数组中
					while ((length = in.read(buf)) != -1) {
						// 在 buf 数组中 取出数据 写到 （输出流）磁盘上
						out.write(buf, 0, length);

					}

					in.close();
					out.close();
				}
			}
			// 存入数据库
			eventService.add((String) request.getAttribute("econtent"),
					"/upload/image/" + filename,
					(String) request.getAttribute("etime"));

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			errorList.add(properties.getProperty("unknownError"));
		}

	}

	/*
	 * try { eventService.add(params.get("econtent")[0],
	 * params.get("ephoto_url")[0], Integer.parseInt(params.get("etime")[0]),
	 * Integer.parseInt(params.get("create_time")[0])); } catch (AddException e)
	 * { errorList.add(properties.getProperty("sqlError")); } catch (Exception
	 * e) { errorList.add(properties.getProperty("unknownError")); }
	 */

}
