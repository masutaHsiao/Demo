import java.util.ArrayList;

public abstract class AbstractPage {
	private MainApplet parent;
	private String name;
	private int type;
	private ArrayList<AbstractBtn>buttons;
	
	AbstractPage(MainApplet parent){
		this.parent = parent;
		buttons = new ArrayList<AbstractBtn>();
	}	
	
	public abstract void display();
	
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
}
