package marcsEisdiele.client;

//import marcsEisdiele.shared.Unternehmen;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuBar;
import com.sencha.gxt.widget.core.client.menu.MenuBarItem;
import com.sencha.gxt.widget.core.client.menu.MenuItem;


public class start extends Composite implements EntryPoint, HasText {

	private static startUiBinder uiBinder = GWT.create(startUiBinder.class);

	interface startUiBinder extends UiBinder<Widget, start> {
	}

	public start() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	static UnternehmensUI unUiArray[] = new UnternehmensUI[4];
	static Runde rundenArray[] = new Runde[10];
	static Auswertung auswertungsArray[] = new Auswertung[10];

	public static int zaehlerRunde = 1;
	public static int zaehlerUN = 0;
	
	//Mainpanel definieren
	static TabPanel mainPanel = new TabPanel();

	public start(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
//		bSaveCompany.setText(firstName);
	}

	    //Eventhandler für die Menüleiste 
	SelectionHandler<Item> MenuBarhandler = new SelectionHandler<Item>() {
 	      @Override
 	      public void onSelection(SelectionEvent<Item> event) {
 	        MenuItem item = (MenuItem)event.getSelectedItem();
 	        
 	        if(item.getText()=="Spieleinstellungen"){
 	        	Options o = new Options();
 	        	mainPanel.add(o.getOptions(), new TabItemConfig(item.getText(), true));	
 	        }
 	        else if(item.getText()=="Spielanleitung"){
 	        	PopupWindowSpielanleitung sAnleitung = new PopupWindowSpielanleitung();
 	        }
 	       else if(item.getText()=="Die Attribute"){
	        	PopupWindowAttribute dAttribute = new PopupWindowAttribute();
	        }
 	        else if(item.getText()=="Infos zum Produkt"){
	        	PopupWindowProductinfo pInfo = new PopupWindowProductinfo();
	        }
 	        else if(item.getText()=="neues Spiel"){
 	        	PopupWindowConfirm pCOnfirm = new PopupWindowConfirm();
 	        }
 	        else{
 	        	mainPanel.add(new HTML(item.getText()), new TabItemConfig(item.getText(), true));	
 	        }
// 	        mainPanel.setWidget(mainPanel.getWidget(mainPanel.getWidgetCount()-1));
 	      }
 	};
 	    
	@Override
	public void onModuleLoad() {
		
		//Menüleiste definieren
		
		MenuBar bar = new MenuBar();

	    Menu menu = new Menu();
	    menu.add(new MenuItem("neues Spiel"));

	    MenuBarItem item = new MenuBarItem("Neues Spiel starten", menu);
	    bar.add(item);
	    menu.addSelectionHandler(MenuBarhandler);

	    
	    //TO BE DONE: Spiel laden 
	    menu = new Menu();
	    menu.add(new MenuItem("Spiel 1"));
	    menu.add(new MenuItem("Spiel 2"));

	    item = new MenuBarItem("Spiel laden", menu);
	    bar.add(item);
	    menu.addSelectionHandler(MenuBarhandler);
	    
	    menu = new Menu();

	    //TO BE DONE: Spieleinstellungen
	    item = new MenuBarItem("Optionen", menu);
	    menu.add(new MenuItem("Spieleinstellungen"));
	    bar.add(item);
	    menu.addSelectionHandler(MenuBarhandler);
	    
	    menu = new Menu();

	    item = new MenuBarItem("Hilfe", menu);
	    menu.add(new MenuItem("Spielanleitung"));
	    menu.add(new MenuItem("Die Attribute"));
	    menu.add(new MenuItem("Infos zum Produkt"));
	    bar.add(item);
	    menu.addSelectionHandler(MenuBarhandler);

	    RootPanel.get().add(bar);
	    
	    //Tabpanel definieren	  
	    mainPanel.setTabScroll(true);
	    mainPanel.setWidth("100%");
	    mainPanel.setResizeTabs(true);
	    mainPanel.setAnimScroll(true);
	    mainPanel.setCloseContextMenu(true);
	    
		unUiArray[zaehlerUN] = new UnternehmensUI();
		
		//den ersten Tab mit der UnternehmensUI für das eigene Unternehmen erstellen
		mainPanel.add(unUiArray[zaehlerUN].getPanelUnternehmen(zaehlerUN, "Eigenes Unternehmen definieren"), new TabItemConfig("eigenes Unternehmen definieren"));

	    
		//Startpanel setzen
		mainPanel.setActiveWidget(mainPanel.getWidget(0));
	 
	    RootPanel.get().add(mainPanel);
	}
	
