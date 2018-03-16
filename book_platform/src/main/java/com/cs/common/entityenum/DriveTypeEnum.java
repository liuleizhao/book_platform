package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;
import com.cs.mvc.mybatis.enumhandler.Identifiable;
import com.cs.common.utils.EnumUtils;
/**
 * 机动车类型
 * @author LLZ
 */
public enum DriveTypeEnum implements Identifiable<Integer>{
	
	TWO_WHEEL_DRIVE(0,"两驱（含非全时四驱）"), 
	FOUR_WHEEL_DRIVE(1,"全时四驱"), 
	TWO_AXIS_UNDER(2,"两轴及以下"), 
	TWO_AXIS(3,"两轴"), 
	THREE_AXIS_ABOVE_TRAILER(4,"三轴及以上（挂车）"), 
	THREE_AXIS_ABOVE_LARGERCAR(5,"三轴及以上（大型汽车）"), 
	SINGLE_AXLE(6,"单轴轴重超15吨"), 
	TWO_AXIS_ABOVE(7,"并装双轴及以上");
     
	    private int id;  
	    private String description;
	      
	    private DriveTypeEnum(int type ,String description){  
	        this.id = type;  
	        this.description = description;
	    }  
	      
	    @Override  
	    public Integer getId(){  
	        return this.id;  
	    }

		@Override
		public String getDescription() {
			return  this.description;
		}  
		
		
		public static List<DriveTypeEnum> getAll() {
			return Arrays.asList(DriveTypeEnum.class.getEnumConstants());
		}

		public static DriveTypeEnum findByIndex(Integer index) {
			return EnumUtils.getEnum(DriveTypeEnum.class, index);
		}

		public String getValue() {
			return this.name();
		}

		public String toString() {
			return this.name();
		}
		

}
