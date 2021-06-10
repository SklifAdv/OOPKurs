package threads;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import myclass.CounterOfQueue;
import myclass.Student;
import myclass.VisualClass;
import rnd.Randomable;
import rnd.Uniform;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Vector;

import javax.swing.ImageIcon;

public abstract class AbstractClass implements Runnable{
	    protected VisualClass gui;
	    protected JLabel label;
	    protected JSlider minWorkTimeSlider;
	    protected CounterOfQueue queue;
	    protected int sizeofstudentmin;
	    protected int sizeofstudentmax;
	    protected int amountofpictures;
	    protected JTextField counterWork;
		Randomable rnd= new Uniform(1,500,true);
	  Vector<Student> std=new Vector<>();
	    public AbstractClass(VisualClass gui, JLabel label, CounterOfQueue queue, JSlider minWorkTimeSlider,int size,int sizemax, JTextField counterWork) {
	        this.gui = gui;
	        this.label = label;
	        this.queue = queue;
	        this.minWorkTimeSlider = minWorkTimeSlider;
	        this.sizeofstudentmin=size;
	        this.sizeofstudentmax=sizemax;
	        this.counterWork=counterWork;
	    }
	    

	    protected synchronized void display(String pct) {
	        URL u = this.getClass().getResource(pct);
	        ImageIcon image = new ImageIcon(u);
	         resize(image);
	    }



	   public void onOut(Student tr) {
	    }

	   public void onIn(Student tr) {
	    }

	    public Component getComponent() {
	        return this.label;
	    }
	    private synchronized void resize(ImageIcon img) {
	    	  BufferedImage resizedImg = new BufferedImage(this.label.getWidth(), this.label.getHeight(), BufferedImage.TYPE_INT_ARGB);
	    	    Graphics2D g2 = resizedImg.createGraphics();
	    	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    	    g2.drawImage(img.getImage(), 0, 0, this.label.getWidth(), this.label.getHeight(), null);
	    	    g2.dispose();
	    	    img.setImage(resizedImg);
	    	    this.label.setIcon(img);
	    }
	    
}
