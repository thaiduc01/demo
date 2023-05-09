package com.example.demochauluc.Service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demochauluc.Service.DanTocService;
import com.example.demochauluc.dtos.DanTocDto;
import com.example.demochauluc.dtos.DanhSachDanTocDto;
import com.example.demochauluc.entities.DanToc;
import com.example.demochauluc.entities.QChauLuc;
import com.example.demochauluc.entities.QDanToc;
import com.example.demochauluc.entities.QQuocGia;
import com.example.demochauluc.entities.QuocGia;
import com.example.demochauluc.mapper.DanTocMapper;
import com.example.demochauluc.mapper.DanhSachDanTocMapper;
import com.example.demochauluc.repository.DanTocRepository;
import com.example.demochauluc.repository.QuocGiaRepository;
import com.example.demochauluc.utils.Paging;
import com.querydsl.jpa.impl.JPAQuery;

@Service
public class DanTocServiceImpl implements DanTocService{

    @Autowired
    private DanTocMapper mapper;
    
    @Autowired
    private DanhSachDanTocMapper dsdantocMapper;
    
    @Autowired
    private DanTocRepository repository;
    
    @Autowired
    private QuocGiaRepository quocGiaRepository;
    
    @Autowired
    private EntityManager em;
    
  //  @Override
//    public Paging<DanTocDto> getAllDanTocPaging(int pageNumber, int pageSize){
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        Page<DanTocDto> result= repository.findAll(pageable).map(mapper::toDto);
//        return Paging.of(result);
//    }
    
    @Override
    public Paging<DanTocDto> getAllDanTocPaging(Pageable pageable){
        Page<DanTocDto> result = repository.findAll(pageable).map(mapper::toDto);
        return Paging.of(result);
    }
    
    @Override
    public DanTocDto getDanTocByID(Long id) {
        DanToc dantoc = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.toDto(dantoc);
    }
    
    @Override
    public DanTocDto createDanToc(DanTocDto dto) {
        DanToc newDanToc = mapper.toEntity(dto);
        Set<QuocGia> dsquocgia = dto.getIdQuocGias()
                .stream()
                .map(quocGiaRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
        newDanToc.setQuocgia(dsquocgia);
        repository.save(newDanToc);
        return mapper.toDto(newDanToc);
    }
    
    @Override
    public boolean updateDanToc(DanTocDto dto) {
        Long dantoc_id = dto.getId();
        DanToc dantoc = repository.findById(dantoc_id).orElseThrow(EntityNotFoundException::new);
        dantoc = mapper.updateDanToc(dto, dantoc);
        repository.save(dantoc);
        return true;
    }
    
    @Override
    public boolean deleteDanToc(Long id) {
        DanToc dantoc = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        repository.delete(dantoc);
        return true;
    }
    
    @Override
    public List<DanhSachDanTocDto> findByNameQuocGia(String tenQuocGia){
            JPAQuery<DanToc> query = new JPAQuery<>(em);
            QDanToc dantoc = QDanToc.danToc;
            QQuocGia quocgia = QQuocGia.quocGia;
            List<DanToc> dantocs =  query.select(dantoc)
                    .from(dantoc)
                    .join(dantoc.quocgia,quocgia)
                    .where(quocgia.tenQuocGia.containsIgnoreCase(tenQuocGia))
                    .fetch();
            return dsdantocMapper.toDto(dantocs);
    }
    
    @Override
    public List<DanhSachDanTocDto> findByNameChauLuc(String tenChauLuc){
        JPAQuery<DanToc> query = new JPAQuery<>(em);
        QDanToc dantoc = QDanToc.danToc;
        QChauLuc chauluc = QChauLuc.chauLuc;
        List<DanToc> dantocs = query.select(dantoc)
                .from(dantoc)
                .join(dantoc.chauluc,chauluc)
                .where(chauluc.tenChauLuc.containsIgnoreCase(tenChauLuc))
                .fetch();
        return dsdantocMapper.toDto(dantocs);
    }
}
