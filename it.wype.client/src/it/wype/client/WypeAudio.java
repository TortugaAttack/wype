package it.wype.client;

import it.wype.util.WypeProp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Mixer.Info;
import javax.sound.sampled.TargetDataLine;

//EXPERIMENTAL!
public class WypeAudio{
	
	private TargetDataLine targetLine;
	
	private OutputStream outStream;
	
	private InputStream inStream;

//	TestMain
//	public static void main(String[] argc){
//		WypeAudio wv = new WypeAudio();
//		wv.startMicrophone();
//		wv.capture();
//		wv.closeMicrophone();
//	}
	
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
		
		Info infoMic = null;
		String propInfo = WypeProp.parse("client.properties", "microphone");
		for(Info info : AudioSystem.getMixerInfo()){
			if(info.toString().equals(propInfo)){
				infoMic = info;
				break;
			}
		}
		
		try{
			if(infoMic == null){
				this.targetLine = AudioSystem.getTargetDataLine(format);
			}
			else{
				this.targetLine = AudioSystem.getTargetDataLine(format, infoMic);
			}
			this.targetLine.open();
			this.targetLine.start();
		}
		catch(LineUnavailableException ex){
			throw new RuntimeException(ex);
		}
	}
	
	private void listen(){
		new Listen().run();
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
	
	class Listen extends Thread{
		public void run(){
			Clip cp;
			try {
				Info infoHP = null;
				String propInfo = WypeProp.parse("client.properties", "speaker");
				for(Info info : AudioSystem.getMixerInfo()){
					if(info.toString().equals(propInfo)){
					infoHP = info;
					break;
					}	
				}
				if(infoHP == null){
					cp = AudioSystem.getClip();
				}
				else{
					cp = AudioSystem.getClip(infoHP);
				}
				cp.open(new AudioInputStream(inStream));
			
			} catch (LineUnavailableException | IOException ex) {
				throw new RuntimeException(ex);
			}
		}
	}
	
}
