package components;

import Rixx.Camera;
import Rixx.GameObject;
import Rixx.KeyListener;
import Rixx.Window;
import org.jbox2d.dynamics.contacts.Contact;
import org.joml.Vector2f;
import physics2D.components.RigidBody2D;
import util.AssetPool;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_L;

public class EyeFlyAI extends  Component{

    private transient Vector2f velocity = new Vector2f();
    private transient Vector2f terminalVelocity = new Vector2f(2.1f, 3.1f);
    private transient Vector2f targetPos = new Vector2f();
    private transient RigidBody2D rb;
    private transient GameObject player;
    private transient float speed = 1.0f;
    private transient Vector2f spawnLocation = new Vector2f();
    private transient float xScale ;

    private transient StateMachine stateMachine;

    private transient int health  = 200;
    private transient boolean isDead =false;
    private transient float deathTimer = 1f;

    private transient float hitTime = .6f;
    private transient boolean hit = false;
    private transient Vector2f hitDirection;

    private transient float attackTime = 1.2f;
    private transient boolean attacking = false;
    private transient boolean playedSound = false;


    @Override
    public void start()
    {
        player =  Window.getScene().getGameObjectWith(PlayerController.class);
        this.rb = gameObject.getComponent(RigidBody2D.class);
        spawnLocation.set(this.gameObject.transform.position);
        this.stateMachine = this.gameObject.getComponent(StateMachine.class);
        xScale = this.gameObject.transform.scale.x;
    }

    @Override
    public void update(float dt)
    {



        //TODO FLEYEYe

        if(KeyListener.keyBeginPress(GLFW_KEY_L))
        {
            hit(100);
        }

        if(isDead)
        {
           deathTimer -= dt;
           if(deathTimer < 0)
           {
               this.gameObject.destroy();
           }
           return;
        }


        if(hit)
        {
            hitTime -= dt;
            if(hitTime >= 0)
            {
                return;
            }
            hit = false;
            hitTime = .6f;
            stateMachine.trigger("hitFly");
        }





        if(player != null && rb != null)
        {


            targetPos = new Vector2f(player.transform.position.x, player.transform.position.y);
            Vector2f diff = targetPos.sub(gameObject.transform.position);



            if(Math.abs(diff.x) > 5f)
            {
                rb.setVelocityY(0);
                return;
            }
            else if(Math.abs(diff.x) > 6.0f)
            {
                velocity = new Vector2f(0,0);
                return;

            }

            if(Math.abs(diff.x) < .5f && Math.abs(diff.y) < .5f && !attacking)
            {
                stateMachine.trigger("attack");
                attacking = true;

            }
            else if( attacking)
            {
                attackTime -=dt;
                if(attackTime <= 0)
                {
                    this.attackTime = 1.2f;
                    this.attacking = false;
                    stateMachine.trigger("attackFly");
                    playedSound = false;
                }
                else if(!playedSound && attackTime < .5f)
                {
                    AssetPool.getSound("assets/sounds/mobs/bite-small.ogg").play();
                    playedSound = true;
                }
            }





            if(diff.x < -0.3f)
            {
                velocity.x  = -speed;
                this.gameObject.transform.scale.x = -xScale;
            }
            else if(diff.x > 0.3f)
            {
                velocity.x = speed;
                this.gameObject.transform.scale.x = xScale;
            }
            else
            {
                velocity.x = 0;
            }

            if(diff.y < -0.3f)
            {
                velocity.y = -speed/5;
            }
            else if(diff.y > 0.05f)
            {
                velocity.y = speed/5;
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
            if(obj.getComponent(PlayerController.class).isHurtInvincible() ||obj.getComponent(PlayerController.class).isInvincible() || isDead)
            {
                return;
            }

            obj.getComponent(PlayerController.class).damage(100);
        }else if(obj.getComponent(Fireball.class) != null)
        {

            hit(10);

        }
    }

    public void hit(int amount)
    {
        health -= amount;
        if(health <= 0)
        {
            stateMachine.trigger("attackFly");
            stateMachine.trigger("die");
            this.rb.setIsSensor();
            isDead = true;
            if(player != null)
            {
                player.getComponent(PlayerController.class).replenishMana(50);
            }


            return;

        }
        hit = true;
        stateMachine.trigger("hit");



    }
}
