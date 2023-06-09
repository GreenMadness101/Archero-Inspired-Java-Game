package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/** KeyHandler class for when keys for movement are pressed and released
 * 
 * @author Samarth
 * @author Ishan 
 */
public class KeyHandler implements KeyListener 
{
/** boolean fields for if the key is pressed for movement  */
private boolean upPressed, downPressed, leftPressed, rightPressed;


    @Override
    public void keyTyped(KeyEvent e) 
    {
        
    }

    /**sets the fields true if the key corresponding to the movements is pressed
     *  
     * @param e   gets what key is clicked
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch(code)
        {
            case KeyEvent.VK_W:
                upPressed = true;
                break;
            case KeyEvent.VK_A:
                leftPressed = true;
                break;
            case KeyEvent.VK_S:
                downPressed = true;
                break;
            case KeyEvent.VK_D:
                rightPressed = true;
                break;
            
            // case KeyEvent.VK_F:
            //     shotKeyPressed = true; 
            //     break;   

        }
    }

    /**sets the fields false if the key corresponding to the movements is released
     *  
     * @param e   gets what key is clicked
     */
    @Override
    public void keyReleased(KeyEvent e) 
    {
        int code = e.getKeyCode();

        switch(code)
        {
            case KeyEvent.VK_W:
                upPressed = false;
                break;
            case KeyEvent.VK_A:
                leftPressed = false;
                break;
            case KeyEvent.VK_S:
                downPressed = false;
                break;
            case KeyEvent.VK_D:
                rightPressed = false;
                break;
           

        }
    }

//GETTERS AND SETTERS
public boolean getUpPressed()
{
    return upPressed;
}
public boolean getDownPressed()
{
    return downPressed;
}
public boolean getLeftPressed()
{
    return leftPressed;
}
public boolean getRightPressed()
{
    return rightPressed;
}
public void setDownPressed(boolean downPressed) 
{
    this.downPressed = downPressed;
}
public void setLeftPressed(boolean leftPressed) 
{
    this.leftPressed = leftPressed;
}
public void setRightPressed(boolean rightPressed) 
{
    this.rightPressed = rightPressed;
}
public void setUpPressed(boolean upPressed) 
{
    this.upPressed = upPressed;
}



// public void setShotKeyPressed(boolean shotKeyPressed)
// {
//     this.shotKeyPressed = shotKeyPressed;
// }
public boolean getShotKeyPressed()
{
    if(!downPressed && !upPressed && !leftPressed && !rightPressed)
    {
        return true;
    }
    return false;
}

    
}
