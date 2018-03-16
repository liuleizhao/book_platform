package com.cs.book.entity;

import java.util.Date;

import com.cs.common.entityenum.BookStateEnum;
import com.cs.mvc.dao.BaseEntity;

public class BookInfo extends BaseEntity{

	private static final long serialVersionUID = 3180114580774933398L;

	private String carTypeId;

    private String platNumber;

    private String frameNumber;

    private String mobile;

    private String bookDate;

    private String bookTime;

    private String stationId;

    private BookStateEnum bookState;

    private String bookNumber;

    private Date createDate;

    private int bookChannel;

    private String bookerName;

    private String idTypeId;

    private String idNumber;

    private String useCharater;

    private String vehicleType;

    private Date checkTime;

    private String checkStation;

    private String checkSerialNumber;

    private int checkOperationMark;

    private String checkState;

    private String engineNumber;

    private String driverType;

    private String fuelType;

    private String carTypeName;

    private String stationName;

    private String idTypeName;

    private String userId;

    private String userName;

    private String vehicleCharacter;

    private String requestIp;

    private String verifyCode;

    private String otherIdNumber;

    private String newflag;

    private String compApplyFormId;

    private String field1;

    private String field2;

    private String field3;

    private String field4;

	public String getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(String carTypeId) {
		this.carTypeId = carTypeId;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBookDate() {
		return bookDate;
	}

	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}

	public String getBookTime() {
		return bookTime;
	}

	public void setBookTime(String bookTime) {
		this.bookTime = bookTime;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public BookStateEnum getBookState() {
		return bookState;
	}

	public void setBookState(BookStateEnum bookState) {
		this.bookState = bookState;
	}

	public String getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(String bookNumber) {
		this.bookNumber = bookNumber;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getBookChannel() {
		return bookChannel;
	}

	public void setBookChannel(int bookChannel) {
		this.bookChannel = bookChannel;
	}

	public String getBookerName() {
		return bookerName;
	}

	public void setBookerName(String bookerName) {
		this.bookerName = bookerName;
	}

	public String getIdTypeId() {
		return idTypeId;
	}

	public void setIdTypeId(String idTypeId) {
		this.idTypeId = idTypeId;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getUseCharater() {
		return useCharater;
	}

	public void setUseCharater(String useCharater) {
		this.useCharater = useCharater;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getCheckStation() {
		return checkStation;
	}

	public void setCheckStation(String checkStation) {
		this.checkStation = checkStation;
	}

	public String getCheckSerialNumber() {
		return checkSerialNumber;
	}

	public void setCheckSerialNumber(String checkSerialNumber) {
		this.checkSerialNumber = checkSerialNumber;
	}

	public int getCheckOperationMark() {
		return checkOperationMark;
	}

	public void setCheckOperationMark(int checkOperationMark) {
		this.checkOperationMark = checkOperationMark;
	}

	public String getCheckState() {
		return checkState;
	}

	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
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

	public String getCarTypeName() {
		return carTypeName;
	}

	public void setCarTypeName(String carTypeName) {
		this.carTypeName = carTypeName;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getIdTypeName() {
		return idTypeName;
	}

	public void setIdTypeName(String idTypeName) {
		this.idTypeName = idTypeName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getVehicleCharacter() {
		return vehicleCharacter;
	}

	public void setVehicleCharacter(String vehicleCharacter) {
		this.vehicleCharacter = vehicleCharacter;
	}

	public String getRequestIp() {
		return requestIp;
	}

	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getOtherIdNumber() {
		return otherIdNumber;
	}

	public void setOtherIdNumber(String otherIdNumber) {
		this.otherIdNumber = otherIdNumber;
	}

	public String getNewflag() {
		return newflag;
	}

	public void setNewflag(String newflag) {
		this.newflag = newflag;
	}

	public String getCompApplyFormId() {
		return compApplyFormId;
	}

	public void setCompApplyFormId(String compApplyFormId) {
		this.compApplyFormId = compApplyFormId;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}
   
}