package po;

import java.io.Serializable;
import java.util.ArrayList;
import enums.Condition;
import enums.DocumentCondition;
import enums.Position;
import enums.Traffic;
import enums.TransportType;

public class TransportPO implements Serializable {
	private TransportType sign;
	private String id;
	private Position departure;
	private Position destination;
	private String time;
	private Traffic traffic;
	private double fare;
	private String nameOfWriter;
	private ArrayList<String> member;
	private ArrayList<String> order;
	private ArrayList<Condition> condition;
	private DocumentCondition dCondition;

	public TransportPO(TransportType sign, String id, Position departure, Position destination,
			String time, Traffic traffic, double fare, ArrayList<String> member,
<<<<<<< HEAD
			ArrayList<String> order, ArrayList<Condition> condition,DocumentCondition dCondition,String nameOfWriter) {
=======
			ArrayList<String> order, ArrayList<Condition> condition,DocumentCondition dCondition) {
>>>>>>> origin/master
		this.id=id;
		this.sign=sign;
		this.departure=departure;
		this.destination=destination;
		this.time=time;
		this.traffic=traffic;
		this.fare=fare;
		this.member=member;
		this.order=order;
		this.condition=condition;
		this.dCondition = dCondition;
<<<<<<< HEAD
		this.nameOfWriter = nameOfWriter;
	}
	
	public String  getNameOfWriter(){
		return nameOfWriter;
	}
	
	public void setNameOfWriter(String nameOfWriter){
		this.nameOfWriter = nameOfWriter;
	}
	
=======
	}
	
>>>>>>> origin/master
	public DocumentCondition getdCondition() {
		return dCondition;
	}

	public void setdCondition(DocumentCondition dCondition) {
		this.dCondition = dCondition;
	}

	public TransportType getSign(){
		return sign;
	}
	public String getID(){
		return id;
	}

	public String getTime(){
		return time;
	}
	public Traffic getTraffic(){
		return traffic;
	}
	public double getfare(){
		return fare;
	}
	public  ArrayList<String> getMember(){
		return member;
	}
	public  ArrayList<String> getOrder(){
		return order;
	}
	public  ArrayList<Condition> getCondition(){
		return condition;
	}
	public  void setSign(TransportType sign){
		this.sign=sign;
	}
	public  void setID(String id){
		this.id=id;
	}
	
	public Position getDeparture() {
		return departure;
	}

	public void setDeparture(Position departure) {
		this.departure = departure;
<<<<<<< HEAD
	}

	public Position getDestination() {
		return destination;
	}

=======
	}

	public Position getDestination() {
		return destination;
	}

>>>>>>> origin/master
	public void setDestination(Position destination) {
		this.destination = destination;
	}

	public  void setTime(String time){
		this.time=time;
	}
	public  void setTraffic(Traffic traffic){
		this.traffic=traffic;
	}
	public  void setFare(double fare){
		this.fare=fare;
	}
	public  void setMember(ArrayList<String> member){
		this.member=member;
	}
	public void setOrder(ArrayList<String> order){
		this.order=order;
	}
	public void setCondition(ArrayList<Condition> condition){
		this.condition=condition;
	}
}
