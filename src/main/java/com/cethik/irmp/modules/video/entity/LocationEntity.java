package com.cethik.irmp.modules.video.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 菜单
 *
 * @author daniel.yu
 * @date 2019年6月23日 上午11:07:36
 */
public class LocationEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单id
	 */
	private Long locationId;

	/**
	 * 父级id，一级菜单为0
	 */
	private Long parentId;

	/**
	 * 父级菜单名称
	 */
	private String parentName;

	/**
	 * 菜单名称
	 */
	private String name;

	/**
	 * 类型(0：目录   1：菜单   2：按钮)
	 */
	private Integer type;

	/**
	 * 菜单图标
	 */
	private String icon;

	/**
	 * 排序
	 */
	private Integer orderNum;

	/**
	 * 创建时间
	 */
	private Timestamp gmtCreate;

	/**
	 * 修改时间
	 */
	private Timestamp gmtModified;

	/**
	 * ztree属性
	 */
	private Boolean open;

	private List<?> list;

	public LocationEntity() {
		super();
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Timestamp getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Timestamp gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Timestamp getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Timestamp gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

}
