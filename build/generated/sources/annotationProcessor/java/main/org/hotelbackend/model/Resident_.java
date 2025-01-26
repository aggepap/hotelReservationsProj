package org.hotelbackend.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hotelbackend.core.CountryCode;
import org.hotelbackend.core.Gender;

@StaticMetamodel(Resident.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Resident_ {

	
	/**
	 * @see org.hotelbackend.model.Resident#firstname
	 **/
	public static volatile SingularAttribute<Resident, String> firstname;
	
	/**
	 * @see org.hotelbackend.model.Resident#address
	 **/
	public static volatile SingularAttribute<Resident, String> address;
	
	/**
	 * @see org.hotelbackend.model.Resident#gender
	 **/
	public static volatile SingularAttribute<Resident, Gender> gender;
	
	/**
	 * @see org.hotelbackend.model.Resident#vat
	 **/
	public static volatile SingularAttribute<Resident, String> vat;
	
	/**
	 * @see org.hotelbackend.model.Resident#idNumber
	 **/
	public static volatile SingularAttribute<Resident, String> idNumber;
	
	/**
	 * @see org.hotelbackend.model.Resident#birthDate
	 **/
	public static volatile SingularAttribute<Resident, LocalDate> birthDate;
	
	/**
	 * @see org.hotelbackend.model.Resident#lastname
	 **/
	public static volatile SingularAttribute<Resident, String> lastname;
	
	/**
	 * @see org.hotelbackend.model.Resident#createdAt
	 **/
	public static volatile SingularAttribute<Resident, LocalDateTime> createdAt;
	
	/**
	 * @see org.hotelbackend.model.Resident#phoneNumber
	 **/
	public static volatile SingularAttribute<Resident, String> phoneNumber;
	
	/**
	 * @see org.hotelbackend.model.Resident#countryCode
	 **/
	public static volatile SingularAttribute<Resident, CountryCode> countryCode;
	
	/**
	 * @see org.hotelbackend.model.Resident#reservation
	 **/
	public static volatile SingularAttribute<Resident, Reservation> reservation;
	
	/**
	 * @see org.hotelbackend.model.Resident#id
	 **/
	public static volatile SingularAttribute<Resident, Long> id;
	
	/**
	 * @see org.hotelbackend.model.Resident
	 **/
	public static volatile EntityType<Resident> class_;
	
	/**
	 * @see org.hotelbackend.model.Resident#email
	 **/
	public static volatile SingularAttribute<Resident, String> email;
	
	/**
	 * @see org.hotelbackend.model.Resident#isAdult
	 **/
	public static volatile SingularAttribute<Resident, Boolean> isAdult;
	
	/**
	 * @see org.hotelbackend.model.Resident#updatedAt
	 **/
	public static volatile SingularAttribute<Resident, LocalDateTime> updatedAt;

	public static final String FIRSTNAME = "firstname";
	public static final String ADDRESS = "address";
	public static final String GENDER = "gender";
	public static final String VAT = "vat";
	public static final String ID_NUMBER = "idNumber";
	public static final String BIRTH_DATE = "birthDate";
	public static final String LASTNAME = "lastname";
	public static final String CREATED_AT = "createdAt";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String COUNTRY_CODE = "countryCode";
	public static final String RESERVATION = "reservation";
	public static final String ID = "id";
	public static final String EMAIL = "email";
	public static final String IS_ADULT = "isAdult";
	public static final String UPDATED_AT = "updatedAt";

}

