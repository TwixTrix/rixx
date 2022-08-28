package components;

import Rixx.Camera;
import Rixx.Window;
import org.joml.Vector2f;
import org.joml.Vector3f;
import renderer.DebugDraw;
import util.Settings;

public class GridLines extends Component{

    @Override
    public void editorUpdate(float dt)
    {
        Camera camera = Window.getScene().camera();
        Vector2f cameraPos = camera.position;
        Vector2f projectionSize = camera.getProjectionSize();

        float firstX = ((int)(cameraPos.x/ Settings.GRID_WIDTH))*Settings.GRID_WIDTH ;
        float firstY =  ((int)(cameraPos.y/ Settings.GRID_HEIGHT))*Settings.GRID_HEIGHT;

        int numVerticalLines =(int)(projectionSize.x * camera.getZoom() / Settings.GRID_WIDTH) + 2;
        int numHorizontalLInes = (int)(projectionSize.y * camera.getZoom() / Settings.GRID_HEIGHT) + 2;

        float height = (int) (projectionSize.y * camera.getZoom() )+ 5 * Settings.GRID_HEIGHT;
        float width = (int) (projectionSize.x  * camera.getZoom() )+ 5 * Settings.GRID_WIDTH;

        int maxLines = Math.max(numVerticalLines, numHorizontalLInes);
        Vector3f color = new Vector3f(0.2f,0.2f, 0.2f );
        for(int i = 0 ; i < maxLines; i++)
        {
            float x= firstX + (Settings.GRID_WIDTH*i);
            float y = firstY + (Settings.GRID_HEIGHT*i);

            if(i< numVerticalLines)
            {
                DebugDraw.addLine2D(new Vector2f(x, firstY ), new Vector2f(x, firstY+ height), color );
            }

            if( i< numHorizontalLInes)
            {
                DebugDraw.addLine2D(new Vector2f(firstX ,y), new Vector2f(firstX + width,y), color);
            }
        }





    }

}
