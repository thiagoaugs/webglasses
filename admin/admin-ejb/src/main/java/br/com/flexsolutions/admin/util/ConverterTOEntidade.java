/**  
 * jass-ejb - ConverteTOEntidade.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.util;

import br.com.flexsolutions.admin.to.GrupoRoleTO;
import br.com.flexsolutions.admin.to.GrupoTO;
import br.com.flexsolutions.admin.to.PessoaTO;
import br.com.flexsolutions.admin.to.RolesTO;
import br.com.flexsolutions.admin.to.SistemaTO;
import br.com.flexsolutions.admin.to.TelaMenuGrupoTO;
import br.com.flexsolutions.admin.to.TelaMenuTO;
import br.com.flexsolutions.admin.to.UsuarioGrupoTO;
import br.com.flexsolutions.admin.to.UsuarioSistemaGrupoTO;
import br.com.flexsolutions.admin.to.UsuarioSistemaTO;
import br.com.flexsolutions.admin.to.UsuarioTO;
import br.com.flexsolutions.security.pojo.Grupo;
import br.com.flexsolutions.security.pojo.GrupoRole;
import br.com.flexsolutions.security.pojo.Pessoa;
import br.com.flexsolutions.security.pojo.Roles;
import br.com.flexsolutions.security.pojo.Sistema;
import br.com.flexsolutions.security.pojo.TelaMenu;
import br.com.flexsolutions.security.pojo.TelaMenuGrupo;
import br.com.flexsolutions.security.pojo.Usuario;
import br.com.flexsolutions.security.pojo.UsuarioGrupo;
import br.com.flexsolutions.security.pojo.UsuarioSistema;
import br.com.flexsolutions.security.pojo.UsuarioSistemaGrupo;

/**
 *  @author Thiago Augusto
 *  
 * Classe para conversao de transfer object em entidades.
 */
public class ConverterTOEntidade {

	/**
	 * @param gpoTO
	 * @return
	 */
	public static Grupo criarGrupo(GrupoTO gpoTO) {
		Grupo gpo = null;

		if (gpoTO == null) {
			return gpo;
		}
		// converte o TO em Entidade
		gpo = new Grupo();

		gpo.setGpoId(gpoTO.getGpoId());
		gpo.setGpoNome(gpoTO.getGpoNome().toUpperCase().trim());
		gpo.setGpoDescricao(gpoTO.getGpoDescricao().trim());

		Sistema sis = new Sistema();
		sis.setSisId(gpoTO.getGpoSistemaTO().getSisId());
		gpo.setGpoSistema(sis);

		return gpo;

	}

	/**
	 * @param gprTO
	 * @return
	 */
	public static GrupoRole criarGrupoRoles(GrupoRoleTO gprTO) {
		GrupoRole gpr = null;

		if (gprTO == null) {
			return gpr;
		}
		// converte o TO em Entidade
		gpr = new GrupoRole();

		gpr.setGprId(gprTO.getGprId());

		if (gprTO.getGprGrupo() != null
				&& gprTO.getGprGrupo().getGpoId() != null) {

			Grupo gpo = new Grupo();
			gpo.setGpoId(gprTO.getGprGrupo().getGpoId());

			gpr.setGprGrupo(gpo);
		}

		if (gprTO.getGprRole() != null && gprTO.getGprRole().getRolId() != null) {

			Roles rol = new Roles();
			rol.setRolId(gprTO.getGprRole().getRolId());

			gpr.setGprRole(rol);
		}

		return gpr;

	}

	/**
	 * @param logTO
	 * @return
	 */
	// public static Log criarLog(LogTO logTO) {
	//
	// Log log = null;
	//
	// if (logTO == null) {
	// return log;
	// }
	// // converte o TO em Entidade
	// log = new Log();
	//
	// log.setLogId(logTO.getLogId());
	// log.setLogUsuario(logTO.getLogUsuario());
	// log.setLogData(logTO.getLogData());
	// log.setLogSistema(logTO.getLogSistema());
	// log.setLogMensagem(logTO.getLogMensagem());
	//
	// return log;
	// }

	/**
	 * @param pesTO
	 * @return
	 */
	public static Pessoa criarPessoa(PessoaTO pesTO) {

		Pessoa pes = null;

		if (pesTO == null) {
			return pes;
		}
		// converte o TO em Entidade
		pes = new Pessoa();

		return pes;
	}

