package com.example.demochauluc.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demochauluc.dtos.DanhSachNgonNguDto;
import com.example.demochauluc.dtos.NgonNguDto;
import com.example.demochauluc.entities.NgonNgu;
import com.example.demochauluc.entities.QNgonNgu;
import com.example.demochauluc.entities.QQuocGia;
import com.example.demochauluc.entities.QuocGia;
import com.example.demochauluc.mapper.DanhSachNgonNguMapper;
import com.example.demochauluc.mapper.NgonNguMapper;
import com.example.demochauluc.repository.NgonNguRepository;
import com.example.demochauluc.repository.QuocGiaRepository;
import com.example.demochauluc.service.NgonNguService;
import com.example.demochauluc.utils.Paging;
import com.querydsl.jpa.impl.JPAQuery;

@Service
public class NgonNguServiceImpl implements NgonNguService{

    @Autowired
    private NgonNguMapper mapper;
    
    @Autowired
    private NgonNguRepository repository;
    
    @Autowired
    private QuocGiaRepository quocGiaRepository;
    
    @Autowired
    private DanhSachNgonNguMapper dsmapper;
    
    @Autowired
    private EntityManager em;
    
    @Override
    public List<NgonNguDto> getAllNgonNgu(){
        List<NgonNgu> ngonngus = repository.findAll();
        return mapper.toDto(ngonngus);
    }
    
    @Override
    public Paging<NgonNguDto> getAllNgonNguPaging(Pageable pageable){
        Page<NgonNguDto> result = repository.findAll(pageable).map(mapper::toDto);
        return Paging.of(result);
    }
    
    @Override
    public NgonNguDto getNgonNguByID(Long id) {
     NgonNgu ngonngu = repository.findById(id).orElseThrow(EntityNotFoundException::new);
     return mapper.toDto(ngonngu);
    }
    
    @Override
    public NgonNguDto createNgonNgu(NgonNguDto dto) {
        NgonNgu newNgonNgu = mapper.toEntity(dto);
        Set<QuocGia> dsQuocGia = dto.getIdQuocGia()
                .stream()
                .map(quocGiaRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
        newNgonNgu.setQuocgia(dsQuocGia);
        repository.save(newNgonNgu);
        return mapper.toDto(newNgonNgu);
    }
    
    @Override
    public boolean updateNgonNgu(NgonNguDto dto) {
        Long id_ngonngu = dto.getId();
        NgonNgu ngonngu = repository.findById(id_ngonngu).orElseThrow(EntityNotFoundException::new);
        ngonngu = mapper.updateNgonngu(dto, ngonngu);
        repository.save(ngonngu);
        return true;
    }
    
    @Override
    public boolean deleteNgonNgu(Long id) {
        NgonNgu existingNgonNgu = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        repository.delete(existingNgonNgu);
        return true;
    }
    
    @Override
    public Paging<DanhSachNgonNguDto> findByNameQuocGia(String tenQuocGia,Pageable pageable){
        JPAQuery<NgonNgu> query = new JPAQuery<>(em);
        QNgonNgu ngonngu = QNgonNgu.ngonNgu;
        QQuocGia quocgia = QQuocGia.quocGia;
        
        boolean quocGiaExists = query.select(quocgia)
                .from(quocgia)
                .where(quocgia.tenQuocGia.equalsIgnoreCase(tenQuocGia))
                .fetchFirst() == null;
        
        if(quocGiaExists) {
            throw new com.example.demochauluc.exception.EntityNotFoundException("Tên quốc gia không tồn tại");
        }else {
        Long ngonngus =  query.select(ngonngu.id.count())
                .from(ngonngu)
                .join(ngonngu.quocgia,quocgia)
                .where(quocgia.tenQuocGia.containsIgnoreCase(tenQuocGia))
                .fetchFirst();
        
        List<NgonNgu> dsngonngu = query.select(ngonngu)
                .from(ngonngu)
                .join(ngonngu.quocgia,quocgia)
                .where(quocgia.tenQuocGia.containsIgnoreCase(tenQuocGia))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        
        Page<NgonNgu> pages = new PageImpl<NgonNgu>(dsngonngu, pageable, ngonngus);
        Page<DanhSachNgonNguDto> dtos = pages.map(dsmapper::toDto);
        
        return Paging.of(dtos);
    }
    }
}

