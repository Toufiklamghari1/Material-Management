package com.gestionR.controllers;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.gestionR.add.Counter;
import com.gestionR.models.Administratif;
import com.gestionR.models.Affectation;
import com.gestionR.models.Besoin;
import com.gestionR.models.Demande;
import com.gestionR.models.ERole;
import com.gestionR.models.Enseignant;
import com.gestionR.models.Fournisseur;
import com.gestionR.models.FrequencePanne;
import com.gestionR.models.Imprimante;
import com.gestionR.models.Notification;
import com.gestionR.models.Ordinateure;
import com.gestionR.models.Panne;
import com.gestionR.models.Personnel;
import com.gestionR.models.ResponsableRessource;
import com.gestionR.models.Ressource;
import com.gestionR.models.Role;
import com.gestionR.models.Soumission;
import com.gestionR.models.SoumissionAfficher;
import com.gestionR.models.Technicien;
import com.gestionR.models.User;
import com.gestionR.models.constat;
import com.gestionR.models.AppelsDoffre;
import com.gestionR.payload.request.LoginRequest;
import com.gestionR.payload.request.SignupRequest;
import com.gestionR.payload.response.JwtResponse;
import com.gestionR.repository.AdministratifRepository;
import com.gestionR.repository.AffectationRep;
import com.gestionR.repository.BesoinRespository;
import com.gestionR.repository.DemandeRep;
import com.gestionR.repository.EnseignantRepository;
import com.gestionR.repository.FournisseurRepository;
import com.gestionR.repository.ImprimanteRepository;
import com.gestionR.repository.NotificationRepository;
import com.gestionR.repository.OrdinateurRepository;
import com.gestionR.repository.ResponsableRessourceRepository;
import com.gestionR.repository.TechnicienRepository;
import com.gestionR.repository.UserRepository;
import com.gestionR.service.AppelsDoffreService;
import com.gestionR.service.ConstatService;
import com.gestionR.service.FournisseurService;
import com.gestionR.service.NotificationService;
import com.gestionR.service.PanneService;
import com.gestionR.service.RessourceService;
import com.gestionR.service.SoumissionService;
import com.gestionR.service.TechnicienService;

@Controller
public class AppController {
	
	
	/* les autoweired */
	
	
	@Autowired
	FournisseurRepository fournisseurRepository;
	
	@Autowired
	PanneService panneService;
	
	@Autowired
	ConstatService constatService;

	@Autowired
	AppelsDoffreService OffreService;
	
	@Autowired 
	FournisseurService fournisseurSer;
	
	@Autowired
	SoumissionService soumissionService;
	
	@Autowired
	BesoinRespository besoinRep;

	@Autowired
	AffectationRep ressourceRep;
	@Autowired
	UserRepository userRepository;
	@Autowired
	DemandeRep demandeRep;
	@Autowired
	AffectationRep affectationRep;
	
	@Autowired 
	RessourceService ressourceService;
	
	
	@Autowired 
	OrdinateurRepository ordinateurRep;
	
	@Autowired
	ImprimanteRepository imprimanteRep;
	
	
	@Autowired 
	NotificationRepository netificationRep;
	
	@Autowired
	NotificationService notificationSer;
	
	@Autowired 
	
	EnseignantRepository enseignantRep;
	
	@Autowired
	
	AdministratifRepository administratifRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	TechnicienRepository techRep;
	
	@Autowired
	TechnicienService technicienSer;
	
	@Autowired
	ResponsableRessourceRepository respoRess;
	
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	/* login */
	
	@SuppressWarnings("unchecked")
	@GetMapping("/")
	
	public ModelAndView getLogin(Model model)
	{
		model.addAttribute("user", new LoginRequest());
		model.addAttribute("guest", new SignupRequest());
		return new ModelAndView("login",(Map<String, Object>) model);
		
	}
	/* index */
	@SuppressWarnings("unchecked")
	@GetMapping("/index")
	
	public ModelAndView getIndex(HttpSession session,Model model)
	{
		
		JwtResponse user = (JwtResponse) session.getAttribute("user");
		
		List<String> role =user.getRoles();
	  
		if(role.get(0)=="ROLE_FOURNISSEUR")
		{
			
			List<Soumission> soumissions = soumissionService.soumissionAcceptee(user.getId());
			int a = soumissions.size();
			for(Soumission soumission : soumissions)
			{
				if(soumission.isAccepted()==false)
					soumissions.remove(soumission);
			}
			model.addAttribute("soumissionsAcceptee",soumissions);
			
			 List<Notification> list = notificationSer.getNotification(user.getUsername());
				
			model.addAttribute("notification", list);
			model.addAttribute("size", list.size());
			model.addAttribute("nombreOffreAccepte",a);
			return new ModelAndView("examples/dashboard",(Map<String, Object>) model);
		}
		else if(role.get(0)=="ROLE_TECHNICIEN" )
		{
		
	    List<Notification> list = notificationSer.getNotification(user.getId());
		
		model.addAttribute("notification", list);
		model.addAttribute("size", list.size());
		return new ModelAndView("examples/dashboard",(Map<String, Object>) model);
		}
		
		else
		{
			return new ModelAndView("examples/dashboard");
			
		}
			
	}
	
	
	