	/**
	 * @param rolTO
	 * @return
	 */
	public static Roles criarRoles(RolesTO rolTO) {

		Roles rol = null;

		if (rolTO == null) {
			return rol;
		}
		// converte o TO em Entidade
		rol = new Roles();
		rol.setRolId(rolTO.getRolId());
		rol.setRolAtivo(rolTO.getRolAtivo());
		rol.setRolDescricao(rolTO.getRolDescricao());
		rol.setRolNome(rolTO.getRolNome().toUpperCase());

		if (rolTO.getRolSistemaTO() != null
				&& rolTO.getRolSistemaTO().getSisId() != null) {

			Sistema sis = new Sistema();
			sis.setSisId(rolTO.getRolSistemaTO().getSisId());

			rol.setRolSistema(sis);
		}

		return rol;
	}

	/**
	 * @param sisTO
	 * @return
	 */
	public static Sistema criarSistema(SistemaTO sisTO) {

		Sistema sis = null;

		if (sisTO == null) {
			return sis;
		}
		// converte o TO em Entidade
		sis = new Sistema();
		sis.setSisId(sisTO.getSisId());
		sis.setSisNome(sisTO.getSisNome().toUpperCase());

		return sis;
	}

	/**
	 * @param tmeTO
	 * @return
	 */
	public static TelaMenu criarTelaMenu(TelaMenuTO tmeTO) {
		TelaMenu tme = null;

		if (tmeTO == null) {
			return tme;
		}
		// converte o TO em Entidade
		tme = new TelaMenu();

		tme.setTmeId(tmeTO.getTmeId());
		tme.setTmeLegenda(tmeTO.getTmeLegenda());
		tme.setTmeAcao(tmeTO.getTmeAcao());
		tme.setTmeNivel(tmeTO.getTmeNivel());
		tme.setTmeOrdem(tmeTO.getTmeOrdem());
		tme.setTmeCamada(tmeTO.getTmeCamada());

		if (tmeTO.getTmeSistemaTO() != null
				&& tmeTO.getTmeSistemaTO().getSisId() != null) {
			Sistema sis = new Sistema();
			sis.setSisId(tmeTO.getTmeSistemaTO().getSisId());
			tme.setTmeSistema(sis);
		}
		if (tmeTO.getTmeMenuSuperior() != null
				&& tmeTO.getTmeMenuSuperior().getTmeId() != null && tmeTO.getTmeMenuSuperior().getTmeId() != 0) {
			TelaMenu tmePai = new TelaMenu();

			tmePai.setTmeId(tmeTO.getTmeMenuSuperior().getTmeId());
			tmePai.setTmeLegenda(tmeTO.getTmeMenuSuperior().getTmeLegenda());
			tmePai.setTmeAcao(tmeTO.getTmeMenuSuperior().getTmeAcao());
			tmePai.setTmeNivel(tmeTO.getTmeMenuSuperior().getTmeNivel());
			tmePai.setTmeCamada(tmeTO.getTmeMenuSuperior().getTmeCamada());

			tme.setTmeSuperior(tmePai);
		}else{
			tmeTO.setTmeMenuSuperior(null) ;
		}

		return tme;

	}

	/**
	 * @param tmgTO
	 * @return
	 */
	public static TelaMenuGrupo criarTelaMenuGrupo(TelaMenuGrupoTO tmgTO) {

		TelaMenuGrupo tmg = null;

		if (tmgTO == null) {
			return tmg;
		}
		// converte o TO em Entidade
		tmg = new TelaMenuGrupo();
		tmg.setTmgId(tmgTO.getTmgId());

		if (tmgTO.getTmgGrupo() != null
				&& tmgTO.getTmgGrupo().getGpoId() != null) {
			Grupo gpo = new Grupo();
			gpo.setGpoId(tmgTO.getTmgGrupo().getGpoId());

			tmg.setTmgGrupo(gpo);

		}
		if (tmgTO.getTmgTelaMenu() != null
				&& tmgTO.getTmgTelaMenu().getTmeId() != null) {

			TelaMenu tme = new TelaMenu();
			tme.setTmeId(tmgTO.getTmgTelaMenu().getTmeId());

			tmg.setTmgTelaMenu(tme);

		}

		return tmg;
	}

