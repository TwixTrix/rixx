package editor;

import Rixx.Window;
import imgui.ImGui;
import observers.EventSystem;
import observers.events.Event;
import observers.events.EventType;
import scenes.LevelEditorSceneInitializer;

public class MenuBar {

    public void imgui()
    {
        ImGui.beginMenuBar();

        if(ImGui.beginMenu("File"))
        {
            if(ImGui.menuItem("Save", "Ctrl+S"))
            {
                EventSystem.notify(null, new Event(EventType.SaveLevel));
            }
            if(ImGui.menuItem("Load", "Ctrl+O"))
            {
                EventSystem.notify(null, new Event(EventType.LoadLevel));
            }
            if(ImGui.menuItem("level1"))
            {
                Window.incrementLevel(1);
                Window.changeScene(new LevelEditorSceneInitializer() , "level1.txt");
            }
            if(ImGui.menuItem("level2"))
            {
                Window.incrementLevel(2);
                Window.changeScene(new LevelEditorSceneInitializer() , "level2.txt");
            }
            if(ImGui.menuItem("level3"))
            {
                Window.incrementLevel(3);
                Window.changeScene(new LevelEditorSceneInitializer() , "level3.txt");
            }
            if(ImGui.menuItem("level4"))
            {
                Window.incrementLevel(4);
                Window.changeScene(new LevelEditorSceneInitializer() , "level4.txt");
            }
            if(ImGui.menuItem("level5"))
            {
                Window.incrementLevel(5);
                Window.changeScene(new LevelEditorSceneInitializer() , "level5.txt");
            }
            if(ImGui.menuItem("level6"))
            {
                Window.incrementLevel(6);
                Window.changeScene(new LevelEditorSceneInitializer() , "level6.txt");
            }
            if(ImGui.menuItem("level7"))
            {
                Window.incrementLevel(7);
                Window.changeScene(new LevelEditorSceneInitializer() , "level7.txt");
            }
            if(ImGui.menuItem("level8"))
            {
                Window.incrementLevel(8);
                Window.changeScene(new LevelEditorSceneInitializer() , "level8.txt");
            }
            if(ImGui.menuItem("level9"))
            {
                Window.incrementLevel(9);
                Window.changeScene(new LevelEditorSceneInitializer() , "level9.txt");
            }

            ImGui.endMenu();
        }

        ImGui.endMenuBar();
    }

}
