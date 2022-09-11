package components;

import Rixx.GameObject;
import Rixx.KeyListener;
import Rixx.Prefabs;
import Rixx.Window;
import org.jbox2d.dynamics.contacts.Contact;
import org.joml.Vector2f;
import physics2D.components.RigidBody2D;
import physics2D.enums.BodyType;
import util.AssetPool;
import util.RMath;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_L;

public class fireWormAI extends Component{

    private transient GameObject player;
    private transient boolean facingRight;
    private transient RigidBody2D rb;
    private transient float speed = 0.7f;
    private transient Vector2f velocity;
    private transient StateMachine stateMachine;
    private transient float skellyWidth;

    private transient Vector2f attackPos;


    private transient Vector2f diff;


    private transient int health = 500;


    private transient float distanceFromPlayer = 2.4f;



    private transient float timeTillNextAttack = 1f;
    private transient boolean isAttacking = true;
    private transient float attackTimeLeft = 1.6f;
    private transient boolean fired= false;


    private transient boolean isDead =false;
    private transient float deathTimeLeft = 2f;



    @Override
    public void start()
    {
        player = Window.getScene().getGameObjectWith(PlayerController.class);
        rb = this.gameObject.getComponent(RigidBody2D.class);
        stateMachine = this.gameObject.getComponent(StateMachine.class);
        skellyWidth = this.gameObject.transform.scale.x;
        velocity = new Vector2f();
        facingRight = RMath.randomBoolean();




    }



    @Override
    public void update(float dt)
    {

        if(isDead)
        {
            deathTimeLeft -=dt;
            if(deathTimeLeft <= 0)
            {
                this.gameObject.destroy();
            }
            return;
        }





        if(player == null)
        {
            return;
        }

        if(player.getComponent(PlayerController.class).isDead())
        {

            return;
        }

        diff = new Vector2f(this.gameObject.transform.position.x, this.gameObject.transform.position.y).sub(player.transform.position.x, player.transform.position.y);
        if(Math.abs(diff.x) > 7f )
        {
            return;
        }

        timeTillNextAttack -=dt;
        if(isAttacking && attackTimeLeft <= .55f && !fired)
        {
            Vector2f fireballPosition = new Vector2f(this.gameObject.transform.position.x, this.gameObject.transform.position.y);
            if(facingRight)
            {
                fireballPosition.add(-.42f,0.185f);
            }
            else
            {
                fireballPosition.add(.42f,.185f);
            }
            Window.getScene().addGameObjectToScene(Prefabs.generateFireWormFireball(fireballPosition));
            fired = true;
        }
        if(isAttacking && attackTimeLeft <= 0)
        {

            stateMachine.trigger("attackWalk");
            isAttacking = false;
            this.rb.setBodyType(BodyType.Dynamic);
            this.stateMachine.trigger("attackWalk");
            fired = false;
        }
        else if(isAttacking)
        {
            attackTimeLeft -=dt;
            return;
        }




        if(diff.x > .05f)
        {
            this.gameObject.transform.scale.x = -this.skellyWidth;
            facingRight = true;
            stateMachine.trigger("idleWalk");
        }
        else
        {
            this.gameObject.transform.scale.x = this.skellyWidth;
            facingRight = false;
            stateMachine.trigger("idleWalk");
        }

        if(timeTillNextAttack <=0)
        {
            attack();
        }



        if(diff.x > distanceFromPlayer)
        {




            this.rb.setVelocityX(-speed);

        }
        else if(diff.x < -distanceFromPlayer)
        {

            this.rb.setVelocityX(speed);
        }



    }


    public void hit(int amount)
    {
        health -= amount;
        if(health <= 0)
        {
            die();
        }
    }

    @Override
    public void beginCollision(GameObject obj, Contact contact , Vector2f contactNormal)
    {
        if(!isDead && obj.getComponent(PlayerController.class) != null )
        {
            obj.getComponent(PlayerController.class).damage(50);
        }
        else
        {
            contact.setEnabled(false);
        }
    }




    public void die()
    {
        isDead = true;
       if(isAttacking)
       {
           this.stateMachine.trigger("attackDie");
       }
       else
       {
           stateMachine.trigger("die");
       }
        this.rb.setBodyType(BodyType.Static);
        this.rb.setIsSensor();

        if(player != null)
        {
            player.getComponent(PlayerController.class).replenishMana(125);
        }


    }

    public void attack()
    {
        timeTillNextAttack = 5f;
        isAttacking = true;
        attackTimeLeft = 1.6f;
        stateMachine.trigger("idleWalk");
        stateMachine.trigger("walkAttack");
        this.rb.setBodyType(BodyType.Static);



    }

    public void fireball()
    {
        Vector2f fireballPosition = new Vector2f(this.gameObject.transform.position.x, this.gameObject.transform.position.y);
        if(facingRight = true)
        {
            fireballPosition.add(.1f,0);
        }
        else
        {
            fireballPosition.sub(.1f,0);
        }
        Window.getScene().addGameObjectToScene(Prefabs.generateFireWormFireball(fireballPosition));
    }







}
