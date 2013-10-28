package it.wype.client;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.VideoCapture;
import org.opencv.highgui.Highgui;

public class WypeVideo extends JFrame{

	private static VideoCapture vc;
	
	
	
	public WypeVideo(){
		super();
		addWindowListener(   
			      new java.awt.event.WindowAdapter()   
			      {  
			        public void windowClosing( java.awt.event.WindowEvent e )   
			        {  
			          vc.release();
			          System.exit( 0 );  
			        }  
			      }  
			    );  
		
		JLabel picLabel = new JLabel();
		add(picLabel);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// Webcam test
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		vc = new VideoCapture(1);
		
		
		WypeVideo wv = new WypeVideo();
		wv.setVisible(true);
		
		}
	
	
	
	@Override
	public void paint(Graphics g){
		Mat mat = new Mat();
		vc.read(mat);
	    MatOfByte matOfByte = new MatOfByte();

	    Highgui.imencode(".png", mat, matOfByte); 

	    byte[] byteArray = matOfByte.toArray();
	    BufferedImage bufImage = null;

	    try {

	        InputStream in = new ByteArrayInputStream(byteArray);
	        bufImage = ImageIO.read(in);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    g.drawImage(bufImage, 0, 0, this.getWidth(), this.getHeight(), null);
	    repaint();
	}

}
