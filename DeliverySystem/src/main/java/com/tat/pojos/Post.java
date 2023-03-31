/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.pojos;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author trant
 */
@Entity
@Table(name = "post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p"),
    @NamedQuery(name = "Post.findById", query = "SELECT p FROM Post p WHERE p.id = :id"),
    @NamedQuery(name = "Post.findByCreatedDate", query = "SELECT p FROM Post p WHERE p.createdDate = :createdDate"),
    @NamedQuery(name = "Post.findByUpdatedDate", query = "SELECT p FROM Post p WHERE p.updatedDate = :updatedDate"),
    @NamedQuery(name = "Post.findByActive", query = "SELECT p FROM Post p WHERE p.active = :active"),
    @NamedQuery(name = "Post.findByProductName", query = "SELECT p FROM Post p WHERE p.productName = :productName"),
    @NamedQuery(name = "Post.findByProductImg", query = "SELECT p FROM Post p WHERE p.productImg = :productImg"),
    @NamedQuery(name = "Post.findByFromAddress", query = "SELECT p FROM Post p WHERE p.fromAddress = :fromAddress"),
    @NamedQuery(name = "Post.findByToAddress", query = "SELECT p FROM Post p WHERE p.toAddress = :toAddress"),
    @NamedQuery(name = "Post.findByDescription", query = "SELECT p FROM Post p WHERE p.description = :description")})
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull(message = "{post.null}")
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @NotNull(message = "{post.null}")
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Basic(optional = false)
    @NotNull(message = "{post.null}")
    @Column(name = "active")
    private boolean active;
    @Basic(optional = false)
    @NotNull(message = "{post.null}")
    @Size(min = 1, max = 50, message = "{post.product_name.len}")
    @Column(name = "product_name")
    private String productName;
    @Basic(optional = false)
    @NotNull(message = "{post.null}")
    @Size(min = 1, max = 255)
    @Column(name = "product_img")
    private String productImg;
    @Basic(optional = false)
    @NotNull(message = "{post.null}")
    @Size(min = 1, max = 150)
    @Column(name = "from_address")
    private String fromAddress;
    @Basic(optional = false)
    @NotNull(message = "{post.null}")
    @Size(min = 1, max = 150)
    @Column(name = "to_address")
    private String toAddress;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Customer customerId;
    @JoinColumn(name = "discount_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Discount discountId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postId")
    private Set<Auction> auctionSet;
    @Transient
    private MultipartFile file;

    {
        this.active = true;
    }
    
    public Post() {
    }

    public Post(Long id) {
        this.id = id;
    }

    public Post(Long id, Date createdDate, Date updatedDate, boolean active, String productName, String productImg, String fromAddress, String toAddress) {
        this.id = id;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.active = active;
        this.productName = productName;
        this.productImg = productImg;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Discount getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Discount discountId) {
        this.discountId = discountId;
    }

    @XmlTransient
    public Set<Auction> getAuctionSet() {
        return auctionSet;
    }

    public void setAuctionSet(Set<Auction> auctionSet) {
        this.auctionSet = auctionSet;
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
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tat.pojos.Post[ id=" + id + " ]";
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    
}
