package org.hotelbackend.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import java.time.LocalDateTime;

@StaticMetamodel(Reservation.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Reservation_ {

	
	/**
	 * @see org.hotelbackend.model.Reservation#reservationEndDate
	 **/
	public static volatile SingularAttribute<Reservation, LocalDate> reservationEndDate;
	
	/**
	 * @see org.hotelbackend.model.Reservation#reservationStartDate
	 **/
	public static volatile SingularAttribute<Reservation, LocalDate> reservationStartDate;
	
	/**
	 * @see org.hotelbackend.model.Reservation#advancePaid
	 **/
	public static volatile SingularAttribute<Reservation, Boolean> advancePaid;
	
	/**
	 * @see org.hotelbackend.model.Reservation#isActive
	 **/
	public static volatile SingularAttribute<Reservation, Boolean> isActive;
	
	/**
	 * @see org.hotelbackend.model.Reservation#reservationBookedDate
	 **/
	public static volatile SingularAttribute<Reservation, LocalDateTime> reservationBookedDate;
	
	/**
	 * @see org.hotelbackend.model.Reservation#guestsNumber
	 **/
	public static volatile SingularAttribute<Reservation, Integer> guestsNumber;
	
	/**
	 * @see org.hotelbackend.model.Reservation#room
	 **/
	public static volatile SingularAttribute<Reservation, Room> room;
	
	/**
	 * @see org.hotelbackend.model.Reservation#reservationCode
	 **/
	public static volatile SingularAttribute<Reservation, String> reservationCode;
	
	/**
	 * @see org.hotelbackend.model.Reservation#createdAt
	 **/
	public static volatile SingularAttribute<Reservation, LocalDateTime> createdAt;
	
	/**
	 * @see org.hotelbackend.model.Reservation#residents
	 **/
	public static volatile SetAttribute<Reservation, Resident> residents;
	
	/**
	 * @see org.hotelbackend.model.Reservation#id
	 **/
	public static volatile SingularAttribute<Reservation, Long> id;
	
	/**
	 * @see org.hotelbackend.model.Reservation
	 **/
	public static volatile EntityType<Reservation> class_;
	
	/**
	 * @see org.hotelbackend.model.Reservation#updatedAt
	 **/
	public static volatile SingularAttribute<Reservation, LocalDateTime> updatedAt;

	public static final String RESERVATION_END_DATE = "reservationEndDate";
	public static final String RESERVATION_START_DATE = "reservationStartDate";
	public static final String ADVANCE_PAID = "advancePaid";
	public static final String IS_ACTIVE = "isActive";
	public static final String RESERVATION_BOOKED_DATE = "reservationBookedDate";
	public static final String GUESTS_NUMBER = "guestsNumber";
	public static final String ROOM = "room";
	public static final String RESERVATION_CODE = "reservationCode";
	public static final String CREATED_AT = "createdAt";
	public static final String RESIDENTS = "residents";
	public static final String ID = "id";
	public static final String UPDATED_AT = "updatedAt";

}

