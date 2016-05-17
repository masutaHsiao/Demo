import java.util.ArrayList;

public class Page extends AbstractPage {
	
	Page(MainApplet parent,String name){
		super(parent);
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		ArrayList<AbstractBtn> tmpBtn = this.getBtn();
		int btnSize = tmpBtn.size();
		for(int i=0;i<btnSize;i++){
			tmpBtn.get(i).display();
		}
		
	}

}
