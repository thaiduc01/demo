package com.example.demochauluc.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "chauluc")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChauLuc extends AbstractAuditingEntity<Long>{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "idChauLuc")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String tenChauLuc;
    
    private long dienTich;
    
    private long soQuocGia;
    
    private String mota;
    
    @Column(name = "ma_code")
    private String code;
    
    @OneToMany(mappedBy = "chauluc")
    private Set<QuocGia> quocgia;
    
    @OneToMany(mappedBy = "chauluc")
    private Set<DanToc> dantoc;
    
    
    //Chuyển thành chữ hoa trước khi vào db
    @PrePersist
    @PreUpdate
    private void beforeSave(){
        code = code.toUpperCase();
    }
}
