package marcsEisdiele.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class PopupWindowConfirm implements IsWidget {
	private static UnternehmensServiceAsync service;

	PopupWindowConfirm(){
		RootPanel.get().add(asWidget());
	}
	private VerticalPanel vp = null;
	  @Override
	  public Widget asWidget() {
		if (vp == null) {
	      final Window window = new Window();
	      window.setPixelSize(450, 200);
	      window.setModal(true);
	      window.setBlinkModal(true);

	      window.setHeadingText("Warnung!");
	 
	      VerticalPanel vPanel = new VerticalPanel();

	      vPanel.setStyleName("popup"); 
	      
	      Label label1 = new Label("Sind Sie sicher, dass sie ein neues Spiel starten wollen? Der momentane Fortschritt geht dabei verloren.");
	      label1.addStyleName("label_popup_confirm");  
	      vPanel.add(label1);
	 
	      window.add(vPanel);
	      
	      TextButton bCancel = new TextButton("Abbrechen");
	      bCancel.addSelectHandler(new SelectHandler() {
	        @Override
	        public void onSelect(SelectEvent event) {
	          window.hide();
	        }
	      });
	      window.addButton(bCancel);
	      window.setFocusWidget(window.getButtonBar().getWidget(0));
	      
	      TextButton bConfirm = new TextButton("Fortfahren");
	      bConfirm.addSelectHandler(new SelectHandler(){

			@Override
			public void onSelect(SelectEvent event) {
						Game.gameID++;
						start.zaehlerRunde=1;
						start.zaehlerUN=0;
						start start = new start();
						RootPanel.get().clear();
						start.clear();
						start.onModuleLoad();
						window.hide();			
			}
	    	  
	      });
	      window.addButton(bConfirm);
	      window.setFocusWidget(window.getButtonBar().getWidget(0));
	      window.show();
	      vp = new VerticalPanel();
		}
		return vp;
	  }
	  
}
