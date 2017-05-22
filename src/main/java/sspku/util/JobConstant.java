package sspku.util;

public class JobConstant {
	
	public static final String defaultOrder="createTime desc";
	
	public static final int defaultCount=100;
	
	public static final double thresholdSearch=2.0;
	
	public enum JobState {  
	    HIRING("招聘中", 1), CLOSED("招聘已结束", 0);
	    private String name;  
	    private int index;  
	    private JobState(String name, int index) {  
	        this.name = name;  
	        this.index = index;  
	    } 
	    public String getName() {
			return name;
		}
		public int getIndex() {
			return index;
		}
		//覆盖方法  
	    @Override  
	    public String toString() {  
	        return this.index+"_"+this.name;  
	    }  
	}
	
	public enum WorkType {  
	    FullTime("全职工作", 1), PartTime("兼职工作", 2);
	    private String name;  
	    private int index;  
	    private WorkType(String name, int index) {  
	        this.name = name;  
	        this.index = index;  
	    } 
	    public String getName() {
			return name;
		}
		public int getIndex() {
			return index;
		}
		//覆盖方法  
	    @Override  
	    public String toString() {  
	        return this.index+"_"+this.name;  
	    }  
	}
	
	
}