	@PostMapping("/notification")
	public RedirectView saveNotification(Notification not,HttpSession s) {
		
		Notification n = not;
		User user =(User) s.getAttribute("compteUser");
		n.setName(user.getUsername());
		List<Ressource> ress = ressourceService.getRessourse();
		Ressource ressource = ressourceService.findByIdR(not.getIdRessource(), ress);
		n.setIdDerstination(ressource.getFournisseur());
		netificationRep.save(n);
		
		return  new RedirectView("listerConstat");
	}
	

	@PostMapping("/PageAddRessources")
	public ModelAndView addRessource(Ordinateure ordinateur,Imprimante imprimante) {
		
		if(ordinateur.getRam() != 0 && ordinateur.getRom() !=0) {
			Ordinateure ordinateure = new Ordinateure();
			ordinateure = ordinateur;
			ordinateurRep.save(ordinateure);
		}
		if(imprimante.getResolution() != 0 && imprimante.getVetesseImpristion()!=0) {
			Imprimante impri = new Imprimante();
			impri = imprimante;
			imprimanteRep.save(impri);
		}
		List<String> fournisseur = fournisseurSer.getFounisseurUsername();

		Map<String,Object> M =  new HashMap<String,Object>();
		M.put("ordinateur", new Ordinateure());
		M.put("imprimante", new Imprimante());
		M.put("fourni", fournisseur);
		return new ModelAndView("examples/addRessource",M);
	}	

	@GetMapping("/PageAddRessources")
	public ModelAndView getPageAdd(HttpSession s) {
		Map<String,Object> M =  new HashMap<String,Object>();
		Ordinateure ordinateur = new Ordinateure();
		Imprimante imprimante = new Imprimante();
		List<String> fournisseur = fournisseurSer.getFounisseurUsername();
		M.put("ordinateur", ordinateur);
		M.put("imprimante", imprimante);
		M.put("fourni", fournisseur);
		return new ModelAndView("examples/addRessource",M);
	}
	
	
	@GetMapping("/profile")
	public ModelAndView getProfile(HttpSession session , Model model){
		
		JwtResponse user = (JwtResponse) session.getAttribute("user");
		
		List<String> role = user.getRoles();
	    
		if(role.get(0)=="ROLE_FOURNISSEUR")
		{

			model.addAttribute("profile",new Fournisseur());
			return new ModelAndView("examples/userFournisseur");
			
		}
		if(role.get(0)=="ROLE_ENSEIGNANT")
		{
			
			model.addAttribute("profile",new Enseignant());
			return new ModelAndView("examples/userEnseignant");
			
		}
		else
		{
			
			model.addAttribute("profile",session.getAttribute("compteUser"));
			return new ModelAndView("examples/user");
		}
	}

	@PostMapping("/profileEditFournisseur")
	public ModelAndView getProfileEditF(Fournisseur fournisseur , HttpSession session)
	{
		JwtResponse u= (JwtResponse)session.getAttribute("user");
		
		Fournisseur fournisseurA = fournisseurRepository.findByIdFourniseur(u.getId());
		User userToModify = userRepository.findByEmail(u.getEmail());
		
		/* user to modify */
	
		userToModify.setEmail(fournisseur.getEmail());
		userToModify.setPassword(encoder.encode(fournisseur.getPassword()));
		userToModify.setUsername(fournisseur.getUsername());

		/* fournisseur to modify */

		fournisseurA.setEmail(fournisseur.getEmail());
		fournisseurA.setUsername(fournisseur.getUsername());
		fournisseurA.setPassword(userToModify.getPassword());
		
		fournisseurA.setGerant(fournisseur.getGerant());
		fournisseurA.setNomSociete(fournisseur.getNomSociete());
		fournisseurA.setLieuSociete(fournisseur.getLieuSociete());
		/* save */
		fournisseurRepository.save(fournisseurA);
		userRepository.save(userToModify);
		session.setAttribute("compteUser", fournisseurA);
		JwtResponse a = (JwtResponse) session.getAttribute("user");
		a.setEmail(userToModify.getEmail());
		a.setId(userToModify.getId());
		a.setUsername(userToModify.getUsername());
		session.setAttribute("user", a);
		
		
		/* redirect */
		
		RedirectView redirect = new RedirectView();
		redirect.setUrl("/index");
		return new ModelAndView(redirect);
		
	}
	@PostMapping("/profileEdit")
	
