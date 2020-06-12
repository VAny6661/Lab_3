package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtchart.Chart;
import org.eclipse.swtchart.IAxisSet;
import org.eclipse.swtchart.ILineSeries;
import org.eclipse.swtchart.ILineSeries.PlotSymbolType;
import org.eclipse.swtchart.ISeries;
import org.eclipse.swtchart.ISeriesSet;


public class Graphic {
    private final Chart chart;

    Graphic(Composite composite) {
        this.chart = new Chart(composite, SWT.FILL);
        chart.getTitle().setVisible(false);
        chart.getAxisSet().getXAxis(0).getTitle().setVisible(false);
        chart.getAxisSet().getYAxis(0).getTitle().setVisible(false);
    }

    private IAxisSet getAxisSet() {
        return this.chart.getAxisSet();
    }

    private ISeriesSet getISeriesSet() {
        return this.chart.getSeriesSet();
    }

    public void adjustRange() {
        this.getAxisSet().adjustRange();
    }

    public void addChart(String seriesDescription, double[] seriesX, double[] seriesY) {
        ISeries series = this.getISeriesSet().createSeries(ISeries.SeriesType.LINE, seriesDescription);
        ILineSeries series1 = (ILineSeries) chart.getSeriesSet().getSeries(seriesDescription);
        series.setYSeries(seriesY);
        series.setXSeries(seriesX);
        series1.setSymbolType(PlotSymbolType.NONE);
        
    }

    public void setColor(String seriesDescription, consts.Color color) {
        ILineSeries series = (ILineSeries) chart.getSeriesSet().getSeries(seriesDescription);
        Display display = this.chart.getDisplay();
        if (color == consts.Color.RED) {
            series.setLineColor(new Color(display, 255, 0, 0));
            series.setSymbolType(PlotSymbolType.NONE);}
        else if (color == consts.Color.BLUE) {
            series.setLineColor(new Color(display, 0, 0, 255));
            series.setSymbolType(PlotSymbolType.NONE);}
    }

    public Chart getChart() {
        return chart;
    }

    public void redraw() {
        this.getChart().redraw();
    }

    public void clear () {
        ISeriesSet seriesSet = this.getChart().getSeriesSet();
        ISeries[] a = seriesSet.getSeries();
        for (ISeries series: a){
            if (!series.getId().equals("X-3"))
                this.getChart().getSeriesSet().deleteSeries(series.getId());
        }
        this.redraw();
    }
}
