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

public class FileUploadTool {

	public static String fileUpload(HttpServletRequest request, String dir)
			throws Exception {
		String filename = null;
		try {
			request.setCharacterEncoding("utf-8");
			DiskFileItemFactory factory = new DiskFileItemFactory();
			String path = request.getServletContext().getRealPath(dir);

			factory.setRepository(new File(path));
			// ���� ����Ĵ�С�����ϴ��ļ������������û���ʱ��ֱ�ӷŵ� ��ʱ�洢��
			factory.setSizeThreshold(1024 * 1024);

			// ��ˮƽ��API�ļ��ϴ�����
			ServletFileUpload upload = new ServletFileUpload(factory);

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

		} catch (Exception e) {
			throw e;
		}
		return filename;

	}
}
