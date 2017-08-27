package br.com.simnetwork.old;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.simnetwork.model.entity.arquivo.Box;
import br.com.simnetwork.model.entity.basico.LogMensagem;
import br.com.simnetwork.model.entity.basico.rota.Rota;
import br.com.simnetwork.model.entity.basico.rota.RouteGroup;
import br.com.simnetwork.model.entity.basico.usuario.PersonTipo;
import br.com.simnetwork.model.entity.basico.usuario.Usuario;
import br.com.simnetwork.model.entity.competencia.Competencia;
import br.com.simnetwork.model.entity.competencia.CompetenciaTipo;
import br.com.simnetwork.model.entity.competencia.CompetenciaUN;
import br.com.simnetwork.model.entity.framework.App;
import br.com.simnetwork.model.entity.framework.HibernateUtil;
import br.com.simnetwork.model.entity.project.Project;
import br.com.simnetwork.model.entity.project.ProjectStatus;
import br.com.simnetwork.model.entity.project.ProjectType;

/*
public class Model{
	
		//Variáveis Globais
	
		public List<Pessoa> persons = new LinkedList<>();
		public List<Rota> routes = new LinkedList<>();
		public List<MensagemAgendada> scheduleMessages = new LinkedList<>();
		
		public ProjectType projectType = ProjectType.MONETIZADO;
		public ProjectStatus projectStatus = ProjectStatus.IDEIA;
		public TermoTopico termoTopico = TermoTopico.VALIDADE;
		public CompetenciaTipo competenciaTipo = CompetenciaTipo.PROGRAMACAO;
		public CompetenciaUN competenciaUN = CompetenciaUN.UNIDADE;
		public RouteGroup routeGroup = RouteGroup.CLIENTES;
		public Box box = null;
		public final ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);
		public Administracao administracao;
		
		
		//Construtor
		
		@SuppressWarnings("unchecked")
		public Model(){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria critPerson = session.createCriteria(Pessoa.class);
			critPerson.add(Restrictions.eq("personType",PersonTipo.PARCEIRO));
			critPerson.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			this.persons = (List<Pessoa>) critPerson.list();
			Criteria critRoute = session.createCriteria(Rota.class);
			this.routes = (List<Rota>) critRoute.list();
			Criteria critScheduleMessage = session.createCriteria(MensagemAgendada.class);
			critScheduleMessage.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			this.scheduleMessages = (List<MensagemAgendada>) critScheduleMessage.list();
			Criteria critAdministracao = session.createCriteria(Administracao.class);
			this.administracao = (Administracao) critAdministracao.uniqueResult();
			if(administracao==null) {
				administracao = new Administracao();
				session.save(administracao);
				session.getTransaction().commit();
			}
			session.close();
			
			List<String> routesString = new LinkedList<>();
			List<RouteGroup> routesGroup = new LinkedList<>();
			
			routesString.add("Termos");
			routesGroup.add(RouteGroup.INFORMACOES);
			
			routesString.add("Apelido");
			routesGroup.add(RouteGroup.MEUSDADOS);
			
			routesString.add("Nome Completo");
			routesGroup.add(RouteGroup.MEUSDADOS);
			
			routesString.add("Competências");
			routesGroup.add(RouteGroup.MEUSDADOS);
			
			routesString.add("Dar Permissão");
			routesGroup.add(RouteGroup.ADMINISTRATIVO);
			
			routesString.add("Remover Permissão");
			routesGroup.add(RouteGroup.ADMINISTRATIVO);
			
			routesString.add("Comandos");
			routesGroup.add(RouteGroup.NAVEGACAO);
			
			routesString.add("Dados dos Usuários");
			routesGroup.add(RouteGroup.ADMINISTRATIVO);
			
			routesString.add("Dados Login");
			routesGroup.add(RouteGroup.MEUSDADOS);
			
			routesString.add("Adicionar");
			routesGroup.add(RouteGroup.PROJETO);
			
			routesString.add("Remover Usuário");
			routesGroup.add(RouteGroup.ADMINISTRATIVO);
			
			routesString.add("Excluir");
			routesGroup.add(RouteGroup.PROJETO);
			
			routesString.add("Detalhes");
			routesGroup.add(RouteGroup.PROJETO);
			
			routesString.add("Editar");
			routesGroup.add(RouteGroup.PROJETO);
			
			routesString.add("Adicionar");
			routesGroup.add(RouteGroup.CLIENTES);
			
			routesString.add("Testes");
			routesGroup.add(RouteGroup.NAVEGACAO);
			
			routesString.add("Ativar Usuário");
			routesGroup.add(RouteGroup.ADMINISTRATIVO);
			
			routesString.add("Dar Permissão por menu");
			routesGroup.add(RouteGroup.ADMINISTRATIVO);
			
			routesString.add("Remover Permissão por menu");
			routesGroup.add(RouteGroup.ADMINISTRATIVO);
			
			routesString.add("Desativar Usuário");
			routesGroup.add(RouteGroup.ADMINISTRATIVO);
			
			routesString.add("Adicionar");
			routesGroup.add(RouteGroup.TERMOS);
			
			routesString.add("Editar");
			routesGroup.add(RouteGroup.TERMOS);
			
			routesString.add("Ler");
			routesGroup.add(RouteGroup.TERMOS);
			
			routesString.add("Deletar");
			routesGroup.add(RouteGroup.TERMOS);
			
			routesString.add("Oficializar");
			routesGroup.add(RouteGroup.TERMOS);
			
			routesString.add("Aceitar");
			routesGroup.add(RouteGroup.TERMOS);
			
			routesString.add("Adicionar");
			routesGroup.add(RouteGroup.COMPETENCIAS);
			
			routesString.add("Editar");
			routesGroup.add(RouteGroup.COMPETENCIAS);
			
			routesString.add("Consultar");
			routesGroup.add(RouteGroup.COMPETENCIAS);
			
			routesString.add("Deletar");
			routesGroup.add(RouteGroup.COMPETENCIAS);
			
			for (int i = 0; i < routesString.size(); i++) {
				if(locateRoute(routesGroup.get(i).getDesc()+" - "+routesString.get(i))==null){
					addRoute(new Rota(routesString.get(i),routesGroup.get(i)));
				}
			}
			
			
			
		}
		
		public void save(Object object) {
			HibernateUtil.save(object);
		}
		
		//Person-------------------------------------------------------------------
		
		public void addPerson(Pessoa person){
			PersonRepository personRepo = (PersonRepository) ApplicationContext.getContext().getBean("personRepository");
			personRepo.save(person);
			
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(person);
			session.getTransaction().commit();
			session.close();
			if(person.getPersonType().equals(PersonTipo.PARCEIRO)){
				persons.add(person);
				grandBasic(person);
			}
			
		}
		
		public void editPerson(Pessoa person){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(person);
			session.getTransaction().commit();
			session.close();
		}
		
		public Pessoa locatePerson(String person){
			for (Pessoa currentPerson : persons) {
				if(currentPerson.getApelido()!=null){
					if(currentPerson.getApelido().equals(person))
						return currentPerson;
				}
			}
			return null;
		}
		
		
		public Pessoa locateTelegramUser(String idTelegram){
			for (Pessoa person : persons) {
				if(person.getIdTelegram().equals(idTelegram))
					return person;
			}
			return null;
		}
			
		public Pessoa addPersonByTelegram(String idTelegram){
			Pessoa person = new Pessoa(idTelegram);
			person.setPersonType(PersonTipo.PARCEIRO);
			addPerson(person);
			person.setIdTelegram(idTelegram);
			return person;
		}
		
		public void editUsernamePassword(Pessoa person){
			editPerson(person);
		}
		
		public boolean havePermission(Rota route,Pessoa person){
			for (Rota rota : person.getRotasPermitidas()) {
				if(rota.getCompleteWay().equals(route.getCompleteWay())){
					return true;
				}
			}
			return false;
		}
		
		public Set<Rota> routesDenieds(Pessoa person){
			Set<Rota> result = new HashSet<Rota>();
			for (Rota route : routes){
				if(!havePermission(route, person)){
					result.add(route);
				}
			}
			return result;
		}
		
		public void removePerson(Pessoa person){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(person);
			session.getTransaction().commit();
			session.close();
			persons.remove(person);
		}
		
		//Route-------------------------------------------------------------------
		
		public void addRoute(Rota route){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(route);
			session.getTransaction().commit();
			session.close();
			routes.add(route);
		}
		
		public Rota locateRoute(String route){
			for (Rota currentRoute : routes) {
				if(currentRoute.getCompleteWay().equals(route))
					return currentRoute;
			}
			return null;
		}
		
		
		
		//Grand/Revoke Permissions-------------------------------------------------------------------
		
		
		public void grandBasic(Pessoa person){
			grandPermission(person, RouteGroup.MEUSDADOS);
			grandPermission(person, RouteGroup.NAVEGACAO);
		}
		
		public Pessoa revokePermission(Pessoa person,Rota route){
			Set<Rota> rotas = person.getRotasPermitidas();
			for (Rota currentRoute : rotas) {
				if(currentRoute.getName().equals(route.getName())){
					rotas.remove(currentRoute);
					break;
				}
			}
			person.setRotasPermitidas(rotas);
			editPerson(person);
			return person;
		}
		
		public Pessoa grandPermission(Pessoa person, Rota route){
			if(!person.getRotasPermitidas().contains(route)) {
				Set<Rota> rotas = person.getRotasPermitidas();
				rotas.add(route);
				person.setRotasPermitidas(rotas);
				editPerson(person);
				return person;
			}
			return person;
		}
		
		public void grandPermission(Pessoa person, RouteGroup routeGroup){
			List<Rota> routesToAdd = new LinkedList<>();
			for (Rota route : routes) {
				if(route.getRouteGroup()==routeGroup){
					routesToAdd.add(route);
				}
				
			}
			
			for (Rota route : routesToAdd) {
				grandPermission(person, route);
			}
		}
		
		public void revokePermission(Pessoa person, RouteGroup routeGroup){
			List<Rota> routesToRemove = new LinkedList<>();
			for (Rota route : routes) {
				if(route.getRouteGroup()==routeGroup){
					routesToRemove.add(route);
				}
				
			}
			
			for (Rota route : routesToRemove) {
				revokePermission(person, route);
			}
		}
		
		//Projets--------------------------------------------------------------------
		
		public void addProject(Project project){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(project);
			session.getTransaction().commit();
			session.close();
		}
		
		public void removeProject(Project project){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(project);
			session.getTransaction().commit();
			session.close();
		}
		
		public Project addProjectByTelegram(String title, String description, ProjectType projectType, Pessoa person){
			Project project = new Project(title,description,projectType,person);
			addProject(project);
			return project;
		}
		
		public List<Project> locateAllProjects(){
			List<Project> projects = new LinkedList<>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria critProject = session.createCriteria(Project.class);
			critProject.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			projects = (List<Project>) critProject.list();
			session.close();
			return projects;
		}
		
		public Project locateProjectById(int id){
			List<Project> projects = locateAllProjects();
			for (Project project : projects) {
				if(project.getId()==id){
					return project;
				}
			}
			return null;
		}
		
		public Project locateProjectByString(String string){
			List<Project> projects = locateAllProjects();
			for (Project project : projects) {
				if(project.getId()==Integer.parseInt(string.substring(0,string.indexOf(" - ")))){
					return project;
				}
			}
			return null;
		}
		
		public void editProject(Project project){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(project);
			session.getTransaction().commit();
			session.close();
		}
		
		//Cliente--------------------------------------------------------------------
		
		public void addClient(Pessoa person){
			person.setPersonType(PersonTipo.CLIENTE);
			addPerson(person);
		}
		
		//Visualizações Bot----------------------------------------------------------
		
		public List<String> showGroupRoutes(Set<Rota> routesDenieds){
			if(routesDenieds == null){
				return null;
			}
			List<String> saida = new LinkedList<>();
			
			for (RouteGroup routeGroup : RouteGroup.values()) {
				boolean first = true;
				for (Rota route : routesDenieds) {
					if(route.getRouteGroup().equals(routeGroup)){
						if(first){
							saida.add(routeGroup.desc);
							first = false;
						}
					}
				}
			}
			
			return saida;
		}
		
		public List<String> showRoutes(RouteGroup routeGroup, Set<Rota> rotas){
			if(routeGroup == null){
				return null;
			}
			List<String> saida = new LinkedList<>();
			
			for (Rota route : rotas) {
				if(route.getRouteGroup().equals(routeGroup)){
					saida.add(route.getName()+"\n");
					}
					
				}
			
			return saida;
		}
		
		public String showRoutes(List<Rota> rotas){
			if(rotas == null){
				return "Nenhuma rota";
			}
			StringBuilder saida = new StringBuilder();
			
			for (RouteGroup routeGroup : RouteGroup.values()) {
				boolean first = true;
				for (Rota route : rotas) {
					if(route.getRouteGroup().equals(routeGroup)){
						if(first){
							saida.append(routeGroup.desc + "------------\n");
							first = false;
						}
						saida.append(route.getName()+"\n");
					}
				}
			}
			
			return saida.toString();
		}
		
		public List<String> showPersons(List<Pessoa> persons){
			if(persons == null){
				return null;
			}
			List<String> saida = new LinkedList<>();
			for (Pessoa person : persons) {
				saida.add(person.getApelido());
			}
			return saida;
		}
		
		public String showUserDataWithoutPassword(Pessoa person){
			if(persons == null){
				return "Nenhuma pessoa";
			}
			StringBuilder saida = new StringBuilder();
			saida.append("\n Id do Telegram: " + person.getIdTelegram());
			saida.append("\n Nome do Usuário: "+ person.getApelido());
			return saida.toString();
		}
		
		public String showUserData(Pessoa person){
			if(persons == null){
				return "Nenhuma pessoa";
			}
			StringBuilder saida = new StringBuilder();
			saida.append("\n Id do Telegram: " + person.getIdTelegram());
			saida.append("\n Nome do Usuário: "+ person.getApelido());
			return saida.toString();
		}
		
		public List<String> showProjects(List<Project> projects){
			if(projects == null){
				return null;
			}
			List<String> saida = new LinkedList<String>();
			for (Project project : projects) {
				saida.add(project.toString());
			}
			return saida;
			
		}
		
		public String showProject(Project project){
			StringBuilder saida = new StringBuilder();
			saida.append("\n Id do Projeto: "+project.getId());
			saida.append("\n Título: "+project.getTitle());
			saida.append("\n Descrição: "+project.getDesc());
			saida.append("\n Tipo: "+project.getType().description);
			saida.append("\n Status: "+project.getStatus().description+"\n");
			return saida.toString();
			
		}
		
		public List<String> showProjectTypes(){
			ProjectType[] types = ProjectType.values();
			List<String> saida = new LinkedList<>();
			for (ProjectType projectType : types) {
				saida.add(projectType.description);
			}
			return saida;
		}
		
		public List<String> showProjectStatus(){
			ProjectStatus[] status = ProjectStatus.values();
			List<String> saida = new LinkedList<String>();
			for (ProjectStatus projectStatus : status) {
				saida.add(projectStatus.description);
			}
			return saida;
		}
		
		//Termos-------------------------------------------------------------------------
		
		public void addTermo(Termo termo){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(termo);
			session.getTransaction().commit();
			session.close();
		}
		
		public void editTermo(Termo termo){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(termo);
			session.getTransaction().commit();
			session.close();
		}
		
		public void deleteTermo(Termo termo){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(termo);
			session.getTransaction().commit();
			session.close();
		}
		
		public Termo locateTermosByTopicoDesc(TermoTopico termoTopico, String desc){
			List<Termo> termos = new LinkedList<>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria crit = session.createCriteria(Termo.class);
			crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			termos = (List<Termo>) crit.list();
			session.close();
			
			for (Termo termo : termos) {
				if(
				   (termo.getTopico().equals(termoTopico) &&
				   (termo.getCodigoParagrafo()+" - "+termo.getDescricao()).equals(desc))
				) {
					return termo;
				}
			}
			
			return null;
		}
		
		public List<Termo> locateTermosByTopicoOficiais(TermoTopico termoTopico){
			List<Termo> termos = new LinkedList<>();
			List<Termo> result = new LinkedList<>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria crit = session.createCriteria(Termo.class);
			crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			termos = (List<Termo>) crit.list();
			session.close();
			
			for (Termo termo : termos) {
				if((termo.getTopico().equals(termoTopico)) && termo.isOficial()) {
					result.add(termo);
				}
			}
			
			return result;
		}
		
		public List<String> showTermosByTopico(TermoTopico termoTopico){
			List<Termo> termos = new LinkedList<>();
			List<String> result = new LinkedList();
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria crit = session.createCriteria(Termo.class);
			crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			termos = (List<Termo>) crit.list();
			session.close();
			
			for (Termo termo : termos) {
				if(termo.getTopico().equals(termoTopico)) {
					result.add(termo.getCodigoParagrafo()+" - "+termo.getDescricao());
				}
			}
			
			Collections.sort(result);
			
			return result;
		}
		
		public List<Termo> locateUnofficialTermos(){
			List<Termo> termos = new LinkedList<>();
			List<Termo> result = new LinkedList<>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria crit = session.createCriteria(Termo.class);
			crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			termos = (List<Termo>) crit.list();
			session.close();
			
			for (Termo termo : termos) {
				if(!termo.isOficial()) {
					result.add(termo);
				}
			}
			
			return result;
		}
		
		public List<Termo> locateAllTermos(){
			List<Termo> result = new LinkedList<>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria crit = session.createCriteria(Termo.class);
			crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			result = (List<Termo>) crit.list();
			session.close();
			
			return result;
		}
		
		public int lastParagraph(TermoTopico termoTopico) {
			List<Termo> termos = new LinkedList<>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria crit = session.createCriteria(Termo.class);
			crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			termos = (List<Termo>) crit.list();
			session.close();
			
			int result = 0;
			
			if(termos.size()!=0) {
				for (Termo termo : termos) {
					if(termo.getTopico().equals(termoTopico)) {
						result++;
					}
				}	
			}
			
			return result;
			
			
		}
		
		public void enumerateTopicos(TermoTopico termoTopico) {
			List<Termo> termos = locateTermosByTopicoOficiais(termoTopico);
			int n = 0;
			for (Termo termo : termos) {
				termo.setCodigoParagrafo(++n);
			}
		}
		
		public void plusModifiedTermo() {
			administracao.plusVersaoModificacoesTermos();
			editAdministracao();
		}
		
		public void plusEstructureTermo() {
			administracao.plusVersaoEstruturaTermos();
			editAdministracao();
		}
		
		//Competencia--------------------------------------------------------------------
		
		public void addCompetencia(Competencia competencia){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(competencia);
			session.getTransaction().commit();
			session.close();
		}
		
		public void editCompetencia(Competencia competencia){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(competencia);
			session.getTransaction().commit();
			session.close();
		}
		
		public void deleteCompetencia(Competencia competencia){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(competencia);
			session.getTransaction().commit();
			session.close();
		}

		
		public List<String> showAllCompetencias(){
			List<Competencia> competencias = new LinkedList<>();
			List<String> result = new LinkedList();
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria crit = session.createCriteria(Competencia.class);
			crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			competencias = (List<Competencia>) crit.list();
			session.close();
			
			for (Competencia competencia : competencias) {
				result.add(competencia.getDescricao());
			}
			
			Collections.sort(result);
			
			return result;
		}
		
		public List<Competencia> locateCompetenciaByCompetenciaTipo(CompetenciaTipo competenciaTipo){
			List<Competencia> competencias = new LinkedList<>();
			List<Competencia> result = new LinkedList<>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria crit = session.createCriteria(Competencia.class);
			crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			competencias = (List<Competencia>) crit.list();
			session.close();
			
			for (Competencia competencia : competencias) {
				if(competencia.getTipo().equals(competenciaTipo)) {
					result.add(competencia);
				}
				
			}
			
			return result;
		}
		
		public Competencia locateCompetenciaByTipoDesc(CompetenciaTipo competenciaTipo, String descricao) {
			List<Competencia> competencias = new LinkedList<>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria crit = session.createCriteria(Competencia.class);
			crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			competencias = (List<Competencia>) crit.list();
			session.close();
			
			for (Competencia competencia : competencias) {
				if(competencia.getDescricao().equals(descricao)) {
					return competencia;
				}
				
			}
			
			return null;
		}
		
		//Administração------------------------------------------------------------------
		
		public void editAdministracao() {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(administracao);
			session.getTransaction().commit();
			session.close();
		}
		
		//BoxFileObject------------------------------------------------------------------
		
		public void inicializeBox() {
			if(box == null)
			box = new Box();
		}
		
		public void addBoxFileObject(Arquivo boxFileObject){
			inicializeBox();
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(boxFileObject);
			session.getTransaction().commit();
			session.close();
		}
		
		public Arquivo locateBoxFileObjectByName(String name, String... folder){
			inicializeBox();
			List<Arquivo> boxFileObjects = new LinkedList<>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria critBoxFileObject = session.createCriteria(Arquivo.class);
			boxFileObjects = (List<Arquivo>) critBoxFileObject.list();
			session.close();
			
			Pasta currentFolder = null;
			Pasta rootFolder = null;
			
			for (String string : folder) {
				currentFolder = locateBoxFolderObjectByName(string);
				if(currentFolder == null){
					return null;
				}
				rootFolder = currentFolder;
			}
			
			System.out.println(currentFolder.getName());
			
			for (Arquivo boxFileObject : boxFileObjects) {
				inicializeBox();
				if(boxFileObject.getName().equals(name) && boxFileObject.getBoxFolderObject().getId() == currentFolder.getId()){
					System.out.println(boxFileObject.getBoxId()+" - "+boxFileObject.getName());
					return boxFileObject;
				}
			}
			
			return null;
		}
		
		//BoxFolderObject------------------------------------------------------------------
		
				public void addBoxFolderObject(Pasta boxFolderObject){
					inicializeBox();
					Session session = HibernateUtil.getSessionFactory().openSession();
					session.beginTransaction();
					session.save(boxFolderObject);
					session.getTransaction().commit();
					session.close();
				}
				
				public Pasta locateBoxFolderObjectByName(String name){
					inicializeBox();
					List<Pasta> boxFolderObjects = new LinkedList<>();
					Session session = HibernateUtil.getSessionFactory().openSession();
					session.beginTransaction();
					Criteria critBoxFolderObject = session.createCriteria(Pasta.class);
					boxFolderObjects = (List<Pasta>) critBoxFolderObject.list();
					session.close();
					
					for (Pasta boxFolderObject : boxFolderObjects) {
						if(boxFolderObject.getName().equals(name)){
							return boxFolderObject;
						}
					}
					
					return null;
				}
				
		//MessageLog---------------------------------------------------------------------
				
				public void addMessageLog(LogMensagem messageLog){
					
					Session session = HibernateUtil.getSessionFactory().openSession();
					session.beginTransaction();
					session.save(messageLog);
					session.getTransaction().commit();
					session.close();
					
				}
				
				
		
		//Schedule------------------------------------------------------------------------
				
				public void addScheduleMessage(MensagemAgendada scheduleMessage){
					Session session = HibernateUtil.getSessionFactory().openSession();
					session.beginTransaction();
					session.save(scheduleMessage);
					session.getTransaction().commit();
					session.close();
				}
				
				public void removeScheduleMessage(MensagemAgendada scheduleMessage){
					Session session = HibernateUtil.getSessionFactory().openSession();
					session.beginTransaction();
					session.delete(scheduleMessage);
					session.getTransaction().commit();
					session.close();
				}
				
		
		//Métodos REST-------------------------------------------------------------------
		
		
		public JSONArray termos() throws IOException{
			try {
				
				JSONArray json = Utils.readJsonFromUrl("https://api.myjson.com/bins/x7jzr");
				return json;
				
			} catch (JSONException e) {

				e.printStackTrace();

			}
			return null;
				
		}


		


		
	
}
*/