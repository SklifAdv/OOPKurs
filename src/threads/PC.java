package threads;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import myclass.Counter;
import myclass.CounterOfQueue;
import myclass.VisualClass;

public class PC extends AbstractClass {
	private Counter counter;
int i ;
	public PC(VisualClass gui, JLabel label, CounterOfQueue queue, JSlider minWorkTimeSlider, int size, int sizemax,
			Counter cnt, JTextField counterWork) {
		super(gui, label, queue, minWorkTimeSlider, size, sizemax, counterWork);
		this.counter=cnt;
	}

	@Override
	public void run() {
		  while(this.gui.isCreatorWorking() || this.queue.getQue().size()> 0) {
		  synchronized(this.queue) {
			  while((Integer.parseInt(this.counterWork.getText())<this.sizeofstudentmin)||(Integer.parseInt(this.counterWork.getText())<this.sizeofstudentmax && !this.queue.getQue().isEmpty() ) ) {
					    while(this.queue.getQue().size() <= 0) {
	              this.display("/resource/pc.png");
	              try {
	                  this.queue.wait();
	              } catch (InterruptedException var4) {
	                  var4.printStackTrace();
	              }
	          }
	    	  if(!this.queue.getQue().isEmpty()) {
	          
	          this.std.add(this.queue.deleteFromQueue());
	          this.counterWork.setText(String.valueOf((Integer.parseInt(this.counterWork.getText())+1)));
	             if((Integer.parseInt(this.counterWork.getText()))>=this.sizeofstudentmin && (Integer.parseInt(this.counterWork.getText()))<this.sizeofstudentmax) {
	            	 this.counterWork.setForeground(Color.ORANGE);
	             }else if((Integer.parseInt(this.counterWork.getText()))==this.sizeofstudentmax) {
	            	 this.counterWork.setForeground(Color.RED);
	             }
	             this.display("/resource/pc1.png");
	             
	        }
	    	  this.queue.notify(); 
	
	      }
		  }
	       

	 
	 for(int k=0;k<this.std.size();k++) {
	      synchronized (this.std.get(k)) {
   	   this.std.get(k).place=choose();
          i= this.std.get(k).getPlace().size();
          try {this.std.get(k).wait(minWorkTimeSlider.getValue()*1000);
  
  	         this.counter.setCount(this.counter.getCount()+1);
  	       this.counterWork.setText(String.valueOf((Integer.parseInt(this.counterWork.getText())-1)));
          }
         catch (Exception e) {
			e.printStackTrace();
		}

	      
          this.display("/resource/pc.png"); 
      this.std.get(k).notify();
      this.gui.println(this.std.get(k).place.toString());

  }}
     //this.amountinAtr.setText("0");   
	  this.counterWork.setForeground(Color.black);
			  this.std.clear();
}
}	 

ArrayList<Integer> choose() {

this.i=(int)rnd.next();

try {
	Thread.sleep((long)100);
	
	ArrayList<Integer> arr=new ArrayList<>();
	for (int j=0;j<this.i;j++) {
		int a = (int)rnd.next();
		arr.add(a);}
	return arr;
} catch (InterruptedException e) {
	e.printStackTrace();
	return null;
}



}	
	}


