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
		pages.add(new AreaPage(this,"area"));
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
	        	nowPage = pages.get(theChosenOne);	 
	            break;
	        case 98:
	        case 50:	       
	        	theChosenOne =1;
	        	nowPage = pages.get(theChosenOne);	 
	            break;
	        case 99:
	        case 51:	        	
	        	theChosenOne =2;
	        	nowPage = pages.get(theChosenOne);	 
	            break;
	        case 100:
	        case 52 :	        	
	        	theChosenOne =3;
	        	nowPage = pages.get(theChosenOne);	 
	        	break;	      
	        case 101:
	        case 53:
	        	theChosenOne =4;
	        	nowPage = pages.get(theChosenOne);	 
	        default :
	        	break;	        	
	     }
	    
	       
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
			
			//System.out.println(MainApplet.this.focusBtn);
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
			
			//System.out.println(MainApplet.this.focusBtn);
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
			
			//top circle (index:0)
			this.addBtn(new CircleBtn(MainApplet.this,x,y,radius,""));
			this.getBtn().get(0).setRGB(136, 135, 91);
			
			//below big rect (index:1)
			this.addBtn(new RectBtn(MainApplet.this,x-radius,y + radius + 20,width,height,""));
			this.getBtn().get(1).setRGB(240,133,26);
			
			//item box & input box (white) (index:2~9)
			for(int i =0;i<4;i++){
				this.addBtn(new RectBtn(MainApplet.this,x-radius + 10,y + radius + 20 + 10 + (10+70)*(i), 150,70,"Item" + i));
				this.addBtn(new RectBtn(MainApplet.this,x-radius + 10 + 150 + 10 ,y + radius + 20 + 10 + (10+70)*(i), 400,70,"Input" + i));
				this.getBtn().get(2+i*2).setRGB(135, 86, 118);
				this.getBtn().get(3+i*2).setRGB(135, 86, 118);				
			}
			
			//next box (index:10)
			this.addBtn(new RectBtn(MainApplet.this,x -radius  + 380, y + radius + 20 + height + 10, 200,80,"Next"));
			this.getBtn().get(10).setRGB(196, 70, 173);
							
		}

		@Override
		public void function() {
			// TODO Auto-generated method stub
			AbstractBtn btn = this.getBtn().get(10);
			float btnX = btn.getX();
			float btnY = btn.getY();
			float btnWidth = btn.getWidth();
			float btnHeight = btn.getHeight();
			boolean hasFocus = false;
			if( (MainApplet.this.mouseX > btnX && MainApplet.this.mouseX < btnX + btnWidth) && (MainApplet.this.mouseY > btnY && MainApplet.this.mouseY < btnY + btnHeight)){
				btn.setIsFocus(true);				
				hasFocus = true;
				MainApplet.this.focusBtn = btn;		
			}
			else{
				btn.setIsFocus(false);						
			}
			
			if(!hasFocus){
				MainApplet.this.focusBtn = null;
			}
			
		}
		
	}		
	
	private class AreaPage extends AbstractPage{

		AreaPage(MainApplet parent, String name) {
			super(parent, name);
			setup();
			// TODO Auto-generated constructor stub			
		}

		@Override
		public void setup() {
			// TODO Auto-generated method stub
			float x = 505, y = 80;		
			float width = 190, height = 100;
			
			//top title			
			this.addBtn(new RectBtn(MainApplet.this,x,y,width,height,"Area"));
			this.getBtn().get(0).setRGB(64, 128, 256);
			
			//bellow four area
			this.addBtn(new RectBtn(MainApplet.this, 550,210,100,100,"N"));
			this.addBtn(new RectBtn(MainApplet.this, 495,320,100,100,"W"));
			this.addBtn(new RectBtn(MainApplet.this, 605,320,100,100,"E"));
			this.addBtn(new RectBtn(MainApplet.this, 550,430,100,100,"N"));
			for(int i =1;i<5;i++){
				this.getBtn().get(i).setRGB(256, 128, 64);
			}					
			
			//next box
			this.addBtn(new RectBtn(MainApplet.this,x + width + 50, y +height +3*100 , 150,50,"Commit"));
			this.getBtn().get(5).setRGB(196, 70, 173);
		}

		@Override
		public void function() {
			// TODO Auto-generated method stub
			boolean hasFocus = false;		
			
			for(int i=1;i<6;i++){
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
		}
	}
		
	public void setConnect(){
		AbstractPage p0 = pages.get(0); //main
		AbstractPage p1 = pages.get(1); //choose
		AbstractPage p2 = pages.get(2); //choose
		AbstractPage p3 = pages.get(3); //choose
		AbstractPage p4 = pages.get(4); //report page
		AbstractPage p5 = pages.get(5); //area
		
		//MainPage btn set target
		for(int i =1;i<p0.getBtn().size();i++){
			p0.getBtn().get(i).setTarget(pages.get(i));
			p0.getBtn().get(i).setTarget(pages.get(i));
			p0.getBtn().get(i).setTarget(pages.get(i));
		}
		
		//ChoosePage btn set target
		for(int i=1;i<p1.getBtn().size();i++){
			p1.getBtn().get(i).setTarget(p4);
			p2.getBtn().get(i).setTarget(p4);
			p3.getBtn().get(i).setTarget(p4);
		}
		
		//ReportPage btn set target
		p4.getBtn().get(10).setTarget(p5);
		
		//Area btn set target
		p5.getBtn().get(5).setTarget(p0);
		
		
				
	}
}
