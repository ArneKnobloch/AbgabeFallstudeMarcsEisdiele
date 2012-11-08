package marcsEisdiele.client;

import java.util.List;

import marcsEisdiele.shared.Unternehmen;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("UnternehmensService")
public interface UnternehmensService extends RemoteService {
	
	void addUnternehmen(Unternehmen unternehmen);
	Unternehmen getUnternehmen(int id, int runde,int gameID);
	List<Unternehmen> getAlleUnternehmen(int gameID);
	List<Unternehmen> getAlleUnternehmenRunde(int runde,int gameID);
	List<Unternehmen> getAlleSpezifisch(int UNID,int gameID);
	void addAllUnternehmen(List<Unternehmen> unternehmenAll);
	Unternehmen getNewestCompany();

}
