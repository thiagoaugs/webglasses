/**  
 * jass-ejb - ConverterDomainToTO.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.util;

import javax.management.relation.Role;

import br.com.flexsolutions.admin.to.GrupoRoleTO;
import br.com.flexsolutions.admin.to.GrupoTO;
import br.com.flexsolutions.admin.to.PessoaTO;
import br.com.flexsolutions.admin.to.RolesTO;
import br.com.flexsolutions.admin.to.SistemaTO;
import br.com.flexsolutions.admin.to.TelaMenuGrupoTO;
import br.com.flexsolutions.admin.to.TelaMenuTO;
import br.com.flexsolutions.admin.to.TipoPessoaTO;
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
import br.com.flexsolutions.security.pojo.TipoPessoa;
import br.com.flexsolutions.security.pojo.Usuario;
import br.com.flexsolutions.security.pojo.UsuarioGrupo;
import br.com.flexsolutions.security.pojo.UsuarioSistema;
import br.com.flexsolutions.security.pojo.UsuarioSistemaGrupo;

/**
 * Classe para conversao das entidades para os transfer objects.
 */
/**
 * @author Thiago Augusto
 *
 */
public class ConverterEntidadeTO {

	/**
	 * @param {@link Grupo}
	 * @return {@link GrupoTO}
	 */
	public static GrupoTO criarGrupoTO(Grupo gpo) {
		GrupoTO gpoTO = null;

		if (gpo == null) {
			return gpoTO;
		}

		gpoTO = new GrupoTO();
		gpoTO.setGpoId(gpo.getGpoId());
		gpoTO.setGpoNome(gpo.getGpoNome());
		gpoTO.setGpoDescricao(gpo.getGpoDescricao());

		if (gpo.getGpoSistema() != null
				&& gpo.getGpoSistema().getSisId() != null) {

			SistemaTO sisTO = new SistemaTO();
			sisTO.setSisId(gpo.getGpoSistema().getSisId());
			sisTO.setSisNome(gpo.getGpoSistema().getSisNome());
			
			gpoTO.setGpoSistemaTO(sisTO);

		}

		return gpoTO;
	}

	/**
	 * @param {@link GrupoRole}
	 * @return {@link GrupoRoleTO}
	 */
	public static GrupoRoleTO criarGrupoRoleTO(GrupoRole gpr) {

		GrupoRoleTO gprTO = null;

		if (gpr == null) {
			return gprTO;
		}

		gprTO = new GrupoRoleTO();
		gprTO.setGprId(gpr.getGprId());

		// grupo
		if (gpr.getGprGrupo() != null && gpr.getGprGrupo().getGpoId() != null) {
			GrupoTO gpoTO = criarGrupoTO(gpr.getGprGrupo());
			gprTO.setGprGrupo(gpoTO);
		}

		// role
		if (gpr.getGprRole() != null && gpr.getGprRole().getRolId() != null) {
			RolesTO rolTO = criarRoleTO(gpr.getGprRole());
			gprTO.setGprRole(rolTO);
		}

		return gprTO;
	}

	/**
	 * @param {@link Log}
	 * @return {@link LogTO}
	 */
	// public static LogTO criarLogTO(Log log) {
	// LogTO logTO = null;
	//
	// if (log == null) {
	// return logTO;
	// }
	//
	// logTO = new LogTO();
	// logTO.setLogId(log.getLogId());
	// logTO.setLogUsuario(log.getLogUsuario());
	// logTO.setLogData(log.getLogData());
	// logTO.setLogSistema(log.getLogSistema());
	// logTO.setLogMensagem(log.getLogMensagem());
	//
	// return logTO;
	// }

	/**
	 * @param {@link Pessoa}
	 * @return {@link PessoaTO}
	 */
	public static PessoaTO criarPessoaTO(Pessoa pes) {
		PessoaTO pesTO = null;

		if (pes == null) {
			return pesTO;
		}

		pesTO = new PessoaTO();
		pesTO.setPesId(pes.getPesId());

		return pesTO;

	}

	/**
	 * @param {@link tipoPessoa}
	 * @return {@link TipoPessoaTO}
	 */
	public static TipoPessoaTO criarTipoPessoaTO(TipoPessoa tps) {

		TipoPessoaTO tpsTO = null;

		if (tps == null) {
			return null;
		}

		tpsTO = new TipoPessoaTO();
		tpsTO.setTpsId(tps.getTpsId());
		tpsTO.setTpsDescricao(tps.getTpsDescricao());

		return tpsTO;

	}

	/**
	 * @param {@link Role}
	 * @return {@link RoleTO}
	 */
	public static RolesTO criarRoleTO(Roles rol) {
		RolesTO rolTO = null;

		if (rol == null) {
			return rolTO;
		}

		rolTO = new RolesTO();
		rolTO.setRolId(rol.getRolId());
		rolTO.setRolAtivo(rol.getRolAtivo());
		rolTO.setRolDescricao(rol.getRolDescricao());
		rolTO.setRolNome(rol.getRolNome());

		if (rol.getRolSistema() != null
				&& rol.getRolSistema().getSisId() != null) {

			SistemaTO sisTO = criarSistemaTO(rol.getRolSistema());
			rolTO.setRolSistemaTO(sisTO);
		}

		return rolTO;
	}

