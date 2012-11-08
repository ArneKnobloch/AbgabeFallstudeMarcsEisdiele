package marcsEisdiele.client;

import java.util.List;

import marcsEisdiele.server.API_4_calculation;
import marcsEisdiele.server.API_4_evaluation;
import marcsEisdiele.shared.Unternehmen;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.IntegerBox;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Slider;
import com.sencha.gxt.widget.core.client.TabPanel;


public class Runde extends Composite implements  HasText {

	private static RundeUiBinder uiBinder = GWT.create(RundeUiBinder.class);
	private UnternehmensServiceAsync service;
	private Unternehmen unternehmen, unternehmen1, unternehmen2, unternehmen3;
	private int runde, marktentwicklung;
	private List<Unternehmen> alleUnternehmenRunde;
	public boolean oldGame;

	interface RundeUiBinder extends UiBinder<Widget, Runde> {
	} 

	public Runde(int nRunde) {
		runde = nRunde;
		
		
	}
	
	@UiField Button bPersonal;
	@UiField Button bMaschine;
	@UiField Slider sResearch;
	@UiField IntegerBox currentResearch;
	@UiField Slider sMarketing;
	@UiField IntegerBox currentMarketing;
	@UiField Slider sWorkload;
	@UiField IntegerBox currentWorkload;
	@UiField Slider sPrice;
	@UiField IntegerBox currentPrice;
	@UiField Button bCompetitor;
	@UiField Button bStart;
	@UiField RadioButton rbSinkend;
	@UiField RadioButton rbStagnierend;
	@UiField RadioButton rbSteigend;
	@UiField Label lUnName;
	@UiField Label lKapital;
	@UiField Label lPersonal;
	@UiField Label lLager;
	@UiField Label lFixkosten;
	

	TabPanel mainPanel = new TabPanel();

	ContentPanel panel = new ContentPanel();
	VerticalPanel vPanel = new VerticalPanel();
	HorizontalPanel hPanel = new HorizontalPanel();
	PopupWindow pPersonal;
	PopupWindow pMaschine;
	
	//min und max Werte für die Slider festlegen.
	int minMarketing = 10000, maxMarketing = 100000, marketing;
	int minResearch = 50000, maxResearch = 150000, research;
	int minWorkload = 0, maxWorkload = 100, workload; 
	int minPrice = 10, maxPrice = 200, price;
	int stratUn1 = 7, stratUn2 = 7, stratUn3 = 7; 

	public Runde(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		bPersonal.setText(firstName);
	}
	
