package com.bite.bitespeed.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="CUSTOMER_CONTACT_MAPPING")
public class CustomerContactMapping {


    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name="PHONE_NUMBER")
    private String phoneNumber;

    @Column(name="EMAIL")
    private String email;

    @Column(name="LINKED_ID")
    //@OneToOne
    //@PrimaryKeyJoinColumn
    //LinkedID will be thew link to Primary Contact. (If present otherwise NULL);
    private Long linkedId;

    @Column(name="LINK_PRECEDENCE")
    @Enumerated(EnumType.STRING)
    private LinkPrecedence linkPrecedence;

    @Column(name="CREATED_AT")
    private Date createdAt;

    @Column(name="UPDATED_AT")
    private Date updatedAt;

    @Column(name="DELETED_AT")
    private Date deletedAt;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getLinkedId() {
        return linkedId;
    }

    public void setLinkedId(Long linkedId) {
        this.linkedId = linkedId;
    }

    public LinkPrecedence getLinkPrecedence() {
        return linkPrecedence;
    }

    public void setLinkPrecedence(LinkPrecedence linkPrecedence) {
        this.linkPrecedence = linkPrecedence;
    }

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

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}
