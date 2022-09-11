package components;

import Rixx.GameObject;
import Rixx.Window;
import org.jbox2d.dynamics.contacts.Contact;
import org.joml.Vector2f;
import physics2D.components.RigidBody2D;
import physics2D.enums.BodyType;

public class fireWormFireballAI extends  Component{

    private transient RigidBody2D rb;
    private transient StateMachine stateMachine;
    private transient GameObject player;
    private transient Vector2f playerLocation;
    private transient Vector2f spawnLocation;
    private transient Vector2f pointer = new Vector2f();

    private transient float speed= 2.5f;

    private transient boolean isExploding =false;
    private transient float explosionTimeLeft = .9f;

    private transient float timeLeft = 7f;

    public fireWormFireballAI(Vector2f location)
    {
     this.spawnLocation = location;

    }






    @Override
    public void start()
    {
        this.rb = this.gameObject.getComponent(RigidBody2D.class);
        this.stateMachine = this.gameObject.getComponent(StateMachine.class);
        this.player = Window.getScene().getGameObjectWith(PlayerController.class);

        this.gameObject.transform.position.set(spawnLocation);

        if(player != null)
        {
            playerLocation = new Vector2f(player.transform.position.x, player.transform.position.y + .3f);
            pointer = playerLocation.sub(spawnLocation);
            pointer.normalize();
            pointer.mul(speed);
        }






    }


    @Override
    public void update(float dt)
    {
         timeLeft -=dt;
        if(player == null)
        {
            this.gameObject.destroy();
        }

        if((isExploding && explosionTimeLeft <= 0) || timeLeft <=0)
        {
            this.gameObject.destroy();
        }
        else if(isExploding)
        {

            explosionTimeLeft -=dt;
        }




        this.rb.setVelocity(pointer);
    }

    @Override
    public void beginCollision(GameObject obj, Contact contact , Vector2f contactNormal)
    {
        if( !isExploding && obj.getComponent(PlayerController.class) != null)
        {
            obj.getComponent(PlayerController.class).damage(150);
            explode();
        }
        else if(obj.getComponent(Ground.class) != null)
        {
            explode();
        }
        else if(obj.getComponent(fireWormAI.class) != null || obj.getComponent(fireWormFireballAI.class) != null)
        {
           contact.setEnabled(false);
        }
        else if(obj.getComponent(playerAttackBox.class) != null )
        {
            this.gameObject.destroy();
        }
        else
        {
         contact.setEnabled(false);
        }
    }

    public void explode()
    {
        this.rb.setBodyType(BodyType.Static);
        this.stateMachine.trigger("explode");
        isExploding = true;
    }



}
