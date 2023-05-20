package com.example.demochauluc.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "quocgia")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class QuocGia extends AbstractAuditingEntity<Long>{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "idQuocGia",unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String tenQuocGia;
    
    private long dienTich;
    
    private long soDan;
    
    private String mota;
    
    @Column(name = "ma_code",unique = true)
    private String code;
    
    @ManyToOne
    @JoinColumn(name = "idChauLuc")
    private ChauLuc chauluc;
    
    @ManyToMany(mappedBy = "quocgia")
    private Set<DanToc> dantoc;
    
    @ManyToMany(mappedBy = "quocgia")
    private Set<NgonNgu> ngonngu;
    
    
  //Chuyển thành chữ hoa trước khi vào db
    @PrePersist
    @PreUpdate
    private void beforeSave(){
        code = code.toUpperCase();
    }
}
