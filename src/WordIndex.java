import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class WordIndex {
	private String s = "";
	private Set<IdAndOffset> indexAll = new HashSet<IdAndOffset>();
	
	public WordIndex(String s){
		this.s = s;
		this.indexAll = toIdAndOffset();
	}
	
	public Set<IdAndOffset> getindexAll(){
		return this.indexAll;
	}
	
	
	public Set<IdAndOffset> toIdAndOffset(){
		
		String[] index = this.s.split("\\|");
		//System.out.println(index.length);
		for(String i:index){
			//System.out.println(i);
			IdAndOffset ind = new IdAndOffset(i.trim());
			indexAll.add(ind);
			//System.out.println(indexAll.size());
		}
		return indexAll;
	}
	
	public String getIndexSetById(int i){
		for(IdAndOffset k :this.indexAll){
			if(k.getId() == i){
				return k.getOffset();
			}
		}
		return null;
	}
	
	
	public Set<Integer> makeIdSet(){
		Set<Integer> IdAll = new HashSet<Integer>();
		for(IdAndOffset ind : indexAll){
			IdAll.add(ind.id);
		}
		return IdAll;
	}
}
