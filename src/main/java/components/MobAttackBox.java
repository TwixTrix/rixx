package components;

import Rixx.GameObject;
import Rixx.Window;
import org.jbox2d.dynamics.contacts.Contact;
import org.joml.Vector2f;

public class MobAttackBox extends  Component{
    private transient float timeLeft;
    private transient int damageAmount;
    private transient boolean hit = false;




    public MobAttackBox(float time, int damageAmount )
    {
        this.timeLeft = time;
        this.damageAmount = damageAmount;


    }

    @Override
    public void start()
    {

    }

    @Override
    public void update(float dt)
    {
        if(hit)
        {
            this.gameObject.destroy();
            return;
        }
        timeLeft -= dt;
        if( timeLeft <= 0)
        {
            this.gameObject.destroy();
        }
    }

    @Override
    public void beginCollision(GameObject obj, Contact contact , Vector2f contactNormal)
    {
        if(hit)
        {
            return;
        }

        if(obj.getComponent(PlayerController.class) != null )
        {
            obj.getComponent(PlayerController.class).damage(damageAmount);
            hit = true;
        }
        else
        {
            contact.setEnabled(false);
        }

    }

    @Override
    public void preSolve(GameObject obj, Contact contact , Vector2f contactNormal)
    {
        contact.setEnabled(false);
    }

}
