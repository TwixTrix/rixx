package components;

import Rixx.Camera;
import Rixx.GameObject;
import Rixx.Prefabs;
import Rixx.Window;
import org.jbox2d.dynamics.contacts.Contact;
import org.joml.Vector2f;
import physics2D.components.RigidBody2D;

public class AttackBugAI extends Component{
    private transient Vector2f velocity = new Vector2f();
    private transient Vector2f terminalVelocity = new Vector2f(2.1f, 3.1f);
    private transient Vector2f targetPos = new Vector2f();
    private transient RigidBody2D rb;
    private transient GameObject player;
    private transient float speed = 1.0f;
    private transient Vector2f spawnLocation = new Vector2f();

    private transient int health  = 200;


    @Override
    public void start()
    {
       player =  Window.getScene().getGameObjectWith(PlayerController.class);
       this.rb = gameObject.getComponent(RigidBody2D.class);
       spawnLocation.set(this.gameObject.transform.position);
    }

    @Override
    public void update(float dt)
    {
        if(health <= 0)
        {
            this.gameObject.destroy();
        }

        Camera camera = Window.getScene().camera();
        if(this.gameObject.transform.position.x > camera.position.x + camera.getProjectionSize().x * camera.getZoom())
        {
            return;
        }

        if(player != null && rb != null)
        {
            targetPos = new Vector2f(player.transform.position.x, player.transform.position.y);
            Vector2f diff = targetPos.sub(gameObject.transform.position);
            if(Math.abs(diff.x) > 6.0f)
            {
                velocity = new Vector2f(0,0);
                return;

            }

            if(diff.x < -0.05f)
            {
                velocity.x  = -speed;
            }
            else if(diff.x > 0.05f)
            {
                velocity.x = speed;
            }
            else
            {
                velocity.x = 0;
            }

            if(diff.y < -0.05f)
            {
                velocity.y = -speed;
            }
            else if(diff.y > 0.05f)
            {
                velocity.y = speed;
            }
            else
            {
                velocity.y =0;
            }

            this.rb.setVelocity(velocity);




        }

    }


    public void beginCollision(GameObject obj, Contact contact , Vector2f contactNormal)
    {
        if (obj.getComponent(PlayerController.class) != null)
        {
            if(obj.getComponent(PlayerController.class).isHurtInvincible() ||obj.getComponent(PlayerController.class).isInvincible())
            {
                return;
            }

            obj.getComponent(PlayerController.class).damage(100);
        }else if(obj.getComponent(Fireball.class) != null)
        {
           health -= 100;
        }
    }

}
