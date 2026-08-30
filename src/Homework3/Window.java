package Homework3;

public class Window implements UICommonInterface {
    private final UICommonInterface parent;
    public boolean doesHandle;
    
    public Window() {
        this.parent = null;
    }
    
    public Window(UICommonInterface parent) {
        this.parent = parent;
    }
    
    @Override
    public UICommonInterface getParent() {
        return null;
    }

    public boolean handle(UIRequest request)
    {
        if(doesActuallyHandle())
        {
            IO.println("Request handled by: Window");
            return true;
        }

        if(parent != null)
        {
            parent.handle(request);
        }

        return false;
    }

    //Placeholder
    private boolean doesActuallyHandle()
    {
        return doesHandle;
    }
}
