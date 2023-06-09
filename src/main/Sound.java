package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * 
 */
public class Sound 
{
  //used to open audio file
  Clip clip;
  //used to hold all the sounds
  URL soundURL[] = new URL[30];

  public Sound()
  {
    soundURL[0] = getClass().getResource("/res//sound/BlueBoyAdventure.wav");
  }

  public void setFile(int i)
  {
    try
    {
      AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
      clip = AudioSystem.getClip();
      clip.open(ais);
    }
    catch(Exception e)
    {
      
    }

  }
  public void play()
  {
    clip.start();
  }
  public void loop()
  {
    clip.loop(Clip.LOOP_CONTINUOUSLY);
  }
  public void stop()
  {
    clip.stop();
  }
  
}
