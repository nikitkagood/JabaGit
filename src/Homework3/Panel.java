package Homework3;

public class Panel implements UICommonInterface {
    private final UICommonInterface parent;
    public boolean doesHandle;

    public Panel(UICommonInterface parent) {
        this.parent = parent;
    }

    @Override
    public UICommonInterface getParent() {
        return parent;
    }

    public boolean handle(UIRequest request)
    {
        if(doesActuallyHandle())
        {
            IO.println("Request handled by: Panel");
            return true;
        }

        if(parent != null)
        {
            return parent.handle(request);
        }

        return false;
    }

    //Placeholder
    private boolean doesActuallyHandle()
    {
        return doesHandle;
    }
}
