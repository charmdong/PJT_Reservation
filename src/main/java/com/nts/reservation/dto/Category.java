package com.nts.reservation.dto;

/**
 * 카테고리 dto
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 17.
 */
public class Category {
	private int id;
	private String name;
	
	/**
	 * 쿼리를 통해 전달되는 추가 변수
	 */
	private int count;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "CategoryDto [id=" + id + ", name=" + name + ", count=" + count + "]";
	}
}
