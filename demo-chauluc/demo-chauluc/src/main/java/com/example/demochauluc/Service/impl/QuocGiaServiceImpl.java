package com.example.demochauluc.Service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demochauluc.Service.QuocGiaService;
import com.example.demochauluc.dtos.DanhSachQuocGiaDto;
import com.example.demochauluc.dtos.QuocGiaDto;
import com.example.demochauluc.entities.QChauLuc;
import com.example.demochauluc.entities.QQuocGia;
import com.example.demochauluc.entities.QuocGia;
import com.example.demochauluc.mapper.DanhSachQuocGiaMapper;
import com.example.demochauluc.mapper.QuocGiaMapper;
import com.example.demochauluc.repository.QuocGiaRepository;
import com.example.demochauluc.utils.Paging;
import com.querydsl.jpa.impl.JPAQuery;

@Service
public class QuocGiaServiceImpl implements QuocGiaService {

    @Autowired
    private QuocGiaMapper mapper;
    
    @Autowired
    private DanhSachQuocGiaMapper dsmapper;
    
    @Autowired
    private QuocGiaRepository repository;
    
    @Autowired
    private EntityManager em;
    
    @Override
    public List<QuocGiaDto> getAllQuocGia(){
        List<QuocGia> quocgias = repository.findAll();
        return mapper.toDto(quocgias);
    }
    
    @Override
    public Paging<QuocGiaDto> getAllQuocGiaPaging(Pageable pageable){
        Page<QuocGiaDto> result = repository.findAll(pageable).map(mapper :: toDto);
        return Paging.of(result);
    }
    
    @Override
    public QuocGiaDto getQuocGiaById(Long id) {
        QuocGia quocgia = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.toDto(quocgia);
    }
    
    @Override
    public QuocGiaDto createQuocGia(QuocGiaDto dto) {
        QuocGia quocgia = mapper.toEntity(dto);
        repository.save(quocgia);
        return mapper.toDto(quocgia);
    }
    
    @Override
    public boolean updateQuocGia(QuocGiaDto dto) {
        Long quocgia_id = dto.getId();
        QuocGia quocgia = repository.findById(quocgia_id).orElseThrow(EntityNotFoundException::new);
        quocgia = mapper.updateQuocGia(dto, quocgia);
        repository.save(quocgia);
        return true;
    }
    
    @Override
    public boolean deleteQuocGia(Long id) {
        QuocGia existingQuocGia = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        repository.delete(existingQuocGia);
        return true;
    }
    
    @Override
    public Paging<DanhSachQuocGiaDto> findQuocGiaByChauLuc(String tenChauLuc,Pageable pageable){
        JPAQuery<QuocGia> query = new JPAQuery<>(em);
        QQuocGia quocgia = QQuocGia.quocGia;
        QChauLuc chauluc = QChauLuc.chauLuc;
        List<QuocGia> quocgias =  query.select(quocgia)
        .from(quocgia)
        .join(quocgia.chauluc,chauluc)
        .where(chauluc.tenChauLuc.containsIgnoreCase(tenChauLuc))
        .fetch();
        Page<QuocGia> pages = new PageImpl<>(quocgias);
        Page<DanhSachQuocGiaDto> result =pages.map(dsmapper :: toDto);
        return Paging.of(result);
    }
}
