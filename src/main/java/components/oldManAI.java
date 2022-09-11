package components;

import Rixx.Camera;
import Rixx.GameObject;
import Rixx.Window;
import org.jbox2d.dynamics.contacts.Contact;
import org.joml.Vector2f;
import physics2D.Physics2D;
import physics2D.components.CircleCollider;
import physics2D.components.RigidBody2D;

public class oldManAI extends Component{
    private transient boolean onGround = false;
    private transient RigidBody2D rb;
    private transient float walkSpeed = .1f ;
    private transient Vector2f velocity = new Vector2f();
    private transient Vector2f acceleration = new Vector2f();
    private transient Vector2f terminalVelocity = new Vector2f(1.8f, 3.1f);
    private transient boolean goingRight = true;
    private transient StateMachine stateMachine ;
    private transient float xScale ;
    private transient Vector2f start;
    private transient boolean hasSpoken;
    private transient float restLeft = 2.5f;
    private transient float timeSinceRest = 2f;

    @Override
    public void start()
    {
        this.stateMachine = this.gameObject.getComponent(StateMachine.class);
        this.rb = this.gameObject.getComponent(RigidBody2D.class);
        this.acceleration.y = Window.getPhysics().getGravity().y * 0.7f;
        stateMachine.trigger("startWalking");
        xScale = Math.abs(this.gameObject.transform.scale.x);
        start = new Vector2f(this.gameObject.transform.position.x, this.gameObject.transform.position.y);
    }

    @Override
    public void update(float dt)
    {
        if(hasSpoken)
        {

            if(restLeft < 0)
            {
                stateMachine.trigger("idleLeft");
                if(timeSinceRest < 0)
                {
                    goingRight = !goingRight;
                    hasSpoken = false;
                    restLeft = 2.5f;
                    stateMachine.trigger("rewalk");
                    timeSinceRest = 2;
                }
              timeSinceRest -=dt;
            }
            restLeft -= dt;
            return;
        }

        Camera camera = Window.getScene().camera();
        if(this.gameObject.transform.position.x > camera.position.x + camera.getProjectionSize().x * camera.getZoom() + 1f)
        {
            return;
        }

        float xDifferenceFromStart = start.x - this.gameObject.transform.position.x;
        if(Math.abs(xDifferenceFromStart) > 50f)
        {
            this.rb.setVelocity(new Vector2f(0,0));
            this.stateMachine.trigger("stopWalking");
            return;
        }


        if(goingRight)
        {
            velocity.x = walkSpeed;
            this.gameObject.transform.scale.x = xScale;

        }
        else
        {
            velocity.x = -walkSpeed;
            this.gameObject.transform.scale.x = -xScale;
        }

        checkOnGround();


        this.rb.setVelocityX(velocity.x);

    }

    @Override
    public void beginCollision(GameObject obj, Contact contact , Vector2f contactNormal)
    {


        PlayerController playerController = obj.getComponent(PlayerController.class);
        if(playerController != null && !hasSpoken)
        {
            speak();
        }

        if(playerController != null ||obj.getComponent(beardedAI.class) != null || obj.getComponent(hatManAI.class) != null || obj.getComponent(womanAI.class) != null || obj.getComponent(oldManAI.class) != null)
        {
            contact.setEnabled(false);
        }
        else if(Math.abs(contactNormal.y) < 0.1f)
        {
            goingRight = contactNormal.x < 0;
        }


    }

    @Override
    public void preSolve(GameObject obj, Contact contact , Vector2f contactNormal)
    {

        PlayerController playerController = obj.getComponent(PlayerController.class);
        if(playerController != null ||obj.getComponent(beardedAI.class) != null || obj.getComponent(hatManAI.class) != null  || obj.getComponent(womanAI.class) != null || obj.getComponent(oldManAI.class) != null)
        {
            contact.setEnabled(false);
        }
    }

    public void checkOnGround()
    {
        CircleCollider circleCollider  = this.gameObject.getComponent(CircleCollider.class);
        float innerPlayerWidth = .08f;
        float yVal = -.1f;
        onGround  = Physics2D.checkOnGround(this.gameObject, innerPlayerWidth, yVal);
    }

    public void speak()
    {
        this.stateMachine.trigger("stopWalking");


        hasSpoken = true;

    }


}