	//Diese Methode wird benötigt falls ein Spielstand nach Schließen des Browsers nachgeladen wird
	public void fakeStart(){
		//MENUBAR
		
		MenuBar bar = new MenuBar();

	    Menu menu = new Menu();
	    menu.add(new MenuItem("neues Spiel"));

	    MenuBarItem item = new MenuBarItem("Neues Spiel starten", menu);
	    bar.add(item);
	    menu.addSelectionHandler(MenuBarhandler);

	    
	    //Letzten X Spiele aus Datenbank anzeigen+++
	    menu = new Menu();
	    menu.add(new MenuItem("Spiel 1"));
	    menu.add(new MenuItem("Spiel 2"));

	    item = new MenuBarItem("Spiel laden", menu);
	    bar.add(item);
	    menu.addSelectionHandler(MenuBarhandler);
	    
	    menu = new Menu();

	    item = new MenuBarItem("Optionen", menu);
	    menu.add(new MenuItem("Spieleinstellungen"));
	    bar.add(item);
	    menu.addSelectionHandler(MenuBarhandler);
	    
	    menu = new Menu();

	    item = new MenuBarItem("Hilfe", menu);
	    menu.add(new MenuItem("Spielanleitung"));
	    menu.add(new MenuItem("Die Attribute"));
	    menu.add(new MenuItem("Infos zum Produkt"));
	    bar.add(item);
	    menu.addSelectionHandler(MenuBarhandler);

	    RootPanel.get().add(bar);
	    
	    //TABPANEL
	    
	    mainPanel.setTabScroll(true);
	    mainPanel.setWidth("100%");
	    mainPanel.setResizeTabs(true);
	    mainPanel.setAnimScroll(true);
	    mainPanel.setCloseContextMenu(true);
	    
//				unUiArray[zaehlerUN] = new UnternehmensUI();
//				
//				panel.add(unUiArray[zaehlerUN].getPanelUnternehmen(zaehlerUN, "eigene Unternehmen definieren"), new TabItemConfig("eigenes Unternehmen definieren"));

	    
	    //STARTPANEL SETZEN
//			    panel.setActiveWidget(panel.getWidget(0));
	 
	    RootPanel.get().add(mainPanel);
	}
	
	
//	neues Tab mit UnternehmensUI für Konkurrenzunternehmen erstellen
	public static void getKonkurrenzUN(){
		zaehlerUN++;
		unUiArray[zaehlerUN] = new UnternehmensUI();
		mainPanel.add(unUiArray[zaehlerUN].getPanelUnternehmen(zaehlerUN, "Konkurrenzunternehmen "+zaehlerUN+" definieren."), new TabItemConfig("Konkurrenzunternehmen" + zaehlerUN));
		mainPanel.setActiveWidget(mainPanel.getWidget(mainPanel.getWidgetCount()-1));
	}
	//Spiel starten - alle Tabs werden gelöscht
	public static void getSpielstarten(){
		rundenArray[zaehlerRunde] = new Runde(zaehlerRunde);
		for(int i = zaehlerUN; i>=0; i--){
			try {
				mainPanel.remove(i);
				
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	    mainPanel.add(rundenArray[zaehlerRunde].getRunde(), new TabItemConfig("Runde" + zaehlerRunde) );
		mainPanel.setActiveWidget(mainPanel.getWidget(mainPanel.getWidgetCount()-1));
	}

	//neues Tab mit Runde erstellen
	public static void getRunde(){
		zaehlerRunde++;
		rundenArray[zaehlerRunde] = new Runde(zaehlerRunde);
	    mainPanel.add(rundenArray[zaehlerRunde].getRunde(), new TabItemConfig("Runde" + zaehlerRunde) );
		mainPanel.setActiveWidget(mainPanel.getWidget(mainPanel.getWidgetCount()-1));
	}
	//neues Tab mit Auswertung erstellen 
	public static void getAuswertung(){
	    auswertungsArray[zaehlerRunde] = new Auswertung(zaehlerRunde);
	    mainPanel.add(auswertungsArray[zaehlerRunde].getAuswertung(), new TabItemConfig("Auswertung" + zaehlerRunde) );
		mainPanel.setActiveWidget(mainPanel.getWidget(mainPanel.getWidgetCount()-1));
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		
	}
	public void clear(){
		for(int i = mainPanel.getWidgetCount();i>0;i--){
			mainPanel.remove(i-1);
		}
	}
}