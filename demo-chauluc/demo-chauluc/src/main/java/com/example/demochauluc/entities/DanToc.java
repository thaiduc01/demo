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
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dantoc")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DanToc extends AbstractAuditingEntity<Long>{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "idDanToc",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String tenDanToc;
    
    private String mota;
    
    @Column(name = "ma_code",unique = true)
    private String code;
    
    @ManyToMany
    @JoinTable(
            name = "dantoc_quocgia",
            joinColumns = @JoinColumn(name = "idDanToc"),
            inverseJoinColumns = @JoinColumn(name = "idQuocGia"))
    private Set<QuocGia> quocgia;
    
    @ManyToOne
    @JoinColumn(name = "idChauLuc")
    private ChauLuc chauluc;
    
  //Chuyển thành chữ hoa trước khi vào db
    @PrePersist
    @PreUpdate
    private void beforeSave(){
        code = code.toUpperCase();
    }

}