	public ModelAndView getProfileEdit(User user , HttpSession session)
	{
		JwtResponse u= (JwtResponse)session.getAttribute("user");
		
		
		User userToModify = userRepository.findByEmail(u.getEmail());
		
		/* user to modify */
	
		userToModify.setEmail(user.getEmail());
		userToModify.setPassword(encoder.encode(user.getPassword()));
		userToModify.setUsername(user.getUsername());
		
		
		/* administratif */
		
		if(u.getRoles().get(0)=="ROLE_ADMINISTRATIF")
		{
			Administratif adminst = administratifRepository.findByEmail(u.getEmail());
			adminst.setEmail(user.getEmail());
			adminst.setPassword(userToModify.getPassword());
			adminst.setUsername(user.getUsername());
	
			administratifRepository.save(adminst);
			session.setAttribute("compteUser", adminst);
		
		
		
		
		}
		else if(u.getRoles().get(0)=="ROLE_TECHNICIEN")
		{
			Technicien tech = techRep.findByEmail(u.getEmail());
			tech.setEmail(user.getEmail());
			tech.setPassword(userToModify.getPassword());
			tech.setUsername(user.getUsername());
	
			techRep.save(tech);
			session.setAttribute("compteUser", tech);
		
		
		
		
		}
		
		
		
		else if(u.getRoles().get(0)=="ROLE_RESPO_RESSOURCES")
		{
			ResponsableRessource respo = respoRess.findByEmail(u.getEmail());
			respo.setEmail(user.getEmail());
			respo.setPassword(userToModify.getPassword());
			respo.setUsername(user.getUsername());
	
			respoRess.save(respo);
			session.setAttribute("compteUser", respo);
		
		
		
		
		}
		
		
		/* save */
		
		userRepository.save(userToModify);
		
		JwtResponse a = (JwtResponse) session.getAttribute("user");
		a.setEmail(userToModify.getEmail());
		a.setId(userToModify.getId());
		a.setUsername(userToModify.getUsername());
		session.setAttribute("user", a);
	
		
		
		/* redirect */
		
		RedirectView redirect = new RedirectView();
		redirect.setUrl("/index");
		return new ModelAndView(redirect);
		
	}
	
	@PostMapping("/profileEditEnseignant")
	
