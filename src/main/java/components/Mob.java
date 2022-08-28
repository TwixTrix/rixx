package components;

import Rixx.Window;

public class Mob extends Component{


    public transient int health = 100;



    public void setHealth(int amount)
    {
        this.health = amount;
    }

    public void damage(int amount)
    {
        this.health -= amount;
        if(this.health <= 0)
        {
            eliminate();
        }
    }

    public void eliminate()
    {
        this.gameObject.destroy();
    }








}
