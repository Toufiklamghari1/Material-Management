package com.gestionR.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.gestionR.models.Administratif;
import com.gestionR.models.Departement;
import com.gestionR.models.ERole;
import com.gestionR.models.Enseignant;
import com.gestionR.models.Fournisseur;
import com.gestionR.models.ResponsableRessource;
import com.gestionR.models.Role;
import com.gestionR.models.Technicien;
import com.gestionR.models.User;
import com.gestionR.payload.request.LoginRequest;
import com.gestionR.payload.request.SignupRequest;
import com.gestionR.payload.response.JwtResponse;
import com.gestionR.payload.response.MessageResponse;
import com.gestionR.repository.AdministratifRepository;
import com.gestionR.repository.EnseignantRepository;
import com.gestionR.repository.FournisseurRepository;
import com.gestionR.repository.ResponsableRessourceRepository;
import com.gestionR.repository.RoleRepository;
import com.gestionR.repository.TechnicienRepository;
import com.gestionR.repository.UserRepository;
import com.gestionR.security.jwt.JwtUtils;
import com.gestionR.security.services.UserDetailsImpl;
import com.gestionR.service.PersonnelService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	FournisseurRepository fournisseurReposiory ;
	@Autowired
	EnseignantRepository enseignantRepository;
	@Autowired
	TechnicienRepository technicienRepository;
	@Autowired
	AdministratifRepository administratifRepository;
	@Autowired
	ResponsableRessourceRepository responsableRepository;

	@PostMapping("/login")
	public ModelAndView authenticateUser(@Valid LoginRequest loginRequest , HttpSession session) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
	   /* User user = new User();
	    user.setId(userDetails.getId());
	    user.setEmail(userDetails.getEmail());
	    user.setRoles(roles.get(0));
	    user.setUsername( userDetails.getUsername());*/
		session.setAttribute("user", new JwtResponse(jwt,
												 userDetails.getId(),
												 userDetails.getUsername(),
												 userDetails.getEmail(),
												 roles));
		Enseignant e =enseignantRepository.findByEmail(userDetails.getEmail());
		Administratif a  =administratifRepository.findByEmail(userDetails.getEmail());
		Technicien t = technicienRepository.findByEmail(userDetails.getEmail());
		ResponsableRessource r = responsableRepository.findByEmail(userDetails.getEmail());
		Fournisseur f = fournisseurReposiory.findByEmail(userDetails.getEmail());
		if(e!=null)
		{
			session.setAttribute("compteUser",(User)e);
		}
		if(a!=null) {
		  session.setAttribute("compteUser",(User)a); }
		if(t!=null) {

		  session.setAttribute("compteUser", (User)t); }
		if(r!=null)
		{
			session.setAttribute("compteUser",(User)r);
		}
		if(f!=null)
		{
			session.setAttribute("compteUser",(User)f);
		}
		RedirectView redirect = new RedirectView();
		redirect.setUrl("/index");
		return new ModelAndView(redirect);
	}

	@PostMapping("/signup")
	public ModelAndView registerUser(@Valid  SignupRequest signUpRequest,HttpSession session) {
		/*
		 *  if (userRepository.existsByUsername(signUpRequest.getUsername())) {
		 * return }
		 *
		 * if (userRepository.existsByEmail(signUpRequest.getEmail())) { return
		 * ResponseEntity .badRequest() // .body(new
		 * MessageResponse("Error: Email is already in use!")); }
		 *
		 * Create new user's account
		 */
		User user = new User(signUpRequest.getUsername(),
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));
		String strRoles = signUpRequest.getRoles();
		Role roles = null;
		if (strRoles == null) {
			Role fournisseurRole = roleRepository.findByName(ERole.ROLE_FOURNISSEUR)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					 roles = fournisseurRole;
					 Fournisseur fournisseur = new Fournisseur(signUpRequest.getUsername(),
					 signUpRequest.getEmail(),
					 encoder.encode(signUpRequest.getPassword())
					 , "gerant ", "ville" ,"societe");

					 user.setRoles(roles);
					 userRepository.save(user);

					 /*find user */
					 User usertofournisseur = userRepository.findByEmail(user.getEmail());
					 fournisseur.setIsblocked(false);
					 fournisseur.setId(usertofournisseur.getId());
					 fournisseur.setIdFourniseur(usertofournisseur.getId());
					 fournisseurReposiory.save(fournisseur);
					 session.setAttribute("user",user);
					 session.setAttribute("compteUser",usertofournisseur);

		} else {

				switch (strRoles) {
				case "chef_departement":
					Role chefDepartementRole = roleRepository.findByName(ERole.ROLE_CHEF_DEPARTEMENT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles = chefDepartementRole;

					break;
				case "enseignant":
					Role enseignantRole = roleRepository.findByName(ERole.ROLE_ENSEIGNANT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles = enseignantRole;

					Enseignant enseignant = new Enseignant(signUpRequest.getUsername(),
					signUpRequest.getEmail(),
					encoder.encode(signUpRequest.getPassword())
					, "fstLab");

					 user.setRoles(roles);
					 userRepository.save(user);

					 /*find user */
					 User usertoEnseignant = userRepository.findByEmail(user.getEmail());
					 enseignant.setId(usertoEnseignant.getId());
					 enseignantRepository.save(enseignant);
					 session.setAttribute("user",user);
					 session.setAttribute("compteUser",enseignant);

					break;

				case "fournisseur":
					Role fournisseurRole = roleRepository.findByName(ERole.ROLE_FOURNISSEUR)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles = fournisseurRole;

					Fournisseur fournisseur = new Fournisseur(signUpRequest.getUsername(),
					signUpRequest.getEmail(),
					encoder.encode(signUpRequest.getPassword())
					, "gerant ", "ville" ,"societe");

					 user.setRoles(roles);
					 userRepository.save(user);

					 /*find user */
					 User usertofournisseur = userRepository.findByEmail(user.getEmail());
					 fournisseur.setIsblocked(false);
					 fournisseur.setId(usertofournisseur.getId());
					 fournisseur.setIdFourniseur(usertofournisseur.getId());
					 fournisseurReposiory.save(fournisseur);
					 session.setAttribute("user",user);
					 session.setAttribute("compteUser",usertofournisseur);

					break;
				case "responsable":
					Role respoRRole = roleRepository.findByName(ERole.ROLE_RESPO_RESSOURCES)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles = respoRRole;

					ResponsableRessource responsable = new ResponsableRessource(signUpRequest.getUsername(),
					signUpRequest.getEmail(),
					encoder.encode(signUpRequest.getPassword())
					);
					/* assign to responsable */

					 user.setRoles(roles);
					 userRepository.save(user);

					 /*find user */
					 User usertoResponsable = userRepository.findByEmail(user.getEmail());
					 responsable.setId(usertoResponsable.getId());
					 responsableRepository.save(responsable);
					 session.setAttribute("user",user);
					 session.setAttribute("compteUser",responsable);
					break;
				case "technicien":
					Role technicienRole = roleRepository.findByName(ERole.ROLE_TECHNICIEN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles = technicienRole;

					Technicien technicien = new Technicien(signUpRequest.getUsername(),
							signUpRequest.getEmail(),
							encoder.encode(signUpRequest.getPassword()));
					 user.setRoles(roles);
					 userRepository.save(user);
					 /*find user */
					 User usertoTechnicien = userRepository.findByEmail(user.getEmail());
					 technicien.setId(usertoTechnicien.getId());
					 technicienRepository.save(technicien);
					 session.setAttribute("user",user);
					 session.setAttribute("compteUser",technicien);
					break;
				case "administratif" :
					Role administratifRole = roleRepository.findByName(ERole.ROLE_ADMINISTRATIF)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles = administratifRole;
					Administratif administratif = new Administratif(signUpRequest.getUsername(),
					signUpRequest.getEmail(),
					encoder.encode(signUpRequest.getPassword()));
					 user.setRoles(roles);
					 userRepository.save(user);
					 /*find user */
					 User userToAdministratif = userRepository.findByEmail(user.getEmail());
					 administratif.setId(userToAdministratif.getId());
					 administratifRepository.save(administratif);
					 session.setAttribute("user",user);
					 session.setAttribute("compteUser",administratif);
					break;
				}
			}
		RedirectView redirect = new RedirectView();
		redirect.setUrl("/");
		return new ModelAndView(redirect);
	}
}