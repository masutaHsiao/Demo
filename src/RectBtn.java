
public class RectBtn extends AbstractBtn {

	RectBtn(MainApplet parent, float x, float y, float width, float height, String name) {
		super(parent, x, y, width, height, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		MainApplet tmpParent = getParent();
		float fontSize = this.getWidth()/this.getName().length() > this.getHeight()/2 ? this.getHeight()/2 : this.getWidth()/this.getName().length();
		
		if(this.getIsFocus()){
			tmpParent.stroke(255-getRGB().get(0),255-getRGB().get(1),255-getRGB().get(2));
			tmpParent.fill(255-getRGB().get(0),255-getRGB().get(1),255-getRGB().get(2));
		}
		else{
		tmpParent.stroke(getRGB().get(0),getRGB().get(1),getRGB().get(2));
		tmpParent.fill(getRGB().get(0),getRGB().get(1),getRGB().get(2));
		}
		tmpParent.rect(this.getX(),this.getY(), this.getWidth(), this.getHeight(),5);
		
		tmpParent.textSize(fontSize);
		tmpParent.fill(255);		
		tmpParent.text(this.getName(), this.getX() + (this.getWidth() -tmpParent.textWidth(this.getName()))/2  , this.getY() + this.getHeight()*2/3);		
	}

}