	//Eventhandler bei Klicken von "Personal einstellen/ entlassen"
	int personal;
	@UiHandler("bPersonal")
	void onClickPersonal(ClickEvent e) {	
		pPersonal = new PopupWindow("Personalverwaltung", "Personal",personal);

	}
	//Eventhandler bei Klicken von "Maschinenverwaltung"
	int maschine; 
	@UiHandler("bMaschine")
	void onClickMaschine(ClickEvent e) {	
		pMaschine = new PopupWindow("Maschinen verwalten", "Maschinenanzahl",maschine);

	}
	//Eventhandler für die RadioButtons von Marktentwicklung
    @UiHandler( { "rbSinkend", "rbStagnierend", "rbSteigend" })
    void onMarktentwicklung(ValueChangeEvent<Boolean> e) {
    	if (rbSinkend.getValue()== true) marktentwicklung = 1;
    	if (rbStagnierend.getValue()== true) marktentwicklung = 2;
    	if (rbSteigend.getValue()== true) marktentwicklung = 3;
    }
  //Eventhandler bei klicken von "Konkurrenzverhalten verwalten"
	@UiHandler("bCompetitor")
	void onClickCompetitor(ClickEvent e) {	
		PopupWindowCompetitor pCompetitor = new PopupWindowCompetitor("Konkurrenzverhalten bestimmen", unternehmen1.getNameUN(), unternehmen2.getNameUN(), unternehmen3.getNameUN());
		stratUn1 = pCompetitor.getStrategie1();
		stratUn2 = pCompetitor.getStrategie2();
		stratUn3 = pCompetitor.getStrategie3();
	}
	//Eventhandler bei Klicken von "Runde starten"
	@UiHandler("bStart")	
	void onClickStart(ClickEvent e) {
		try {
			if (pPersonal.getWert()>0) personal = pPersonal.getWert();
		} catch (Exception e2) {
			personal = unternehmen.getPersonal();
		}
		try {
			if(pMaschine.getWert()>0) maschine = pMaschine.getWert();
		} catch (Exception e2) {
			maschine = unternehmen.getMachines();
		}
		//Eventhandler bei Klicken von "Runde starten"
		API_4_calculation.personalAenderung(unternehmen,  personal - unternehmen.getPersonal());
		API_4_calculation.maschineKaufen(unternehmen, maschine - unternehmen.getMachines());
		API_4_calculation.forschungsInvestition(unternehmen, research);
		API_4_calculation.marketingInvestition(unternehmen, marketing);
		API_4_calculation.marketBehavior(marktentwicklung);
		
		//Zufallszahlen für die Strategien der Konkurrenzunternehmen festlegen, falls diese im Popup nicht gesetzt wurden.
		if ((stratUn1 == 7) ^ (stratUn2 == 7) ^ (stratUn3 == 7)){
			stratUn1 = Random.nextInt(6);
			stratUn2 = Random.nextInt(6);
			stratUn3 = Random.nextInt(6);
		}
		//Konkurrenzunternehmen berechnen	
		API_4_calculation.competitorStrategie(unternehmen1, stratUn1);
		API_4_calculation.competitorStrategie(unternehmen2, stratUn2);
		API_4_calculation.competitorStrategie(unternehmen2, stratUn3);
		//Marktsimulation starten
		API_4_evaluation.startEvaluation(alleUnternehmenRunde);
	
		//Slider und Buttons ausschalten
		sMarketing.setEnabled(false);
		sPrice.setEnabled(false);
		sResearch.setEnabled(false);
		sWorkload.setEnabled(false);
		bCompetitor.setEnabled(false);
		bMaschine.setEnabled(false);
		bPersonal.setEnabled(false);
		bStart.setEnabled(false);

		//den Unternehmensobjekten die richtige Runden-ID zuweisen
		unternehmen.setRound(runde);
		unternehmen1.setRound(runde);
		unternehmen2.setRound(runde);
		unternehmen3.setRound(runde);
		
		alleUnternehmenRunde.clear();
		alleUnternehmenRunde.add(unternehmen);
		alleUnternehmenRunde.add(unternehmen1);
		alleUnternehmenRunde.add(unternehmen2);
		alleUnternehmenRunde.add(unternehmen3);

		//die Unternehmen der Runde in die Datenbank schreiben
		service.addAllUnternehmen(alleUnternehmenRunde,new AsyncCallback<Void>(){
		@Override
		public void onFailure(Throwable caught){
			
		}
		@Override
		public void onSuccess(Void result) {
			start.getAuswertung();
		}
			
		});
		
	}
	//diese Methode wird aufgerufen falls von der GameID schon Objekte vorliegen.
	//der alte Spielstand wird nachgeladen
	public void fakeClickstart(){
		bStart.setEnabled(false);
		sMarketing.setEnabled(false);
		sPrice.setEnabled(false);
		sResearch.setEnabled(false);
		sWorkload.setEnabled(false);
		start.getAuswertung();
	}

	public void setText(String text) {
		bPersonal.setText(text);
	}

	public String getText() {
		return bPersonal.getText();
	}
	
