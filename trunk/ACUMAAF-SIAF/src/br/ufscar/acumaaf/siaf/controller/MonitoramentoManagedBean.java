package br.ufscar.acumaaf.siaf.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;


@ManagedBean(name="monitoramentoMB")
@ViewScoped
public class MonitoramentoManagedBean {
	
	private DashboardModel model;
	
	// Construtor da Classe ManagedBean
	public MonitoramentoManagedBean() {
		model = new DefaultDashboardModel();  
        DashboardColumn column1 = new DefaultDashboardColumn();  
        DashboardColumn column2 = new DefaultDashboardColumn();  
        DashboardColumn column3 = new DefaultDashboardColumn(); 
        DashboardColumn column4 = new DefaultDashboardColumn();  
        DashboardColumn column5 = new DefaultDashboardColumn(); 
          
        column1.addWidget("participante_1");  
        column1.addWidget("Participante 1");  
          
        column1.addWidget("participante_2");
        
        column1.addWidget("participante_3");
        
        column1.addWidget("participante_4");
        
        column1.addWidget("participante_5");
  
        model.addColumn(column1);  
        model.addColumn(column2);  
        model.addColumn(column3);
        model.addColumn(column4);
        model.addColumn(column5);
	}
	
	public void handleReorder(DashboardReorderEvent event) {  
        FacesMessage message = new FacesMessage();  
        message.setSeverity(FacesMessage.SEVERITY_INFO);  
        message.setSummary("Reordered: " + event.getWidgetId());  
        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());  
          
        addMessage(message);  
    }  
      
    public void handleClose(CloseEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");  
          
        addMessage(message);  
    }  
      
    public void handleToggle(ToggleEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());  
          
        addMessage(message);  
    }  
      
    private void addMessage(FacesMessage message) {  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
      
    public DashboardModel getModel() {  
        return model;  
    }
	
	//Método chamado para navegação implícita, levando o usuário para a página de Central de Monitoramento
	public String chamaPaginaCentralMonitoramento(){
		
		return "/monitoramento/gerenciaMonitoramento.xhtml?faces-redirect=true";
	}

}
