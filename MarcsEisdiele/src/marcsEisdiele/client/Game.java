package marcsEisdiele.client;

import java.util.List;

import marcsEisdiele.shared.Unternehmen;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class Game implements EntryPoint {

public static int gameID, runde;
private static UnternehmensServiceAsync service;
	

	@Override
	public void onModuleLoad() {
		service = GWT.create(UnternehmensService.class);
		service.getNewestCompany(new AsyncCallback<Unternehmen>(){

			@Override
			public void onFailure(Throwable caught) {
				start start = new start();
				start.onModuleLoad();
			}

			@Override
			public void onSuccess(Unternehmen result) {
				try{
					if(result==null){
					start start = new start();
					start.onModuleLoad();
				}
					if(result.getRound()==0){
//						System.out.println(result.getGameID());
					Game.gameID=result.getGameID()+1;
					start start = new start();
					start.onModuleLoad();
				}else{
				runde = result.getRound();
				start start = new start();
				start.fakeStart();
				start.zaehlerRunde=0;
				for(int i=runde; i>0; i--){
				start.getRunde();
				start.rundenArray[start.zaehlerRunde].fakeClickstart();
				}
				}
				}
				catch(java.lang.NullPointerException e){}
			}
		});
	}
}
