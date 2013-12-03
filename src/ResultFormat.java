
public class ResultFormat implements Comparable<Object>{

	int rank;
	int id;
	int low;
	int high;
		
	public ResultFormat(int rank, int id, int low, int high){
		this.rank = rank;
		this.id = id;
		this.low = low;
		this.high = high;
	}
	
	public int getLow(){
		return this.low;
	}
	public int getHigh(){
		return this.high;
	}

	@Override
	public int compareTo(Object obj) {
		ResultFormat n = (ResultFormat)obj;
		if(rank == n.rank){
			return id-n.id;
		}else{
			return rank-n.rank;	
		}
	}
}
