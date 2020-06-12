package view;

import controller.ActionController;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.*;

import consts.Color;

public class MainWindow {

    public Graphic chartField;
    protected Shell mainShell;
    private Composite chartComposite;
    private Display display;
    private ActionController actionController;

    private Table table;
    private Button startButton;
    private Button stopButton;
    private Spinner xSpinner;
    private Spinner nSpinner;
    private Button zoomInButton;
    private Button zoomOutButton;

    public Composite getChartComposite() {
        return chartComposite;
    }

    public MainWindow() {
        this.init();
    }

    public Table getTable() {
        return table;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getStopButton() {
        return stopButton;
    }

    public Spinner getxSpinner() {
        return xSpinner;
    }

    public Spinner getNSpinner() {
        return nSpinner;
    }

    public Button getZoomInButton() {
        return zoomInButton;
    }

    public Button getZoomOutButton() {
        return zoomOutButton;
    }

    public void run() {
        try {
            this.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        this.display = Display.getDefault();
        this.createWindow();
        this.actionController = new ActionController(this);
        this.actionController.addEventListeners();
    }

    private void open() {
        this.mainShell.open();
        this.mainShell.layout();
        while (!mainShell.isDisposed()) {
            if (!this.display.readAndDispatch()) {
                this.display.sleep();
            }
        }
    }

    protected void createWindow() {
        this.createShell();
        this.createChartField();
        this.createControls();
    }

    protected void createShell() {
        this.mainShell = new Shell();
        this.mainShell.setMinimumSize(new Point(640, 480));
        this.mainShell.setText("График");
        this.mainShell.setLayout(new FormLayout());
    }

    protected void createChartField() {
        this.chartComposite = new Composite(mainShell, SWT.NONE);
        this.chartComposite.setLayout(new FillLayout());
        FormData fdComposite = new FormData();
        fdComposite.bottom = new FormAttachment(100, -46);
        fdComposite.right = new FormAttachment(75);
        fdComposite.top = new FormAttachment(0);
        fdComposite.left = new FormAttachment(0);
        this.chartComposite.setLayoutData(fdComposite);

        this.chartField = new Graphic(this.chartComposite);
        this.chartField.redraw();
    }

    public void clearTable() {
        this.getTable().clearAll();
    }

    protected void createControls() {
        table = new Table(this.mainShell, SWT.BORDER | SWT.FULL_SELECTION);
        FormData fdTable = new FormData();
        fdTable.right = new FormAttachment(100, -10);
        fdTable.left = new FormAttachment(76);
        table.setLayoutData(fdTable);
        table.setHeaderVisible(true);
        String[] titles = { "x", "y(x)"};
        for (String title : titles) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setWidth(170);
            column.setMoveable(true);
            column.setText(title);
        }


		

        startButton = new Button(this.mainShell, SWT.NONE);
        fdTable.bottom = new FormAttachment(startButton, 0, SWT.BOTTOM);
        fdTable.top = new FormAttachment(0);
        FormData fdStartButton = new FormData();
        fdStartButton.top = new FormAttachment(100, -40);
        fdStartButton.bottom = new FormAttachment(100, -6);
        fdStartButton.left = new FormAttachment(0, 10);
        startButton.setLayoutData(fdStartButton);
        startButton.setText("Start");

        stopButton = new Button(this.mainShell, SWT.NONE);
        FormData fdStopButton = new FormData();
        fdStopButton.left = new FormAttachment(startButton, 6);
        fdStopButton.top = new FormAttachment(this.chartComposite, 6);
        fdStopButton.bottom = new FormAttachment(100, -6);
        stopButton.setLayoutData(fdStopButton);
        stopButton.setText("Stop");

        xSpinner = new Spinner(this.mainShell, SWT.BORDER);
        FormData fdXSpinner = new FormData();
        fdXSpinner.left = new FormAttachment(stopButton, 6);
        fdXSpinner.top = new FormAttachment(this.chartComposite, 6);
        fdXSpinner.bottom = new FormAttachment(100, -6);
        xSpinner.setLayoutData(fdXSpinner);
        xSpinner.setMinimum(-100);
        xSpinner.setMaximum(1000);

        nSpinner = new Spinner(this.mainShell, SWT.BORDER);
        fdXSpinner.right = new FormAttachment(nSpinner, -6);
        FormData fdNSpinner = new FormData();
        fdNSpinner.top = new FormAttachment(this.chartComposite, 6);
        fdNSpinner.right = new FormAttachment(0, 363);
        fdNSpinner.bottom = new FormAttachment(100, -6);
        fdNSpinner.left = new FormAttachment(0, 242);
        nSpinner.setLayoutData(fdNSpinner);
        nSpinner.setMinimum(-1);
        nSpinner.setMaximum(5);

        zoomInButton = new Button(mainShell, SWT.NONE);
        FormData fdZoomInButton = new FormData();
        fdZoomInButton.bottom = new FormAttachment(table, 0, SWT.BOTTOM);
        fdZoomInButton.left = new FormAttachment(nSpinner, 6);
        zoomInButton.setLayoutData(fdZoomInButton);
        zoomInButton.setText("+");

        zoomOutButton = new Button(mainShell, SWT.NONE);
        zoomOutButton.setText("-");
        FormData fdZoomOutButton = new FormData();
        fdZoomOutButton.left = new FormAttachment(zoomInButton, 6);
        fdZoomOutButton.bottom = new FormAttachment(100, -6);
        zoomOutButton.setLayoutData(fdZoomOutButton);
    }
}

