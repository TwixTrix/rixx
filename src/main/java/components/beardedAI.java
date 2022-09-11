package components;

import Rixx.Camera;
import Rixx.GameObject;
import Rixx.Window;
import org.jbox2d.dynamics.contacts.Contact;
import org.joml.Vector2f;
import physics2D.Physics2D;
import physics2D.components.RigidBody2D;

public class beardedAI extends Component {

    private transient boolean onGround = false;
    private transient RigidBody2D rb;
    private transient float walkSpeed = 0.4f ;
    private transient Vector2f velocity = new Vector2f();
    private transient Vector2f acceleration = new Vector2f();
    private transient Vector2f terminalVelocity = new Vector2f(3f, 3.1f);
    private transient boolean goingRight = false;
    private transient StateMachine stateMachine ;
    private transient float xScale ;
    private transient Vector2f start;

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
        Camera camera = Window.getScene().camera();
        if(this.gameObject.transform.position.x > camera.position.x + camera.getProjectionSize().x * camera.getZoom() + 10f)
        {
            return;
        }

        float xDifferenceFromStart = start.x - this.gameObject.transform.position.x;
        if(Math.abs(xDifferenceFromStart) > 20f)
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
        if(playerController != null ||obj.getComponent(beardedAI.class) != null || obj.getComponent(hatManAI.class) != null || obj.getComponent(womanAI.class) != null || obj.getComponent(oldManAI.class) != null)
        {
            contact.setEnabled(false);
        }
    }

    public void checkOnGround()
    {
        float innerPlayerWidth = 0.25f * 0.7f;
        float yVal = -0.14f;
        onGround  = Physics2D.checkOnGround(this.gameObject, innerPlayerWidth, yVal);
    }

}