	//diese Methode wird aufgerufen wenn eine neue Runde gespielt werden möchte.
	public Widget getRunde(){
		initWidget(uiBinder.createAndBindUi(this));
		 //Alle Unternehmen einer Runde auslesen:
		service = GWT.create(UnternehmensService.class);
		service.getAlleUnternehmenRunde(start.zaehlerRunde-1,Game.gameID,new AsyncCallback<List<Unternehmen>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<Unternehmen> result) {
				alleUnternehmenRunde = result;
				unternehmen = (Unternehmen) alleUnternehmenRunde.get(0);
				unternehmen1 = (Unternehmen) alleUnternehmenRunde.get(1);
				unternehmen2 = (Unternehmen) alleUnternehmenRunde.get(2);
				unternehmen3 = (Unternehmen) alleUnternehmenRunde.get(3);

				//Die Texte der Buttons werden gesetzt
				bPersonal.setText("Personal einstellen/ entlassen");
				bMaschine.setText("Maschinen verwalten");
				bCompetitor.setText("Konkurrenzverhalten bearbeiten");
				bStart.setText("Runde starten");
				//die Box mit den Daten des eigenen Unternehmens wird gesetzt.
				setBox();
				
				//das Personal/ Maschinen wird aus dem eigenen Unternehmen gelesen - wird in dem jeweiligen Popup benötigt
				personal = unternehmen.getPersonal();
				maschine = unternehmen.getMachines();
				
				//Die Slider werden initialisert
				marketing = unternehmen.getMarketing();
				sMarketing.setMinValue(minMarketing);
				sMarketing.setMaxValue(maxMarketing);
				sMarketing.setIncrement(10); 
				sMarketing.addValueChangeHandler(new ValueChangeHandler<Integer>() {
					public void onValueChange(ValueChangeEvent<Integer> event) {
						marketing = event.getValue(); 
						currentMarketing.setValue(marketing);
					}
				});
				//und die jeweiligen Textboxen werden initialisiert 
				currentMarketing.setValue((minMarketing+maxMarketing)/2);
				currentMarketing.addChangeHandler(new ChangeHandler() {
					@Override
					public void onChange(ChangeEvent event) {
						marketing = currentMarketing.getValue();
						if(marketing<minMarketing) marketing = minMarketing;
						if(marketing>maxMarketing) marketing = maxMarketing;
			            sMarketing.setValue(marketing);
					}
				});
				
				
				sResearch.setMinValue(minResearch);
				sResearch.setMaxValue(maxResearch);
				sResearch.setIncrement(10); //--> 5000
				sResearch.addValueChangeHandler(new ValueChangeHandler<Integer>() {
					public void onValueChange(ValueChangeEvent<Integer> event) {
						research = event.getValue(); 
						currentResearch.setValue(research);
					}
				});
				currentResearch.setValue((minResearch+maxResearch)/2);
				currentResearch.addChangeHandler(new ChangeHandler() {
					@Override
					public void onChange(ChangeEvent event) {
						research = currentResearch.getValue();
						if(research<minResearch) research =minResearch;
						if(research>maxResearch) research=maxResearch;
			            sResearch.setValue(research);
					}
				});
				
				sWorkload.setMinValue(minWorkload);
				sWorkload.setMaxValue(maxWorkload);
				sWorkload.setIncrement(1); //--> 5
				sWorkload.addValueChangeHandler(new ValueChangeHandler<Integer>() {
					public void onValueChange(ValueChangeEvent<Integer> event) {
						workload = event.getValue(); 
						currentWorkload.setValue(workload);
					}
				});
				currentWorkload.setValue((minWorkload+maxWorkload)/2);
				currentWorkload.addChangeHandler(new ChangeHandler() {
					@Override
					public void onChange(ChangeEvent event) {
						workload = currentWorkload.getValue();
						if(workload<minWorkload) workload = minWorkload;
						if(workload>maxWorkload) workload = maxWorkload;
			            sWorkload.setValue(workload);
					}
				});
				
				sPrice.setMinValue(minPrice);
				sPrice.setMaxValue(maxPrice);
				sPrice.setIncrement(1); //--> 1
				sPrice.addValueChangeHandler(new ValueChangeHandler<Integer>() {
					public void onValueChange(ValueChangeEvent<Integer> event) {
						price = event.getValue(); 
						currentPrice.setValue(price);
					}
				});
				currentPrice.setValue((minPrice+maxPrice)/2);
				currentPrice.addChangeHandler(new ChangeHandler() {
					@Override
					public void onChange(ChangeEvent event) {
						price = currentPrice.getValue();
						if(price<minPrice) price = minPrice;
						if(price>maxPrice) price = maxPrice;
			            sPrice.setValue(price);
					}
				});
				
				setBox();
			}
			
		});
		
		return this;
	}
	void setBox(){
		//Initialiserung der Textbox mit den Daten des eigenen Unternehmens
		service.getUnternehmen(0,runde,Game.gameID,new AsyncCallback<Unternehmen>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Unternehmen result) {
				lUnName.setText(unternehmen.getNameUN());
		    	Integer inte = new Integer(unternehmen.getKapital());
				String s = inte.toString();
				lKapital.setText(s);
				inte = unternehmen.getPersonal();
				s = inte.toString();
				lPersonal.setText(s);
				inte = unternehmen.getStorage();
				s = inte.toString();
				lLager.setText(s);
				inte=unternehmen.getFixCost();
				s=inte.toString();
				lFixkosten.setText(s);
			}
		});
    }
	
}