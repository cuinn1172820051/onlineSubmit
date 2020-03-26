package org.onlineSubmit.service.impl;

import java.util.List;
import java.util.Map;

import org.onlineSubmit.dao.ExpertDao;
import org.onlineSubmit.entity.Distribute;
import org.onlineSubmit.entity.Expert;
import org.onlineSubmit.entity.Manuscript;
import org.onlineSubmit.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ExpertServiceImpl implements ExpertService {
	@Autowired
	private ExpertDao expertDao;
	public Expert findExpertByUserName(String username) {
		// TODO Auto-generated method stub
		return expertDao.findExpertByUserName(username);
	}
	public List<Distribute> findDistributeList() {
		// TODO Auto-generated method stub
		return expertDao.findDistributeList();
	}
	public List<Manuscript> findManuscriptListByExpert(Map<String,Object> queryMap) {
		// TODO Auto-generated method stub
		return expertDao.findManuscriptListByExpert(queryMap);
	}
	public int getManuscriptTotalByExpert(Map<String,Object> queryMap) {
		// TODO Auto-generated method stub
		return expertDao.getManuscriptTotalByExpert(queryMap);
	}
	public int editManuscriptByManuscript(Map<String,Object> updateMap) {
		// TODO Auto-generated method stub
		return expertDao.editManuscriptByManuscript(updateMap);
	}
	public int addExpertReviewInfo(Map<String, Object> updateMap) {
		// TODO Auto-generated method stub
		return expertDao.addExpertReviewInfo(updateMap);
	}
	public Expert queryOwnInfoByExpert(int expert_id) {
		// TODO Auto-generated method stub
		return expertDao.queryOwnInfoByExpert(expert_id);
	}
	public int editOwnInfoByExpert(Expert expert) {
		// TODO Auto-generated method stub
		return expertDao.editOwnInfoByExpert(expert);
	}
	public List<Manuscript> findManuscriptNoReviewListByExpert(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return expertDao.findManuscriptNoReviewListByExpert(queryMap);
	}
	public int getManuscriptTotalNoReviewByExpert(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return expertDao.getManuscriptTotalNoReviewByExpert(queryMap);
	}
}
