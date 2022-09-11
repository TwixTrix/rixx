package components;

import Rixx.GameObject;
import Rixx.Prefabs;
import Rixx.Window;
import org.jbox2d.dynamics.contacts.Contact;
import org.joml.Vector2f;
import physics2D.components.CircleCollider;
import physics2D.components.RigidBody2D;
import physics2D.enums.BodyType;
import util.RMath;

import javax.swing.plaf.nimbus.State;

public class gluttonyAI extends  Component{
    private transient float distanceTillStart = 6f;
    private transient boolean hasBegun = false;
    private transient GameObject player;
    private transient RigidBody2D rb;
    private transient StateMachine stateMachine;

    private transient Vector2f spawnLocation ;
    private transient float speed = 1f;

    private transient boolean isIdle = true;

    private transient float attackCoolDown = 3f;
    private transient boolean isAttacking = false;
    private transient boolean isCasting = false;
    private transient float attackTimeLeft = 0;
    private transient boolean attacked = false;

    private transient int health = 10000;
    private transient int maxHealth = 10000;
    private transient boolean wasHit = false;
    private transient boolean isDead = false;
    private transient float deathTimeLeft = 3f;






    @Override
    public void start()
    {
       this.player =  Window.getScene().getGameObjectWith(PlayerController.class);
       this.rb = this.gameObject.getComponent(RigidBody2D.class);
       this.stateMachine = this.gameObject.getComponent(StateMachine.class);
       spawnLocation = new Vector2f(this.gameObject.transform.position.x, this.gameObject.transform.position.y);
    }


    @Override
    public void update(float dt)
    {

        Vector2f distance = new Vector2f(this.player.transform.position.x, this.player.transform.position.y).sub(this.gameObject.transform.position.x, this.gameObject.transform.position.y);
        Vector2f distanceFromSpawn = new Vector2f(this.spawnLocation.x, this.spawnLocation.y).sub(this.gameObject.transform.position.x , this.gameObject.transform.position.y);
        if((Math.abs(distance.x) > 6 || Math.abs(distance.y) > 4 ) && !hasBegun)
        {
            return;
        }else if(!hasBegun)
        {

            bossFight();
        }

        if(isDead)
        {
            deathTimeLeft -=dt;
            if(deathTimeLeft <= 0)
            {
                this.gameObject.destroy();
            }
            return;
        }


        this.rb.setBodyType(BodyType.Dynamic);

        if(player == null)
        {
            return;
        }

        if(isCasting || isAttacking)
        {
            attackTimeLeft -=dt;

            if(isCasting && attackTimeLeft < 1.6f && !attacked)
            {
                attacked = true;

            }
            else if( isAttacking && attackTimeLeft <= 1.3 && !attacked)
            {
                attacked = true;
                Vector2f position = new Vector2f(this.gameObject.transform.position.x , this.gameObject.transform.position.y);
                position.add(new Vector2f(-.5f , -.1f));
                Window.getScene().addGameObjectToScene(Prefabs.MobAttackBox(position, 2.4f,2.5f, 250 , .1f));
            }

            if(attackTimeLeft <= 0)
            {
                this.rb.setVelocityX(speed * 3);
                if(isAttacking)
                {
                    this.stateMachine.trigger("attackIdle");
                }else
                {
                    this.stateMachine.trigger("castIdle");
                }

                isAttacking = false;
                isCasting = false;
                attacked = false;
                attackCoolDown = randomCoolDown();
            }else
            {
                return;
            }

        }



        attackCoolDown -=dt;
        if(attackCoolDown <= 0)
        {
            attack();
        }
        else if(attackCoolDown <.6f)
        {
            return;
        }






        if(Math.abs(distanceFromSpawn.x ) > 8 )
        {
            this.stateMachine.trigger("walkIdle");
            return;

        }
        else if(distance.x < -1.5f)
        {

            this.rb.setVelocityX(-speed);
            this.stateMachine.trigger("idleWalk");


        }
        else
        {
            this.stateMachine.trigger("walkIdle");
        }













    }

    @Override
    public void preSolve(GameObject obj, Contact contact , Vector2f contactNormal)
    {
        if(isDead)
        {
            contact.setEnabled(false);

        }

        if(obj.getComponent(PlayerController.class) != null)
        {
            obj.getComponent(PlayerController.class).damage(50);
            this.rb.setBodyType(BodyType.Static);
        }
    }


    public void bossFight()
    {
        hasBegun = true;

        Window.getScene().addGameObjectToScene(Prefabs.generateGluttonyHealth());
        Window.getScene().addGameObjectToScene(Prefabs.generateEmptyGluttonyHealthBar());
    }


    public float randomCoolDown()
    {
        return (float)((Math.random() * 3) + 2);
    }

    public void attack()
    {
        this.stateMachine.trigger("walkIdle");
        if(RMath.randomBoolean() && RMath.randomBoolean())
        {
            castAttack();
            isCasting = true;
        }
        else
        {

            swingAttack();
            isAttacking = true;
        }
    }

    public void swingAttack()
    {
        this.stateMachine.trigger("idleAttack");
        attackTimeLeft = 2f;
    }

    public void castAttack()
    {
        this.stateMachine.trigger("idleCast");
        attackTimeLeft = 1.8f;
        Window.getScene().addGameObjectToScene(Prefabs.generateGluttonySpell());
    }

    public void hit(int damage)
    {

        health -= damage;
        if(health <= 0)
        {
            die();
            health = 0;
        }
        else
        {
            wasHit = true;
        }

    }

    public void die()
    {
        isDead = true;
        this.stateMachine.trigger("walkDeath");
        this.stateMachine.trigger("idleDeath");
        this.stateMachine.trigger("castDeath");
        this.stateMachine.trigger("attackDeath");
        player.getComponent(PlayerController.class).removeMana(1000);
        Window.getScene().getGameObjectWith(gluttonyEmpty.class).destroy();
        Window.getScene().getGameObjectWith(gluttonyHealth.class).destroy();

    }

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public int getHealth()
    {
        return health;
    }

}
