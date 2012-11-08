package marcsEisdiele.client.diagramm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import marcsEisdiele.client.UnternehmensService;
import marcsEisdiele.client.UnternehmensServiceAsync;
import marcsEisdiele.shared.Unternehmen;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class TestData {

//	  private static final String[] monthsFull = new String[] {
//	      "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November",
//	      "December"};	

	  private static final String[] quartalFull = new String[] {
	      "1. Quartal", "2. Quartal", "3. Quartal", "4. Quartal", "5. Quartal", "6. Quartal", "7. Quartal", "8. Quartal", "9. Quartal", "10. Quartal", "11. Quartal",
	      "12. Quartal"};	
	  private static int zaehler;

	  public static List<Data> getData(int[] werte) {
		zaehler = 0;
	    List<Data> data = new ArrayList<Data>();
	    for (int i = 0; i < werte.length; i=i+4) {
	      data.add(new Data(quartalFull[zaehler % quartalFull.length],werte[i], werte[i+1],werte[i+2], werte[i+3],50, 60, 70, 80, 90));
	      zaehler++;
	    }
	    return data;
	  }


	  
	  
	}

