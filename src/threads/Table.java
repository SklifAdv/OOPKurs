package threads;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import myclass.Counter;
import myclass.CounterOfQueue;
import myclass.VisualClass;
import rnd.Randomable;
import rnd.Uniform;

public class Table extends AbstractClass{
	  private Counter counter;
	  int i ;
	  Randomable rnd= new Uniform(1,3,true);
	    public Table(VisualClass gui, JLabel label, CounterOfQueue queue, JSlider minWorkTimeSlider, Counter counter,int size, int sizemax, JTextField counterTable) {
	        super(gui, label, queue, minWorkTimeSlider,size,sizemax,counterTable);
	        this.counter = counter;
	    }
	
	@Override
	public void run() {
		
		   while(this.gui.isCreatorWorking() || this.queue.getQue().size()> 0){
		synchronized(this.queue) {
			  while((Integer.parseInt(this.counterWork.getText())<this.sizeofstudentmin)||(Integer.parseInt(this.counterWork.getText())<this.sizeofstudentmax && !this.queue.getQue().isEmpty() ) ) {
								
		while(this.queue.getQue().size() <= 0) {
		
	              this.display("/resource/table.png");
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
		          this.display("/resource/table1.png"); 
		          this.queue.notify();

		}}}
		    
	         
	          for(int k=0;k<this.std.size();k++) {
	    	      synchronized (this.std.get(k)) {
	        	   this.std.get(k).place=choose();
		           i= this.std.get(k).getPlace().size();
	               try {
			        	  
	            	   this.std.get(k).wait(minWorkTimeSlider.getValue()*1000);
			         	
	       
	       	         this.counter.setCount(this.counter.getCount()+1);
		           }
		          catch (Exception e) {
					e.printStackTrace();
				}

			      
	           this.display("/resource/table.png"); 
	           this.std.get(k).notify();
	           this.gui.println(this.std.get(k).place.toString());
	   
		   }}
	          this.counterWork.setText("0");   
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
