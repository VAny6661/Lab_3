package main;

import functions.EasyFunction;
import view.MainWindow;


public class Main {
    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        EasyFunction first = new EasyFunction(200,-200, 5);
        window.chartField.addChart(
                "X-3", first.getXes(),
                first.getYes());
        window.run();
    }
}
