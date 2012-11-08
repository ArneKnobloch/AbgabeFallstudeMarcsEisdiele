package marcsEisdiele.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.info.Info;

public class Options extends Composite implements  HasText {

	private static OptionsUiBinder uiBinder = GWT.create(OptionsUiBinder.class);

	interface OptionsUiBinder extends UiBinder<Widget, Options> {
	} 

	//Hier sollen mal die Spieleinstellungen festgelegt werden
	public Options() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField Button bSave;

	@UiHandler("bSave")
	void onClickSave(ClickEvent e) {
		//DB +++
		Info.display("Einstellungen gespeichert!", "Die neuen Einstellungen sind jetzt aktiv.");
	}
	
	ContentPanel panel = new ContentPanel();
	VerticalPanel vPanel = new VerticalPanel();
	HorizontalPanel hPanel = new HorizontalPanel();

	public Options(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setText(String text) {
	}

	public String getText() {
		return null;
	}
	
	public Widget getOptions(){
		bSave.setText("Einstellungen speichern");
		
		panel.setBodyStyleName("options");

		return this;
	}
}