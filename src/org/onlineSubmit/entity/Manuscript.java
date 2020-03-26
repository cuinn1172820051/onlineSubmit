package org.onlineSubmit.entity;
import java.util.Date;

import org.springframework.stereotype.Component;
/**
 * 
 * @author Administrator
 *
 */
@Component
public class Manuscript {
	private int manuscript_id;
	private int id;
	private String author_name;
	private String title;
	private String summary;//ժҪ
	private String submit_time;
	private String filePath;
	private String state;
	private int nav_id;
	private String remarks;
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getNav_id() {
		return nav_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNav_id(int nav_id) {
		this.nav_id = nav_id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public int getManuscript_id() {
		return manuscript_id;
	}
	public void setManuscript_id(int manuscript_id) {
		this.manuscript_id = manuscript_id;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getSubmit_time() {
		return submit_time;
	}
	public void setSubmit_time(String submit_time) {
		this.submit_time = submit_time;
	}
	
}
