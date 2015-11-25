 package po;

import enums.Sex;
import enums.Work;

public class DriverPO extends StaffPO{
	public int driverYear;
	public DriverPO(String name,Work work,String workNumber,String workPlaceNumber,
			String birthDate,String idNumber,String phoneNumber,String address,Sex sex,int driverYear,double page){
		this.name = name;
		this.work = work;
		this.workNumber = workNumber;
		this.workPlaceNumber = workPlaceNumber;
		this.birthDate = birthDate;
		this.idNumber = idNumber;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.sex = sex;
		this.page = page;
		this.driverYear = driverYear;
		}
	
	public void setDriverYear(int driverYear){
		this.driverYear = driverYear;
	}
	public int getDriverYear(){
		return driverYear;
	}
	
	public DriverPO(){}
}
