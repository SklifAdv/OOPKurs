package threads;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import myclass.Counter;
import myclass.CounterOfQueue;
import myclass.VisualClass;

public class Lecturer extends AbstractClass{
	private Counter counter;
	public Lecturer(VisualClass gui, JLabel label, CounterOfQueue queue, JSlider minWorkTimeSlider, int size,int sizemax,
			Counter cnt,JTextField amountinAtr) {
		super(gui, label, queue, minWorkTimeSlider, size, sizemax, amountinAtr);
		this.counter=cnt;
	}

@Override
public void run() {
	 
	while(this.gui.isCreatorWorking() || this.queue.getQue().size()> 0) {
	  synchronized(this.queue) {
		  
		  while((Integer.parseInt(this.counterWork.getText())<this.sizeofstudentmin)||(Integer.parseInt(this.counterWork.getText())<this.sizeofstudentmax && !this.queue.getQue().isEmpty() ) ) {
			  
		  while(this.queue.getQue().size() <= 0) {
              this.display("/resource/lecturer.png");
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
          this.display("/resource/lecturer1.png");
          
         this.queue.notify();
}
      }
		  }



  

  this.display("/resource/lecturer1.png");

 for(int i=0;i<this.std.size();i++) {
	  synchronized (this.std.get(i)) {
		  try {
	        	 
			  this.std.get(i).wait(minWorkTimeSlider.getValue()*1000);
	       	} catch (InterruptedException e) {
	       		// TODO Auto-generated catch block
	       		e.printStackTrace();
	       	}
	  this.std.get(i).place.remove(2);
       this.std.get(i).notify();
       this.counter.setCount(this.counter.getCount()+1);
}
  }
  this.std.clear();
  this.counterWork.setText("0");
  this.counterWork.setForeground(Color.black);
}
}

}
