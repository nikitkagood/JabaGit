package Homework3;

public interface UICommonInterface {
    UICommonInterface getParent();
    boolean handle(UIRequest request);

    public final class UIRequest {}
}

