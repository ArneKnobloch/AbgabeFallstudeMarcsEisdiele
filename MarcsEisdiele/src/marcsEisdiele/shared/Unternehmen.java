package marcsEisdiele.shared;

import java.io.Serializable;

import marcsEisdiele.client.Game;
import marcsEisdiele.client.start;

import com.google.gwt.user.client.Window;

public class Unternehmen implements Serializable {
	
	private static final long serialVersionUID = -5374561203702292915L;
	private String NameUN;
	private int personal,
				research,
				productivity,
				gewinn,
				kapital,
				productprice,
				marketing,
				fixCost,
				storage,
				machines,
				machinesCapacity,
				round,
				usedStorage,
				soldProducts,
				totalExpenditure,
				producedProducts,
				quality,
				varCosts,
				marketShare,
				machinesWorkload,
				UNID,
				gameID;
	
	public Unternehmen(String nNameUN, int npersonal, int nkapital, int nqualitaet,
						int nmachines,int nmachinesCapacity, int nmachinesWorkload,int nstorage, 
						int nvarCosts, int nprice, int nmarketing, int nmarketShare, int nUNID) {

		

		NameUN=nNameUN;
		personal=npersonal;
		productivity = 50;
		gewinn= 0;
		kapital = nkapital;
		quality=nqualitaet;
		research = 0;
		machines = nmachines;
		machinesCapacity = nmachinesCapacity*nmachines;
		machinesWorkload = nmachinesWorkload;
		storage = nstorage;
		usedStorage = 0;
		soldProducts = 0;
		totalExpenditure = 0;
		producedProducts = 0;
		varCosts = nvarCosts;
		productprice = nprice;
		marketing = nmarketing;
		marketShare = nmarketShare;
		UNID = nUNID;
		gameID = Game.gameID;
		
		fixCost = machinesCapacity*5 + personal * 8000 + storage*10; 	
		
	}
	public int getResearch(){
		return research;
	}
	public void setResearch(int research){
		this.research = research;
	}
	public int getProductivity(){
		return productivity;
	}
	public void setProductivity(int productivity){
		this.productivity = productivity;
	}
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public int getUNID() {
		return UNID;
	}
	public void setUNID(int uNID) {
		UNID = uNID;
	}
	public int getProductprize() {
		return productprice;
	}

	public void setProductprize(int productprize) {
		this.productprice = productprize;
	}

	public int getMarketing() {
		return marketing;
	}

	public void setMarketing(int marketing) {
		this.marketing = marketing;
	}

	public int getFixCost() {
		return fixCost;
	}

	public void setFixCost(int fixCost) {
		this.fixCost = fixCost;
	}

	public int getStorage() {
		return storage;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}

	public int getMarketShare() {
		return marketShare;
	}

	public void setMarketShare(int marketShare) {
		this.marketShare = marketShare;
	}

	public int getMachines() {
		return machines;
	}

	public void setMachines(int machines) {
		this.machines = machines;
	}

	public int getMachinesCapacity() {
		return machinesCapacity;
	}

	public void setMachinesCapacity(int machinesCapacity) {
		this.machinesCapacity = machinesCapacity;
	}

	public int getMachineWorkload() {
		return machinesWorkload;
	}

	public void setMachineWorkload(int machineWorkload) {
		this.machinesWorkload = machineWorkload;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getUsedStorage() {
		return usedStorage;
	}

	public void setUsedStorage(int usedStorage) {
		this.usedStorage = usedStorage;
	}

	public int getSoldProducts() {
		return soldProducts;
	}

	public void setSoldProducts(int soldProducts) {
		this.soldProducts = soldProducts;
	}

	public int getTotalExpenditure() {
		return totalExpenditure;
	}

	public void setTotalExpenditure(int totalExpenditure) {
		this.totalExpenditure = totalExpenditure;
	}

	public int getProducedProducts() {
		return producedProducts;
	}

	public void setProducedProducts(int producedProducts) {
		this.producedProducts = producedProducts;
	}

	public int getVarCosts() {
		return varCosts;
	}

	public void setVarCosts(int varCosts) {
		this.varCosts = varCosts;
	}

	public Unternehmen(){
		
	}
	public String getNameUN() {
		return NameUN;
	}

	public void setNameUN(String nameUN) {
		NameUN = nameUN;
	}
	

	public int getPersonal() {
		return personal;
	}

	public void setPersonal(int personal) {
		this.personal = personal;
	}

	public int getGewinn() {
		return gewinn;
	}

	public void setGewinn(int gewinn) {
		this.gewinn = gewinn;
	}
	public int getKapital(){
		return kapital;
	}
	public void setKapital(int kapital){
		this.kapital = kapital;
	}
	public int getQuality() {
		return quality;
	}
	public void setQuality(int qualitaet) {
		this.quality = qualitaet;
	}

}
