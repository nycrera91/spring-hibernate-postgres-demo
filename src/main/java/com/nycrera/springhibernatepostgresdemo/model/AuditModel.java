package com.nycrera.springhibernatepostgresdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Alperen Asa
 * Date: 19.05.2020
 * Time: 11:40
 * Project Name: spring-hibernate-postgres-demo
 */


//All your domain objects could have a creation date, modification date and ID, and you could
//thus make them inherit from a base AbstractDomainObject class. But no entity will ever have
//an association to an AbstractDomainObject. It will always be an association to a more specific
//entity: Customer, Company, whatever. In this case, it makes sense to use a MappedSuperClass.
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true) // serializable true but deserializable false
public abstract class AuditModel implements Serializable {


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Date updatedAt;


    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
