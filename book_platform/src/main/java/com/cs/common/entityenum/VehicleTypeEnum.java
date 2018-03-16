package com.cs.common.entityenum;

import java.util.Arrays;
import java.util.List;
import com.cs.mvc.mybatis.enumhandler.Identifiable;
import com.cs.common.utils.EnumUtils;
/**
 * 机动车类型
 * @author LLZ
 */
public enum VehicleTypeEnum implements Identifiable<Integer>{
	
		SMALL_CAR(1,"小型汽车"), 
		LARGERCAR(2,"大型汽车"), 
		TRAILER(3,"挂车");
     
	    private int id;  
	    private String description;
	      
	    private VehicleTypeEnum(int type ,String description){  
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
		
		
		public static List<VehicleTypeEnum> getAll() {
			return Arrays.asList(VehicleTypeEnum.class.getEnumConstants());
		}

		public static VehicleTypeEnum findByIndex(Integer index) {
			return EnumUtils.getEnum(VehicleTypeEnum.class, index);
		}

		public String getValue() {
			return this.name();
		}

		public String toString() {
			return this.name();
		}
		

}
