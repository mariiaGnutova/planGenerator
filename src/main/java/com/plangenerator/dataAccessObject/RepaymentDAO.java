package com.plangenerator.dataAccessObject;

import com.plangenerator.entity.RepaymentDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RepaymentDAO extends CrudRepository<RepaymentDO, Long> {

    @Override
    List<RepaymentDO> findAll();

}
