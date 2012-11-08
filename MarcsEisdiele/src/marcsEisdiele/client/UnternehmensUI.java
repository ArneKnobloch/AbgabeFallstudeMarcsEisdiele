package marcsEisdiele.client;

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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Slider;
import com.sencha.gxt.widget.core.client.info.Info;

public class UnternehmensUI extends Composite implements HasText {

	private static UnternehmensUIUiBinder uiBinder = GWT
			.create(UnternehmensUIUiBinder.class);

	interface UnternehmensUIUiBinder extends UiBinder<Widget, UnternehmensUI> {
	}

	public UnternehmensUI() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	private UnternehmensServiceAsync service;
	
	int id;
	//Startwerte setzen min-max
	int minpersonal = 60, maxpersonal = 250, personal;
	int minquality = 1, maxquality = 10 , quality;
	//1000er Punkttrennung
	int mincapital = 200000, maxcapital = 600000, capital;
	int mincapaticity = 3000, maxcapaticity = 6000, capaticity;
	
	int minvarcost = 10, maxvarcost = 100, varcost;
	int minmaschine = 2, maxmaschine = 7, maschine;
	int minWorkload = 0, maxWorkload = 100, workload;
	int minstorage = 7500, maxstorage = 15000, storage;
	int minprice = 10, maxprice = 200, price;
	int minmarketing = 10000, maxmarketing = 100000, marketing;
	int minmarket = 0, maxmarket = 100, market;
	int pseudozaehler;
	

	@UiField Label lCompanyname;
	@UiField TextBox tCompanyname;
	@UiField IntegerBox currentPersonal;
	@UiField IntegerBox currentProdQuality;
	@UiField IntegerBox currentCapital;
	@UiField IntegerBox currentCapaticity;
	@UiField IntegerBox currentVarCost;
	@UiField IntegerBox currentworkload;
	@UiField IntegerBox currentPrice;
	@UiField IntegerBox currentMarketing;
	@UiField IntegerBox currentStorage;
	@UiField IntegerBox currentMaschine;
	@UiField IntegerBox currentMarket;
	@UiField Slider sPersonal;
	@UiField Slider sCapital;
	@UiField Slider sQuality;	
	@UiField Slider sCapaticity;	
	@UiField Slider sVarCost;
	@UiField Slider sWorkload;
	@UiField Slider sPrice;
	@UiField Slider sMarketing;
	@UiField Slider sStorage;
	@UiField Slider sMaschine;
	@UiField Slider sMarket;	
	@UiField Button bSaveCompany;
	@UiField Button bUnternehmenFestlegen;

	public UnternehmensUI(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		bSaveCompany.setText(firstName);
	}
	
	//Eventhandler für "Spiel starten"
	@UiHandler("bSaveCompany")
	void onClickSaveCompany(ClickEvent e) {
		String companyName = tCompanyname.getText();
		//Firmennamen überprüfen
		if(companyName.isEmpty()){
			Window.alert("Bitte Firmenname eingeben!");
		}
		//In Datenbank speichern +++
		else{		
			Unternehmen unternehmen = new Unternehmen(tCompanyname.getText(),currentPersonal.getValue(),
				currentCapital.getValue(), currentProdQuality.getValue(), currentMaschine.getValue(), 
				currentCapaticity.getValue(), currentworkload.getValue(), currentStorage.getValue(), 
				currentVarCost.getValue(),currentPrice.getValue(),
				currentMarketing.getValue(),currentMarket.getValue(),start.zaehlerUN);
			service.addUnternehmen(unternehmen, new AddUnternehmenCallback());
			//es sind immer 4 Unternehmen nötig damit das Spiel funktioniert.
			//wenn der User weniger angibt werden Unternehmen mit zufälligen Zahlen erzeugt
			if(start.zaehlerUN<3)
			{
				for( pseudozaehler = start.zaehlerUN+1; pseudozaehler<4; pseudozaehler++)
				{
					//Unternehmen mit zufälligen Werten erzeugen
					Unternehmen unternehmenNeu = new Unternehmen(
					//name
					"Konkurrenz-Unternehmen "+pseudozaehler,
					//personal
					(int)(Math.random() * (100) + 60),
					//capital
					(int)(Math.random() * (200000) + 300000),			
					//quality
					(int)(Math.random() * ((10-1)*0.5) + 3),
					//machine
					(int)(Math.random() * (4) + 2),
					//machinescapacity
					(int)(Math.random() * (3000) + 3000),
					//workload
					(int)(Math.random() * (60) + 40),
					//storage
					(int)(Math.random() * ((15000-6000)*0.5) + 9500),
					//varcost
					(int)(Math.random() * (60) + 40),
					//price
					(int)(Math.random() * (60) + 100),
					//marketing
					(int)(Math.random() * (40000) + 10000),
					//market
					(int)(Math.random() * 10),
					(pseudozaehler));
				service.addUnternehmen(unternehmenNeu, new AddUnternehmenCallback());
				}
				pseudozaehler=0;
				start.zaehlerUN=0;
				id=0;
			}
			else{
				start.getSpielstarten();
			}
		
		}
	}
	
