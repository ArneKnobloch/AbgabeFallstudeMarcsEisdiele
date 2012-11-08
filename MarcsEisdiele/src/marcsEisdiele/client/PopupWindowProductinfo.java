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


public class PopupWindowProductinfo implements IsWidget {
    
	PopupWindowProductinfo(){
		RootPanel.get().add(asWidget());
	}
	private VerticalPanel vp = null;
	  @Override
	  public Widget asWidget() {
		if (vp == null) {
	      final Window window = new Window();
	      window.setPixelSize(450, 300);
	      window.setModal(true);
	      window.setBlinkModal(true);

	      window.setHeadingText("Productinfo");
	      window.addHideHandler(new HideHandler() {
	        @Override
	        public void onHide(HideEvent event) {
	          TextButton open = ((Window) event.getSource()).getData("open");
	          open.focus();
	        }
	      });
	 
	      VerticalPanel vPanel = new VerticalPanel();

	      vPanel.setStyleName("popup"); 
	      
	      Label label1 = new Label("Marcs Eisdiele Software AG");
	      Label label2 = new Label("");
	      Label label3 = new Label("GUI: Hannah Krust, Marc-Philipp Boeckle und Stephan Grabaum");
	      Label label4 = new Label("Datenbankverantwortlicher: Arne Knobloch");
	      Label label5 = new Label("Dokumentation: Lisa Schmidt und Team");
	      Label label6 = new Label("Logik: Tobias Kratofil und Lisa Schmidt");
	      label1.addStyleName("label_popup_head");
	      label2.addStyleName("label_popup");
	      label3.addStyleName("label_popup");
	      label4.addStyleName("label_popup");
	      label5.addStyleName("label_popup");
	      label6.addStyleName("label_popup");
	      
	      vPanel.add(label1);
	      vPanel.add(label2);
	      vPanel.add(label3);
	      vPanel.add(label4);
	      vPanel.add(label5);
	      vPanel.add(label6);
	 
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