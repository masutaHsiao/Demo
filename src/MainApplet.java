import java.awt.event.KeyEvent;
import java.util.ArrayList;

import processing.core.PApplet;

@SuppressWarnings("serial")
public class MainApplet extends PApplet {	
	private ArrayList<Page> pages = new ArrayList<Page>();
	private int theChosenOne;
	private Page prePage;
	private Page nowPage;
	private AbstractBtn focusBtn;
	
	public void setup(){	
		theChosenOne = 0;
		pages.add(p0Setup());	
		pages.add(p1Setup("btn0"));
		pages.add(p1Setup("btn1"));
		pages.add(p1Setup("btn2"));
		pages.add(p2Setup("problem"));
		nowPage = (Page)pages.get(theChosenOne);
		
		setConnect();
	}
	
	public void draw(){
		background(255);
		
		
		nowPage.display();
		
		switch(nowPage.getType()){
			case 0:
				p0Fun();
				break;
			case 1:
				p1Fun();
				break;
			case 2:
				p2Fun();
			default:
				break;
		}
		
		
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
	    nowPage = (Page)pages.get(theChosenOne);	    
   	} 
	
	public Page p0Setup(){
		int width = 600, height = 300;
		float x = 300, y = 100;		
		Page tmp = new Page(this,"MainPage");
		ArrayList<AbstractBtn> tmpBtn = tmp.getBtn();
		
		//set page type to 0(using function 0)
		tmp.setType(0);
		
		//Add top button
		tmp.addBtn(new RectBtn(this,x,y,width,height,"Intro"));
		
		//Add below 3 buttons
		for(int i=0;i<3;i++){
			tmp.addBtn(new RectBtn(this, x + i * (15 + (width-30) /3), y+height + 10, (width-30) /3, height/3, "btn" + i));			
		}
		
		//set all buttons color
		int tmpInt = tmp.getBtn().size();
		for(int i=0;i<tmpInt;i++){
			tmpBtn.get(i).setRGB(33,114,194);
		}							
		
		return tmp;
	}		
	
	public void p0Fun(){
		Page tmp = nowPage;
		int size = tmp.getBtn().size();
		boolean hasFocus = false;		
	
		for(int i=1;i<size;i++){
			AbstractBtn tmpBtn = tmp.getBtn().get(i);
			float btnX = tmpBtn.getX();
			float btnY = tmpBtn.getY();
			float btnWidth = tmpBtn.getWidth();
			float btnHeight = tmpBtn.getHeight();
			if( (this.mouseX > btnX && this.mouseX < btnX + btnWidth) && (this.mouseY > btnY && this.mouseY < btnY + btnHeight)){
				tmpBtn.setIsFocus(true);				
				hasFocus = true;
				this.focusBtn = tmpBtn;		
			}
			else{
				tmpBtn.setIsFocus(false);
			}				
			
		}
		
		if(!hasFocus){
			this.focusBtn = null;
		}
		
		System.out.println(this.focusBtn);
		if(this.focusBtn!=null)
		System.out.println(this.focusBtn.getTarget());
	}
	
	public Page p1Setup(String name){
		float x = 505, y = 80;		
		float width = 190, height = 100, radius = 40;			
		Page tmp = new Page(this, name);		
		
		//set page type to 1 (using function 1)
		tmp.setType(1);
		
		//set top button
		tmp.addBtn(new RectBtn(this, x , y, width, height, name ));
		tmp.getBtn().get(0).setRGB(138,131,228);
		
		//set below circle buttons 
		for(int i=0;i<16;i++){
			tmp.addBtn(new CircleBtn(this,x + 190/5 + 190*3/5 * (int) (i%4-1) , y + height + 30 + radius +  (20 + 2 * radius) *(int) (i/4), radius, Character.toString((char)(65+i))));
			tmp.getBtn().get(i+1).setRGB(238,121,198);
		}				
			
		return tmp;
	}
	
	public void p1Fun(){
		Page tmp = nowPage;
		int size = tmp.getBtn().size();
		boolean hasFocus=false;
			
		for(int i=1;i<size;i++){
			AbstractBtn tmpBtn = tmp.getBtn().get(i); 
			float x = tmpBtn.getX();
			float y = tmpBtn.getY();
			float radius = tmpBtn.getWidth()/2;
			if(PApplet.dist(this.mouseX,this.mouseY,x,y) < radius){
				tmpBtn.setIsFocus(true);
				hasFocus = true;
				this.focusBtn = tmpBtn;				
			}
			else{
				tmpBtn.setIsFocus(false);
			}
		}
		
		if(!hasFocus){
			this.focusBtn = null;
		}
		
		System.out.println(this.focusBtn);
	}
	
	public Page p2Setup(String name){
		float x = 350, y = 130;
		float width = 580, height = 330, radius = 40;			
		Page tmp = new Page(this,name);		
		
		//set page type to 2
		tmp.setType(2);
		
		//top circle
		tmp.addBtn(new CircleBtn(this,x,y,radius,""));
		tmp.getBtn().get(0).setRGB(136, 135, 91);
		
		//below big rect
		tmp.addBtn(new RectBtn(this,x-radius,y + radius + 40,width,height,""));
		tmp.getBtn().get(1).setRGB(115, 207, 215);
		
		
		return tmp;
	}
	
	public void p2Fun(){
		
	}
	
	public void setConnect(){
		Page p0 = pages.get(0);
		Page p1 = pages.get(1);
		Page p2 = pages.get(2);
		//p1Btn set target
		p0.getBtn().get(1).setTarget(pages.get(1));
		p0.getBtn().get(2).setTarget(pages.get(2));
		p0.getBtn().get(3).setTarget(pages.get(3));
				
	}
}
