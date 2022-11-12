package com.payrolltask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payrolltask.entity.ExcelEntity;
@Repository
public interface ExcelRepository extends JpaRepository<ExcelEntity, Long>
{

}