	/**
	 * @param {@link Sistema}
	 * @return {@link SistemaTO}
	 */
	public static SistemaTO criarSistemaTO(Sistema sis) {
		SistemaTO sisTO = null;

		if (sis == null) {
			return sisTO;
		}

		sisTO = new SistemaTO();
		sisTO.setSisId(sis.getSisId());
		sisTO.setSisNome(sis.getSisNome());

		return sisTO;
	}

	/**
	 * @param {@link TelaMenu}
	 * @return {@link TelaMenuTO}
	 */
	public static TelaMenuTO criarTelaMenuTO(TelaMenu tme) {
		TelaMenuTO tmeTO = null;

		if (tme == null) {
			return tmeTO;
		}

		tmeTO = new TelaMenuTO();
		tmeTO.setTmeId(tme.getTmeId());
		tmeTO.setTmeLegenda(tme.getTmeLegenda());
		tmeTO.setTmeAcao(tme.getTmeAcao());
		tmeTO.setTmeNivel(tme.getTmeNivel());
		tmeTO.setTmeOrdem(tme.getTmeOrdem());
		tmeTO.setTmeCamada(tme.getTmeCamada());

		// sistema
		if (tme.getTmeSistema() != null
				&& tme.getTmeSistema().getSisId() != null) {
			SistemaTO sisTO = criarSistemaTO(tme.getTmeSistema());
			tmeTO.setTmeSistemaTO(sisTO);
		}

		// telamenu filhos
		if (tme.getTmeSuperior() != null
				&& tme.getTmeSuperior().getTmeId() != null) {
			TelaMenuTO tmeSuperiorTO = null;

			tmeSuperiorTO = new TelaMenuTO();
			tmeSuperiorTO.setTmeId(tme.getTmeSuperior().getTmeId());
			tmeSuperiorTO.setTmeLegenda(tme.getTmeSuperior().getTmeLegenda());
			tmeSuperiorTO.setTmeAcao(tme.getTmeSuperior().getTmeAcao());
			tmeSuperiorTO.setTmeNivel(tme.getTmeSuperior().getTmeNivel());
			tmeSuperiorTO.setTmeCamada(tme.getTmeSuperior().getTmeCamada());

			tmeTO.setTmeMenuSuperior(tmeSuperiorTO);
		}else
		{
			TelaMenuTO tmeSuperiorTO = new TelaMenuTO();
			
			tmeTO.setTmeMenuSuperior(tmeSuperiorTO);
		}

		return tmeTO;
	}

	/**
	 * @param {@link TelaMenuGrupo}
	 * @return {@link TelaMenuGrupoTO}
	 */
	public static TelaMenuGrupoTO criarTelaMenuGrupoTO(TelaMenuGrupo tmg) {

		TelaMenuGrupoTO tmgTO = null;

		if (tmg == null) {
			return tmgTO;
		}

		tmgTO = new TelaMenuGrupoTO();
		tmgTO.setTmgId(tmg.getTmgId());

		// grupo
		if (tmg.getTmgGrupo() != null && tmg.getTmgGrupo().getGpoId() != null) {
			GrupoTO gpoTO = criarGrupoTO(tmg.getTmgGrupo());
			tmgTO.setTmgGrupo(gpoTO);
		}

		// telamenu
		if (tmg.getTmgTelaMenu() != null
				&& tmg.getTmgTelaMenu().getTmeId() != null) {
			TelaMenuTO tmeTO = criarTelaMenuTO(tmg.getTmgTelaMenu());
			tmgTO.setTmgTelaMenu(tmeTO);

		}

		return tmgTO;

	}

	/**
	 * Retorna um UsuarioTO baseando-se na entidade Usuario.
	 * 
	 * @param {@link Usuario}
	 * @return {@link UsuarioTO}
	 */
	public static UsuarioTO criarUsuarioTO(Usuario usu) {
		UsuarioTO usuTO = null;

		if (usu == null) {
			return usuTO;
		}

		usuTO = new UsuarioTO();
		usuTO.setUsuId(usu.getUsuId());
		usuTO.setUsuCpf(usu.getUsuCpf());
		usuTO.setUsuNome(usu.getUsuNome());
		usuTO.setUsuLogin(usu.getUsuLogin());
		usuTO.setUsuSenha(usu.getUsuSenha());
		usuTO.setUsuDtCad(usu.getUsuDtCad());
		usuTO.setUsuDtAlt(usu.getUsuDtAlt());
		usuTO.setUsuBloq(usu.getUsuBloq());
		usuTO.setUsuEmail(usu.getUsuEmail());
		usuTO.setUsuTelefone(usu.getUsuTelefone());
		usuTO.setUsuRamal(usu.getUsuRamal());
		usuTO.setUsuCelular(usu.getUsuCelular());
		usuTO.setUsuCep(usu.getUsuCep());
		usuTO.setUsuRua(usu.getUsuRua());
		usuTO.setUsuBairro(usu.getUsuBairro());
		usuTO.setUsuNumero(usu.getUsuNumero());
		usuTO.setUsuComplemento(usu.getUsuComplemento());

		if (usu.getUsuPessoa() != null && usu.getUsuPessoa().getPesId() != null) {
			// criar uma nova pessoa e chamar o metodo de criarPessoaTO passando
			// a entidade pessoa
			PessoaTO pesTO = criarPessoaTO(usu.getUsuPessoa());
			usuTO.setUsuPessoaTO(pesTO);
		} else {
			usuTO.setUsuPessoaTO(null);
		}
		return usuTO;
	}

