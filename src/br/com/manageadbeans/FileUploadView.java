/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.manageadbeans;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import br.com.negocios.PlanilhaExcel;
import br.com.negocios.UploadPlanilha;
import br.com.util.Arquivo;

/**
 *
 * @author Ricardo
 */
@ManagedBean
@RequestScoped
public class FileUploadView {
	
	private UploadPlanilha upload;
	private PlanilhaExcel plExcel;
	
	@PostConstruct
	public void init(){
		if(upload==null)
			upload=new UploadPlanilha();
		if(plExcel==null)
			plExcel=new PlanilhaExcel();
	}
    
    public void handleFileUpload(FileUploadEvent event){
        byte[] dados = event.getFile().getContents();
        String nomeArquivo = event.getFile().getFileName();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
        String arquivo = scontext.getRealPath("/WEB-INF/upload/" + nomeArquivo);
        Arquivo.caminho = arquivo;
        try {
			upload.salvar(dados, nomeArquivo, arquivo);
		} catch (IOException e) {
			FacesMessage message = new FacesMessage("Erro", event.getFile().getFileName() + " is not uploaded.");
	        FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
			return;
		}    
        FacesMessage message = new FacesMessage("Sucesso", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public String verPlanilha(){
    	return "inicio";
    }
}
