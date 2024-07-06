package com.heliton.controller;

// Spring //
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.MediaType;

// Java //
import java.util.List;

// Repositorio MYSQL e Modelos //
import com.heliton.repository.BeneficiarioRepositorioMySQL;
import com.heliton.model.Beneficiario;
import com.heliton.model.Documento;

@Controller
public class BeneficiarioController {
    
	@Autowired
	private BeneficiarioRepositorioMySQL mysqlRepositorio;
	
    @RequestMapping(value = "/listarBeneficiario", method = RequestMethod.GET)
    @ResponseBody    
    public List<Beneficiario> listarBeneficiario() {
		try {
			List<Beneficiario> beneficiario = mysqlRepositorio.listarBeneficiario();
			return beneficiario;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }	
    
    @RequestMapping(value = "/listarBeneficiarioDocumento/{id}", method = RequestMethod.GET)
    @ResponseBody    
    public List<Documento> listarBeneficiarioDocumento(@PathVariable int id) {    	
		try {
			List<Documento> documento = mysqlRepositorio.listarBeneficiarioDocumento(id);
			return documento;			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
    
    @RequestMapping(value = "/adicionarBeneficiario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody    
    public String adicionarBeneficiario(@RequestBody Beneficiario beneficiario) {
		try {
			mysqlRepositorio.adicionarBeneficiario(beneficiario);			
		} catch (Exception e) {
			return "eero : " + e.getMessage();
		}
		return "Registro Adicionado com sucesso!";
    }    
    @RequestMapping(value = "/alterarBeneficiario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody    
    public String alterarBeneficiario(@RequestBody Beneficiario beneficiario) {    	
		try {
			mysqlRepositorio.alterarBeneficiario(beneficiario);			
		} catch (Exception e) {
			return "Error : " + e.getMessage();
		}
		return "Registro Atualizado com sucesso!";
    }    
    
    @RequestMapping(value = "/deletarBeneficiario/{id}", method = RequestMethod.POST)
    @ResponseBody    
    public String deletarBeneficiario(@PathVariable int id) {    	
		try {
			mysqlRepositorio.deletarBeneficiario(id);			
		} catch (Exception e) {
			return "Error : " + e.getMessage();
		}
		return "Registro Deletado com sucesso!";
    }    
}