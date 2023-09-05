package vn.tholv.web.core.base.service.core;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import vn.tholv.web.core.base.dao.core.Dao;
import vn.tholv.web.core.base.entity.core.BaseEntity;
import vn.tholv.web.core.override.util.CryptoUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
@SuppressWarnings("all")
public abstract class AbstractService<T extends BaseEntity<T, ID>, ID> implements Service<T, ID> {
    @Autowired
    protected Dao<T, ID> repository;

    @PersistenceContext
    protected EntityManager entityManager;
    private static String PRIORITY_FIELD_NAME = "priority";

    protected boolean isValidate = true;

    @Override
    public T findById(String id) throws Exception {
        return this.repository.findById(this.getIdFromEncrypt(id)).orElse(null);
    }

    @Override
    @Transactional(rollbackOn = RuntimeException.class)
    public T save(T entity) {
        if (this.isValidate) this.validateInsert(entity);
        return this.repository.save(entity);
    }

    @Override
    @Transactional(rollbackOn = RuntimeException.class)
    public void deleteById(String id) throws Exception {
        this.repository.deleteById(this.getIdFromEncrypt(id));
    }

    @Override
    @Transactional(rollbackOn = RuntimeException.class)
    public T update(T entity) throws Exception {
        T oldEntity = this.repository.findById(getIdFromEncrypt(entity.getUid())).orElse(null);
        if (oldEntity == null) {
            throw new RuntimeException("Có lỗi xảy ra khi cập nhật dữ liệu");
        }
        if (isValidate) this.validateUpdate(entity);
        oldEntity.copyData(entity);
        return this.repository.save(oldEntity);
    }

    @Override
    public List<T> findAll() {
        List<T> result = this.repository.findAll();
        if (result == null) {
            return new ArrayList<>();
        }
        if (!result.isEmpty() && this.isHadPriorityField(result.get(0))) {
            return this.repository.findAll((root, query, builder) -> {
                query.orderBy(
                    builder.asc(root.get(PRIORITY_FIELD_NAME)),
                    builder.desc(root.get(BaseEntity._id))
                );
                return builder.and(builder.conjunction());
            });
        }
        return result;
    }

    @Override
    public List<T> findAllByCondition(T t) {
        T entity = t;
        if (entity == null) {
            entity = (T) new BaseEntity();
        }
        return this.repository.findAll(Example.of(entity));
    }

    @Override
    public Page<T> findPageByCondition(T t, int page, int size) {
        T entity = t;
        if (entity == null) {
            entity = (T) new BaseEntity();
        }
        return this.repository.findAll(Example.of(entity), PageRequest.of(page, size));
    }


    @Override
    public List<T> priority(List<T> entityList) {
        try {
            if (entityList == null || entityList.size() == 0) {
                throw new RuntimeException("Bạn chưa chọn dữ liệu");
            }
            if (entityList.size() == 1) {
                return entityList;
            }
            if (entityList.get(0).getClass().getDeclaredField(PRIORITY_FIELD_NAME) == null) {
                throw new RuntimeException("Không tìm thấy trường" + PRIORITY_FIELD_NAME + "trong đối tượng");
            }
            List<T> result = new ArrayList<>(entityList.size());
            for (int i = 0; i < entityList.size(); i++) {
                T entity = this.repository.findById(entityList.get(i).getId()).orElse(null);
                if (entity == null) {
                    throw new RuntimeException("không tìm thấy dữ liệu");
                }
                Field field = entity.getClass().getDeclaredField(PRIORITY_FIELD_NAME);
                field.setAccessible(true);
                field.set(entity, i);
                result.add(entity);
                this.repository.save(entity);
            }
            return (List<T>) result;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private boolean isHadPriorityField(T entity) {
        try {
            return entity.getClass().getDeclaredField(PRIORITY_FIELD_NAME) != null;
        } catch (Exception e) {
            return false;
        }
    }

    private ID getIdFromEncrypt(String idHash) throws Exception {
        try{
            if (idHash == null) return null;
            String idDecode = CryptoUtils.decrypt(idHash);
            if (idDecode == null) return null;
            if (idDecode.matches("[0-9]+"))
                return (ID) Integer.valueOf(idDecode);
            return null;
        }catch (Exception e){
            throw new Exception("Có lỗi xảy ra khi giải mã dữ liệu, dữ liệu của bạn không an toàn");
        }
    }

    @Override
    public Page<T> findPage(int page, int size) {
        return this.repository.findAll(PageRequest.of(page, size));
    }

    protected abstract void validateInsert(T entity);

    protected abstract void validateUpdate(T entity);

    protected void setPriorityFieldName(String priorityFieldName) {
        if (priorityFieldName != null && !priorityFieldName.isEmpty()) {
            PRIORITY_FIELD_NAME = priorityFieldName;
        } else {
            PRIORITY_FIELD_NAME = "priority";
        }
    }

    protected void nothingValidate() {
        this.isValidate = false;
    }
}
