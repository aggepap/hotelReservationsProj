package org.hotelbackend.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.hotelbackend.core.CountryCode;

@StaticMetamodel(Resident.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Resident_ extends org.hotelbackend.model.AbstractEntity_ {

	
	/**
	 * @see org.hotelbackend.model.Resident#countryCode
	 **/
	public static volatile SingularAttribute<Resident, CountryCode> countryCode;
	
	/**
	 * @see org.hotelbackend.model.Resident#name
	 **/
	public static volatile SingularAttribute<Resident, String> name;
	
	/**
	 * @see org.hotelbackend.model.Resident#vat
	 **/
	public static volatile SingularAttribute<Resident, String> vat;
	
	/**
	 * @see org.hotelbackend.model.Resident#reservation
	 **/
	public static volatile SingularAttribute<Resident, Reservation> reservation;
	
	/**
	 * @see org.hotelbackend.model.Resident#id
	 **/
	public static volatile SingularAttribute<Resident, Long> id;
	
	/**
	 * @see org.hotelbackend.model.Resident#idNumber
	 **/
	public static volatile SingularAttribute<Resident, String> idNumber;
	
	/**
	 * @see org.hotelbackend.model.Resident
	 **/
	public static volatile EntityType<Resident> class_;
	
	/**
	 * @see org.hotelbackend.model.Resident#age
	 **/
	public static volatile SingularAttribute<Resident, Integer> age;
	
	/**
	 * @see org.hotelbackend.model.Resident#lastname
	 **/
	public static volatile SingularAttribute<Resident, String> lastname;

	public static final String COUNTRY_CODE = "countryCode";
	public static final String NAME = "name";
	public static final String VAT = "vat";
	public static final String RESERVATION = "reservation";
	public static final String ID = "id";
	public static final String ID_NUMBER = "idNumber";
	public static final String AGE = "age";
	public static final String LASTNAME = "lastname";

}

