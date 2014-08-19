package westion.www.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import westion.www.dao.NoticeDao;
import westion.www.dao.impl.NoticeDaoImpl;
import westion.www.entity.Notice;
import westion.www.service.NoticeService;

public class NoticeServiceImpl implements NoticeService {

	/** Dao层对应操作实例 {@link NoticeDaoImpl#NoticeDaoImpl()} */
	private NoticeDao noticeDao = new NoticeDaoImpl();

	/**
	 * 获取最近的一条通知
	 * 
	 */
	@Override
	public Notice getNew() {
		List<Notice> list = new ArrayList<Notice>();
		list.add(noticeDao.getNew());
		Notice notice = formatTime(list).get(0);
		return notice;
	}

	/**
	 * 增加一条通知
	 * 
	 * @param ncontent
	 *            String
	 * @param destination_url
	 *            String
	 * @param nphoto_url
	 *            String
	 * @param create_time
	 *            Long
	 */
	@Override
	public void add(String ncontent, String destination_url, String nphoto_url,
			Long create_time) {
		if (create_time == null) {
			create_time = new Date().getTime();
		}
		noticeDao.add(ncontent, destination_url, nphoto_url, create_time);

	}

	/**
	 * 获取所有通知信息
	 * 
	 * @return events List\<Notice\>
	 */
	public List<Notice> list() {

		return formatTime(noticeDao.list());

	}

	/**
	 * 删除一条通知
	 * 
	 * @param id
	 *            Integer
	 */
	@Override
	public void delete(Integer id) {
		noticeDao.delete(id);
	}

	/**
	 * 更新一条通知
	 * 
	 * @param id
	 *            Integer
	 * @param ncontent
	 *            String
	 * @param destination_url
	 *            String
	 * @param nphoto_url
	 *            String
	 * @param create_time
	 *            Long
	 */
	@Override
	public void update(Integer id, String ncontent, String destination_url,
			String nphoto_url, Long create_time) {
		if (create_time == null) {
			create_time = new Date().getTime();
		}
		noticeDao
				.update(id, ncontent, destination_url, nphoto_url, create_time);

	}

	/**
	 * 格式化对象的时间
	 * 
	 * @param notices
	 *            List\<Notice\>
	 * @return List\<Notice\>
	 */
	private List<Notice> formatTime(List<Notice> notices) {
		Notice notice = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < notices.size(); i++) {
			notice = notices.get(i);
			notice.setFormatTime(format.format(notice.getCreate_time()));
			;
		}
		return notices;
	}

	/**
	 * 获取一条通知
	 * 
	 * @param id
	 *            Integer
	 */
	@Override
	public Notice findById(Integer id) {
		List<Notice> list = new ArrayList<Notice>();
		list.add(noticeDao.findById(id));
		Notice notice = formatTime(list).get(0);
		return notice;
	}
}
