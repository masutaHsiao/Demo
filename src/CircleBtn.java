
public class CircleBtn extends AbstractBtn {

	CircleBtn(MainApplet parent, float x, float y, float radius, String name) {
		super(parent, x, y, radius, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		MainApplet tmpParent = getParent();
		float fontSize = this.getRadius() /  this.getName().length();
				
		if(this.getIsFocus()){
			tmpParent.fill(255-this.getRGB().get(0),255-this.getRGB().get(1),255-this.getRGB().get(2));			
			tmpParent.stroke(255-this.getRGB().get(0),255-this.getRGB().get(1),255-this.getRGB().get(2));			
		}
		else{
			tmpParent.fill(this.getRGB().get(0),this.getRGB().get(1),this.getRGB().get(2));
			tmpParent.stroke(this.getRGB().get(0),this.getRGB().get(1),this.getRGB().get(2));
		}
		
		tmpParent.ellipse(getX(), getY(), getWidth(), getWidth());
		
		tmpParent.fill(255);
		tmpParent.textSize(fontSize);
		tmpParent.text(this.getName(), this.getX() - tmpParent.textWidth(this.getName()) /2 , this.getY() + fontSize/3);
	}

}
