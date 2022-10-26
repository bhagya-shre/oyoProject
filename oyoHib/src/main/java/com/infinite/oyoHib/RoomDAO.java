package com.infinite.oyoHib;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.mysql.cj.Query;

public class RoomDAO {
	
	SessionFactory sessionFactory;
//	public String generateRoomID(){
//		sessionFactory=SessionHelper.getConnection();
//		Session session=sessionFactory.openSession();
//		Criteria cr=session.createCriteria(Room.class);
//		List<Room> roomList=cr.list();
//		session.close();
//		String id=roomList.get(roomList.size()-1).getRoomid();
//		int id1=Integer.parseInt(id.substring(1));
//		id1++;
//		String id2=String.format("R%03d", id1);
//		return id2;	
//	}
//	
//	  public String addRoom(Room room){
//		 
//		  sessionFactory=SessionHelper.getConnection();
//		  Session session = sessionFactory.openSession();
//		  String roomId=generateRoomID();
//		  room.setRoomid(roomId);
//		  room.setStatus(Status.AVAILABLE);
//		 
//		  Criteria cr = session.createCriteria(Room.class);
//	        org.hibernate.Transaction transaction = session.beginTransaction();
//	        session.save(room);
//	        transaction.commit();
//	        return "room inserted";
//	  }
//	
//	  public String generateBookID(){
//			sessionFactory=SessionHelper.getConnection();
//			Session session=sessionFactory.openSession();
//			Criteria cr=session.createCriteria(Booking.class);
//			List<Booking> roomList=cr.list();
//			session.close();
//			String id=roomList.get(roomList.size()-1).getBookId();
//			int id1=Integer.parseInt(id.substring(1));
//			id1++;
//			String id2=String.format("B%03d", id1);
//			return id2;	
//		}
//	   
		public List<Room> showRoom(String roomid){
			sessionFactory = SessionHelper.getConnection();
			Session session = sessionFactory.openSession();
			Criteria cr = session.createCriteria(Room.class);
			//cr.add(Restrictions.eq("roomid", roomid));
			return cr.list();
		}
		public List<Billing> showBill(String bid){
			sessionFactory = SessionHelper.getConnection();
			Session session = sessionFactory.openSession();
			Criteria cr = session.createCriteria(Billing.class);
			return cr.list();
		}
//		
//		
//		public List<String> selectroom(String roomid){
//			sessionFactory = SessionHelper.getConnection();
//			Session session = sessionFactory.openSession();
//			EntityManager entityManager = null;
//			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//			CriteriaQuery<String> query = builder.createQuery(String.class);
//			Root<Room> product = query.from(Room.class);
//			
//			query.select(product.get("RoomID"));
//			List<String> resultList = entityManager.createQuery(query).getResultList();
//			//return resultList;
////			Query query = (Query) entityManager.createQuery("select RoomID from Room");
////			List<Room> resultList = query.;
//			return resultList;
//		}
public String generateRoomID() {
		
		sessionFactory = SessionHelper.getConnection();
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Room.class);
		List<Room> roomlist = cr.list();
		session.close();
		
		if(roomlist.size()==0) {
			return "R001";
		}
		else {
		String id = roomlist.get(roomlist.size()-1).getRoomid();
		int id1 = Integer.parseInt(id.substring(1));
		id1++;
		String id2 = String.format("R%03d", id1);
		return id2;
		}
	}
	
	//AddRoom 
	
	public String addRoom(Room room) {
		sessionFactory = SessionHelper.getConnection();
		Session session = sessionFactory.openSession();
		String roomid=generateRoomID();
		room.setRoomid(roomid);
		room.setStatus(Status.AVAILABLE);
		Criteria cr = session.createCriteria(Room.class);
		Transaction tran = session.beginTransaction();
		session.save(room);
		tran.commit();
		return "Added Room";
		
	}
	
//date
	public Date convertDate(java.util.Date dt) {
		java.sql.Date sqlDate = new java.sql.Date(dt.getTime());
		return sqlDate;
	}

	//Booking
		public String bookingRoom(Booking booking) {
			sessionFactory = SessionHelper.getConnection();
			Session session = sessionFactory.openSession();
			Criteria cr = session.createCriteria(Booking.class);
			String bookId = generateBookID();
    		booking.setBookId(bookId);
			
			java.util.Date date = new java.util.Date();
			Date bookDate = new Date(date.getTime());
			booking.setBookDate(bookDate);
			
			Transaction transaction = session.beginTransaction();
			session.save(booking);
			transaction.commit();
			session.close();
			
			Room rooms = room(booking.getRoomId());
			rooms.setStatus(Status.BOOKED);
			
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(rooms);
			transaction.commit();
			session.close();
		
			return "Room Booked.";
		}
	
		
//bookingSearch
		public Booking bookings(String roomId) {
			sessionFactory = SessionHelper.getConnection();
			Session session = sessionFactory.openSession();
			
			Criteria criteria = session.createCriteria(Booking.class);
			criteria.add(Restrictions.eq("roomId", roomId));
			List<Booking> bookingList = criteria.list();
			
			return bookingList.get(0);
		}
		
		//roomSearch
		
		public Room room(String roomId) {
			sessionFactory = SessionHelper.getConnection();
			Session session = sessionFactory.openSession();
			
			Criteria criteria = session.createCriteria(Room.class);
			criteria.add(Restrictions.eq("roomid", roomId));
			List<Room> roomList = criteria.list();
			
			return roomList.get(0);
		}
		
		//noOfDays
		
		public int noOfDays(Date chkInDate, Date chkOutDate) {
			
			int totalDays= chkOutDate.getDate() - chkInDate.getDate();
			return ++totalDays;
		}
		
		//billing
		
		public String billing(String roomId) {
			sessionFactory = SessionHelper.getConnection();
			Session session = sessionFactory.openSession();
			
			Billing billing = new Billing();
			Booking booking = bookings(roomId);
			Room rooms = room(roomId);
			
			int noOfDays = booking.getChkOutDate().getDate() - booking.getChkInDate().getDate() + 1;
			long billAmt = noOfDays * rooms.getCostPerDay();
			
			
			billing.setRoomId(roomId);
			billing.setNoOfDays(noOfDays);
			billing.setBillAmt(billAmt);
			billing.setBookId(booking.getBookId());
			
			Transaction transaction = session.beginTransaction();
			session.save(billing);
			transaction.commit();
			session.close();
			
			rooms.setStatus(Status.AVAILABLE);
			
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(rooms);
			transaction.commit();
			session.close();
			
			return "bill is =  "+ billAmt + " For room Id = "+roomId+" ...";
		}
	  //GenerateBookId
	
		public String generateBookID() {
			
			sessionFactory = SessionHelper.getConnection();
			Session session = sessionFactory.openSession();
			Criteria cr = session.createCriteria(Booking.class);
			List<Booking> booklist = cr.list();
			session.close();
			
			if(booklist.size()==0) {
				return "B001";
			}
			else {
			String id = booklist.get(booklist.size()-1).getBookId();
			int id1 = Integer.parseInt(id.substring(1));
			id1++;
			String id2 = String.format("B%03d", id1);
			return id2;
			}
		}
		
		//ShowAvailability
		
		public List<String> showAvailableRooms(){
			
			sessionFactory = SessionHelper.getConnection();
			Session session = sessionFactory.openSession();
			Criteria cr = session.createCriteria(Room.class);
			cr.add(Restrictions.eq("status",Status.AVAILABLE));
			Projection projection = Projections.property("roomid");
			cr.setProjection(projection);
			List<String> list = cr.list();
			return list;
		}
		
		
		
		

}

