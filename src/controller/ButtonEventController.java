package controller;

import consts.Color;
import functions.HardFunction;
import view.MainWindow;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.TableItem;

public class ButtonEventController {
    private final MainWindow window;

    public ButtonEventController(MainWindow window) {
        this.window = window;
        setHandlers();
    }

    private void setHandlers() {
        this.window.getStartButton().addMouseListener(new MouseListener() {
            @Override
            public void mouseDoubleClick(MouseEvent mouseEvent) { }

            @Override
            public void mouseDown(MouseEvent mouseEvent) {
                int min = window.getxSpinner().getSelection();
                int n = window.getNSpinner().getSelection();
                int max = -min;
                HardFunction second = new HardFunction(min,max, n);
                window.chartField.addChart("1/n! ∑((-1)^k+ x^k)/(k!+1)!", second.getXes(), second.getYes());
                window.chartField.setColor("1/n! ∑((-1)^k+ x^k)/(k!+1)!", Color.RED);
                window.chartField.redraw();
                window.getTable().removeAll();
                for (int loopIndex = 0; loopIndex < second.getXes().length; loopIndex++) {
                    TableItem item = new TableItem(window.getTable(), SWT.NULL);
                    item.setText(0, String.valueOf(second.getXes()[loopIndex]));
                    item.setText(1, String.valueOf(second.getYes()[loopIndex]));
                }
            }

            @Override
            public void mouseUp(MouseEvent mouseEvent) { }
        });

        this.window.getStopButton().addMouseListener(new MouseListener() {
            @Override
            public void mouseDoubleClick(MouseEvent mouseEvent) { }

            @Override
            public void mouseDown(MouseEvent mouseEvent) {
                window.chartField.clear();
                window.getTable().removeAll();

            }

            @Override
            public void mouseUp(MouseEvent mouseEvent) { }
        });
    }





}
