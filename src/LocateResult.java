import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;


public class LocateResult {
	ArrayList<ResultFormat> resultStack = new ArrayList<ResultFormat>();
	
	public LocateResult(Hashtable<Integer,Set<String>> result){
		int low = 0;
		int high = 0;
		int rank = 0;
		ArrayList<ResultFormat> resultStack = new ArrayList<ResultFormat>();
		ArrayList<Integer> offsets = new ArrayList<Integer>();
		for(int i:result.keySet()){
			Iterator<String> it = result.get(i).iterator();
			if(it.hasNext()){
				String str = it.next();
				//System.out.println(str+" yi");
				offsets = deal(str);
				//System.out.println(offsets.size() + " fa");
				for(int n:offsets){
					low = n;
					high = n;
					resultStack.add(new ResultFormat(rank,i,low,high));      
				}
				//System.out.println(resultStack.size() + " dad");
				//this.printResult();
				while(it.hasNext()){
					String str2 = it.next();
					//System.out.println(str2+" hou xu");
					offsets = deal(str2);
					ArrayList<ResultFormat> former = copy(resultStack);
					resultStack.clear();
					//System.out.println(offsets.size() + " fffa")
					for(int n:offsets){
						for(ResultFormat rf :former){
							low = rf.getLow();
							high = rf.getHigh();
							if(n<=low){
								low = n;
							}
							if(n >= high){
								high = n;
							}
							rank = high - low;
							resultStack.add(new ResultFormat(rank,i,low,high)); 	
						}     
					}
				}
			}
		}
		System.out.println(resultStack.size());
		Collections.sort(resultStack);
		//System.out.println(resultStack.size());
		this.resultStack = resultStack;
	}
	
	
	public static ArrayList<ResultFormat> copy(ArrayList<ResultFormat> from){
		ArrayList<ResultFormat> result = new ArrayList<ResultFormat>();
		for(ResultFormat rf:from){
			result.add(rf);
		}
		return result;	
	}
	
	
	public ArrayList<Integer> deal(String s){
		s = toTakeShellOff(s);
		String[] off = s.split(",");
		ArrayList<Integer> offset = new ArrayList<Integer>();
		for(String str1:off){
			int im = Integer.parseInt(str1.trim());
			//System.out.println(im);
			offset.add(im);
		}
		return offset;
	}
	
	public void printResult(){
		for(ResultFormat rf:this.resultStack){
			System.out.println(rf.id+" "+rf.low+" "+rf.high);
		}
	}
		
	
	public ArrayList<Integer> charToArray(String s){
		String[] string = s.split(",");
		ArrayList<Integer> offset = new ArrayList<Integer>(); 
		for(int i = 0; i < string.length; i++){
			string[i] = string[i].trim();
			offset.add(i, Integer.parseInt(string[i]));
		}
		return offset;
	}
	
	
	public static String toTakeShellOff(String s){
		return s.substring(1, s.length()-1);
	}
}
