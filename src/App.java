public class App 
{
    public static Settings[] presetSettings = new Settings[] 
    {
        new Settings(Settings.DrawMode.LINE, Settings.RenderMode.DEVELOP, true, new Hand[] // Cursive
        {
            new Hand(56, 10),
            new Hand(25, 6),
            new Hand(34, 30),
            new Hand(79, -10)
        }),
        new Settings(Settings.DrawMode.LINE, Settings.RenderMode.DEVELOP, true, new Hand[] // Fish
        {
            new Hand(56, 10),
            new Hand(-28, 0),
            new Hand(56, -10),
            new Hand(56, -20)
        }), 
        new Settings(Settings.DrawMode.LINE, Settings.RenderMode.DEVELOP, true, new Hand[] 
        {
            new Hand(10, 5),
            new Hand(25, -16),
            new Hand(22, 20),
            new Hand(79, -10)
        }),
        new Settings(Settings.DrawMode.LINE, Settings.RenderMode.DEVELOP, true, new Hand[] 
        {
            new Hand(60, 7),
            new Hand(34, -5),
        }),
        new Settings(Settings.DrawMode.LINE, Settings.RenderMode.DEVELOP, true, new Hand[] // Star
        {
            new Hand(60, 10),
            new Hand(45, -15),
            new Hand(34, 10),
        }),
    };

    public static void main(String[] args) throws Exception 
    {
        new Frame();
    }
}
