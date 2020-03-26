package org.onlineSubmit.service.impl;

import java.util.List;
import java.util.Map;

import org.onlineSubmit.dao.EditorDao;
import org.onlineSubmit.entity.Distribute;
import org.onlineSubmit.entity.Editor;
import org.onlineSubmit.entity.Expert;
import org.onlineSubmit.entity.ExpertReview;
import org.onlineSubmit.entity.Manuscript;
import org.onlineSubmit.entity.Nav;
import org.onlineSubmit.entity.User;
import org.onlineSubmit.service.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EditorServiceImpl implements EditorService {
	@Autowired
	private EditorDao editorDao;
	public Editor findEditorByUserName(String username) {
		// TODO Auto-generated method stub
		return editorDao.findEditorByUserName(username);
	}
	public List<Manuscript> findManuscriptList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return editorDao.findManuscriptList(queryMap);
	}
	public int getTotalByEditor(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return editorDao.getTotalByEditor(queryMap);
	}
	public Editor queryOwnInfoByEditor() {
		// TODO Auto-generated method stub
		return editorDao.queryOwnInfoByEditor();
	}
	public int editOwnInfoByEditor(Editor editor) {
		// TODO Auto-generated method stub
		return editorDao.editOwnInfoByEditor(editor);
	}
	public List<User> findUserList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return editorDao.findUserList(queryMap);
	}
	public int getUserTotalByEditor(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return editorDao.getUserTotalByEditor(queryMap);
	}
	public int editUserInfo(User user) {
		// TODO Auto-generated method stub
		return editorDao.editUserInfo(user);
	}
	public int deleteUserInfo(String ids) {
		// TODO Auto-generated method stub
		return editorDao.deleteUserInfo(ids);
	}
	public Expert queryExpertInfo() {
		// TODO Auto-generated method stub
		return editorDao.queryExpertInfo();
	}
	public int editExpertInfo(Expert expert) {
		// TODO Auto-generated method stub
		return editorDao.editExpertInfo(expert);
	}
	public int deleteExpertInfo(String ids) {
		// TODO Auto-generated method stub
		return editorDao.deleteExpertInfo(ids);
	}
	public List<Nav> findNavList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return editorDao.findNavList(queryMap);
	}
	public int getNavTotalByEditor(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return editorDao.getNavTotalByEditor(queryMap);
	}
	public Nav findByNavName(String nav_name) {
		// TODO Auto-generated method stub
		return editorDao.findByNavName(nav_name);
	}
	public int addNavInfo(Nav nav) {
		// TODO Auto-generated method stub
		return editorDao.addNavInfo(nav);
	}
	public int deleteNavInfo(String ids) {
		// TODO Auto-generated method stub
		return editorDao.deleteNavInfo(ids);
	}
	public int editNavInfo(Nav nav) {
		// TODO Auto-generated method stub
		return editorDao.editNavInfo(nav);
	}
	public List<Manuscript> findManuscriptListByNavId(String ids) {
		// TODO Auto-generated method stub
		return editorDao.findManuscriptListByNavId(ids);
	}
	public int addDistributeInfo(Distribute distribute) {
		// TODO Auto-generated method stub
		return editorDao.addDistributeInfo(distribute);
	}
	public int updateManuscriptReMarks(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return editorDao.updateManuscriptReMarks(map);
	}
	public List<ExpertReview> queryExpertReviewList() {
		// TODO Auto-generated method stub
		return editorDao.queryExpertReviewList();
	}
	public List<Manuscript> findManuscriptListByEditorReview(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return editorDao.findManuscriptListByEditorReview(queryMap);
	}
	public int getManuscriptTotalByEditor(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return editorDao.getManuscriptTotalByEditor(queryMap);
	}
	public int addEditorReviewInfo(Map<String, Object> updateMap) {
		// TODO Auto-generated method stub
		return editorDao.addEditorReviewInfo(updateMap);
	}
	public List<Manuscript> findManuscriptListNodDistribute(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return editorDao.findManuscriptListNodDistribute(queryMap);
	}
	public int getTotalByEditorNodDistribute(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return editorDao.getTotalByEditorNodDistribute(queryMap);
	}

}
