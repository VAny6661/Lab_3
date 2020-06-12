package controller;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swtchart.IAxis;

import model.Pair;
import view.MainWindow;

public class DragEventListener implements MouseListener, MouseMoveListener {

    private final MainWindow window;
    private final Pair<Integer> coordinates;
    private boolean isDragging;

    public DragEventListener(MainWindow window) {
        this.window = window;
        this.isDragging = false;
        this.coordinates = new Pair<>();
        ((Control) this.window.chartField.getChart().getPlotArea()).addMouseListener(this);
        this.window.chartField.getChart().getPlotArea().addMouseMoveListener(this);
    }


    @Override
    public void mouseDoubleClick(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDown(MouseEvent mouseEvent) {

        this.coordinates.put(mouseEvent.x, mouseEvent.y);
        isDragging = ((Control) this.window.chartField.getChart().getPlotArea()).dragDetect(mouseEvent);
    }

    @Override
    public void mouseUp(MouseEvent mouseEvent) {
        this.coordinates.clear();
        this.isDragging = false;
    }

    @Override
    public void mouseMove(MouseEvent mouseEvent) {
        if (this.isDragging) {

            Pair<Pair<Boolean>> direction = this.getDirection(new Pair<>(mouseEvent.x, mouseEvent.y));

            IAxis xAsix = this.window.chartField.getChart().getAxisSet().getXAxis(0);
            IAxis yAsix = this.window.chartField.getChart().getAxisSet().getYAxis(0);

            boolean left = direction.getFirst().getFirst();
            boolean right = direction.getFirst().getSecond();
            boolean up = direction.getSecond().getFirst();
            boolean down = direction.getSecond().getSecond();

            if (left) xAsix.scrollUp();
            if (right) xAsix.scrollDown();
            if (up) yAsix.scrollDown();
            if (down) yAsix.scrollUp();

            this.coordinates.put(mouseEvent.x, mouseEvent.y);
            this.window.chartField.redraw();
        }
    }

    private Pair<Pair<Boolean>> getDirection(Pair<Integer> currentCursorCoordinates) {

        Pair<Pair<Boolean>> direction = new Pair<>();
        Pair<Boolean> leftRight = new Pair<>();
        Pair<Boolean> upDown = new Pair<>();

        int prevX = this.coordinates.getFirst();
        int prevY = this.coordinates.getSecond();
        int currentX = currentCursorCoordinates.getFirst();
        int currentY = currentCursorCoordinates.getSecond();

        leftRight.setFirst(currentX < prevX + 20);    
        leftRight.setSecond(currentX > prevX - 20);   
        upDown.setFirst(currentY < prevY + 20);       
        upDown.setSecond(currentY > prevY - 20); 

        direction.put(leftRight, upDown);

        return direction;
    }
}
