
public class Test03 {
	public static void main(String[] args){
		String s = "curettage	765=[2660, 2665, 1031, 994, 1053]";
		String[] ss = s.split("\\s+", 2);
		for (int i = 0; i < ss.length; i++)
            System.out.println(ss[i]);
    	}

}
