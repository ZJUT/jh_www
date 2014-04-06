package westion.www.service.impl;

import java.util.List;

import westion.www.dao.NavigatorDao;
import westion.www.dao.impl.NavigatorDaoImpl;
import westion.www.entity.Navigator;
import westion.www.service.NavigatorService;

public class NavigatorServiceImpl implements NavigatorService {

	/** Dao���Ӧ����ʵ�� {@link NavigatorDaoImpl#NavigatorDaoImpl()} */
	private NavigatorDao navigatorDao = new NavigatorDaoImpl();
	
	
	/**
	 * ��ȡ���е�����Ŀ
	 * 
	 * @return navigators List\<Navigator\>
	 */
	@Override
	public List<Navigator> list() {
		return navigatorDao.list();
	}

	/**
	 * ����һ��������Ŀ
	 * 
	 * @param naname String
	 * @param destination_url String
	 */
	@Override
	public void add(String naname, String destination_url) {
		navigatorDao.add(naname, destination_url);
		
	}

	/**
	 * ɾ��һ��������Ŀ
	 * 
	 * @param id Integer
	 * 
	 */
	@Override
	public void delete(Integer id) {
		navigatorDao.delete(id);
	}

	/**
	 * ����һ��������Ŀ
	 * 
	 * @param id Integer
	 * @param naname String
	 * @param destination_url String
	 */
	@Override
	public void update(Integer id, String naname, String destination_url) {
		navigatorDao.update(id, naname, destination_url);
	}
	
	/**
	 * ��ȡһ��������Ŀ,ͨ��id
	 * 
	 * @param id Integer
	 */
	@Override
	public Navigator findById(Integer id) {
		Navigator navigator = navigatorDao.findById(id);
		return navigator;
	}

}
