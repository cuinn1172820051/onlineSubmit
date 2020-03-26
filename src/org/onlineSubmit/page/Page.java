package org.onlineSubmit.page;

import org.springframework.stereotype.Component;

@Component
public class Page {
	private int page;//页面数
	private int rows;//每页显示数量
	private int offset;//页起始记录数
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset() {
		this.offset = (this.page-1)*rows;
	}
}
