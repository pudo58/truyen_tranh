package vn.tholv.web.core.base.dao.core;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.tholv.web.core.base.entity.core.BaseEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface Dao<T extends BaseEntity, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    default <DTO extends BaseEntity> Page<DTO> findAll(Specification<T> specification, Pageable pageable, Class<DTO> dtoClass) {
        try{
            Page<T> entityPage = findAll(specification, pageable);
            List<DTO> dtoList = entityPage.getContent().stream()
                .map(entity -> convertToDTO(entity, dtoClass))
                .collect(Collectors.toList());

            return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    default <DTO> DTO convertToDTO(T entity, Class<DTO> dtoClass) {
        try {
            DTO dto = dtoClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Error converting entity to DTO", e);
        }
    }
}
