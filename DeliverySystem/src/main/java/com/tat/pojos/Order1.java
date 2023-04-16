/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.pojos;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author trant
 */
@Entity
@Table(name = "order_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Order1.findAll", query = "SELECT o FROM Order1 o"),
    @NamedQuery(name = "Order1.findById", query = "SELECT o FROM Order1 o WHERE o.id = :id"),
    @NamedQuery(name = "Order1.findByCreatedDate", query = "SELECT o FROM Order1 o WHERE o.createdDate = :createdDate"),
    @NamedQuery(name = "Order1.findByUpdatedDate", query = "SELECT o FROM Order1 o WHERE o.updatedDate = :updatedDate"),
    @NamedQuery(name = "Order1.findByActive", query = "SELECT o FROM Order1 o WHERE o.active = :active"),
    @NamedQuery(name = "Order1.findByStatusOrder", query = "SELECT o FROM Order1 o WHERE o.statusOrder = :statusOrder")})
public class Order1 implements Serializable {

    private static final long serialVersionUID = 3L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    private String createdDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_date")
    private String updatedDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status_order")
    private String statusOrder;
    @JoinColumn(name = "auction_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Auction auctionId;
    @JoinColumn(name = "shipper_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Shipper shipperId;

    {
        this.active = true;
        DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date today = new Date();
        this.createdDate = formatDate.format(today);
        this.updatedDate = formatDate.format(today);
    }
    
    public Order1() {
    }

    public Order1(Integer id) {
        this.id = id;
    }

    public Order1(Integer id, String createdDate, String updatedDate, boolean active, String statusOrder) {
        this.id = id;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.active = active;
        this.statusOrder = statusOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public Auction getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Auction auctionId) {
        this.auctionId = auctionId;
    }

    public Shipper getShipperId() {
        return shipperId;
    }

    public void setShipperId(Shipper shipperId) {
        this.shipperId = shipperId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order1)) {
            return false;
        }
        Order1 other = (Order1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tat.pojos.Order1[ id=" + id + " ]";
    }
    
}
