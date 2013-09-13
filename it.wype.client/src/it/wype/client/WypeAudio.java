package it.wype.client;

import java.io.IOException;
import java.io.OutputStream;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

//EXPERIMENTAL!
public class WypeAudio{
	
	private TargetDataLine targetLine;
	
	private OutputStream outStream;
	
	
	private void startMicrophone(){
		
		float sampleRate = 44100f;
		int sampleSize = 8;
		int channels = 2;
		boolean signed = true;
		boolean bigEndian = false;
		
		AudioFormat format = new AudioFormat(
				sampleRate,
				sampleSize,
				channels,
				signed,
				bigEndian
				);
		
		try{
			this.targetLine = AudioSystem.getTargetDataLine(format);
			this.targetLine.open();
			this.targetLine.start();
		}
		catch(LineUnavailableException ex){
			throw new RuntimeException(ex);
		}
	}
	
	private void capture(){
		new Capture().start();
	}
	
	private void closeMicrophone(){
		this.targetLine.flush();
		this.targetLine.stop();
		this.targetLine.close();
		this.targetLine = null;
	}
	
	class Capture extends Thread{
		public void run(){
			try {
				AudioSystem.write(
						new AudioInputStream(targetLine),
						AudioFileFormat.Type.WAVE,
						outStream
						);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
	}
	
}