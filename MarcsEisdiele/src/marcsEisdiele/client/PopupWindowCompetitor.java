package marcsEisdiele.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.ToggleGroup; 
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.info.Info;


	/**
	 * Klasse schmeisst Exception beim schliessen vom Fenster
	 * dazu folgendes:
	 * Turns out that client Java code (which is complied to Javascript) had try/catch block which worked when executed in Java, but failed silently when compiled to Javascript.
	 * **/

	public class PopupWindowCompetitor implements IsWidget {
	    String header;
	    String bezeichnung;
	    String kName1, kName2, kName3;
	    int stratUn1, stratUn2, stratUn3;
	    
		PopupWindowCompetitor(String header, String kName1, String kName2, String kName3){
			this.header = header;
			this.kName1 = kName1;
			this.kName2 = kName2;
			this.kName3 = kName3;
			RootPanel.get().add(asWidget());
		}
		
		private VerticalPanel vp = null;
		  @Override
		  public Widget asWidget() {
			if (vp == null) {
		      final Window window = new Window();
		      window.setPixelSize(1000, 700);
		      window.setModal(true);
		      window.setBlinkModal(true);

		      window.setHeadingText(header);
		      window.addHideHandler(new HideHandler() {
		        @Override
		        public void onHide(HideEvent event) {
		          TextButton open = ((Window) event.getSource()).getData("open");
		          open.focus();
		        }
		      });
		 
		     TabPanel panel = new TabPanel();
		     panel.setBorders(false);
		     
		     //1st Competitor
		     
		     VerticalPanel vPanel = new VerticalPanel();
		     vPanel.addStyleName("PopupWindowCompetitorVPanel"); 
		     
		     Radio radio1 = new Radio();
		     radio1.setBoxLabel("Strategie 1");
		     radio1.setStyleName("PopupWindowCompetitorRadio");
		     radio1.setValue(true);
		     Label l1 = new Label("Das Unternehmen setzt viel auf Entwicklung und Forschung und investiert dort stark. Durch die vorhandene Maschinen- und Personalkapazitaet ist es nicht notwendig, weitere Gelder zu investieren.");
		     l1.setStyleName("PopupWindowCompetitorLabel");
		     
		     Radio radio2 = new Radio();
		     radio2.setBoxLabel("Strategie 2");
		     radio2.setStyleName("PopupWindowCompetitorRadio");
		     Label l2 = new Label("Durch das massive Marketing in den vorigen Perioden hat sich die Nachfrage am Produkt erhoeht. Deswegen benoetigt man nun mehr Personal und zwei weitere Maschinen. Der Preis wird ausserdem um 7% erhoeht.");
		     l2.setStyleName("PopupWindowCompetitorLabel");
		     
		     Radio radio3 = new Radio();
		     radio3.setBoxLabel("Strategie 3");
		     radio3.setStyleName("PopupWindowCompetitorRadio");
		     Label l3 = new Label("Die anderen Unternehmen sind weit voraus wenn es sich um die Qualitaet des Produktes handelt. Das Unternehmen setzt alles auf Forschung um die Mitbewerber aufzuholen. Da ein grosser Ansturm auf das Produkt wegen der Forschung erwartet wird, wird eine neue Maschine gekauft.");
		     l3.setStyleName("PopupWindowCompetitorLabel");
		    
		     Radio radio4 = new Radio();
		     radio4.setBoxLabel("Strategie 4");
		     radio4.setStyleName("PopupWindowCompetitorRadio");
		     Label l4 = new Label("Der Bewerber ist gut am Markt und hat nicht das Beduerfnis irgendeine Grossinvestition vorzunehmen. Um dennoch nicht zu verlieren, investiert er in jeden Bereich etwas, jedoch kauft er keine neue Maschine.");
		     l4.setStyleName("PopupWindowCompetitorLabel");
		    
		     Radio radio5 = new Radio();
		     radio5.setBoxLabel("Strategie 5");
		     radio5.setStyleName("PopupWindowCompetitorRadio");
		     Label l5 = new Label("Die letzten Perioden liefen nicht so gut. 5% des Personals, sowie zwei Maschinen werden nicht mehr benoetigt und verkauft. Ausserdem wird der Preis etwas niedriger und durchschnittlich viel fuer das Marketing ausgegeben, um die Marktsituation aus Unternehmenssicht zu verbessern.");
		     l5.setStyleName("PopupWindowCompetitorLabel");
		     
		     Radio radio6 = new Radio();
		     radio6.setBoxLabel("Strategie 6");
		     radio6.setStyleName("PopupWindowCompetitorRadio");
		     Label l6 = new Label("Die letzten Perioden waren nicht so erfolgreich, da nicht der gewuenschte Absatz erreicht wurde. Jetzt gilt es Gelder einzusparen und den Absatz durch niedrige Preise zu erhoehen. Personal muss auf Grund der schlechten Situation entlassen werden. Da mit einem grossen Ansturm auf die billigen Preise gerechnet wird, werden die Maschinen voll ausgelastet.");
		     l6.setStyleName("PopupWindowCompetitorLabel");
		     
		     Radio radio7 = new Radio();
		     radio7.setBoxLabel("Strategie 7");
		     radio7.setStyleName("PopupWindowCompetitorRadio");
		     Label l7 = new Label("Das Unternehmen hat in den letzten Periode mit einem grossen Ansturm gerechnet, doch leider blieb dieser aus. Das Lager ist fast voll und der Gewinn bleibt aus. Damit nicht ueberproduziert wird, werden die Maschinen nur zu 35% ausgelastet. Um wieder mehr Umsatz zu erlangen, setzt das Unternehmen den Preis etwas niedriger und investiert in das Marketing. Personal wird jedoch nicht entlassen, da man optimistisch in die Zukunft blickt.");
		     l7.setStyleName("PopupWindowCompetitorLabel");
		     
		     vPanel.add(radio1);
		     vPanel.add(l1);
		     vPanel.add(radio2);
		     vPanel.add(l2);
		     vPanel.add(radio3);
		     vPanel.add(l3);
		     vPanel.add(radio4);
		     vPanel.add(l4);
		     vPanel.add(radio5);
		     vPanel.add(l5);
		     vPanel.add(radio6);
		     vPanel.add(l6);
		     vPanel.add(radio7);
		     vPanel.add(l7);
		     
		   //RadioButtons werden in eine Gruppe zusammengefasst
		     ToggleGroup toggle = new ToggleGroup();
		     toggle.add(radio1);
		     toggle.add(radio2);
		     toggle.add(radio3);
		     toggle.add(radio4);
		     toggle.add(radio5);
		     toggle.add(radio6);
		     toggle.add(radio7);
		     
		   //Eventhandler für die Gruppe der RadioButtons
		     toggle.addValueChangeHandler(new ValueChangeHandler<HasValue<Boolean>>() {
		       @Override
		       public void onValueChange(ValueChangeEvent<HasValue<Boolean>> event) {
		         ToggleGroup group = (ToggleGroup)event.getSource();
		         Radio radio = (Radio)group.getValue();
		         
		         //setzen von der Strategie des Unternehmens 1
		         if (radio.getBoxLabel() == "Strategie 1") stratUn1=0;
		         if (radio.getBoxLabel() == "Strategie 2") stratUn1=1;
		         if (radio.getBoxLabel() == "Strategie 3") stratUn1=2;
		         if (radio.getBoxLabel() == "Strategie 4") stratUn1=3;
		         if (radio.getBoxLabel() == "Strategie 5") stratUn1=4;
		         if (radio.getBoxLabel() == "Strategie 6") stratUn1=5;
		         if (radio.getBoxLabel() == "Strategie 7") stratUn1=6;
		         
		       }
		     });
		     
		     
		     //2nd Competitor
		     
		     VerticalPanel vPanel2 = new VerticalPanel();
		     vPanel2.addStyleName("PopupWindowCompetitorVPanel"); 
		     
		     Radio radio8 = new Radio();
		     radio8.setBoxLabel("Strategie 1");
		     radio8.setStyleName("PopupWindowCompetitorRadio");
		     radio8.setValue(true);
		     Label l8 = new Label("Das Unternehmen setzt viel auf Entwicklung und Forschung und investiert dort stark. Durch die vorhandene Maschinen- und Personalkapazitaet ist es nicht notwendig, weitere Gelder zu investieren.");
		     l8.setStyleName("PopupWindowCompetitorLabel");
		     
		     Radio radio9 = new Radio();
		     radio9.setBoxLabel("Strategie 2");
		     radio9.setStyleName("PopupWindowCompetitorRadio");
		     Label l9 = new Label("Durch das massive Marketing in den vorigen Perioden hat sich die Nachfrage am Produkt erhoeht. Deswegen benoetigt man nun mehr Personal und zwei weitere Maschinen. Der Preis wird ausserdem um 7% erhoeht.");
		     l9.setStyleName("PopupWindowCompetitorLabel");
		     
		     Radio radio10 = new Radio();
		     radio10.setBoxLabel("Strategie 3");
		     radio10.setStyleName("PopupWindowCompetitorRadio");
		     Label l10 = new Label("Die anderen Unternehmen sind weit voraus wenn es sich um die Qualitaet des Produktes handelt. Das Unternehmen setzt alles auf Forschung um die Mitbewerber aufzuholen. Da ein grosser Ansturm auf das Produkt wegen der Forschung erwartet wird, wird eine neue Maschine gekauft.");
		     l10.setStyleName("PopupWindowCompetitorLabel");
		    
		     Radio radio11 = new Radio();
		     radio11.setBoxLabel("Strategie 4");
		     radio11.setStyleName("PopupWindowCompetitorRadio");
		     Label l11 = new Label("Der Bewerber ist gut am Markt und hat nicht das Beduerfnis irgendeine Grossinvestition vorzunehmen. Um dennoch nicht zu verlieren, investiert er in jeden Bereich etwas, jedoch kauft er keine neue Maschine.");
		     l11.setStyleName("PopupWindowCompetitorLabel");
		    
		     Radio radio12 = new Radio();
		     radio12.setBoxLabel("Strategie 5");
		     radio12.setStyleName("PopupWindowCompetitorRadio");
		     Label l12 = new Label("Die letzten Perioden liefen nicht so gut. 5% des Personals, sowie zwei Maschinen werden nicht mehr benoetigt und verkauft. Ausserdem wird der Preis etwas niedriger und durchschnittlich viel fuer das Marketing ausgegeben, um die Marktsituation aus Unternehmenssicht zu verbessern.");
		     l12.setStyleName("PopupWindowCompetitorLabel");
		     
		     Radio radio13 = new Radio();
		     radio13.setBoxLabel("Strategie 6");
		     radio13.setStyleName("PopupWindowCompetitorRadio");
		     Label l13 = new Label("Die letzten Perioden waren nicht so erfolgreich, da nicht der gewuenschte Absatz erreicht wurde. Jetzt gilt es Gelder einzusparen und den Absatz durch niedrige Preise zu erhoehen. Personal muss auf Grund der schlechten Situation entlassen werden. Da mit einem grossen Ansturm auf die billigen Preise gerechnet wird, werden die Maschinen voll ausgelastet.");
		     l13.setStyleName("PopupWindowCompetitorLabel");
		     
		     Radio radio14 = new Radio();
		     radio14.setBoxLabel("Strategie 7");
		     radio14.setStyleName("PopupWindowCompetitorRadio");
		     Label l14 = new Label("Das Unternehmen hat in den letzten Periode mit einem grossen Ansturm gerechnet, doch leider blieb dieser aus. Das Lager ist fast voll und der Gewinn bleibt aus. Damit nicht ueberproduziert wird, werden die Maschinen nur zu 35% ausgelastet. Um wieder mehr Umsatz zu erlangen, setzt das Unternehmen den Preis etwas niedriger und investiert in das Marketing. Personal wird jedoch nicht entlassen, da man optimistisch in die Zukunft blickt.");
		     l14.setStyleName("PopupWindowCompetitorLabel");
		     
		     vPanel2.add(radio8);
		     vPanel2.add(l8);
		     vPanel2.add(radio9);
		     vPanel2.add(l9);
		     vPanel2.add(radio10);
		     vPanel2.add(l10);
		     vPanel2.add(radio11);
		     vPanel2.add(l11);
		     vPanel2.add(radio12);
		     vPanel2.add(l12);
		     vPanel2.add(radio13);
		     vPanel2.add(l13);
		     vPanel2.add(radio14);
		     vPanel2.add(l14);
		     
		     ToggleGroup toggle2 = new ToggleGroup();
		     toggle2.add(radio8);
		     toggle2.add(radio9);
		     toggle2.add(radio10);
		     toggle2.add(radio11);
		     toggle2.add(radio12);
		     toggle2.add(radio13);
		     toggle2.add(radio14);
		     toggle2.addValueChangeHandler(new ValueChangeHandler<HasValue<Boolean>>() {
		       @Override
		       public void onValueChange(ValueChangeEvent<HasValue<Boolean>> event) {
		         ToggleGroup group = (ToggleGroup)event.getSource();
		         Radio radio = (Radio)group.getValue();
//		         System.out.print(radio.getBoxLabel());
		         if (radio.getBoxLabel() == "Strategie 1") stratUn2=0;
		         if (radio.getBoxLabel() == "Strategie 2") stratUn2=1;
		         if (radio.getBoxLabel() == "Strategie 3") stratUn2=2;
		         if (radio.getBoxLabel() == "Strategie 4") stratUn2=3;
		         if (radio.getBoxLabel() == "Strategie 5") stratUn2=4;
		         if (radio.getBoxLabel() == "Strategie 6") stratUn2=5;
		         if (radio.getBoxLabel() == "Strategie 7") stratUn2=6;
		         
		       }
		     });
		     
		     //3rd Competitor
		     
		     VerticalPanel vPanel3 = new VerticalPanel();
		     vPanel3.addStyleName("PopupWindowCompetitorVPanel"); 
		     
		     	     
		     Radio radio15 = new Radio();
		     radio15.setBoxLabel("Strategie 1");
		     radio15.setStyleName("PopupWindowCompetitorRadio");
		     radio15.setValue(true);
		     Label l15 = new Label("Das Unternehmen setzt viel auf Entwicklung und Forschung und investiert dort stark. Durch die vorhandene Maschinen- und Personalkapazitaet ist es nicht notwendig, weitere Gelder zu investieren.");
		     l15.setStyleName("PopupWindowCompetitorLabel");
		     
		     Radio radio16 = new Radio();
		     radio16.setBoxLabel("Strategie 2");
		     radio16.setStyleName("PopupWindowCompetitorRadio");
		     Label l16 = new Label("Durch das massive Marketing in den vorigen Perioden hat sich die Nachfrage am Produkt erhoeht. Deswegen benoetigt man nun mehr Personal und zwei weitere Maschinen. Der Preis wird ausserdem um 7% erhoeht.");
		     l16.setStyleName("PopupWindowCompetitorLabel");
		     
		     Radio radio17 = new Radio();
		     radio17.setBoxLabel("Strategie 3");
		     radio17.setStyleName("PopupWindowCompetitorRadio");
		     Label l17 = new Label("Die anderen Unternehmen sind weit voraus wenn es sich um die Qualitaet des Produktes handelt. Das Unternehmen setzt alles auf Forschung um die Mitbewerber aufzuholen. Da ein grosser Ansturm auf das Produkt wegen der Forschung erwartet wird, wird eine neue Maschine gekauft.");
		     l17.setStyleName("PopupWindowCompetitorLabel");
		    
		     Radio radio18 = new Radio();
		     radio18.setBoxLabel("Strategie 4");
		     radio18.setStyleName("PopupWindowCompetitorRadio");
		     Label l18 = new Label("Der Bewerber ist gut am Markt und hat nicht das Beduerfnis irgendeine Grossinvestition vorzunehmen. Um dennoch nicht zu verlieren, investiert er in jeden Bereich etwas, jedoch kauft er keine neue Maschine.");
		     l18.setStyleName("PopupWindowCompetitorLabel");
		    
		     Radio radio19 = new Radio();
		     radio19.setBoxLabel("Strategie 5");
		     radio19.setStyleName("PopupWindowCompetitorRadio");
		     Label l19 = new Label("Die letzten Perioden liefen nicht so gut. 5% des Personals, sowie zwei Maschinen werden nicht mehr benoetigt und verkauft. Ausserdem wird der Preis etwas niedriger und durchschnittlich viel fuer das Marketing ausgegeben, um die Marktsituation aus Unternehmenssicht zu verbessern.");
		     l19.setStyleName("PopupWindowCompetitorLabel");
		     
		     Radio radio20 = new Radio();
		     radio20.setBoxLabel("Strategie 6");
		     radio20.setStyleName("PopupWindowCompetitorRadio");
		     Label l20 = new Label("Die letzten Perioden waren nicht so erfolgreich, da nicht der gewuenschte Absatz erreicht wurde. Jetzt gilt es Gelder einzusparen und den Absatz durch niedrige Preise zu erhoehen. Personal muss auf Grund der schlechten Situation entlassen werden. Da mit einem grossen Ansturm auf die billigen Preise gerechnet wird, werden die Maschinen voll ausgelastet.");
		     l20.setStyleName("PopupWindowCompetitorLabel");
		     
		     Radio radio21 = new Radio();
		     radio21.setBoxLabel("Strategie 7");
		     radio21.setStyleName("PopupWindowCompetitorRadio");
		     Label l21 = new Label("Das Unternehmen hat in den letzten Periode mit einem grossen Ansturm gerechnet, doch leider blieb dieser aus. Das Lager ist fast voll und der Gewinn bleibt aus. Damit nicht ueberproduziert wird, werden die Maschinen nur zu 35% ausgelastet. Um wieder mehr Umsatz zu erlangen, setzt das Unternehmen den Preis etwas niedriger und investiert in das Marketing. Personal wird jedoch nicht entlassen, da man optimistisch in die Zukunft blickt.");
		     l21.setStyleName("PopupWindowCompetitorLabel");
		     
		     vPanel3.add(radio15);
		     vPanel3.add(l15);
		     vPanel3.add(radio16);
		     vPanel3.add(l16);
		     vPanel3.add(radio17);
		     vPanel3.add(l17);
		     vPanel3.add(radio18);
		     vPanel3.add(l18);
		     vPanel3.add(radio19);
		     vPanel3.add(l19);
		     vPanel3.add(radio20);
		     vPanel3.add(l20);
		     vPanel3.add(radio21);
		     vPanel3.add(l21);
		     
		     ToggleGroup toggle3 = new ToggleGroup();
		     toggle3.add(radio15);
		     toggle3.add(radio16);
		     toggle3.add(radio17);
		     toggle3.add(radio18);
		     toggle3.add(radio19);
		     toggle3.add(radio20);
		     toggle3.add(radio21);
		     toggle3.addValueChangeHandler(new ValueChangeHandler<HasValue<Boolean>>() {
		       @Override
		       public void onValueChange(ValueChangeEvent<HasValue<Boolean>> event) {
		         ToggleGroup group = (ToggleGroup)event.getSource();
		         Radio radio = (Radio)group.getValue();
//		         System.out.print(radio.getBoxLabel());
		         if (radio.getBoxLabel() == "Strategie 1") stratUn3=0;
		         if (radio.getBoxLabel() == "Strategie 2") stratUn3=1;
		         if (radio.getBoxLabel() == "Strategie 3") stratUn3=2;
		         if (radio.getBoxLabel() == "Strategie 4") stratUn3=3;
		         if (radio.getBoxLabel() == "Strategie 5") stratUn3=4;
		         if (radio.getBoxLabel() == "Strategie 6") stratUn3=5;
		         if (radio.getBoxLabel() == "Strategie 7") stratUn3=6;
		         
		         
		       }
		     });
		 
		      panel.add(vPanel, new TabItemConfig(kName1));
		      panel.add(vPanel2, new TabItemConfig(kName2));
		      panel.add(vPanel3, new TabItemConfig(kName3));
		 
		      window.add(panel);
		      
		      
		      TextButton bSpeichern = new TextButton("Speichern");
		      bSpeichern.addSelectHandler(new SelectHandler() {
		 
		        @Override
		        public void onSelect(SelectEvent event) {
		        	try {
				          window.hide();
					} catch (Exception e) {
						// TODO: handle exception
					}
		        }
		      });
		      
		      TextButton bSchliessen = new TextButton("Schliessen");
		      bSchliessen.addSelectHandler(new SelectHandler() {
		        @Override
		        public void onSelect(SelectEvent event) {
		        	try {
				          window.hide();
					} catch (Exception e) {
						// TODO: handle exception
					}

		        }
		      });
		      window.addButton(bSchliessen);
		      window.addButton(bSpeichern);
		      window.setFocusWidget(window.getButtonBar().getWidget(0));

		      window.show();
		      vp = new VerticalPanel();
			}
			return vp;
		  }
		  
		  //Rückgabemethoden für die einzelnen Strategien
		  public int getStrategie1(){

			  return stratUn1;
		  }
		  public int getStrategie2(){

			  return stratUn2;
		  }
		  public int getStrategie3(){

			  return stratUn3;
		  }

}
