package org.onlineSubmit.entity;
import org.springframework.stereotype.Component;
/**
 * 凡是用@Component，@Service，@Repository注解都会将对象放在spring容器里
 * @author Administrator
 *
 */
@Component
public class Editor {
	private Integer editor_id;
	private String username;
	private String password;
	private String editor_name;
	private String sex;
	public Integer getEditor_id() {
		return editor_id;
	}
	public void setEditor_id(Integer editor_id) {
		this.editor_id = editor_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEditor_name() {
		return editor_name;
	}
	public void setEditor_name(String editor_name) {
		this.editor_name = editor_name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
