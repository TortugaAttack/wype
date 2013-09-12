package it.wype.client;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

//EXPERIMENTAL!
public class WypeAudio{
	
	private TargetDataLine targetLine;
	
	
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
	
	private void closeMicrophone(){
		this.targetLine.flush();
		this.targetLine.stop();
		this.targetLine.close();
		this.targetLine = null;
	}
	
	private byte[] read(){
		byte[] data = new byte[targetLine.getBufferSize()/5];
		this.targetLine.read(data, 0, data.length);
		return data;
	}
}