package lab04;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.function.Function2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
* An example showing how to plot a simple function in JFreeChart.  Because
* JFreeChart displays discrete data rather than plotting functions, you need
* to create a dataset by sampling the function values.
*/
public class demo extends ApplicationFrame {

    public demo(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	/**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
	private static void createFrame() {
	    XYSeries series = new XYSeries("Series");
	    for (int i = 0; i <= 100; i++) {
	        series.add(i, Math.pow(2, i));
	    }
	    NumberAxis xAxis = new NumberAxis("X");
	    xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	    LogAxis yAxis = new LogAxis("Y");
	    yAxis.setBase(2);
	    yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	    XYPlot plot = new XYPlot(new XYSeriesCollection(series),
	        xAxis, yAxis, new XYLineAndShapeRenderer(true, true));
	    JFreeChart chart = new JFreeChart(
	        "Chart", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
	    JFrame frame = new JFrame("LogAxis Test");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setContentPane(new ChartPanel(chart));
	    frame.pack();
	    frame.setVisible(true);
	}

	public static void main(String[] args) {
	    EventQueue.invokeLater(new Runnable() {

	        @Override
	        public void run() {
	            createFrame();
	        }
	    });
	}

}