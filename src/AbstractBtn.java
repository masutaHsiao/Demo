import java.util.ArrayList;

public abstract class AbstractBtn{
	private float x,y;
	private float width, height,radius;	
	private String btnName;
	private AbstractPage target;
	private MainApplet parent;
	
	private ArrayList<Integer> RGB;
	
	private boolean isFocus;
	
	AbstractBtn(MainApplet parent, float x, float y, float width, float height, String name){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.btnName = name;
		this.parent = parent;
		this.isFocus = false;
		this.target = null;
		RGB = new ArrayList<Integer>();
		RGB.add(255);
		RGB.add(255);
		RGB.add(255);
	}		
	
	AbstractBtn(MainApplet parent, float x, float y, float radius, String name){
		this.x = x;
		this.y = y;
		this.height = 2*radius;
		this.width = 2*radius;
		this.radius = radius;		
		this.btnName = name;
		this.parent = parent;
		this.isFocus = false;
		RGB = new ArrayList<Integer>();
		RGB.add(255);
		RGB.add(255);
		RGB.add(255);
	}
	
	abstract public void display();
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public float getWidth(){
		return width;
	}
	
	public float getHeight(){
		return height;
	}
	
	public float getRadius(){
		return radius;
	}
	
	public MainApplet getParent(){
		return parent;
	}
	
	public void setIsFocus(boolean b){
		this.isFocus = b;
	}
	
	public boolean getIsFocus(){
		return this.isFocus;
	}
	
	public void setRGB(int red, int green, int blue){
		this.RGB.set(0, red);
		this.RGB.set(1, green);
		this.RGB.set(2, blue);
	}
	
	public ArrayList<Integer> getRGB(){
		return RGB;
	}
	
	public void setName(String name){
		this.btnName = name;
	}
	
	
	public String getName(){
		return this.btnName;
	}	
	
	
	public AbstractPage getTarget(){
		return target;
	}
	
	public void setTarget(AbstractPage target){
		this.target = target;
	}
	
}
