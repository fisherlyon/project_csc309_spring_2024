package math_tutor_project;

public class StartButton extends Button {

    public StartButton(String label, int x, int y, int w, int h) {
        super(label, x, y, w, h);
    }

    public void setScreen() {
        GameData.getInstance().setDisplayScreen(super.getLabel());
    }
}
