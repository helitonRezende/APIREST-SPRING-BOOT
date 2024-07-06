package com.security;

// Spring //
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

// Java //
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Modelos //
import com.heliton.model.Beneficiario;
import com.heliton.model.Documento;

public class BeneficiarioTest {

	public static void main(String[] args) {

		// Adicionar Beneficiario //		
		adicionarBeneficiario();		
		
		// Listar Beneficiario //
		listarBeneficiario();

		// Listar Beneficiario x Documento //		
		listarBeneficiarioDocumento(1);

		// Alterar Beneficiario //		
		//alterarBeneficiario(1);
		
		// Deletar Beneficiario //		
		//deletarBeneficiario(1);
		
	}
	
	private static void listarBeneficiario()  {		
		String endPoint = "http://localhost:8080/listarBeneficiario";		
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.getForEntity(endPoint, String.class);		
			System.out.println(response);
		} catch (Exception e) {
			System.out.println("Erro adicionar beneficiário -> " + e.getMessage());			
		}
	}
	
	private static void listarBeneficiarioDocumento(int id)  {		
		String endPoint = "http://localhost:8080/listarBeneficiarioDocumento/" + id;
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.getForEntity(endPoint, String.class);		
			System.out.println(response);
		} catch (Exception e) {
			System.out.println("Erro adicionar beneficiário -> " + e.getMessage());			
		}
	}	
	
	private static void adicionarBeneficiario()  {		
		String endPoint = "http://localhost:8080/adicionarBeneficiario";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");			
			String dateString = "2010-10-10T18:25:43.511Z";
			Date dtnascimento = sdf.parse(dateString);
			dateString = "2024-07-05T18:25:43.511Z";			
			Date dtinclusao = sdf.parse(dateString);
			
			Beneficiario beneficiario = new Beneficiario();
			beneficiario.setNome("Beneficiario 1");
			beneficiario.setTelefone("(11) 9 9999-9999");
			beneficiario.setDataNascimento(dtnascimento);
			beneficiario.setDataInlusao(dtinclusao);
			
	    	List<Documento> documento = new ArrayList<Documento>();
	    	Documento lst = new Documento();
	    	lst.setTipoDocumento("CPF");
	    	lst.setDescricao("408.147.773-68");	    	
	    	lst.setDataInlusao(dtinclusao);
	    	documento.add(lst);

	    	lst = new Documento();
	    	lst.setTipoDocumento("RG");
	    	lst.setDescricao("50575972-X");	    	
	    	lst.setDataInlusao(dtinclusao);
	    	documento.add(lst);
	    	
	    	beneficiario.setDocumento(documento);
	    	
			//
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Beneficiario> entity = new HttpEntity<Beneficiario>(beneficiario, headers);			
			//
			RestTemplate restTemplate = new RestTemplate();		
			ResponseEntity<String> loginResponse = restTemplate.exchange(endPoint, HttpMethod.POST, entity, String.class);
			if (loginResponse.getStatusCode() == HttpStatus.OK) {
				System.out.println(loginResponse);		
			} else {
				System.out.println("Erro -> adicionar beneficiário");				
			}		
		} catch (Exception e) {
			System.out.println("Erro adicionar beneficiário -> " + e.getMessage());			
		}
	}
	
	private static void alterarBeneficiario(int id)  {		
		String endPoint = "http://localhost:8080/alterarBeneficiario";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");			
			String dateString = "2011-11-11T18:25:43.511Z";
			Date dtnascimento = sdf.parse(dateString);
			dateString = "2024-07-06T18:25:43.511Z";			
			Date dtatualizacao = sdf.parse(dateString);
			
			Beneficiario beneficiario = new Beneficiario();
			beneficiario.setId(id);			
			beneficiario.setNome("Alteracao Beneficiario 1");
			beneficiario.setTelefone("(11) 9 7087-4464");
			beneficiario.setDataNascimento(dtnascimento);
			beneficiario.setDataAtualizacao(dtatualizacao);
			//
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Beneficiario> entity = new HttpEntity<Beneficiario>(beneficiario, headers);			
			//
			RestTemplate restTemplate = new RestTemplate();		
			ResponseEntity<String> response = restTemplate.exchange(endPoint, HttpMethod.POST, entity, String.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				System.out.println(response);		
			} else {
				System.out.println("Erro -> alterar beneficiário");				
			}		
		} catch (Exception e) {
			System.out.println("Erro adicionar beneficiário -> " + e.getMessage());			
		}
	}
	
	private static void deletarBeneficiario(int id)  {		
		String endPoint = "http://localhost:8080/deletarBeneficiario/" + id;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			//
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.exchange(endPoint, HttpMethod.POST, null, String.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				System.out.println(response);		
			} else {
				System.out.println("Erro -> alterar beneficiário");				
			}		
		} catch (Exception e) {
			System.out.println("Erro deletar beneficiário -> " + e.getMessage());			
		}
	}	
}