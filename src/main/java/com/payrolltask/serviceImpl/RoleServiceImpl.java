package com.payrolltask.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.payrolltask.dto.RoleDto;
import com.payrolltask.entity.RoleEntity;
import com.payrolltask.exception.ResourceNotFoundException;
import com.payrolltask.repository.RoleRepository;
import com.payrolltask.serviceInterface.IRoleListDto;
import com.payrolltask.serviceInterface.RoleServiceInterface;
import com.payrolltask.utility.Pagination;

@Service
public class RoleServiceImpl implements RoleServiceInterface
{
  @Autowired
  private RoleRepository roleRepository;
	
    // add role
	@Override
	public void addrole(RoleDto roleDto)
	{
		RoleEntity roleEntity= new RoleEntity();
		roleEntity.setId(roleDto.getId());
		roleEntity.setRoleName(roleDto.getRoleName());
		roleRepository.save(roleEntity);
		
	}
    
	//get all roles with pagination
	@Override
	public Page<IRoleListDto> getAllrole(String search, String pageNumber, String pageSize) 
	{
		Pageable pagable=new Pagination().getPagination(pageNumber, pageSize);
		if((search=="")||(search==null)||(search.length()==0))
		{
			return roleRepository.findByOrderById(pagable,IRoleListDto.class);
		}
		else
		{
			return  roleRepository.findByRoleName(search,pagable,IRoleListDto.class);
		}
		
	
	}
	
    // get role by id
	@Override
	public RoleDto getRoleId(Long id) 
	{
        RoleEntity roleEntity=roleRepository.findById(id).orElseThrow(()-> 
        new ResourceNotFoundException("Not Found role Id"));
		RoleDto roleDto=new RoleDto();
		roleDto.setId(roleEntity.getId());
		roleDto.setRoleName(roleEntity.getRoleName());
		return roleDto;
		
	}

	// update by RoleId 
	@Override
	public RoleDto updaterole(RoleDto roleDto, Long id) 
	{
	   RoleEntity roleEntity=roleRepository.findById(id).orElseThrow(()-> 
	   new ResourceNotFoundException("Not Found role Id"));
		
		
		roleEntity.setRoleName(roleDto.getRoleName());
		roleRepository.save(roleEntity);
		return roleDto;
		
	}
    
	// delete by role id 
	@Override
	public void deleteRole(Long id)
	{
		this.roleRepository.findById(id).orElseThrow( () ->
		new ResourceNotFoundException("role Not Found With Id :"+id));
		this.roleRepository.deleteById(id);
	}

}
