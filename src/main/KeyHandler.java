package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.*;

public class KeyHandler implements KeyListener 
{

private boolean upPressed, downPressed, leftPressed, rightPressed;
private boolean enterPressed;
private boolean shotKeyPressed;

    @Override
    public void keyTyped(KeyEvent e) 
    {
        
    }

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
            case KeyEvent.VK_ENTER:
                enterPressed = true;
                break;
            case KeyEvent.VK_F:
                shotKeyPressed = true; 
                break;   

        }
    }

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
            //delete
            case KeyEvent.VK_ENTER:
                enterPressed = false;
                break;
            case KeyEvent.VK_F:
                shotKeyPressed = false; 
                break;
        }
    }

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

//delete
//ENTER KEY
public void setEnterPressed(boolean enterPressed) 
{
  this.enterPressed = enterPressed;
}
public boolean getEnterPressed()
{
    return enterPressed;
}

public void setShotKeyPressed(boolean shotKeyPressed)
{
    this.shotKeyPressed = shotKeyPressed;
}
public boolean getShotKeyPressed()
{
    return shotKeyPressed;
}

    
}