	/**
	 * @param {@link UsuarioGrupo}
	 * @return {@link UsuarioGrupoTO}
	 */
	public static UsuarioGrupoTO criarUsuarioGrupoTO(UsuarioGrupo ugp) {
		UsuarioGrupoTO ugpTO = null;

		if (ugp == null) {
			return ugpTO;
		}

		ugpTO = new UsuarioGrupoTO();
		ugpTO.setUgpId(ugp.getUgpId());

		// usuario
		if (ugp.getUgpUsuario() != null
				&& ugp.getUgpUsuario().getUsuId() != null) {
			UsuarioTO usuTO = criarUsuarioTO(ugp.getUgpUsuario());
			ugpTO.setUgpUsuario(usuTO);
		}

		// grupo
		if (ugp.getUgpGrupo() != null && ugp.getUgpGrupo().getGpoId() != null) {
			GrupoTO gpoTO = criarGrupoTO(ugp.getUgpGrupo());
			ugpTO.setUgpGrupo(gpoTO);
		}
		return ugpTO;
	}

	/**
	 * @param {@link UsuarioSistema}
	 * @return {@link UsuarioSistemaTO}
	 */
	public static UsuarioSistemaTO criarUsuarioSistemaTO(UsuarioSistema usi) {
		UsuarioSistemaTO usiTO = null;

		if (usi == null) {
			return usiTO;
		}

		usiTO = new UsuarioSistemaTO();
		usiTO.setUsiId(usi.getUsiId());
		usiTO.setUsiTipoUsuario(usi.getUsiTipoUsuario());
		usiTO.setUsiSemRestricao(usi.getUsiSemRestricao());

		// usuario
		if (usi.getUsiUsuario() != null
				&& usi.getUsiUsuario().getUsuId() != null) {
			
			//se der erro de null alterar aqui
			UsuarioTO usuTO = criarUsuarioTO(usi.getUsiUsuario());
			usiTO.setUsiUsuario(usuTO);
		}

		// sistema
		if (usi.getUsiSistema() != null
				&& usi.getUsiSistema().getSisId() != null) {
			SistemaTO sistemaTO = criarSistemaTO(usi.getUsiSistema());
			usiTO.setUsiSistema(sistemaTO);

		}
		return usiTO;
	}

	/**
	 * @param {@link UsuarioSistemaGrupo}
	 * @return {@link UsuarioSistemaGrupoTO}
	 */
	public static UsuarioSistemaGrupoTO criarUsuarioSistemaGrupoTO(
			UsuarioSistemaGrupo usg) {
		
		UsuarioSistemaGrupoTO usgTO = null;

		if (usg == null) {
			return usgTO;
		}

		usgTO = new UsuarioSistemaGrupoTO();
		usgTO.setUsgId(usg.getUsgId());

		// grupo
		if (usg.getUsgGrupo() != null && usg.getUsgGrupo().getGpoId() != null) {
			GrupoTO gpoTO =  new GrupoTO();
			gpoTO.setGpoId(usg.getUsgGrupo().getGpoId());
			gpoTO.setGpoNome(usg.getUsgGrupo().getGpoNome());
			usgTO.setUsgGrupo(gpoTO);
		}

		// usuarioSistema
		if (usg.getUsgUsuarioSistema() != null
				&& usg.getUsgUsuarioSistema().getUsiId() != null) {

			UsuarioSistemaTO usiTO = new UsuarioSistemaTO();
			
			usiTO.setUsiId(usg.getUsgUsuarioSistema().getUsiId());
			
			SistemaTO sisTO = new SistemaTO();
			sisTO.setSisId(usg.getUsgUsuarioSistema().getUsiSistema().getSisId());
			sisTO.setSisNome(usg.getUsgUsuarioSistema().getUsiSistema().getSisNome());
			usiTO.setUsiSistema(sisTO);
			
			UsuarioTO usuTO =  new UsuarioTO();
			usuTO.setUsuId(usg.getUsgUsuarioSistema().getUsiUsuario().getUsuId());
			usuTO.setUsuNome(usg.getUsgUsuarioSistema().getUsiUsuario().getUsuNome());
			usiTO.setUsiUsuario(usuTO);
		
			usgTO.setUsgUsuarioSistema(usiTO);
			
		}

		return usgTO;
	}
}
