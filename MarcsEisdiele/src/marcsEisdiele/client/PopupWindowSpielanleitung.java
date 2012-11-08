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


public class PopupWindowSpielanleitung implements IsWidget {
    
	PopupWindowSpielanleitung(){
		RootPanel.get().add(asWidget());
	}
	private VerticalPanel vp = null;
	  @Override
	  public Widget asWidget() {
		if (vp == null) {
	      final Window window = new Window();
	      window.setPixelSize(600, 700);
	      window.setModal(true);
	      window.setBlinkModal(true);

	      window.setHeadingText("Spielanleitung");
	 
	      VerticalPanel vPanel = new VerticalPanel();

	      vPanel.setStyleName("popup"); 
	      
	      Label label1 = new Label("Spielanleitung");
	      Label label2 = new Label("");
	      Label label3 = new Label("Willkommen in der Unternehmens-Simulations-Software von Marcs Eisdiele Software AG.");
	      Label label4 = new Label("Dies ist eine Anleitung in der Sie schrittweise an das " +
	      						   "Spiel herangefuehrt werden." +
	    		  				   " Zur Uebersicht erklaert sich die Menueleiste wie folgt:");
	      Label label5 = new Label("Unter Neues Spiel starten befindet sich der Reiter in dem Sie ein " +
	      						   "neues Spiel mit neuen Unternehmen und Parameter starten koennen.");
	      Label label6 = new Label("Mit Spiel laden koennen Sie bereits gespielte Simulationen laden.");
	      Label label7 = new Label("In den Optionen koennen Sie Einstellungen fuer Ihre Simulation taetigen.");
	      Label label8 = new Label("Unter Hilfe finden Sie diese Spielanleitung und Infos zum Produkt.");
	      Label label9 = new Label("");
	      Label label10 = new Label("");
	      Label label11 = new Label("Schritt 1: Im Fenster Eigenes Unternehmen definieren koennen " +
	      							"Sie Ihr eigenes Unternehmen mit Ihren aktuellen " +
	    		  					"Kennzahlen und weiteren Angaben in das Spiel initialisieren.");
	      Label label12 = new Label("Schritt 2: Im gleichen Fenster finden Sie den Button Konkurrenzunternehmen " +
	      							"hinzufuegen. Hier ist es moeglich Ihre Konkurrenten mit denselben Parametern " +
	      							"wie zuvor zu initialisieren, um spaeter einen Vergleich in diesem Markt zu " +
	      							"realisieren.");
	      Label label13 = new Label("Schritt 3: Wenn Sie auf Spiel starten im unteren Teil des Fensters klicken, " +
	      							"gelangen Sie in das naechste Fenster Runde definieren. Hier sehen Sie " +
	      							"das Fenster, in dem Sie vor jeder Runde Einstellungen und Entscheidungen " +
	      							"fuer das kommende Quartal eintragen und damit simulieren koennen. ");
	      Label label14 = new Label("Schritt 4: Beim Klicken auf den Button Konkurrenzverhalten bearbeiten finden Sie " +
	      							"sieben Strategien die Sie jede Runde und fuer jedes Konkurrenzunternehmen " +
	      							"auswaehlen koennen.");
	      Label label15 = new Label("Schritt 5: Das neue Fenster Auswertung oeffnet sich wenn Sie den Button " +
	      							"Runde starten klicken. Hier sehen Sie die quartalsweise berechneten " +
	      							"Unternehmenserfolge aller angelegten Unternehmen, eingeteilt in Gewinn und " +
	      							"Verkauften Produkten.");
	      Label label16 = new Label("Schritt 6: Ueber den Button neue Runde gelangen Sie wieder in das " +
	      							"Runde definieren Fenster, ueber das Sie wieder Einstellungen fuer das " +
	      							"naechste Quartal vornehmen koennen.");
	      
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