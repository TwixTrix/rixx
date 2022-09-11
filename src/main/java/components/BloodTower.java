package components;

import Rixx.GameObject;
import org.jbox2d.dynamics.contacts.Contact;
import org.joml.Vector2f;

public class BloodTower extends  Component{


    @Override
    public void beginCollision(GameObject collidingObject , Contact contact, Vector2f contactNormal)
    {
        PlayerController playerController = collidingObject.getComponent(PlayerController.class);
        if(playerController != null)
        {
            playerController.playWinAnimation(this.gameObject);
        }

    }

}
