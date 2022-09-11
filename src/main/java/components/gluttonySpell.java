package components;

import Rixx.GameObject;
import Rixx.Window;

public class gluttonySpell extends  Component{
    private transient float timeLeft = 3.2f;
    private transient GameObject player;
    private transient boolean stolenMana = false;

    @Override
    public void start()
    {
        this.player  = Window.getScene().getGameObjectWith(PlayerController.class);
        this.gameObject.transform.position.x = player.transform.position.x;
        this.gameObject.transform.position.y = player.transform.position.y + 1f;
    }


    @Override
    public void update(float dt)
    {
        timeLeft -= dt;
         if(timeLeft < 1f && !stolenMana)
         {
             stolenMana  = true;
             this.player.getComponent(PlayerController.class).removeMana(150);
         }

         if(timeLeft <= 0)
         {
             this.gameObject.destroy();
         }
        this.gameObject.transform.position.x = player.transform.position.x;
        this.gameObject.transform.position.y = player.transform.position.y + 1f;
    }

}
