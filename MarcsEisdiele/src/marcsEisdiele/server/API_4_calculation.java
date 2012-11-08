package marcsEisdiele.server;

import marcsEisdiele.shared.Unternehmen;


public class API_4_calculation {

	
//////// market behavior ////////
	public static double marketBehavior = 1;
	
	public static void marketBehavior(int i){
		//random Zahl zwischen 0.8 und 1
		switch( i){
		case 1: marketBehavior = Math.random() * (1.0 - 0.8) + 0.8; break;
		case 2: marketBehavior = 1; break;
		case 3: marketBehavior = Math.random() * (1.2 - 1.0) + 1.0; break;
		}

	}
	
	
/////// competitor strategys ////////
	/**competitor strategie**/
	public static void competitorStrategie(Unternehmen un, int i){
		switch( i){
		case 0: cSRaD(un); break;
		case 1: cSPaM(un); break;
		case 2: cSRaM(un); break;
		case 3: cSnRgS(un); break;
		case 4: cSmMlP(un); break;
		case 5: cSlPlP(un); break;
		case 6: cSmPsF(un); break;
		}
	}
	
	/**research and developement**/
	private static void cSRaD(Unternehmen un){
			marketingInvestition(un, (int) (un.getKapital()*0.13));
			forschungsInvestition(un,(int) (un.getKapital()*0.10));
			un.setMachineWorkload(60);
	}
	
	/**personal and machines**/ //gesteigerte Nachfrage im letzten Quartal
	private static void cSPaM(Unternehmen un){
		un.setProductprize( (int)(un.getProductprize()*1.07));
		personalAenderung(un, (int) (un.getPersonal()*1.05) );
		maschineKaufen(un, 2);
		un.setMachineWorkload(100);
		
	}
	
	/**research and machines**/ //mit Qualität im Rückstand
	private static void cSRaM(Unternehmen un){
		forschungsInvestition(un, (int) (un.getKapital()*0.20));
		maschineKaufen(un, 1);
		un.setMachineWorkload(75);
	}
	
	/**no risk, good situation**/
	private static void cSnRgS(Unternehmen un){
		marketingInvestition(un, (int)(un.getKapital()*0.05));
		forschungsInvestition(un, (int)(un.getKapital()*0.05));
		personalAenderung(un, (int)(un.getPersonal()*0.02));
		un.setMachineWorkload(75);
	}
	
	/**bad situation - more marketing, less production**/
	private static void cSmMlP(Unternehmen un){
		personalAenderung(un, - (int)(un.getPersonal()*0.05));
		marketingInvestition(un, (int) ( un.getKapital()*0.13));
		maschineKaufen(un, -2);
		un.setMachineWorkload(50);
		un.setProductprize( (int) (un.getProductprize()*0.95));
	}
	
	/**bad situation - less personal, low price **/
	private static void cSlPlP(Unternehmen un){
		personalAenderung(un, - (int)(un.getPersonal()*0.1));
		un.setMachineWorkload(100);
		un.setProductprize((int)(un.getProductprize()*0.9));
	}
	
	/** to much products, storage full**/
	private static void cSmPsF(Unternehmen un){
		marketingInvestition(un, (int)(un.getKapital()*0.13));
		un.setProductprize((int)(un.getProductprize()*0.95));
		un.setMachineWorkload(35);
	}

///////// activitys ////////
	
	public static void personalAenderung(Unternehmen un, int personal) {
		un.setPersonal(un.getPersonal() + personal);
		un.setFixCost(un.getFixCost() + personal * 8000 );
	}
	
	public static void forschungsInvestition(Unternehmen un, int geld){
		un.setResearch(geld);
		un.setKapital( (int) (un.getKapital() - geld ));
		un.setQuality( (int) (un.getQuality() + geld * 0.000001 ));
		un.setMachinesCapacity( (int) (un.getMachinesCapacity() + geld * 0.005));
		un.setProductivity((int) (un.getProductivity() + geld * 0.0005 ));
		
		if( un.getVarCosts() > 5)
		un.setVarCosts((int) (un.getVarCosts() - geld/20000) );
	}
	
	public static void marketingInvestition(Unternehmen un, int geld){
		un.setKapital( (int) (un.getKapital() - geld ));
		un.setMarketing(un.getMarketing() + geld);
	}
	
	public static void maschineKaufen(Unternehmen un, int maschinen){
		un.setKapital( un.getKapital() - maschinen * 50000);
		int kapazitaet = un.getMachinesCapacity();
		int anzahl = un.getMachines();
		//Unter der Annahme, dass man keine schlechteren Maschinen kauft, als diese die man bereits besitzt :
		un.setMachinesCapacity(kapazitaet + kapazitaet/anzahl * maschinen );
		un.setFixCost(un.getFixCost() + maschinen * 5000);
		un.setMachines(un.getMachines() + maschinen );
	}

}