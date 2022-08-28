package physics2D.components;

import components.Component;
import org.joml.Vector2f;

public class StairCollider extends Component {
    private Vector2f halfSize = new Vector2f(1);
    private Vector2f origin = new Vector2f();
    private Vector2f offset = new Vector2f();
    private boolean facingRight = true;
    private boolean switchedFacing = false;

    @Override
    public void start()
    {
        if(!facingRight && !switchedFacing)
        {

            switchedFacing = true;
        }
    }




    public boolean isFacingRight()
    {
        return facingRight;
    }
    public void setFacingRight(boolean dir)
    {
        facingRight = dir;
    }
    public Vector2f getOffset()
    {
        return this.offset;
    }

    public Vector2f getHalfSize() {
        return halfSize;
    }

    public void setHalfSize(Vector2f halfSize) {
        this.halfSize = halfSize;
    }

    public Vector2f getOrigin()
    {
        return this.origin;
    }

    public void setOffset( Vector2f newOffset)
    {
        this.offset.set(newOffset);
    }
}
