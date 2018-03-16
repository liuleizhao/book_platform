package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;

import com.cs.common.utils.EnumUtils;
import com.cs.mvc.mybatis.enumhandler.Identifiable;

/**
 * 
 * 深圳地区枚举
 */
public enum DistrictEnum implements Identifiable<Integer> {
	
	LUOHU(1,"罗湖区"),
	FUTIAN(2,"福田区"),
	NANSHAN(3,"南山区"),
	BAOAN(4,"宝安区"),
	LONGGANG(5,"龙岗区"),
	YANTIAN(6,"盐田区"),
	LONGHUA(7,"龙华区"),
	PINGSHAN(8,"坪山区"),
	GUANGMING(9,"光明新区"),
	DAPENG(10,"大鹏新区");
	
	private int index;
	private String description;

	private DistrictEnum(int index, String description) {
		this.index = index;
		this.description = description;
	}

	@Override
	public Integer getId() {
		return this.index;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	public static List<DistrictEnum> getAll() {
		return Arrays.asList(DistrictEnum.class.getEnumConstants());
	}

	public static DistrictEnum findByIndex(Integer index) {
		return EnumUtils.getEnum(DistrictEnum.class, index);
	}

	public String getValue() {
		return this.name();
	}

	public String toString() {
		return this.name();
	}

}