	public class AddUnternehmenCallback implements AsyncCallback<java.lang.Void> {

        @Override
        public void onFailure(Throwable caught) {
        Info.display("Speicherung Fehlgeschlagen", "Beim Speichern der Daten in die Datenbank ist ein Fehler aufgetreten");
        }

        @Override
        public void onSuccess(Void result) {
        	Info.display("Speicherung abgeschlossen", "Die Daten wurden erfolgreich in die Datenbank geschrieben");
        	//falls 4 Unternehmen vorhanden sind, kann das Spiel gestartet werden
        	if (pseudozaehler==4)
        	{
    			start.getSpielstarten();
        	}
        }
    }

	//Eventhandler zu "Konkurrenzunternehmen festlegen	
	@UiHandler("bUnternehmenFestlegen")
	void onClickUnternehmenFestlegen(ClickEvent e) {
		String companyName = tCompanyname.getText();
		//Firmennamen überprüfen
		if(companyName.isEmpty()){
			Window.alert("Bitte Firmenname eingeben!");
		}
		else{
		bUnternehmenFestlegen.setEnabled(false);
		//es dürfen nicht mehr wie 4 Unternehmen angelegt werden
		if(id<3)
		{
			Unternehmen unternehmen = new Unternehmen(tCompanyname.getText(),currentPersonal.getValue(),
				currentCapital.getValue(), currentProdQuality.getValue(), currentMaschine.getValue(), 
				currentCapaticity.getValue(), currentworkload.getValue(), currentStorage.getValue(), 
				currentVarCost.getValue(),currentPrice.getValue(),
				currentMarketing.getValue(),currentMarket.getValue(),start.zaehlerUN);
			service.addUnternehmen(unternehmen, new AddUnternehmenCallback());
			bSaveCompany.setEnabled(false);
			start.getKonkurrenzUN();
		}

	}
	}
	public void setText(String text) {
		bSaveCompany.setText(text);
	}

	public String getText() {
		return bSaveCompany.getText();
	}

