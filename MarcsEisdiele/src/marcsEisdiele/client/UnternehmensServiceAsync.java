package marcsEisdiele.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import marcsEisdiele.shared.Unternehmen;


public interface UnternehmensServiceAsync {
	
	void addUnternehmen(Unternehmen unternehmen, AsyncCallback<Void> callback);
	void getUnternehmen(int id, int runde,int gameID, AsyncCallback<Unternehmen> callback);
	void getAlleUnternehmen(int gameID,AsyncCallback<List <Unternehmen>> callback);
	void getAlleUnternehmenRunde(int runde,int gameID,AsyncCallback<List <Unternehmen>> callback);
	void getAlleSpezifisch(int UNID,int gameID,AsyncCallback<List <Unternehmen>> callback);
	void addAllUnternehmen(List<Unternehmen> unternehmenAll, AsyncCallback<Void> callback);
	void getNewestCompany(AsyncCallback<Unternehmen> callback); 

}
