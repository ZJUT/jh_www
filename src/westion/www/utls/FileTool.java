package westion.www.utls;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 * ���岽�裺 1����ô����ļ���Ŀ���� DiskFileItemFactory Ҫ���� 2�� ���� request ��ȡ ��ʵ·�� ������ʱ�ļ��洢����
 * �����ļ��洢 ���������洢λ�ÿɲ�ͬ��Ҳ����ͬ 3���� DiskFileItemFactory ��������һЩ ���� 4����ˮƽ��API�ļ��ϴ�����
 * ServletFileUploadupload = new ServletFileUpload(factory);
 * Ŀ���ǵ���parseRequest��request������ ��� FileItem ����list �� 5���� FileItem ������ ��ȡ��Ϣ�� ������
 * �ж� �����ύ��������Ϣ �Ƿ��� ��ͨ�ı���Ϣ �������� 6�� ��һ��. �õ����� �ṩ�� item.write( new
 * File(path,filename) ); ֱ��д�������� �ڶ���. �ֶ�����
 */
public class FileTool {

	/**
	 * @param request
	 *            HttpServletRequest
	 * @param dir
	 *            String �ϴ��ļ���ŵ�λ��
	 * @return filename String �ϴ��ļ��ĵ�ַ����Ը�Ŀ¼
	 * */
	public static String fileUpload(HttpServletRequest request, String dir)
			throws Exception {
		String filename = null;

		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			String path = request.getServletContext().getRealPath(dir);
			factory.setRepository(new File(path));
			// ���� ����Ĵ�С�����ϴ��ļ������������û���ʱ��ֱ�ӷŵ� ��ʱ�洢��
			factory.setSizeThreshold(1024 * 1024);

			// ��ˮƽ��API�ļ��ϴ�����
			ServletFileUpload upload = new ServletFileUpload(factory);
			// upload.setHeaderEncoding("UTF-8");
			// �����ϴ�����ļ�
			System.out.println(upload.getHeaderEncoding());
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);

			for (FileItem item : list) {
				// ��ȡ��������������
				String name = item.getFieldName();

				// �����ȡ�� ������Ϣ����ͨ�� �ı� ��Ϣ
				if (item.isFormField()) {
					// ��ȡ�û�����������ַ��� ���������ͦ�ã���Ϊ�����ύ�������� �ַ������͵�
					String value = item.getString("UTF-8");

					request.setAttribute(name, value);
				}
				// �Դ���ķ� �򵥵��ַ������д��� ������˵�����Ƶ� ͼƬ����Ӱ��Щ
				else {
					String value = item.getName();
					// �����ļ�
					if (value == null || value.equals("")) {
						continue;
					}
					// ���������һ����б��
					int start = value.lastIndexOf(".");
					String type = value.substring(start); // ��һ����

					// ��ʱ���session���룬��֤Ψһ��
					String encodeToString = Base64.encodeBase64String((request
							.getSession().getId() + new Date().getTime())
							.getBytes());
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

					// in.read(buf) ÿ�ζ��������ݴ���� buf ������
					while ((length = in.read(buf)) != -1) {
						// �� buf ������ ȡ������ д�� ���������������
						out.write(buf, 0, length);

					}
					in.close();
					out.close();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return filename;

	}

	/**
	 * @param filePath
	 *            String �ļ���·��
	 * @return boolean �ļ�ɾ���Ƿ�ɹ�
	 * */
	public static void deleteFile(String filePath) {
		try {
			File file = new File(filePath);
			file.delete();
		} catch (Exception e) {
			throw e;
		}

	}
}