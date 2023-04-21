package com.example.demoall.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity 
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
public class Book extends AbstractAuditingEntity<Long>{
    
    private static final long serialVersionUID = 6517177107040400497L;

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private int price;
    
    private String description;
    
    private int quantity;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "nguoidung_book",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "nguoidung_id")})
    Set<NguoiDung> nguoiDung = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Set<NguoiDung> getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(Set<NguoiDung> nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    
    
}
