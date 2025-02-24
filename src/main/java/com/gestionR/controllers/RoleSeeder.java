package com.gestionR.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.gestionR.models.Departement;
import com.gestionR.models.ERole;
import com.gestionR.models.Fournisseur;
import com.gestionR.models.ResponsableRessource;
import com.gestionR.models.Role;
import com.gestionR.models.User;
import com.gestionR.repository.DepartementRepository;
import com.gestionR.repository.ResponsableRessourceRepository;
import com.gestionR.repository.RoleRepository;
import com.gestionR.repository.UserRepository;

@Component
public class RoleSeeder implements CommandLineRunner{

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	ResponsableRessourceRepository ResponRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	UserRepository UserRep;
	
	@Autowired
	DepartementRepository depRepo;
	
	@Override
	public void run(String... args) throws Exception {
	
		List<Role> roles = roleRepository.findAll();
		List<ResponsableRessource> Respon = ResponRepository.findAll();
		List<Departement> Departements = depRepo.findAll();
		
		
		if(roles.size() == 0) {
			
			List<Role> newRoles = new ArrayList<Role>();
			
			newRoles.add(new Role(ERole.ROLE_CHEF_DEPARTEMENT));
			newRoles.add(new Role(ERole.ROLE_FOURNISSEUR));
			newRoles.add(new Role(ERole.ROLE_ADMINISTRATIF));
			newRoles.add(new Role(ERole.ROLE_ENSEIGNANT));
			newRoles.add(new Role(ERole.ROLE_RESPO_RESSOURCES));
			newRoles.add(new Role(ERole.ROLE_TECHNICIEN));
			
			
			roleRepository.saveAll(newRoles);
			
		}
		
		if(Respon.size() == 0)
		{
			List<Role> roles1 = roleRepository.findAll();
			User R = new User("Resp Roussources", "Responsable@gmail.com",encoder.encode("Respon12345"));
			ResponsableRessource R2 = new ResponsableRessource("Resp Roussources", "Responsable@gmail.com",encoder.encode("Respon12345"));
			R.setRoles(roles1.get(4));
			R2.setRoles(roles1.get(4));
			UserRep.save(R);
			ResponRepository.save(R2);

		}
		if(Departements.size() == 0)
		{
			List<Role> roles1 = roleRepository.findAll();
			User R1 = new User("Scolartite Chef", "ScolartiteChef@gmail.com",encoder.encode("Scolartite12345"));
			User R2 = new User("informatique Chef", "informatiqueChef@gmail.com",encoder.encode("Informatique12345"));
			User R3 = new User("Mecanique Chef", "MecaniqueChef@gmail.com",encoder.encode("Mecanique12345"));
			R1.setRoles(roles1.get(0));
			R2.setRoles(roles1.get(0));
			R3.setRoles(roles1.get(0));
			UserRep.save(R1);
			UserRep.save(R2);
			UserRep.save(R3);
			
			R1 = UserRep.findByEmail("ScolartiteChef@gmail.com");
			R2 = UserRep.findByEmail("informatiqueChef@gmail.com");
			R3 = UserRep.findByEmail("MecaniqueChef@gmail.com");

			
			Departement d1 = new Departement("Scolarite",R1.getId());
			Departement d2 = new Departement("informatique",R2.getId());
			Departement d3 = new Departement("Mecanique",R3.getId());

			
			//ResponsableRessource R2 = new ResponsableRessource("Resp Roussources", "Responsable@gmail.com",encoder.encode("Respon12345"));
			
			
			depRepo.save(d1);
			depRepo.save(d2);
			depRepo.save(d3);

		}
		
		
		
	}

}
