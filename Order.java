public class Order{

	long poNumber;
	int loadID, loadDest, shipPoint;
	int cases, weight, cube, layers, pallets;
	int poType, department, poDestination;
	String loadPUStartDate, loadPUEndDate, carrierPUDate, carrierDueDate, carrierName;
	String loadDestType, loadAddress, mabd, scac, mode, loadMethod, eventCode, confirmDate, heldOrNo, destAddress;
	boolean used;

	public Order(){}

	public long getPONumber(){ return this.poNumber; }
	public int getCases(){ return this.cases; }
	public int getCube(){ return this.cube; }
	public int getPODestination(){ return this.poDestination; }
	public int getLayers(){ return this.layers; }

	public String getInfo(){ //mostly a method used for testing
		return "PO Number: " + getPONumber() + ". Number of cases: " + getCases() + ". Number of cube: " + getCube() + ". Number of layers: " + getLayers() + " PO Destination Number: " + getPODestination() + ".";
	}

	public void setOrder(long poNumber, int loadID, int loadDest, String loadDestType, String loadAddress, String loadPUStartDate, String loadPUEndDate, String mabd, String carrierPUDate, String carrierDueDate, String carrierName, String scac, String mode, int shipPoint, String loadMethod, int cases, int weight, int cube, int pallets, String eventCode, int poType, int department, String confirmDate, String heldOrNo, int poDestination){
		this.poNumber = poNumber;
		this.loadID = loadID;
		this.loadDest = loadDest;
		this.loadDestType = loadDestType;
		this.loadAddress = loadAddress;
		this.loadPUStartDate = loadPUStartDate;
		this.loadPUEndDate = loadPUEndDate;
		this.mabd = mabd;
		this.carrierPUDate = carrierPUDate;
		this.carrierDueDate = carrierDueDate;
		this.carrierName = carrierName;
		this.scac = scac;
		this.mode = mode;
		this.shipPoint = shipPoint;
		this.loadMethod = loadMethod;
		this.cases = cases;
		this.weight = weight;
		this.cube = cube;
		this.pallets = pallets;
		this.eventCode = eventCode;
		this.poType = poType;
		this.department = department;
		this.confirmDate = confirmDate;
		this.heldOrNo = heldOrNo;
		this.poDestination = poDestination; //the variable used to match orders on the same pallet
		this.used = false;

		if (getCube() <= 20){ this.layers = 1; }
		else if (getCube() <= 40){ this.layers = 2; }
		else if (getCube() <= 60){ this.layers = 3; }
		else if (getCube() <= 80){ this.layers = 4; }
		else if (getCube() <= 100){ this.layers = 5; }
		else if (getCube() == 111){ //111 is a special number, meaning it is an entire pallet.
			if (getCases() <= 27){ this.layers = 1; }
			else if (getCases() <= 54){ this.layers = 2; }
			else if (getCases() <= 81){ this.layers = 3; }
			else if (getCases() <= 108){ this.layers = 4; }
			else if (getCases() <= 135){ this.layers = 5; }
			else if (getCases() <= 162){ this.layers = 6; }
			else{ this.layers = 0; } //default
		}
		else{ this.layers = 0; } //default

		switch (this.getPODestination()){
			case 7035:
				this.destAddress = "Alachua (FL)";
				break;
			case 7033:
				this.destAddress = "Apple Valley (CA)";
				break;
			case 7039:
				this.destAddress = "Beaver Dam (WI)";
				break;
			case 6094:
				this.destAddress = "Bentonville (AR)";
				break;
			case 6011:
				this.destAddress = "Brookhaven (MS)";
				break;
			case 6020:
				this.destAddress = "Brooksville (FL)";
				break;
			case 6031:
				this.destAddress = "Buckeye (AZ)";
				break;
			case 6043:
				this.destAddress = "Coldwater (MI)";
				break;
			case 6006:
				this.destAddress = "Cullman (AL)";
				break;
			case 6010:
				this.destAddress = "Douglas (GA)";
				break;
			case 7038:
				this.destAddress = "FT Pierce (FL)";
				break;
			case 7026:
				this.destAddress = "Grantsville (UT)";
				break;
			case 6024:
				this.destAddress = "Grove City (OH)";
				break;
			case 6037:
				this.destAddress = "Hermiston (OR)";
				break;
			case 6040:
				this.destAddress = "Hope Mills (NC)";
				break;
			case 6066:
				this.destAddress = "Hopkinsville (KY)";
				break;
			case 6054:
				this.destAddress = "La Grange (GA)";
				break;
			case 6019:
				this.destAddress = "Loveland (CO)";
				break;
			case 6038:
				this.destAddress = "Marcy (NY)";
				break;
			case 6025:
				this.destAddress = "Menomonie (WI)";
				break;
			case 6039:
				this.destAddress = "Midway (TN)";
				break;
			case 7045:
				this.destAddress = "MT Crawford (VA)";
				break;
			case 6009:
				this.destAddress = "Mount Pleasant (IA)";
				break;
			case 6016:
				this.destAddress = "New Braunfels (TX)";
				break;
			case 6048:
				this.destAddress = "Opelousas (LA)";
				break;
			case 6035:
				this.destAddress = "Ottawa (KS)";
				break;
			case 6036:
				this.destAddress = "Palestine (TX)";
				break;
			case 6012:
				this.destAddress = "Plainview (TX)";
				break;
			case 6021:
				this.destAddress = "Porterville (CA)";
				break;
			case 6030:
				this.destAddress = "Raymond (NH)";
				break;
			case 6026:
				this.destAddress = "Red Bluff (CA)";
				break;
			case 6068:
				this.destAddress = "Sanger (TX)";
				break;
			case 7036:
				this.destAddress = "Sealy (TX)";
				break;
			case 6018:
				this.destAddress = "Searcy (AR)";
				break;
			case 6017:
				this.destAddress = "Seymour (IN)";
				break;
			case 6070:
				this.destAddress = "Shelby (NC)";
				break;
			case 7034:
				this.destAddress = "Smyrna (DE)";
				break;
			case 6092:
				this.destAddress = "Spring Valley (IL)";
				break;
			case 6069:
				this.destAddress = "St. James (MO)";
				break;
			case 6023:
				this.destAddress = "Sutherland (VA)";
				break;
			case 6080:
				this.destAddress = "Tobyhanna (PA)";
				break;
			case 6027:
				this.destAddress = "Woodland (PA)";
				break;
			default:
				this.destAddress = "";
		}
	}

	@Override
	public String toString(){     
		if (this.cases >= 100){
			return this.poNumber + "            " + this.cases + "                     " + this.poDestination + "                   " + this.layers + "             " + this.destAddress;
		}
		return this.poNumber + "             " + this.cases + "                      " + this.poDestination + "                   " + this.layers + "              " + this.destAddress;
	}
}