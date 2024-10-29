public class Settings 
{
    enum DrawMode 
    {
        LINE,
        POINT
    };

    enum RenderMode 
    {
        DEVELOP,
        INSTANT
    }

    DrawMode drawMode;
    RenderMode renderMode;
    boolean showHand;
    Hand[] hands;

    Settings(DrawMode drawMode, RenderMode renderMode, boolean showHand, Hand[] hands)
    {
        this.drawMode = drawMode;
        this.renderMode = renderMode;
        this.showHand = showHand;
        this.hands = hands;
    }
}
