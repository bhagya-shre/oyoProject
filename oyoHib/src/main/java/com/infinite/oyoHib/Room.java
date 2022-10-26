package com.infinite.oyoHib;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Room")
public class Room {
    
	@Id
	@Column(name="RoomID")
	private String roomid;
	@Column(name="Type")
	private String type;
	@Enumerated(EnumType.STRING)
	@Column(name="Status")
	private Status status;
	@Column(name="CostPerDay")
	private int costPerDay;
	public String getRoomid() {
		return roomid;
	}
	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getCostPerDay() {
		return costPerDay;
	}
	public void setCostPerDay(int costPerDay) {
		this.costPerDay = costPerDay;
	}
	public Room(String roomid, String type, Status status, int costPerDay) {
		super();
		this.roomid = roomid;
		this.type = type;
		this.status = status;
		this.costPerDay = costPerDay;
	}
	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Room [roomid=" + roomid + ", type=" + type + ", status=" + status + ", costPerDay=" + costPerDay + "]";
	}
	
	
	   
}
