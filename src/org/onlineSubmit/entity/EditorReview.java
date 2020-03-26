package org.onlineSubmit.entity;
import org.springframework.stereotype.Component;
/**
 * ������@Component��@Service��@Repositoryע�ⶼ�Ὣ�������spring������
 * @author Administrator
 *
 */
@Component
public class EditorReview {
	private Integer editor_review_id;
	private Integer munuscript_id;
	private Integer editor_id;
	private String editor_opinion;//�༭���
	private String editor_review_time;//�༭����ʱ��
	public Integer getEditor_review_id() {
		return editor_review_id;
	}
	public void setEditor_review_id(Integer editor_review_id) {
		this.editor_review_id = editor_review_id;
	}
	public Integer getMunuscript_id() {
		return munuscript_id;
	}
	public void setMunuscript_id(Integer munuscript_id) {
		this.munuscript_id = munuscript_id;
	}
	public Integer getEditor_id() {
		return editor_id;
	}
	public void setEditor_id(Integer editor_id) {
		this.editor_id = editor_id;
	}
	public String getEditor_opinion() {
		return editor_opinion;
	}
	public void setEditor_opinion(String editor_opinion) {
		this.editor_opinion = editor_opinion;
	}
	public String getEditor_review_time() {
		return editor_review_time;
	}
	public void setEditor_review_time(String editor_review_time) {
		this.editor_review_time = editor_review_time;
	}
	
}
