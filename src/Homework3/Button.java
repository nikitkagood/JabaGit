package Homework3;

public class Button implements UICommonInterface {
    private final UICommonInterface parent;
    public boolean doesHandle;

    public Button(UICommonInterface parent)
    {
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
            IO.println("Request handled by: Button");
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
