package lab08;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

public class Charter {

	public JFreeChart buildHistogram(String title, List<Number> data, int bins) {
		HistogramDataset dataset = new HistogramDataset();
		dataset.addSeries("Histogram", convertData(data), bins);
		
		dataset.setType(HistogramType.FREQUENCY);
		
		JFreeChart chart = ChartFactory.createHistogram(title, "Bins", "Count", dataset, PlotOrientation.VERTICAL, false, false, false);
		return chart;
	}
	
	public JFreeChart buildBarChart(String title, DefaultCategoryDataset data) {
		return ChartFactory.createBarChart(title, "Bins", "Count", data);
	}

	private double[] convertData(List<Number> data) {
		double[] returnValues = new double[data.size()];
		for(int dataIdx = 0; dataIdx < data.size(); dataIdx++) {
			returnValues[dataIdx] = data.get(dataIdx).doubleValue();
		}
		return returnValues;
	}

	public void showChart(JFreeChart chart) {
		JFrame frame = new JFrame();
	    frame.setTitle(chart.getTitle().toString());
	    ChartPanel chartPanel = new ChartPanel(chart);
	    frame.setPreferredSize(new Dimension(800, 400));
	    frame.add(chartPanel);
	    frame.pack();
	    frame.setVisible(true);
	}
}
