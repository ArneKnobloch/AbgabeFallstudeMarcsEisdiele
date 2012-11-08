package marcsEisdiele.server;

import java.lang.reflect.Array;
import java.util.List;

import marcsEisdiele.client.Game;
import marcsEisdiele.client.UnternehmensService;
import marcsEisdiele.client.UnternehmensServiceAsync;
import marcsEisdiele.shared.Unternehmen;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


/*
 * Klasse mit der Implememntierung der Datenbankzugriffservices
 */


public class UnternehmensServiceImpl
	extends RemoteServiceServlet
	implements UnternehmensService{

	private static final long serialVersionUID = 3958578023704542908L;
	
	//Methode zum speichern eines Unternehmens in der Datenbank
	@Override
	public void addUnternehmen(Unternehmen unternehmen) {
		PersistenceManager.getDatastore().save(unternehmen);
		
	}
	
	// Methode die ein Unternehmen f�r eine bestimmte Runde zur�ckliefert
	// zu �bergeben: Name des Unternehmens, rundenzahl(sollte man aus der GUI lesen k�nnen)
	@Override
	public Unternehmen getUnternehmen(int id, int runde,int gameID) {
		Datastore ds = PersistenceManager.getDatastore();
		Query<Unternehmen> q = ds.find(
                Unternehmen.class).filter("UNID =", id).filter("round =", runde).filter("gameID =", gameID);
		return q.get();
		
	}
	//Methode die alle Unternehmen als Liste ausgibt
	@Override
	public List<Unternehmen> getAlleUnternehmen(int gameID) {
		Datastore ds = PersistenceManager.getDatastore();
		return ds.createQuery(Unternehmen.class).filter("gameID =", gameID).order("round").asList();
	}
	// Methode die alle Unternehmen f�r eine spezielle Runde ausgibt
	// zu �bergeben: Die Runde f�r die die Unternehmen ausgegeben werden soll
	@Override
	public List<Unternehmen> getAlleUnternehmenRunde(int runde,int gameID) {
		Datastore ds = PersistenceManager.getDatastore();
		return ds.createQuery(Unternehmen.class).filter("round =", runde).filter("gameID =", gameID).asList();
	}
	
	//Methode die f�r ein bestimmtes Unternehmen alle RundenObjekte ausgibt
	//zu �bergeben: Name des Unternehmens
	@Override
	public List<Unternehmen> getAlleSpezifisch(int UNID,int gameID) {
		Datastore ds = PersistenceManager.getDatastore();
		return ds.createQuery(Unternehmen.class).filter("UNID =", UNID).filter("gameID =", gameID).asList();
	}
	
	//Methode zum speichern alle �bergebenen Unternehmen
	//zu �bergeben: Liste mit allen zu �bergebenden Unternehmensobjekten
	@Override
	public void addAllUnternehmen(List<Unternehmen> unternehmenAll) {
		for(int i = 0; i < unternehmenAll.size();i++){
			Unternehmen unternehmen = unternehmenAll.get(i);
			PersistenceManager.getDatastore().save(unternehmen);
		}
		
	}
	
	//Methode die das letzte gespeicherte Unternehmen ausgibt
	@Override
	public Unternehmen getNewestCompany() {
		int[] gameID = new int[2];
		Datastore ds = PersistenceManager.getDatastore();
		Unternehmen unternehmen = ds.createQuery(Unternehmen.class).order("-gameID,-round").limit(1).get();
		return unternehmen;
	}

}