	public Widget getPanelUnternehmen(int id, String unternehmen){
		this.id = id;

		service = GWT.create(UnternehmensService.class);		

		lCompanyname.setText(unternehmen);
		
		//Slider intialisieren	    
        sPersonal.setMinValue(minpersonal);
		sPersonal.setMaxValue(maxpersonal);
		sPersonal.setIncrement(1);
		sPersonal.addValueChangeHandler(new ValueChangeHandler<Integer>() {
			public void onValueChange(ValueChangeEvent<Integer> event) {
				personal = event.getValue(); 
				currentPersonal.setValue(personal);
			}
		});
		//zugehörige Textboxen initialisieren
		currentPersonal.setValue((maxpersonal+minpersonal)/2);
		currentPersonal.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				personal = currentPersonal.getValue();
	            if (personal>maxpersonal) personal = maxpersonal;
	            if (personal<minpersonal) personal = minpersonal;
	            sPersonal.setValue(personal);
			}
		});
		
		
		sQuality.setMinValue(minquality);
		sQuality.setMaxValue(maxquality);
		sQuality.setIncrement(1);	
		sQuality.addValueChangeHandler(new ValueChangeHandler<Integer>() {
			public void onValueChange(ValueChangeEvent<Integer> event) {
				quality = event.getValue(); 
				currentProdQuality.setValue(quality);
			}
		});
		currentProdQuality.setValue((maxquality+minquality)/2);
		currentProdQuality.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				quality = currentProdQuality.getValue();
				if(quality>maxquality) quality=maxquality;
				if(quality<minquality) quality=minquality;
	            sQuality.setValue(quality);
			}
		});
		
		sCapaticity.setMinValue(mincapaticity);
		sCapaticity.setMaxValue(maxcapaticity);
		sCapaticity.setIncrement(1);
		sCapaticity.addValueChangeHandler(new ValueChangeHandler<Integer>() {
			public void onValueChange(ValueChangeEvent<Integer> event) {
				capaticity = event.getValue(); 
				currentCapaticity.setValue(capaticity);
			}
		});
		currentCapaticity.setValue((maxcapaticity+mincapaticity)/2);
		currentCapaticity.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				capaticity = currentCapaticity.getValue();
				if (capaticity<mincapaticity) capaticity=mincapaticity;
				if(capaticity>maxcapaticity) capaticity=maxcapaticity;
	            sCapaticity.setValue(capaticity);
			}
		});
		
		sCapital.setMinValue(mincapital);
		sCapital.setMaxValue(maxcapital);
		sCapital.setIncrement(100);
		sCapital.addValueChangeHandler(new ValueChangeHandler<Integer>() {
			public void onValueChange(ValueChangeEvent<Integer> event) {
				capital = event.getValue(); 
				currentCapital.setValue(capital);
			}
		});
		currentCapital.setValue((maxcapital+mincapital)/2);
		currentCapital.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				capital = currentCapital.getValue();
				if (capital<mincapital) capital=mincapital;
				if(capital>maxcapital) capital= maxcapital;
	            sCapital.setValue(capital);
			}
		});
		
		sVarCost.setMinValue(minvarcost);
		sVarCost.setMaxValue(maxvarcost);
		sVarCost.setIncrement(1);
		sVarCost.addValueChangeHandler(new ValueChangeHandler<Integer>() {
			public void onValueChange(ValueChangeEvent<Integer> event) {
				varcost = event.getValue(); 
				currentVarCost.setValue(varcost);
			}
		});
		currentVarCost.setValue((maxvarcost+minvarcost)/2);
		currentVarCost.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				varcost = currentVarCost.getValue();
				if(varcost<minvarcost) varcost=minvarcost;
				if(varcost>maxvarcost) varcost=maxvarcost;
	            sVarCost.setValue(varcost);
			}
		});
		
		sMaschine.setMinValue(minmaschine);
		sMaschine.setMaxValue(maxmaschine);
		sMaschine.setIncrement(1);
		sMaschine.addValueChangeHandler(new ValueChangeHandler<Integer>() {
			public void onValueChange(ValueChangeEvent<Integer> event) {
				maschine = event.getValue(); 
				currentMaschine.setValue(maschine);
			}
		});
		currentMaschine.setValue((maxmaschine+minmaschine)/2);
		currentMaschine.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				maschine = currentMaschine.getValue();
				if (maschine<minmaschine) maschine=minmaschine;
				if(maschine>maxmaschine) maschine = maxmaschine;
	            sMaschine.setValue(maschine);
			}
		});
		
		sWorkload.setMinValue(minWorkload);
		sWorkload.setMaxValue(maxWorkload);
		sWorkload.setIncrement(1); //--> 5
		sWorkload.addValueChangeHandler(new ValueChangeHandler<Integer>() {
			public void onValueChange(ValueChangeEvent<Integer> event) {
				workload = event.getValue(); 
				currentworkload.setValue(workload);
			}
		});
		currentworkload.setValue((minWorkload+maxWorkload)/2);
		currentworkload.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				workload = currentworkload.getValue();
				if(workload<minWorkload) workload = minWorkload;
				if(workload>maxWorkload) workload = maxWorkload;
	            sWorkload.setValue(workload);
			}
		});
		
		sStorage.setMinValue(minstorage);
		sStorage.setMaxValue(maxstorage);
		sStorage.setIncrement(1);
		sStorage.addValueChangeHandler(new ValueChangeHandler<Integer>() {
			public void onValueChange(ValueChangeEvent<Integer> event) {
				 storage= event.getValue(); 
				currentStorage.setValue(storage);
			}
		});
		currentStorage.setValue((maxstorage+minstorage)/2);
		currentStorage.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				storage = currentStorage.getValue();
				if( storage<minstorage) storage=minstorage;
				if(storage>maxstorage) storage=maxstorage;
	            sStorage.setValue(storage);
			}
		});
		
		sPrice.setMinValue(minprice);
		sPrice.setMaxValue(maxprice);
		sPrice.setIncrement(10);
		sPrice.addValueChangeHandler(new ValueChangeHandler<Integer>() {
			public void onValueChange(ValueChangeEvent<Integer> event) {
				 price= event.getValue(); 
				currentPrice.setValue(price);
			}
		});
		currentPrice.setValue((maxprice+minprice)/2);
		currentPrice.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				price = currentPrice.getValue();
				if( price<minprice) price = minprice;
				if(price>maxprice) price = maxprice;
	            sPrice.setValue(price);
			}
		});
		
		sMarketing.setMinValue(minmarketing);
		sMarketing.setMaxValue(maxmarketing);
		sMarketing.setIncrement(10);
		sMarketing.addValueChangeHandler(new ValueChangeHandler<Integer>() {
			public void onValueChange(ValueChangeEvent<Integer> event) {
				 marketing= event.getValue(); 
				currentMarketing.setValue(marketing);
			}
		});
		currentMarketing.setValue((maxmarketing+minmarketing)/2);
		currentMarketing.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				marketing = currentMarketing.getValue();
				if(marketing<minmarketing) marketing = minmarketing;
				if(marketing>maxmarketing) marketing = maxmarketing;
	            sMarketing.setValue(marketing);
			}
		});
		
		sMarket.setMinValue(minmarket);
		sMarket.setMaxValue(maxmarket);
		sMarket.setIncrement(1);
		sMarket.addValueChangeHandler(new ValueChangeHandler<Integer>() {
			public void onValueChange(ValueChangeEvent<Integer> event) {
				 market= event.getValue(); 
				currentMarket.setValue(market);
			}
		});
		currentMarket.setValue((maxmarket+minmarket)/2);
		currentMarket.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				market = currentMarket.getValue();
				if(market<minmarket) market = minmarket;
				if(market>maxmarket) market = maxmarket;
	            sMarket.setValue(market);
			}
		});
					
		//Buttontext festlegen
		bSaveCompany.setText("Spiel starten");
		bUnternehmenFestlegen.setText("Konkurrenzunternehmen hinzufuegen");
		
		return this;
	}
}

