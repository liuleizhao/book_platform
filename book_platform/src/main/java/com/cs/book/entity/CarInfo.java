package com.cs.book.entity;

import java.util.Date;

import com.cs.mvc.dao.BaseEntity;

public class CarInfo extends BaseEntity {
	
	private static final long serialVersionUID = 1184907732629704332L;

	/*用户id*/
	private String userId;
	
	 /*新旧车标记  1新车  0旧车 */
    private String newflag;

	/*车牌号*/
    private String platNumber;

    /*车架号*/
    private String frameNumber;

    /*发动机号  暂时不用*/
    private String engineNumber;
 
    /*车辆类型*/
    private String vehicleType;
    
    /*车辆性质*/
    private String vehicleCharacter;
    
    /*号牌种类*/
    private String carTypeId;

    /*驱动类型*/
    private String driverType;
    
    /*燃油类型*/
    private String fuelType;

    /*使用性质   暂时不用*/
    private String useCharater;

    /*创建时间*/
    private Date createDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNewflag() {
		return newflag;
	}

	public void setNewflag(String newflag) {
		this.newflag = newflag;
	}

	public String getPlatNumber() {
		return platNumber;
	}

	public void setPlatNumber(String platNumber) {
		this.platNumber = platNumber;
	}

	public String getFrameNumber() {
		return frameNumber;
	}

	public void setFrameNumber(String frameNumber) {
		this.frameNumber = frameNumber;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	public String getVehicleCharacter() {
		return vehicleCharacter;
	}

	public void setVehicleCharacter(String vehicleCharacter) {
		this.vehicleCharacter = vehicleCharacter;
	}

	public String getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(String carTypeId) {
		this.carTypeId = carTypeId;
	}

	public String getDriverType() {
		return driverType;
	}

	public void setDriverType(String driverType) {
		this.driverType = driverType;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getUseCharater() {
		return useCharater;
	}

	public void setUseCharater(String useCharater) {
		this.useCharater = useCharater;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}