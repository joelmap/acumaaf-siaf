package br.ufscar.acumaaf.siaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean(name="homeMB")
@RequestScoped
public class HomeManagedBean {

	private List<String> imagesTop;
	private List<String> imagesBottom;
	
	public HomeManagedBean() {
		imagesTop = new ArrayList<String>();
		imagesBottom = new ArrayList<String>();
		
		imagesTop.add("layout-central-atividade-fisica1.jpg");
		imagesTop.add("layout-central-atividade-fisica2.jpg");
		imagesTop.add("layout-central-atividade-fisica3.jpg");
		imagesTop.add("layout-central-atividade-fisica4.jpg");
		
		
		imagesBottom.add("layout-central-atividade-fisica6.jpg");
		imagesBottom.add("layout-central-atividade-fisica7.jpg");
		imagesBottom.add("layout-central-atividade-fisica8.jpg");
		imagesBottom.add("layout-central-atividade-fisica9.jpg");
		imagesBottom.add("layout-central-atividade-fisica10.jpg");	
	}

	public List<String> getImagesTop() {
		return imagesTop;
	}

	public List<String> getImagesBottom() {
		return imagesBottom;
	}	
}
