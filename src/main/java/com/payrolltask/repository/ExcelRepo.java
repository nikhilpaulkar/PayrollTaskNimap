package com.payrolltask.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payrolltask.entity.UserJobEntity;

@Repository
public interface ExcelRepo extends JpaRepository<UserJobEntity,Long> 
{
  
}
