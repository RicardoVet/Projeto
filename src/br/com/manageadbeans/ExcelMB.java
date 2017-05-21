/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.manageadbeans;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.poi.ss.usermodel.Cell;

import br.com.negocios.PlanilhaExcel;
import br.com.util.Arquivo;

/**
 *
 * @author Ricardo
 */
@ManagedBean
@ViewScoped
public class ExcelMB {
	
	private List<Cell>celulas;

	@PostConstruct
	public void init(){
		try {
			this.ler();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public void ler() throws IOException{
        PlanilhaExcel plan = new PlanilhaExcel();
        celulas=plan.ler(Arquivo.caminho);
    }

	public List<Cell> getCelulas() {
		return celulas;
	}

	public void setCelulas(List<Cell> celulas) {
		this.celulas = celulas;
	}
}
