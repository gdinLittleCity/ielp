package cn.edu.gdin.ilep.page;

import java.util.List;

import cn.edu.gdin.ilep.util.Constant;
/**
 * pageSize 页大小 ---默认为5<br/>
 * pageNum 当前页码 ---默认为1<br/>
 * pageTotal 总页数<br/>
 * beanList 单页数据列表<br/>
 * total 当前数据的总数量<br/>
 * @author administor
 * @param <T>
 */
public class PageBean<T> {
	//页大小
	private int pageSize=Constant.PAGE_SIZE;
	//当前页码
	private int pageNum=Constant.PAGE_NUM;
	//总页数
	private int pageTotal;
	//单页数据
	private List<T> beanList;
	//当前数据的总数 --分页查询使用
	private int total;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	public int getTotal() {
		int num =this.pageNum;
		int size = this.pageSize;
		this.total= (num-1)*size;
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
