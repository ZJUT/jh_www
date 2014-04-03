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
	private String encodeToString;

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
	 * @author Administrator �ļ��ϴ� ���岽�裺 1����ô����ļ���Ŀ���� DiskFileItemFactory Ҫ���� 2��
	 *         ���� request ��ȡ ��ʵ·�� ������ʱ�ļ��洢���� �����ļ��洢 ���������洢λ�ÿɲ�ͬ��Ҳ����ͬ 3����
	 *         DiskFileItemFactory ��������һЩ ���� 4����ˮƽ��API�ļ��ϴ����� ServletFileUpload
	 *         upload = new ServletFileUpload(factory); Ŀ���ǵ���
	 *         parseRequest��request������ ��� FileItem ����list ��
	 * 
	 *         5���� FileItem ������ ��ȡ��Ϣ�� ������ �ж� ���ύ��������Ϣ �Ƿ��� ��ͨ�ı���Ϣ �������� 6�� ��һ��.
	 *         �õ����� �ṩ�� item.write( new File(path,filename) ); ֱ��д�������� �ڶ���. �ֶ�����
	 * 
	 * @param econtent
	 *            String �¼�����
	 * @param ephoto_url
	 *            String �¼�ͼƬ
	 * @param etime
	 *            Integer �¼���ʱ��
	 * @param create_time
	 *            Integer �¼�����ʱ��
	 * @throws UnsupportedEncodingException
	 * 
	 * */
	public void addEvent() throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8"); // ���ñ���
		DiskFileItemFactory factory = new DiskFileItemFactory();
		String path = request.getServletContext().getRealPath("/upload/image/");

		factory.setRepository(new File(path));
		// ���� ����Ĵ�С�����ϴ��ļ������������û���ʱ��ֱ�ӷŵ� ��ʱ�洢��
		factory.setSizeThreshold(1024 * 1024);

		// ��ˮƽ��API�ļ��ϴ�����
		ServletFileUpload upload = new ServletFileUpload(factory);

		String filename = null;

		try {
			// �����ϴ�����ļ�
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);

			for (FileItem item : list) {
				// ��ȡ������������
				String name = item.getFieldName();

				// �����ȡ�� ����Ϣ����ͨ�� �ı� ��Ϣ
				if (item.isFormField()) {
					// ��ȡ�û�����������ַ��� ���������ͦ�ã���Ϊ���ύ�������� �ַ������͵�
					String value = item.getString();

					request.setAttribute(name, value);
				}
				// �Դ���ķ� �򵥵��ַ������д��� ������˵�����Ƶ� ͼƬ����Ӱ��Щ
				else {
					String value = item.getName();
					// ���������һ����б��
					int start = value.lastIndexOf(".");
					String type = value.substring(start); // ��һ����

					// ��ʱ���session���룬��֤Ψһ��
					encodeToString = Base64
							.encodeBase64String((session.getId() + new Date()
									.getTime()).getBytes());
					filename = encodeToString + type;
					// ����д��������
					// ���׳����쳣 ��exception ��׽
					// item.write( new File(path,filename) );//�������ṩ��
					// �ֶ�д��
					File file = new File(path, filename);
					OutputStream out = new FileOutputStream(file);
					InputStream in = item.getInputStream();

					int length = 0;
					byte[] buf = new byte[1024];

					System.out.println("��ȡ�ϴ��ļ����ܹ���������" + item.getSize());

					// in.read(buf) ÿ�ζ��������ݴ���� buf ������
					while ((length = in.read(buf)) != -1) {
						// �� buf ������ ȡ������ д�� ���������������
						out.write(buf, 0, length);

					}

					in.close();
					out.close();
				}
			}
			// �������ݿ�
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