	/**
	 * Retorna um Usuario baseando-se na entidade UsuarioTO.
	 * 
	 * @param {@link UsuarioTO}
	 * @return {@link Usuario}
	 */
	public static Usuario criarUsuario(UsuarioTO usuTO) {

		Usuario usu = null;

		if (usuTO == null) {
			return usu;
		}
		// converte o TO em Entidade

		usu = new Usuario();
		usu.setUsuId(usuTO.getUsuId());
		usu.setUsuCpf(usuTO.getUsuCpf());
		usu.setUsuNome(usuTO.getUsuNome());
		usu.setUsuLogin(usuTO.getUsuLogin());
		usu.setUsuSenha(usuTO.getUsuSenha());
		usu.setUsuDtCad(usuTO.getUsuDtCad());
		usu.setUsuDtAlt(usuTO.getUsuDtAlt());
		usu.setUsuBloq(usuTO.getUsuBloq());
		usu.setUsuEmail(usuTO.getUsuEmail());
		usu.setUsuTelefone(usuTO.getUsuTelefone());
		usu.setUsuRamal(usuTO.getUsuRamal());
		usu.setUsuCelular(usuTO.getUsuCelular());
		usu.setUsuCep(usuTO.getUsuCep());
		usu.setUsuRua(usuTO.getUsuRua());
		usu.setUsuBairro(usuTO.getUsuBairro());
		usu.setUsuNumero(usuTO.getUsuNumero());
		usu.setUsuComplemento(usuTO.getUsuComplemento());

		if (usuTO.getUsuPessoaTO() != null
				&& usuTO.getUsuPessoaTO().getPesId() != null) {
			Pessoa pes = criarPessoa(usuTO.getUsuPessoaTO());
			usu.setUsuPessoa(pes);
		} else {
			Pessoa pes = null;
			usu.setUsuPessoa(pes);
		}

		return usu;
	}

	/**
	 * @param ugpTO
	 * @return
	 */
	public static UsuarioGrupo criarUsuarioGrupo(UsuarioGrupoTO ugpTO) {
		UsuarioGrupo ugp = null;

		if (ugpTO == null) {
			return ugp;
		}
		// converte o TO em Entidade
		ugp = new UsuarioGrupo();

		ugp.setUgpId(ugpTO.getUgpId());

		if (ugpTO.getUgpGrupo() != null
				&& ugpTO.getUgpGrupo().getGpoId() != null) {
			Grupo gpo = new Grupo();
			gpo.setGpoId(ugpTO.getUgpGrupo().getGpoId());
			ugp.setUgpGrupo(gpo);
		}

		if (ugpTO.getUgpUsuario() != null
				&& ugpTO.getUgpUsuario().getUsuId() != null) {
			Usuario usu = new Usuario();
			usu.setUsuId(ugpTO.getUgpUsuario().getUsuId());
			ugp.setUgpUsuario(usu);
		}

		return ugp;
	}

	/**
	 * @param usiTO
	 * @return
	 */
	public static UsuarioSistema criarUsuarioSistema(UsuarioSistemaTO usiTO) {
		UsuarioSistema usi = null;

		if (usiTO == null) {
			return usi;
		}
		// converte o TO em Entidade
		usi = new UsuarioSistema();
		usi.setUsiId(usiTO.getUsiId());

		if (usiTO.getUsiSistema() != null
				&& usiTO.getUsiSistema().getSisId() != null) {
			Sistema sis = new Sistema();
			sis.setSisId(usiTO.getUsiSistema().getSisId());
			usi.setUsiSistema(sis);
		}

		if (usiTO.getUsiUsuario() != null
				&& usiTO.getUsiUsuario().getUsuId() != null) {
			Usuario usu = new Usuario();
			usu.setUsuId(usiTO.getUsiUsuario().getUsuId());
			usi.setUsiUsuario(usu);
		}

		return usi;
	}

	/**
	 * @param usgTO
	 * @return
	 */
	public static UsuarioSistemaGrupo criarUsuarioSistemaGrupo(
			UsuarioSistemaGrupoTO usgTO) {
		UsuarioSistemaGrupo usg = null;

		if (usgTO == null) {
			return usg;
		}
		// converte o TO em Entidade
		usg = new UsuarioSistemaGrupo();
		usg.setUsgId(usgTO.getUsgId());
		
		if(usgTO.getUsgGrupo() != null && usgTO.getUsgGrupo().getGpoId() != null){
			Grupo gpo = new Grupo();
			gpo.setGpoId(usgTO.getUsgGrupo().getGpoId());
			usg.setUsgGrupo(gpo);
		}
		
		if(usgTO.getUsgUsuarioSistema() != null && usgTO.getUsgUsuarioSistema().getUsiId() != null ){
			
			UsuarioSistema usi = new UsuarioSistema();
			usi.setUsiId(usgTO.getUsgUsuarioSistema().getUsiId());
			
			usg.setUsgUsuarioSistema(usi);
		}

		return usg;
	}

}
