package com.produtos.apirestwba.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.produtos.apirestwba.model.DataDTO;
import com.produtos.apirestwba.model.Operacao;
import com.produtos.apirestwba.model.Titulo;
import com.produtos.apirestwba.repository.OperacaoRepository;
import com.produtos.apirestwba.repository.TituloRepository;

@Service
public class OperacaoService {
	
	@Autowired
	OperacaoRepository operacaoRepository;
	
	@Autowired
	TituloRepository tituloRepository;
	
	 Date dataOperacao = new Date();
	 LocalDate userDataOperacao = dataOperacao.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	 Date dataVencimento = new Date();
	 LocalDate userDataVencimento = dataVencimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	 private Long useridOperacao, useridTitulo;
	 private Date userdataOperacao, userdataVencimento;
	 private BigDecimal userPrazo;
	 String data;
	 long dias;
	 
 //Lista Operacao 
 public List<Operacao> listtAll(){
		
    return operacaoRepository.findAll();
	 
	}
  //Lista OperacaoID
  public Operacao getOp(Long id) {
	 
	   return operacaoRepository.findById(id).get();
 }
  //Salvar Operacao + 2 operações
  public Operacao saveOp(Operacao operacao, Titulo titulo) {
	  
	  this.userdataOperacao = operacao.getDataOperacao();
	  this.userdataVencimento = titulo.getDataVencimento();
	  this.userPrazo = titulo.getPrazo();
	  
	  for(int pos = 0; pos < operacao.getTitulos().size(); pos++) {
		  
		   operacao.getTitulos().get(pos).setOperacao(operacao);
		    
		  if(operacao.getValorTotal() != null && titulo.getValor() != null)   
	             //1 - Soma dos Valores
		         operacao.setValorTotal(operacao.getValorTotal().add(titulo.getValor()));
		  //2 - diferença em dias
		   dias = ChronoUnit.DAYS.between(userDataOperacao, userDataVencimento);
		   this.userPrazo = BigDecimal.valueOf(dias);
		   
		   //Teste no console
		    System.out.println(data = this.userdataVencimento.
				               after(this.userdataOperacao)? "Boleto não vencido" + dias : "Urgente - Boleto vencido " + dias);
				   
		}
	  return  operacaoRepository.save(operacao);
	 
 }
//Update Operacao + 2 operações
  public Operacao updateOp(Operacao operacao, Titulo titulo) {
	  
	  this.userdataOperacao = operacao.getDataOperacao();
	  this.userdataVencimento = titulo.getDataVencimento();
	  this.userPrazo = titulo.getPrazo();
	  
	  for(int pos = 0; pos < operacao.getTitulos().size(); pos++) {
		  
		   operacao.getTitulos().get(pos).setOperacao(operacao);
		    
		  if(operacao.getValorTotal() != null && titulo.getValor() != null)   
	             //1 - Soma dos Valores
		         operacao.setValorTotal(operacao.getValorTotal().add(titulo.getValor()));
		  //2 - diferença em dias
		   dias = ChronoUnit.DAYS.between(userDataOperacao, userDataVencimento);
		   this.userPrazo = BigDecimal.valueOf(dias);
		   
		   //Teste no console
		     System.out.println(data = this.userdataVencimento.
				                after(this.userdataOperacao)? "Boleto não vencido" + dias : "Urgente - Boleto vencido " + dias);
				   
		}
	 return  operacaoRepository.save(operacao);
 

 }
 
 public void deleteOp(Long id) {
	 
	 operacaoRepository.deleteById(id);
  }
 
  
 	
   
  
    
  
}
