package marcsEisdiele.client;

import java.util.List;

import marcsEisdiele.client.diagramm.ImageChartExample;
import marcsEisdiele.shared.Unternehmen;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.ContentPanel; 

public class Auswertung{

	int zaehler;
	private UnternehmensServiceAsync service;
	private List alleUnternehmen;
	private Unternehmen un;
	private int gewinne[];
	private int verProdukte[];
	private int marktanteil[];
	private int price[];
	private int kapital[];
	private int qualitaet[];
	private int ausgaben[];
	private int lagerbestand[];
	private int gewinnMax = 0, verProdukteMax = 0, marktanteilMax = 0, priceMax = 0, kapitalMax = 0, ausgabenMax = 0, lagerbestandMax = 0, qualitaetMax = 0;
	String name0, name1, name2, name3;
	
	public Auswertung(int zaehlerRunde) {
		zaehler= zaehlerRunde;
		//Arrays für die einzelnen Werte werden erstellt
		gewinne = new int[4*zaehlerRunde];
		marktanteil = new int[4*zaehlerRunde];
		verProdukte = new int[4*zaehlerRunde];
		price = new int[4*zaehlerRunde];
		kapital = new int[4*zaehlerRunde];
		qualitaet= new int[4*zaehlerRunde];
		ausgaben= new int[4*zaehlerRunde];
		lagerbestand= new int[4*zaehlerRunde];
	}

	HorizontalPanel hPanel = new HorizontalPanel();
	VerticalPanel vPanel1 = new VerticalPanel();
	VerticalPanel vPanel2 = new VerticalPanel();
	VerticalPanel vPanel = new VerticalPanel();
		
	
	public Widget getAuswertung(){
		final Button bNeueRunde = new Button();
		bNeueRunde.setText("neueRunde");
		bNeueRunde.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
	        	bNeueRunde.setEnabled(false);
	    		start.getRunde();	
			}
		});
		
		//alle Unternehmen von jeder Runde werden geladen
		service = GWT.create(UnternehmensService.class);
		service.getAlleUnternehmen(Game.gameID,new AsyncCallback<List<Unternehmen>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<Unternehmen> result) {
			alleUnternehmen = result;
			int anzahl = (zaehler*4)+4;
//			alle ab dem 4. Unternehmen sind von Bedeutung, da davor Unternehmen 0-3 keine berechnete Werte haben
			for(int i=4; i<anzahl; i++){
				un = (Unternehmen) alleUnternehmen.get(i);
				//max-Wert wird bestimmt - wichtig um die Größe des Diagramms zu bestimmen
				if (gewinnMax <= un.getGewinn()) gewinnMax = un.getGewinn();
				if (verProdukteMax <= un.getSoldProducts()) verProdukteMax = un.getSoldProducts();
				if (marktanteilMax <= un.getMarketShare()) marktanteilMax = un.getMarketShare();
				if (priceMax <= un.getProductprize()) priceMax = un.getProductprize();
				if (ausgabenMax <= un.getTotalExpenditure()) ausgabenMax = un.getTotalExpenditure(); 
				if (qualitaetMax <= un.getQuality()) qualitaetMax = un.getQuality();
				if (lagerbestandMax <= un.getUsedStorage()) lagerbestandMax = un.getUsedStorage();
//			//Werte werden ausgelesen und hintereinander ins Array geschrieben
				gewinne[i-4] = un.getGewinn();
				verProdukte[i-4] = un.getSoldProducts();
				marktanteil[i-4] = un.getMarketShare();
				price[i-4] = un.getProductprize();
				ausgaben[i-4] = un.getTotalExpenditure();
				qualitaet[i-4] = un.getQuality();
				lagerbestand[i-4]=un.getUsedStorage();
				
			}
			//Die Namen der Unternehmen wird ermittelt
			name0 = ((Unternehmen) alleUnternehmen.get(0)).getNameUN();
			name1 = ((Unternehmen) alleUnternehmen.get(1)).getNameUN();
			name2 = ((Unternehmen) alleUnternehmen.get(2)).getNameUN();
			name3 = ((Unternehmen) alleUnternehmen.get(3)).getNameUN();
			
			//Die Diagramme werden erstellt
			ImageChartExample chartGewinn = new ImageChartExample("Gewinn", name0, name1, name2, name3, gewinne, gewinnMax);
			//ImageChartExample chartMarktanteil = new ImageChartExample("Marktanteil", name0, name1, name2, name3, marktanteil, marktanteilMax);
//			ImageChartExample chartKapital = new ImageChartExample("Kapital", name0, name1, name2, name3, kapital, kapitalMax);
			ImageChartExample chartAusgaben = new ImageChartExample("Ausgaben", name0, name1, name2, name3, ausgaben, ausgabenMax);
			ImageChartExample chartVerProdukte= new ImageChartExample("Verkaufte Produkte",name0, name1, name2, name3, verProdukte, verProdukteMax);
			ImageChartExample chartPrice = new ImageChartExample("Preis",name0, name1, name2, name3, price, priceMax);
			ImageChartExample chartQualitaet = new ImageChartExample("Qualitaet", name0, name1, name2, name3, qualitaet, qualitaetMax);
			ImageChartExample chartLagerbestand = new ImageChartExample("Lagerbestand", name0, name1, name2, name3, lagerbestand, lagerbestandMax);
//			
			//vPanel1.add(chartMarktanteil.asWidget());
			vPanel1.add(chartGewinn.asWidget());
			vPanel1.add(chartAusgaben.asWidget());
			vPanel1.add(chartVerProdukte.asWidget());
			vPanel2.add(chartPrice.asWidget());
			vPanel2.add(chartQualitaet.asWidget());
			vPanel2.add(chartLagerbestand.asWidget());
			hPanel.add(vPanel1);
			hPanel.add(vPanel2);
			vPanel.add(hPanel);
			vPanel.add(bNeueRunde);
			}
		});
		return vPanel;	
	}

}