package com.example.demochauluc.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ngonngu")
public class NgonNgu extends AbstractAuditingEntity<Long>{
    private static final long serialVersionUID = 1L;
    
    @Column(name = "idNgonngu",unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String tenNgonNgu;
    
    private String mota;
    
    @Column(name = "ma_code",unique = true)
    private String code;
    
    @ManyToMany
    @JoinTable(
            name = "ngonngu_quocgia",
            joinColumns = @JoinColumn(name = "idQuocGia"),
            inverseJoinColumns = @JoinColumn(name = "idNgonNgu"))
    private Set<QuocGia> quocgia;
    
  //Chuyển thành chữ hoa trước khi vào db
    @PrePersist
    @PreUpdate
    private void beforeSave(){
        code = code.toUpperCase();
    }

}
