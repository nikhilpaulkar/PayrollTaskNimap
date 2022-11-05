package com.payrolltask.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payrolltask.entity.RoleEntity;
import com.payrolltask.entity.UserRoleEntity;
import com.payrolltask.entity.UserRoleId;
import com.payrolltask.entity.Users;
import com.payrolltask.exception.ResourceNotFoundException;
import com.payrolltask.payload.UserRoleRequest;
import com.payrolltask.repository.RoleRepository;
import com.payrolltask.repository.UserRepository;
import com.payrolltask.repository.UserRoleRepository;
import com.payrolltask.serviceInterface.UserRoleServiceInterface;

@Service
public class UserRoleServiceImpl implements UserRoleServiceInterface
{

	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	// add role id and user id  
	@Override
	public void add(UserRoleRequest userRoleRequest)
	{
		try
		{
			ArrayList<UserRoleEntity> userrole=new ArrayList<>();
			Users user=this.userRepository.findById(userRoleRequest.getUserid()).orElseThrow(()->new ResourceNotFoundException("user not found with id"));
		    RoleEntity roleenntity = this.roleRepository.findById(userRoleRequest.getRoleid()).orElseThrow(()-> new ResourceNotFoundException("user  nOT FOUNd with roleid"));
			UserRoleEntity userroleentity = new UserRoleEntity();
			
		    UserRoleId userroleid = new UserRoleId (user,roleenntity);
		    
		    userroleentity.setTask(userroleid);
            userrole.add(userroleentity);
            userRoleRepository.saveAll(userrole);
		}   catch(Exception e)
		{
			e.printStackTrace();
			throw new ResourceNotFoundException("Not found ");
			
			
        }

		
	}

    // get all user id and role id 
	@Override
	public List<UserRoleEntity> getAll() 
	{
		List<UserRoleEntity> role= this.userRoleRepository.findAll();
		return role;

		
	}
 
	// update role id and user id 
	@Override
	public void updateuserrole(UserRoleRequest userrolerequest) 
	{
        Users user=this.userRepository.findById(userrolerequest.getUserid()).orElseThrow(()->
        new ResourceNotFoundException("user not found with id"));
		
		RoleEntity roleentity=this.roleRepository.findById(userrolerequest.getRoleid()).orElseThrow(()->
		new ResourceNotFoundException("not found"));
		UserRoleId userroleid = new UserRoleId(user, roleentity);
		
		UserRoleEntity useroleentity = new UserRoleEntity();
		
		useroleentity.setTask(userroleid);
		userRoleRepository.updateuserrole(user.getId(), roleentity.getId());
		
	}
    
	// delete role id and user id 
	@Override
	public UserRoleEntity deleteuserroles(UserRoleRequest userrolerequest)
	{
       Users user=this.userRepository.findById(userrolerequest.getUserid()).orElseThrow(()->
       new ResourceNotFoundException("user not found with id"));
		
	   RoleEntity roleentity=this.roleRepository.findById(userrolerequest.getRoleid()).orElseThrow(()->
       new ResourceNotFoundException("not found"));
		
	   UserRoleId userroleid=new UserRoleId(user,roleentity);
		
	   UserRoleEntity userroleentity=new UserRoleEntity();
	    
	   userroleentity.setTask(userroleid);
	    
	   userRoleRepository.delete(userroleentity);
	   return userroleentity;
		
	}

}
