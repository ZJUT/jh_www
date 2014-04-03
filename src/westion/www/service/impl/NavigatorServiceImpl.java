package westion.www.service.impl;

import java.util.List;

import westion.www.dao.NavigatorDao;
import westion.www.dao.impl.NavigatorDaoImpl;
import westion.www.entity.Navigator;
import westion.www.service.NavigatorService;

public class NavigatorServiceImpl implements NavigatorService {

	/** Dao层对应操作实例 {@link NavigatorDaoImpl#NavigatorDaoImpl()} */
	private NavigatorDao navigatorDao = new NavigatorDaoImpl();
	
	@Override
	public List<Navigator> list() {
		return navigatorDao.list();
	}

	@Override
	public void add(String naname, String destination_url) {
		navigatorDao.add(naname, destination_url);
		
	}

	@Override
	public void delete(Integer id) {
		navigatorDao.delete(id);
	}

	@Override
	public void update(Integer id, String naname, String destination_url) {
		navigatorDao.update(id, naname, destination_url);
	}

}
