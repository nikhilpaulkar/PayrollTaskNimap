package com.payrolltask.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payrolltask.entity.PermissionEntity;
import com.payrolltask.entity.RoleEntity;
import com.payrolltask.entity.RolePermissionEntity;
import com.payrolltask.entity.RolePermissionId;
import com.payrolltask.exception.ResourceNotFoundException;
import com.payrolltask.payload.RolePermissionRequest;
import com.payrolltask.repository.PermissionRepository;
import com.payrolltask.repository.RolePemissionRespository;
import com.payrolltask.repository.RoleRepository;
import com.payrolltask.serviceInterface.RolePermissionServiceInterface;

@Service
public class RolePermissionServiceImpl implements RolePermissionServiceInterface
{
	@Autowired
	private RolePemissionRespository rolePermissionRespository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PermissionRepository permissionRepository;

	//Add role id and Permission id
	@Override
	public void add(RolePermissionRequest rolePermissionRequest)
	{
		 try
		  {
				
			ArrayList<RolePermissionEntity> permissions = new ArrayList<>();
			RoleEntity roleEntityId = this.roleRepository.findById(rolePermissionRequest.getRoleid()).orElseThrow(()->
			new ResourceNotFoundException("Not Found roleid"));
			PermissionEntity permissionEntityId = this.permissionRepository.findById(rolePermissionRequest.getPermissionid()).orElseThrow(()-> 
			new ResourceNotFoundException("Not Found permissionid"));
			
			RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();
			
			RolePermissionId rolePermissionId = new RolePermissionId(roleEntityId,permissionEntityId);
			rolePermissionEntity.setPk(rolePermissionId);
			permissions.add(rolePermissionEntity);
			rolePermissionRespository.saveAll(permissions);
		  }	catch(Exception e)
		  {
			  throw new ResourceNotFoundException("not found");
		  }
			
		
	}

	// get all role id and permission id 
	@Override
	public List<RolePermissionEntity> getAll()
	{
	
		return this.rolePermissionRespository.findAll();
	}

	// update role id and permission id
	@Override
	public void updateRolePermission(RolePermissionRequest rolePermissionRequest)
	{
		RoleEntity roleEntity = this.roleRepository.findById(rolePermissionRequest.getRoleid()).get();
		PermissionEntity permissionEntity = this.permissionRepository.findById(rolePermissionRequest.getPermissionid()).get();
		
		RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();
		RolePermissionId rolePermissionId = new RolePermissionId(roleEntity,permissionEntity);
		rolePermissionEntity.setPk(rolePermissionId);
		this.rolePermissionRespository.updateRolePermission(roleEntity.getId(), permissionEntity.getId());
		
		
	}

	// delete role id and permission id
	@Override
	public void deleteRolePermission(RolePermissionRequest rolePermissionRequest)
	{
		RoleEntity roleEntity=this.roleRepository.findById(rolePermissionRequest.getRoleid()).get();
			
		PermissionEntity permissionEntity=this.permissionRepository.findById(rolePermissionRequest.getPermissionid()).get();
			
		RolePermissionEntity rolePermissionEntity=new RolePermissionEntity();
			
		RolePermissionId rolePermissionId=new RolePermissionId(roleEntity,permissionEntity);
		rolePermissionEntity.setPk(rolePermissionId);
		rolePermissionRespository.delete(rolePermissionEntity);
	}
	
	
	
	

}
