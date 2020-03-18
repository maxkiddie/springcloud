/**
 * 
 */
package com.ydy.base.vo;

import java.util.List;

/**
 * @author xuzhaojie
 *
 *         2018年8月3日 下午5:29:17
 */
public class PageVo<T> {

	private List<T> list;
	private Long total = 0L;//
	private Integer totalPage = 1;
	private Integer size = 10;
	private Integer page = 1;

	public void setTotal(long total) {
		this.total = total;
		if (this.total < 0)
			this.total = 0 - this.total;
		resize();
	}

	private void resize() {
		this.totalPage = Integer.parseInt(
				"" + ((this.total % this.size) == 0 ? (this.total / this.size) : ((this.total / this.size) + 1)));
		if (this.page < 1) {
			this.page = 1;
		} else if (this.page > this.totalPage) {
			this.page = this.totalPage;
		}
	}

	public PageVo() {
		super();
	}

	public PageVo(Integer page, Integer size) {
		if (page != null)
			this.page = Math.abs(page);
		if (size != null) {
			if (size <= 0) {
				this.size = 10;
			} else if (size >= 50) {
				this.size = 50;
			} else {
				this.size = size;
			}
		}
	}

	/**
	 * @return the list
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<T> list) {
		this.list = list;
	}

	/**
	 * @return the total
	 */
	public Long getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Long total) {
		this.total = total;
	}

	/**
	 * @return the totalPage
	 */
	public Integer getTotalPage() {
		return totalPage;
	}

	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * @return the size
	 */
	public Integer getSize() {
		return size;
	}

}
