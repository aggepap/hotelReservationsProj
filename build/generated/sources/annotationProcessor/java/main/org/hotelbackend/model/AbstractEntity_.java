package org.hotelbackend.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.MappedSuperclassType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@StaticMetamodel(AbstractEntity.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class AbstractEntity_ {

	
	/**
	 * @see org.hotelbackend.model.AbstractEntity#createdAt
	 **/
	public static volatile SingularAttribute<AbstractEntity, LocalDateTime> createdAt;
	
	/**
	 * @see org.hotelbackend.model.AbstractEntity
	 **/
	public static volatile MappedSuperclassType<AbstractEntity> class_;
	
	/**
	 * @see org.hotelbackend.model.AbstractEntity#updatedAt
	 **/
	public static volatile SingularAttribute<AbstractEntity, LocalDateTime> updatedAt;

	public static final String CREATED_AT = "createdAt";
	public static final String UPDATED_AT = "updatedAt";

}

