import java.util.ArrayList;

public abstract class AbstractPage {
	private MainApplet parent;
	private String name;
	private int type;
	private ArrayList<AbstractBtn>buttons;
	
	AbstractPage(MainApplet parent, String name){
		this.parent = parent;
		this.name = name;
		buttons = new ArrayList<AbstractBtn>();
	}	
	
	public abstract void setup();
	
	public void display() {
		// TODO Auto-generated method stub
		ArrayList<AbstractBtn> tmpBtn = this.getBtn();
		int btnSize = tmpBtn.size();
		for(int i=0;i<btnSize;i++){
			tmpBtn.get(i).display();
		}
				
	}
	
	public abstract void function();
	
	public void addBtn(AbstractBtn btn){
		this.buttons.add(btn);
	}
	
	public ArrayList<AbstractBtn> getBtn(){
		return buttons;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setType(int type){
		this.type = type;
	}
	
	public int getType(){
		return type;
 	}
	
	public MainApplet getParent(){
		return parent;
	}
}
