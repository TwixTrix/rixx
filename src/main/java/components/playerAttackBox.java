package components;

import Rixx.GameObject;
import org.jbox2d.dynamics.contacts.Contact;
import org.joml.Vector2f;

public class playerAttackBox extends Component{
    private transient float timeLeft;
    private transient int damageAmount;
    private transient boolean hitTarget = false;




    public playerAttackBox(float time, int damageAmount )
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
        if(hitTarget)
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
        if(hitTarget)
        {
            return;
        }

        if(obj.getComponent(EyeFlyAI.class) != null )
        {
            obj.getComponent(EyeFlyAI.class).hit(damageAmount);
            hitTarget = true;
        }
        else if(obj.getComponent(Skelly2AI.class) != null)
        {
            obj.getComponent(Skelly2AI.class).hit(damageAmount);
            hitTarget = true;

        }
        else if(obj.getComponent(fireWormAI.class) != null)
        {
            obj.getComponent(fireWormAI.class).hit(damageAmount);
            hitTarget = true;

        }
        else if(obj.getComponent(gluttonyAI.class) != null)
        {
            obj.getComponent(gluttonyAI.class).hit(damageAmount);
            hitTarget = true;


        }
        else if(obj.getComponent(SkellyAI.class) != null)
        {
            obj.getComponent(SkellyAI.class).damaged(damageAmount);
            hitTarget = true;


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
