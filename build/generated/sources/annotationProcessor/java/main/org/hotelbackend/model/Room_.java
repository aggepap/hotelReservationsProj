package org.hotelbackend.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Room.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Room_ {

	
	/**
	 * @see org.hotelbackend.model.Room#roomNumber
	 **/
	public static volatile SingularAttribute<Room, Integer> roomNumber;
	
	/**
	 * @see org.hotelbackend.model.Room#hasSeaView
	 **/
	public static volatile SingularAttribute<Room, Boolean> hasSeaView;
	
	/**
	 * @see org.hotelbackend.model.Room#id
	 **/
	public static volatile SingularAttribute<Room, Long> id;
	
	/**
	 * @see org.hotelbackend.model.Room#floor
	 **/
	public static volatile SingularAttribute<Room, Integer> floor;
	
	/**
	 * @see org.hotelbackend.model.Room
	 **/
	public static volatile EntityType<Room> class_;
	
	/**
	 * @see org.hotelbackend.model.Room#capacity
	 **/
	public static volatile SingularAttribute<Room, Integer> capacity;

	public static final String ROOM_NUMBER = "roomNumber";
	public static final String HAS_SEA_VIEW = "hasSeaView";
	public static final String ID = "id";
	public static final String FLOOR = "floor";
	public static final String CAPACITY = "capacity";

}

