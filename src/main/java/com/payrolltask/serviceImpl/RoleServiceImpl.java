package com.payrolltask.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.payrolltask.dto.RoleDto;
import com.payrolltask.entity.RoleEntity;
import com.payrolltask.exception.ResourceNotFoundException;
import com.payrolltask.repository.RolePemissionRespository;
import com.payrolltask.repository.RoleRepository;
import com.payrolltask.repository.UserRoleRepository;
import com.payrolltask.serviceInterface.ICandidateListDto;
import com.payrolltask.serviceInterface.IPermissionIdListDto;
import com.payrolltask.serviceInterface.IRoleListDto;
import com.payrolltask.serviceInterface.RoleIdListDto;
import com.payrolltask.serviceInterface.RoleServiceInterface;
import com.payrolltask.utility.Pagination;

@Service
public class RoleServiceImpl implements RoleServiceInterface
{
  @Autowired
  private RoleRepository roleRepository;
	
  @Autowired
  private UserRoleRepository userRoleRepository;
  
  @Autowired
  private RolePemissionRespository  rolePemissionRespository;
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

	@Override
	public ArrayList<String> getPermissionByUserId(Long id)
	{
		ArrayList<RoleIdListDto> roleIds = userRoleRepository.findByTaskUserId(id, RoleIdListDto.class);
		ArrayList<Long> roles = new ArrayList<>();

		for (int i = 0; i < roleIds.size(); i++)
		{

			roles.add(roleIds.get(i).getTaskRoleId());

		}

		List<IPermissionIdListDto> rolesPermission = rolePemissionRespository.findPkPermissionByPkRolesIdIn(roles, IPermissionIdListDto.class);
		ArrayList<String> permissions = new ArrayList<>();

		for (IPermissionIdListDto element : rolesPermission)
		{

			permissions.add(element.getPkPermissionActionName());

		}

		return permissions;
		
	}

	@Override
	public Page<ICandidateListDto> getCandidateList(String search, String pageNumber, String pageSize) 
	{
         Pageable pagable=new Pagination().getPagination(pageNumber, pageSize);
		
		if((search=="")||(search==null)||(search.length()==0))
		{
			//return roleRepository.findByOrderByUserId(pagable,ICandidateListDto.class);
		}
		else
		{
			
			//Page<ICandidateListDto> list= roleRepository.findByUserByCandidate(search,pagable,ICandidateListDto.class);
			//return list;
		}
		return null;
		
	}

}
