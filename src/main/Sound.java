package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**Class for the sound of the game 
 * 
 * @author Ishan Voleti
 * @author Samarth Vysyraju
 */
public class Sound 
{
  /** used to open audio file */
  Clip clip;
  /** array to hold all the sounds  */
  URL soundURL[] = new URL[30];

  /** constructor for the sound class which sets index 0 to a sound */
  public Sound()
  {
    soundURL[0] = getClass().getResource("/res//sound/BlueBoyAdventure.wav");
  }

  /** sets the file for the sound
   * 
   * @param i
   */
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
  //* plays the sound */
  public void play()
  {
    clip.start();
  }
  /** loops the sound */
  public void loop()
  {
    clip.loop(Clip.LOOP_CONTINUOUSLY);
  }
  /** stops the sound */
  public void stop()
  {
    clip.stop();
  }
  
}