	public ModelAndView getProfileEditE(Enseignant enseignant , HttpSession session)
	{
		
		JwtResponse u= (JwtResponse)session.getAttribute("user");
	
		Enseignant enseignantToModify = enseignantRep.findByEmail(u.getEmail());
		User userToModify = userRepository.findByEmail(u.getEmail());
		
		/* user to modify */
	
		userToModify.setEmail(enseignant.getEmail());
		userToModify.setPassword(encoder.encode(enseignant.getPassword()));
		userToModify.setUsername(enseignant.getUsername());
		
		
		
		/* fournisseur to modify */
	
		enseignantToModify.setEmail(enseignant.getEmail());
		enseignantToModify.setUsername(enseignant.getUsername());
		enseignantToModify.setPassword(userToModify.getPassword());
		
		enseignantToModify.setLab(enseignant.getLab());

		
		/* save */
		
		enseignantRep.save(enseignantToModify);
		userRepository.save(userToModify);
		session.setAttribute("compteUser", enseignant);
		JwtResponse a = (JwtResponse) session.getAttribute("user");
		a.setEmail(userToModify.getEmail());
		a.setId(userToModify.getId());
		a.setUsername(userToModify.getUsername());
		session.setAttribute("user", a);
	
		
		
		/* redirect */
		
		RedirectView redirect = new RedirectView();
		redirect.setUrl("/index");
		return new ModelAndView(redirect);
		
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/reclamerPanne")
	
	public ModelAndView reclamerPanne(Model model,HttpSession session)
	{
		String ViewName ="examples/reclamerPanne";
		
		model.addAttribute("panne",new Panne());
		return new ModelAndView(ViewName,(Map<String, Object>) model);
	}
	
	
	
	@PostMapping("/addPanne")
	public ModelAndView setNewPanne(Panne panne , HttpSession session)
	{
		Panne panneAjoutee = panne;
		panneAjoutee.setPersonne((User)session.getAttribute("compteUser")); 
		panneAjoutee.setIdRessource("55555555");
		panneService.ajouterPanne(panneAjoutee);
		
		
		RedirectView redirect = new RedirectView();
		redirect.setUrl("/index");
		return new ModelAndView(redirect);
	}
	@GetMapping("/ListerLesPannes")
	public ModelAndView ListePannes(HttpSession session)
	{
		Map<String, Object> model = new HashMap<String, Object>();

		String ViewName ="examples/ListerLesPannes";
		
		model.put("Pannes",panneService.ListerPannes());
		
		return new ModelAndView(ViewName,model);
	}
	
	
	@GetMapping("/detailPanne")
	public ModelAndView detailPanne(@RequestParam String id)
	{		
		String ViewName ="examples/detailPanne";
		Panne Panne = panneService.findByIdPanne(id);
		Map<String, Object> M = new HashMap<String, Object>();
		
		constat con = new constat();
		
		M.put("Panne", Panne);
		
		M.put("constat", con);
		
		
		return new ModelAndView(ViewName,M);
	}
	
	
	@GetMapping("/detailConstat")
	public ModelAndView detailConstat(@RequestParam String id) {
		constat con =  constatService.findByIdconstat(id);
		List<Ressource> ress = ressourceService.getRessourse();
		
		
		Ressource ressource = ressourceService.findByIdR(con.getCode_ressource(), ress);
		
		Calendar c = Calendar.getInstance();
		System.out.println(c.getTime());
		c.setTime(ressource.getDateliv());
		System.out.println(c.getTime());
		c.add(Calendar.MONTH, ressource.getGarantie());
		System.out.println(sdf.format(c.getTime()));
		
		Calendar now = Calendar.getInstance();
		System.out.println(sdf.format(now.getTime()));
		int garantie=-2;
		if (now.compareTo(c) > 0) {
			garantie = -1;
        } else if (now.compareTo(c) <= 0) {
        	garantie =0;
        }
		
		if(garantie == 0 && con.getOrdrePanne().equals("MATERIEL")) {
			garantie =1;
		}
		
		Map<String,Object> M = new HashMap<String,Object>();
		Notification notification = new Notification();
		M.put("constat", con);
		M.put("ressource", ressource);
		M.put("garantie", garantie);
		M.put("notification", notification);
		return new ModelAndView("examples/detailConstat",M);
	}
	
	
	@GetMapping("/detailOffre")
	public ModelAndView detailOffre(Model model,  @RequestParam String id)
	{
		
		String ViewName ="examples/detailOffre";
		model.addAttribute("soumission",new Soumission());
		
		List<SoumissionAfficher> soumissionAfficher = new ArrayList<SoumissionAfficher>();
		
		
		List<Soumission> soumis = soumissionService.afficherSoumissionOffre(id);
		
		SoumissionAfficher soumissionTemp = new SoumissionAfficher();
		Fournisseur four = new Fournisseur();
		AppelsDoffre offre = OffreService.chercherAppelOffre(id);
		
		if(offre.getIsTerminated()==false)
		{
			
		
		for (Soumission soumission : soumis) {
			
			soumissionTemp.setBudget_proposee(soumission.getBudget_proposee());
			soumissionTemp.setId(soumission.getId());
			soumissionTemp.setDate_prevue_livraison(soumission.getDate_prevue_livraison());
			four= fournisseurRepository.findByIdFourniseur(soumission.getFournisseurAplicants());
			soumissionTemp.setNomFournisseur(four.getUsername());
			soumissionTemp.setGerant(four.getGerant());
			soumissionTemp.setIdFournisseur(four.getId());
			soumissionTemp.setLieuSociete(four.getLieuSociete());
			soumissionTemp.setNomSociete(four.getNomSociete());
			soumissionTemp.setIsblocked(four.isIsblocked());
			soumissionTemp.setSubmitted(soumission.isAccepted());
			soumissionAfficher.add(soumissionTemp);
		}
		}
		else
		{

			for (Soumission soumission : soumis) {
				
				if(soumission.isAccepted()==true)
				{
				soumissionTemp.setBudget_proposee(soumission.getBudget_proposee());
				soumissionTemp.setId(soumission.getId());
				soumissionTemp.setDate_prevue_livraison(soumission.getDate_prevue_livraison());
				four= fournisseurRepository.findByIdFourniseur(soumission.getFournisseurAplicants());
				soumissionTemp.setNomFournisseur(four.getUsername());
				soumissionTemp.setGerant(four.getGerant());
				soumissionTemp.setIdFournisseur(four.getId());
				soumissionTemp.setLieuSociete(four.getLieuSociete());
				soumissionTemp.setNomSociete(four.getNomSociete());
				soumissionTemp.setIsblocked(four.isIsblocked());
				soumissionTemp.setSubmitted(soumission.isAccepted());
				soumissionAfficher.add(soumissionTemp);
				}
			}
		}
		model.addAttribute("soumissions",soumissionAfficher );
	
		Map<String, Object> M = new HashMap<String, Object>();
		
		M.put("offre", offre);

		return new ModelAndView(ViewName,M);
	}
	
	@GetMapping("/offreAcceptee")
	
	public ModelAndView detailOffreacceptee(Model model,  @RequestParam String id)
	{
		
		String ViewName ="examples/offreAccepte";
		
		AppelsDoffre offre = OffreService.chercherAppelOffre(id);
		
		Map<String, Object> M = new HashMap<String, Object>();
		
		M.put("offre", offre);

		return new ModelAndView(ViewName,M);
	}
	
	
	@GetMapping("/accepterOffreFournisseur")
	
	public ModelAndView accepteOffreFour(@RequestParam("id-soumission") String soumission , @RequestParam("id-offre") String offre)
	
	{
		Soumission s = soumissionService.chercherSoumissionId(soumission);
		s.setAccepted(true);
		AppelsDoffre appel = OffreService.chercherAppelOffre(offre);
		appel.setIsTerminated(true);
		
		soumissionService.AjouterSoumission(s);
		OffreService.AjouterOffre(appel);
		return new ModelAndView("examples/AppelDoffreFournisseur");
	}
	@GetMapping("/AppelsDoffreFournisseur")
	
	public ModelAndView getAppelOffreFournisseur(Model model, HttpServletRequest request)
	{
		
		
		model.addAttribute("offres" ,OffreService.afficherOffres() );
		return new ModelAndView("examples/AppelDoffreFournisseur",(Map<String, Object>) model);
		
	}
	
	
	@PostMapping("/modifierOffre")
	public ModelAndView modifierAppelOffre(AppelsDoffre app , @RequestParam("IDD") String id)
	{
		String ViewName ="examples/detailOffre";
		
		
		AppelsDoffre a = new AppelsDoffre();
		
		
		
		a.setIdoffre(id);
		a.setTitreOffre(app.getTitreOffre());
		a.setDateDebut(app.getDateDebut());
		a.setDateFin(app.getDateFin());
		a.setBesoins(app.getBesoins());
		
		
		OffreService.sauvegarderOffre(a);
		
		Map<String, Object> M = new HashMap<String, Object>();

		M.put("offre", app);

		return new ModelAndView(ViewName,M);

		
	}
	
	@PostMapping("/addSoumission")
	public ModelAndView addSoumission(Soumission soummission , HttpSession session,Model model , @RequestParam("idoffresoumission") String idoffresoumissis )
	{
		String ViewName ="examples/AppelDoffreFournisseur";
		User user = (User)session.getAttribute("compteUser");
		soummission.setFournisseurAplicants(user.getId());
		soummission.setIdoffre(idoffresoumissis);
		Soumission souss = soumissionService.AjouterSoumission(soummission);
		souss.setSoum(souss.getId());
		soumissionService.AjouterSoumission(souss);
		model.addAttribute("offres" ,OffreService.afficherOffres() );
		return new ModelAndView(ViewName,(Map<String, Object>) model);
		
		
	}
	/* ajouter constat */
	@PostMapping("/constat")
	public  ModelAndView generConstat(constat con,HttpSession s) {
		constat  constatAdd = con;
		User user =(User) s.getAttribute("compteUser");
		constatAdd.setIdTech(user.getId());
		constatAdd.setCode_ressource("55555555");
		constatService.ajouterConstat(constatAdd);
		
		RedirectView redirect = new RedirectView();
		redirect.setUrl("/ListerLesPannes");
		return new ModelAndView(redirect);
	}
	
	/* Lister les constats */
	@GetMapping("/listerConstat")
	public ModelAndView getConstats(HttpSession s) {
		List<constat> constats = new ArrayList<constat>();
		Map<String,Object> map = new HashMap<String,Object>();
		
		constats = constatService.listerConstat();
		map.put("constats", constats);
		
		return new ModelAndView("examples/listeConstat",map);
	}


	
	@GetMapping("/listmateriel")

	public ModelAndView get(Model model, HttpSession session)
	{

		List materiel = new ArrayList<Ressource>();
		//getlist de  ressource
		String personellID = ((JwtResponse)session.getAttribute("user")).getId();
		//Optional resources = ressourceRep.findById(personellID);
		List besoins = besoinRep.findAll();
		// getAll Enseignant Materiel
		List ordinateures = ordinateurRep.findAll();
		List imprimantes = imprimanteRep.findAll();
		model.addAttribute("counter",new Counter());
		model.addAttribute("besoins",besoins);
		model.addAttribute("materiel",materiel);
		model.addAttribute("imprimantes",imprimantes);
		model.addAttribute("ordinateures",ordinateures);
		return new ModelAndView("examples/listmateriel",(Map<String, Object>) model);
	}
	
	@PostMapping("/listmateriel")
	public RedirectView addBesoin(@RequestBody MultiValueMap<String, String> formData , HttpServletRequest request) {

        String userId = ((Enseignant)request.getSession().getAttribute("compteUser")).getId();
		Besoin besoin = new Besoin(userId,formData.get("ressource").get(0),formData.get("discription").get(0));
		besoinRep.save(besoin);
        return new RedirectView("listmateriel");
    }
	@GetMapping("/AppelsDoffre")
	public ModelAndView AppelsDoffre(Model model, HttpServletRequest request)
	{
		
		//List materiel = new ArrayList<Ressource>();
		//getlist de  ressource
		//String personellID = ((JwtResponse)session.getAttribute("user")).getId();
		//Optional resources = ressourceRep.findById(personellID);
		//List besoins = besoinRep.findAll();
		// getAll Enseignant Materiel
		//model.addAttribute("counter",new Counter());
		//model.addAttribute("materiel",materiel);
		
		
		AppelsDoffre A = new AppelsDoffre();
		model.addAttribute("Offre",A);
		model.addAttribute("offres" ,OffreService.afficherOffres() );
		return new ModelAndView("examples/AppelDOffre",(Map<String, Object>) model);
	}
	
	@PostMapping("/AjouterOffre")
	public RedirectView AjouterOffre(AppelsDoffre A,Model model, HttpServletRequest request)
	{
		
		OffreService.AjouterOffre(A);
		return new RedirectView("/AppelsDoffre");
		
		
		//List materiel = new ArrayList<Ressource>();
		//getlist de  ressource
		//String personellID = ((JwtResponse)session.getAttribute("user")).getId();
		//Optional resources = ressourceRep.findById(personellID);
		//List besoins = besoinRep.findAll();
		// getAll Enseignant Materiel
		//model.addAttribute("counter",new Counter());
		//model.addAttribute("Offre",A);
		//model.addAttribute("materiel",materiel);
		
	}
	 

	@GetMapping("/listeFournisseur")
	
	public ModelAndView getAllFournisseur(Model model)
	{
		model.addAttribute("fournisseurs",fournisseurRepository.findAll());
		return new ModelAndView("examples/ListeFournisseur",(Map<String, Object>) model);
	}
	
	@GetMapping("/ajouterBlackList")
	
	public ModelAndView addBlackList(@RequestParam("idf") String id , @RequestParam("motif") String motif)
	{
		
		Fournisseur four= fournisseurRepository.findByIdFourniseur(id);
		four.setIsblocked(true);
		four.setMotifOfblocking(motif);
		fournisseurRepository.save(four);
		RedirectView redirect = new RedirectView();
		redirect.setUrl("/listeFournisseur");
		return new ModelAndView(redirect);
	}
		
	@GetMapping("/supprimerBlackList")
		
		public ModelAndView removeBlackList(@RequestParam String id)
		{
			
			Fournisseur four= fournisseurRepository.findByIdFourniseur(id);
			four.setIsblocked(false);
			four.setMotifOfblocking("");
			fournisseurRepository.save(four);
			RedirectView redirect = new RedirectView();
			redirect.setUrl("/listeFournisseur");
			return new ModelAndView(redirect);
		}
	
	// pour le chef de depar
		@RequestMapping("/getListBesoin")
		@ResponseBody
		public List<Besoin> getListBesoin() {
	        List besoins = besoinRep.findAll();
			return besoins;
		}

		@GetMapping("/listBesoin")
		public ModelAndView getListBesoins(Model model, HttpSession session)
		{
			List materiel = new ArrayList<Ressource>();
			//getlist de  ressource
			String personellID = ((JwtResponse)session.getAttribute("user")).getId();
			System.out.println(personellID);
			Map<Besoin, String> data = new HashMap<Besoin, String>();
			List besoins = besoinRep.findAll();//a remplacer
			Besoin b;
			Optional<User> pers;
			for(int i=0;i<besoins.size();i++){
				b = (Besoin) besoins.get(i);
				pers =userRepository.findById(b.getpersID());
				data.put(b,pers.get().getUsername());
			}
			// getAll Enseignant Materiel
			model.addAttribute("counter",new Counter());
			model.addAttribute("besoins",data);
			return new ModelAndView("examples/listBesoin",(Map<String, Object>) model);
		}



	    @RequestMapping(value = "/listBesoin",method = RequestMethod.POST)
	    @ResponseBody
	    public boolean storeListBesoin(@RequestParam Map<String, String> formData , HttpSession session) {
			String chef = ((JwtResponse)session.getAttribute("user")).getId();
			List data = new ArrayList<String>();
			List<Besoin> besoins = besoinRep.findAll();
			formData.forEach((k,v)-> {
				for (int i=0;i<besoins.size();i++){
					if(besoins.get(i).getId().equals(v)){
						data.add(besoins.get(i));
					}
				}
			});
			for (int i=0;i<besoins.size();i++){
				besoinRep.deleteById(besoins.get(i).getId());
			}
			Demande demande = new Demande(chef,data);
			demandeRep.save(demande);
			return true;
		}


		@GetMapping("/listDemande")
		public ModelAndView getListDemande(Model model, HttpSession session)
		{

			String chefID = ((JwtResponse)session.getAttribute("user")).getId();
			// pour le chef des ressourece
			// List demandes = demandeRep.findAll()
			// pour le chef deparetement
			List demandes = demandeRep.findByChef(chefID);
			// getAll Enseignant Materiel
			model.addAttribute("counter",new Counter());
			model.addAttribute("demandes",demandes);
			return new ModelAndView("examples/listDemande",(Map<String, Object>) model);
		}

	    @GetMapping("/demandeDetails")
	    public ModelAndView demandeDetails(@RequestParam String num)
	    {
			List materiel = new ArrayList<Ressource>();
			Map<String, Object> map = new HashMap<String, Object>();
			//getlist de  ressource
			Map<Besoin, String> data = new HashMap<Besoin, String>();
			List besoins = (demandeRep.findByNum(num)).get(0).getBesoins();
			Besoin b;
			Optional<User> pers;
			for(int i=0;i<besoins.size();i++){
				b = (Besoin) besoins.get(i);
				pers =userRepository.findById(b.getpersID());
				data.put(b,pers.get().getUsername());
			}
			// getAll Enseignant Materiel
			map.put("counter",new Counter());
			map.put("besoins",data);
	        return new ModelAndView("examples/demandeDetails", (Map<String, Object>) map);
	    }



		@GetMapping("/listRessourceAffecter")
		public ModelAndView getListRessourceAffecter(Model model)
		{
			Map pers = new HashMap<Besoin,User>();
			List<Demande> demandes = demandeRep.findByState("STATE_OFFRE");
			List<Besoin> besoins;
			Besoin besoin;
			for (int i=0;i<demandes.size();i++){
				besoins = (demandes.get(i)).getBesoins();
				for(int j=0;j<besoins.size();j++){
					besoin = besoins.get(j);
					pers.put(besoin,userRepository.findById(besoin.getpersID()).get());
				}
			}
			List ordinateure = ordinateurRep.findByAffected(false);
			List imprimantes = imprimanteRep.findByAffected(false);
			model.addAttribute("counter",new Counter());
			model.addAttribute("personnes",pers);
			model.addAttribute("ordinateures",ordinateure);
			model.addAttribute("imprimantes",imprimantes);
			return new ModelAndView("examples/listRassourceAffecter",(Map<String, Object>) model);
		}
		

		@RequestMapping(value = "/getListPersonnel",method = RequestMethod.GET)
		@ResponseBody
		public List getListPersonnel()
		{
			List personels = new ArrayList();
			List<Demande> demandes = demandeRep.findByState("STATE_OFFRE");
			List<Besoin> besoins;
			Besoin besoin;
			for (int i=0;i<demandes.size();i++){
				besoins = (demandes.get(i)).getBesoins();
				for(int j=0;j<besoins.size();j++){
					besoin = besoins.get(j);
					personels.add(userRepository.findById(besoin.getpersID()).get());
				}
			}
			return personels;
		}
		

		@RequestMapping(value = "/getListOrdinateure",method = RequestMethod.GET)
		@ResponseBody
		public List getListOrdinataure()
		{
			List ordinateures = ordinateurRep.findAll();
			return ordinateures;
		}
		


		@RequestMapping(value = "/getListImprimante",method = RequestMethod.GET)
		@ResponseBody
		public List getListImprimante()
		{
			List imprimantes = imprimanteRep.findAll();
			return imprimantes;
		}
		
		

		@RequestMapping(value = "/storeAffectation",method = RequestMethod.POST)
		@ResponseBody
		public boolean updateListRessourceAffecter(@RequestParam Map<String, String> formData)
		{
			System.out.println(formData);
			List<String> data = new ArrayList<String>();
			formData.forEach((k,v)->{
				data.add(v);
			});
			Affectation affectation = new Affectation(data.get(1),data.get(2),Boolean.parseBoolean(data.get(3)));
			affectationRep.save(affectation);
			switch (data.get(0)){
				case "true":{
				    // update Ordinateure
                    Optional ord =  ordinateurRep.findById(data.get(2));
                    Ordinateure ordinateure = (Ordinateure) ord.get();
                    ordinateure.setAffected(true);
                    ordinateurRep.save(ordinateure);
					List<Demande> demandes = demandeRep.findByState("STATE_OFFRE");
					List<Besoin> besoins;
					Besoin besoin;
					for (int i=0;i<demandes.size();i++){
						besoins = (demandes.get(i)).getBesoins();
						for(int j=0;j<besoins.size();j++){
							besoin = besoins.get(j);
							if(besoin.getType().equals("Ordinateure") && besoin.getpersID().equals(data.get(1))){
								besoins.remove(besoin);
							}
						}
						if(demandes.get(i).getBesoins().size() == 0) {
                            demandeRep.deleteById(demandes.get(i).getId());
                            break;
                        }
                        demandeRep.save(demandes.get(i));
					}
					break;
				}
				case "false": {
				    // update imprimante
                    Optional imp =  imprimanteRep.findById(data.get(2));
                    Imprimante imprimante = (Imprimante) imp.get();
                    imprimante.setAffected(true);
                    imprimanteRep.save(imprimante);
					List<Demande> demandes = demandeRep.findByState("STATE_OFFRE");
					List<Besoin> besoins;
					Besoin besoin;
					for (int i=0;i<demandes.size();i++){
						besoins = (demandes.get(i)).getBesoins();
						for(int j=0;j<besoins.size();j++){
							besoin = besoins.get(j);
							if(besoin.getType().equals("imprimante") && besoin.getpersID().equals(data.get(1))){
                                besoins.remove(besoin);
                            }
                        }
                        if(demandes.get(i).getBesoins().size() == 0) {
                            demandeRep.delete(demandes.get(i));
                            break;
                        }
                        demandeRep.save(demandes.get(i));
					}
					break;
				}
			}
			return true;
		}
	
		

		@GetMapping("/Listage")
		public ModelAndView getListage() {
			
			Map<String,Object> M = new HashMap<String,Object>();
			
			List<Technicien> listT = technicienSer.getTechnicien();
			List<Enseignant> listE = enseignantRep.findAll();
			List<Administratif> listA = administratifRepository.findAll();
			List<Ordinateure> listO = ordinateurRep.findAll();
			List<Imprimante> listI = imprimanteRep.findAll();
			List<Fournisseur> listF = fournisseurRepository.findAll();
			M.put("Techniciens", listT);
			M.put("enseignants", listE);
			M.put("administratifs", listA);
			M.put("ordinateurs", listO);
			M.put("imprimantes", listI);
			M.put("fournisseurs", listF);
			return new ModelAndView("examples/listages",M);
		}
		
		
		
		
		@GetMapping("/PageModifierOrdinateur")
		public ModelAndView getPageModifier(@RequestParam String id) {
			Map<String,Object> M = new HashMap<String,Object>();
			List<Ressource> ress = ressourceService.getRessourse();
			Ressource ressource = ressourceService.findByIdR(id, ress);
			List<String> fournisseur = fournisseurSer.getFounisseurUsername();
			
			M.put("ordinateur", ressource);
			M.put("fournisseur", fournisseur);
			return new ModelAndView("examples/modifierRessource",M);
		}
		
		@PostMapping("/PageModifierOrdinateur")
		public RedirectView PostModifierOrd(Ordinateure ord,@RequestParam("codeRessource") String id) {
			ord.setId(id);
			System.out.println(ord.getId());
			ordinateurRep.save(ord);
			return new RedirectView("/Listage");
		}
		
		@GetMapping("/PageSupprimerOrdinateur")
		public RedirectView deleteOrdinateur(@RequestParam String id) {
			if(id != null) {
				ordinateurRep.deleteById(id);
			}
			
			return new RedirectView("/Listage");
		}
		
		@GetMapping("/PageModifierImprimante")
		public ModelAndView getPageModifierImp(@RequestParam String id) {
			Map<String,Object> M = new HashMap<String,Object>();
			List<Ressource> ress = ressourceService.getRessourse();
			Ressource ressource = ressourceService.findByIdR(id, ress);
			List<String> fournisseur = fournisseurSer.getFounisseurUsername();
			
			M.put("imprimante", ressource);
			M.put("fournisseur", fournisseur);
			return new ModelAndView("examples/ModifierImprimante",M);
		}
		
		@PostMapping("/PageModifierImprimante")
		public RedirectView PostModifierImp(Imprimante imp,@RequestParam("idImp") String id) {
			imp.setId(id);
			System.out.println(imp.getId());
			imprimanteRep.save(imp);
			return new RedirectView("/Listage");
		}
		
		@GetMapping("/PageSupprimerImprimante")
		public RedirectView deleteImprimante(@RequestParam String id) {
			if(id != null) {
				imprimanteRep.deleteById(id);
			}
			
			return new RedirectView("/Listage");
		}

}

