import java.awt.event.KeyEvent;
import java.util.ArrayList;

import processing.core.PApplet;

@SuppressWarnings("serial")
public class MainApplet extends PApplet {	
	private ArrayList<AbstractPage> pages = new ArrayList<AbstractPage>();
	private int theChosenOne;
	private AbstractPage prePage;
	private AbstractPage nowPage;
	private AbstractBtn focusBtn;
	
	public void setup(){	
		theChosenOne = 0;
		pages.add(new MainPage(this,"MainPage"));	
		pages.add(new ChoosePage(this,"btn0"));
		pages.add(new ChoosePage(this,"btn1"));
		pages.add(new ChoosePage(this,"btn2"));
		pages.add(new ReportPage(this,"report"));
		nowPage = pages.get(theChosenOne);
		
		setConnect();
	}
	
	public void draw(){
		background(255);		
		
		nowPage.display();
		nowPage.function();					
		
	}
	
	public void mouseReleased(){
		if(focusBtn!=null){
			if(focusBtn.getTarget()!=null){
			System.out.println("triggered");
			prePage = nowPage;
			nowPage = focusBtn.getTarget();			
			}
		}		
	}
	
	public void keyPressed(KeyEvent e) {
		//判斷按下的鍵盤紐。根據各個狀況設定顯示字以及選取的影集。並且清除網路中的角色
	    int keyCode = e.getKeyCode();
	    switch(keyCode) {
	    	case 97:	
	        case 49:	        	
	        	theChosenOne =0;	        	
	            break;
	        case 98:
	        case 50:	       
	        	theChosenOne =1;	        	
	            break;
	        case 99:
	        case 51:	        	
	        	theChosenOne =2;
	            break;
	        case 100:
	        case 52 :	        	
	        	theChosenOne =3;
	        	break;	      
	        case 101:
	        case 53:
	        	theChosenOne =4;
	        default :
	        	break;
	     }
	    nowPage = pages.get(theChosenOne);	    
   	} 
	
	private class MainPage extends AbstractPage{

		MainPage(MainApplet parent, String name) {
			super(parent,name);
			// TODO Auto-generated constructor stub
			setup();
		}
		
		@Override
		public void setup() {
			// TODO Auto-generated method stub
			float width = 600, height = 300;
			float x = 300, y = 100;				
			ArrayList<AbstractBtn> thisBtn = this.getBtn();
					
			//Add top button
			this.addBtn(new RectBtn(MainApplet.this,x,y,width,height,"Intro"));
			
			//Add below 3 buttons
			for(int i=0;i<3;i++){
				this.addBtn(new RectBtn(this.getParent(), x + i * (15 + (width-30) /3), y+height + 10, (width-30) /3, height/3, "btn" + i));			
			}
			
			//set all buttons color
			int tmpInt = this.getBtn().size();
			for(int i=0;i<tmpInt;i++){
				thisBtn.get(i).setRGB(33,114,194);
			}							
		}
			
		@Override
		public void function() {
			// TODO Auto-generated method stub
			int btnNum = this.getBtn().size();
			boolean hasFocus = false;		
		
			for(int i=1;i<btnNum;i++){
				AbstractBtn tmpBtn = this.getBtn().get(i);
				float btnX = tmpBtn.getX();
				float btnY = tmpBtn.getY();
				float btnWidth = tmpBtn.getWidth();
				float btnHeight = tmpBtn.getHeight();
				if( (MainApplet.this.mouseX > btnX && MainApplet.this.mouseX < btnX + btnWidth) && (MainApplet.this.mouseY > btnY && MainApplet.this.mouseY < btnY + btnHeight)){
					tmpBtn.setIsFocus(true);				
					hasFocus = true;
					MainApplet.this.focusBtn = tmpBtn;		
				}
				else{
					tmpBtn.setIsFocus(false);
				}				
				
			}
			
			if(!hasFocus){
				MainApplet.this.focusBtn = null;
			}
			
			System.out.println(MainApplet.this.focusBtn);
			if(MainApplet.this.focusBtn!=null)
			System.out.println(MainApplet.this.focusBtn.getTarget());
		}
		
	}
	
	private class ChoosePage extends AbstractPage{

		ChoosePage(MainApplet parent, String name) {
			super(parent, name);
			setup();
			// TODO Auto-generated constructor stub
		}

		@Override
		public void setup() {
			// TODO Auto-generated method stub
			float x = 505, y = 80;		
			float width = 190, height = 100, radius = 40;							
										
			//set top button
			this.addBtn(new RectBtn(MainApplet.this, x , y, width, height, getName() ));
			this.getBtn().get(0).setRGB(138,131,228);
			
			//set below circle buttons 
			for(int i=0;i<16;i++){
				this.addBtn(new CircleBtn(MainApplet.this,x + 190/5 + 190*3/5 * (int) (i%4-1) , y + height + 30 + radius +  (20 + 2 * radius) *(int) (i/4), radius, Character.toString((char)(65+i))));
				this.getBtn().get(i+1).setRGB(238,121,198);
			}				
				
			
		}

		@Override
		public void function() {
			// TODO Auto-generated method stub			
			int btnNum = this.getBtn().size();
			boolean hasFocus=false;
				
			for(int i=1;i<btnNum;i++){
				AbstractBtn tmpBtn = this.getBtn().get(i); 
				float x = tmpBtn.getX();
				float y = tmpBtn.getY();
				float radius = tmpBtn.getWidth()/2;
				if(PApplet.dist(MainApplet.this.mouseX,MainApplet.this.mouseY,x,y) < radius){
					tmpBtn.setIsFocus(true);
					hasFocus = true;
					MainApplet.this.focusBtn = tmpBtn;				
				}
				else{
					tmpBtn.setIsFocus(false);
				}
			}
			
			if(!hasFocus){
				MainApplet.this.focusBtn = null;
			}
			
			System.out.println(MainApplet.this.focusBtn);
		}
		
	}	
	
	private class ReportPage extends AbstractPage{

		ReportPage(MainApplet parent, String name) {
			super(parent, name);
			setup();
			// TODO Auto-generated constructor stub
		}

		@Override
		public void setup() {
			// TODO Auto-generated method stub
			float x = 350, y = 130;
			float width = 580, height = 330, radius = 40;							
			
			//top circle
			this.addBtn(new CircleBtn(MainApplet.this,x,y,radius,""));
			this.getBtn().get(0).setRGB(136, 135, 91);
			
			//below big rect
			this.addBtn(new RectBtn(MainApplet.this,x-radius,y + radius + 40,width,height,""));
			this.getBtn().get(1).setRGB(115, 207, 215);
							
		}

		@Override
		public void function() {
			// TODO Auto-generated method stub
			
		}
		
	}		
	
	public void setConnect(){
		AbstractPage p0 = pages.get(0);
		AbstractPage p1 = pages.get(1);
		AbstractPage p2 = pages.get(2);
		//p1Btn set target
		p0.getBtn().get(1).setTarget(pages.get(1));
		p0.getBtn().get(2).setTarget(pages.get(2));
		p0.getBtn().get(3).setTarget(pages.get(3));
				
	}
}
