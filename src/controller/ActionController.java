package controller;

import view.MainWindow;

public class ActionController {
    private final MainWindow window;

    public ActionController(MainWindow window) {
        this.window = window;
    }

    public void addEventListeners() {
        ZoomListener zoomEventListener = new ZoomListener(window);
        DragEventListener dragEventListener = new DragEventListener(window);
        ButtonEventController buttonEventController = new ButtonEventController(window);
    }
}
