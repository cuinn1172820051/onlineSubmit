package org.onlineSubmit.entity;
import org.springframework.stereotype.Component;
/**
 * ������@Component��@Service��@Repositoryע�ⶼ�Ὣ�������spring������
 * @author Administrator
 *
 */
@Component
public class ExpertReview {
	private Integer expert_review_id;
	private Integer manuscript_id;
	private Integer expert_id;
	private String expert_opinion;//�༭���
	private String expert_review_time;//�༭����ʱ��
	public Integer getExpert_review_id() {
		return expert_review_id;
	}
	public void setExpert_review_id(Integer expert_review_id) {
		this.expert_review_id = expert_review_id;
	}
	public Integer getManuscript_id() {
		return manuscript_id;
	}
	public void setManuscript_id(Integer manuscript_id) {
		this.manuscript_id = manuscript_id;
	}
	public Integer getExpert_id() {
		return expert_id;
	}
	public void setExpert_id(Integer expert_id) {
		this.expert_id = expert_id;
	}
	public String getExpert_opinion() {
		return expert_opinion;
	}
	public void setExpert_opinion(String expert_opinion) {
		this.expert_opinion = expert_opinion;
	}
	public String getExpert_review_time() {
		return expert_review_time;
	}
	public void setExpert_review_time(String expert_review_time) {
		this.expert_review_time = expert_review_time;
	}
	
}
