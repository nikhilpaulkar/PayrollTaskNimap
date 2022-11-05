package com.payrolltask.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.payrolltask.dto.PermissionDto;
import com.payrolltask.entity.PermissionEntity;
import com.payrolltask.exception.ResourceNotFoundException;
import com.payrolltask.repository.PermissionRepository;
import com.payrolltask.serviceInterface.PermissionListDto;
import com.payrolltask.serviceInterface.PermissionServiceInterface;
import com.payrolltask.utility.Pagination;

@Service
public class PermissionServiceImpl implements PermissionServiceInterface
{
  @Autowired
  private PermissionRepository permissionRepository;

  
  //  Add Permission 
  @Override
  public PermissionEntity addPemission(PermissionDto permissionDto)
  {
	  
	  PermissionEntity permission = new PermissionEntity();
	  permission.setId(permissionDto.getId());
	  permission.setActionName(permissionDto.getActionName());
	  permission.setBaseUrl(permissionDto.getBaseUrl());
	  permission.setMethod(permissionDto.getMethod());
	  permission.setPath(permissionDto.getPath());
	  return this.permissionRepository.save(permission);
	  
	
	
  }

  // get Permission With Pagination
  @Override
  public Page<PermissionListDto> getAllPermission(String search, String pageNumber, String pageSize) 
  {
	  Pageable pagable=new Pagination().getPagination(pageNumber, pageSize);
		if((search=="")||(search==null)||(search.length()==0))
		{
			return permissionRepository.findByOrderById(pagable,PermissionListDto.class);
		}
		else
		{
			return  permissionRepository.findByActionName(search,pagable,PermissionListDto.class);
		}
  }

  // get permission By id
  @Override
  public PermissionDto getPermissionById(Long id) 
  {
	  PermissionEntity entity=permissionRepository.findById(id).orElseThrow(()->
	  new ResourceNotFoundException("permission id is not found "));
	
	  PermissionDto permissionDto=new PermissionDto();
	  
	  permissionDto.setId(entity .getId());
	  permissionDto.setActionName(entity.getActionName());
	  permissionDto.setBaseUrl(entity.getBaseUrl());
	  permissionDto.setMethod(entity.getMethod());
	  permissionDto.setPath(entity.getPath());
	  return permissionDto;
  }
  
  // Update Permission by id 
  @Override
  public PermissionDto updatepermission(PermissionDto permissionDto,Long id) 
  {
	  PermissionEntity permissionEntity=permissionRepository.findById(id).orElseThrow(()-> 
	  new ResourceNotFoundException("Not Found permission Id"));
		
		permissionEntity.setActionName(permissionDto.getActionName());
		permissionEntity.setBaseUrl(permissionDto.getBaseUrl());
		permissionEntity.setMethod(permissionDto.getMethod());
		permissionEntity.setPath(permissionDto.getPath());
		permissionRepository.save(permissionEntity);
		return permissionDto;
	
  }

  // delete permission by id
  @Override
  public void deletepermissionbyid(Long id)
  { 
	  this.permissionRepository.findById(id).orElseThrow(()->
	  new ResourceNotFoundException("id is not founf"+id));
	  this.permissionRepository.deleteById(id);
	  
	
  }
  
  
  
  
}
