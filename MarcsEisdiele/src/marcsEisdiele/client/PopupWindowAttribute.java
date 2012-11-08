package marcsEisdiele.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;


public class PopupWindowAttribute implements IsWidget {
    
	PopupWindowAttribute(){
		RootPanel.get().add(asWidget());
	}
	private VerticalPanel vp = null;
	  @Override
	  public Widget asWidget() {
		if (vp == null) {
	      final Window window = new Window();
	      window.setPixelSize(800, 800);
	      window.setModal(true);
	      window.setBlinkModal(true);

	      window.setHeadingText("Die Attribute");

	 
	      VerticalPanel vPanel = new VerticalPanel();

	      vPanel.setStyleName("popup"); 
	      
	      Label label1 = new Label("Attribute");
	      Label label2 = new Label("");
	      Label label3 = new Label("Zum Verstaendnis der Attribute bei der Unternehmenserstellung:");
	      Label label4 = new Label("");
	      Label label5 = new Label("Unternehmensname: Der Unternehmensname ist der Name des realen Unternehmens. " +
	      		"Er dient zur einfacheren Uebersicht fuer den Benutzer bei der Auswertung.");
	      Label label6 = new Label("Anzahl der Mitarbeiter: Die Variable Anzahl der Mitarbeiter soll die aktuelle " +
	      		"Mitarbeiteranzahl des realen Unternehmens beinhalten. Das Unternehmen muss mindestens 60 Mitarbeiter " +
	      		"besitzen und kann hoechstens 150 einstellen.");
	      Label label7 = new Label("Startkapital des Unternehmens: Als Startkapital des Unternehmens soll das, fuer die " +
	      		"Unternehmensentscheidungen, verfuegbare Kapital des Unternehmens beinhalten. Es wird mit den Grenzen" +
	      		" 200.000 und 600.000 Euro angelegt.");
	      Label label8 = new Label("Qualitaet des Produkts: Unter Qualitaet des Produkts versteht man die selbst eingeschaetzte" +
	      		" Klassifizierung des eigenen Produktes. Der Wert muss zwischen 1 und 10 liegen, wobei 10 der Maximalwert ist.");
	      Label label9 = new Label("Maschinenanzahl: Die Maschinenanzahl ist die Anzahl der Maschinen, die fuer die Produktion " +
	      		"des behandelten Produktes zu Verfuegung stehen. Es muessen mindestens 2 und koennen maximal 5 Maschinen in " +
	      		"Produktion zu Verfuegung stehen.");
	      Label label10 = new Label("Kapazitaet der Maschinen: Unter der Kapazitaet der Maschinen versteht man die Gesamtproduktion" +
	      		" aller Maschinen, die das Produkt produzieren. Es handelt sich dabei also um die Summe der Produkte, die die " +
	      		"einzelnen Maschinen produzieren.");
	      Label label11 = new Label("Auslastung der Maschinen: Die aktuelle Auslastung der Maschinen soll ebenfalls angegeben werden. " +
	      		"Es handelt sich hierbei um einen Prozentwert, der sich zwischen 1 und 100 % bewegt.");
	      Label label12 = new Label("Lagerkapazitaet fuer Produkte: Unter der Lagerkapazitaet versteht man das zur Verfuegung stehende " +
	      		"Lager und dessen Belastbarkeit. Das bedeutet, dass angegeben werden soll, wie viele Produkte gelagert werden koennen." +
	      		" Die Grenzen fuer diese Variable liegen zwischen 7.500 und 15.000 Produkteinheiten.");
	      Label label13 = new Label("Variable Stueckkosten: Es handelt sich um die Stueckkosten, die ein Produkt verursacht bzw. die" +
	      		" von der Produktion abhaengig sind. Diese koennen zwischen 10 und 100 Euro liegen.");
	      Label label14 = new Label("Preis: Bei dem Preis handelt es sich um den Preis, der fuer das behandelte Produkt vom Kunden " +
	      		"verlangt wird. Dabei wird der Nettopreis betrachtet. Dieser kann zwischen 10 und 200 Euro liegen.");
	      Label label15 = new Label("Marketing: Bei der Kennzahl Marketing handelt es sich um die aktuellen Ausgaben des Unternehmens " +
			"fuer Marketing bei der Definition. Diese Ausgaben koennen zwischen 10.000 und 100.000 Euro liegen.");
	      Label label16 = new Label("Marktanteil: Hier handelt es sich ebenfalls um einen Prozentwert. Dieser Prozentwert beschreibt" +
	      		" den Marktanteil des Unternehmens, welches der Benutzer betrachten moechte.");
	      
	      label1.addStyleName("label_popup_head");
	      label2.addStyleName("label_popup");
	      label3.addStyleName("label_popup");
	      label4.addStyleName("label_popup");
	      label5.addStyleName("label_popup");
	      label6.addStyleName("label_popup");
	      label7.addStyleName("label_popup");
	      label8.addStyleName("label_popup");
	      label9.addStyleName("label_popup");
	      label10.addStyleName("label_popup");
	      label11.addStyleName("label_popup");
	      label12.addStyleName("label_popup");
	      label13.addStyleName("label_popup");
	      label14.addStyleName("label_popup");
	      label15.addStyleName("label_popup");
	      label16.addStyleName("label_popup");
	      
	      vPanel.add(label1);
	      vPanel.add(label2);
	      vPanel.add(label3);
	      vPanel.add(label4);
	      vPanel.add(label5);
	      vPanel.add(label6);
	      vPanel.add(label7);
	      vPanel.add(label8);
	      vPanel.add(label9);
	      vPanel.add(label10);
	      vPanel.add(label11);
	      vPanel.add(label12);
	      vPanel.add(label13);
	      vPanel.add(label14);
	      vPanel.add(label15);
	      vPanel.add(label16);
	      
	      window.add(vPanel);
	      
	      TextButton bSchliessen = new TextButton("Schliessen");
	      bSchliessen.addSelectHandler(new SelectHandler() {
	        @Override
	        public void onSelect(SelectEvent event) {
	          window.hide();
	        }
	      });
	      window.addButton(bSchliessen);
	      window.setFocusWidget(window.getButtonBar().getWidget(0));

	      window.show();
	      vp = new VerticalPanel();
		}
		return vp;
	  }
	  
	}





