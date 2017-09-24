package lab04;

import java.awt.Color;
import java.awt.BasicStroke; 

import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;


	/**
	 * This application generates a graph for the time cost for running 0 to 2 billion
	 * @author Junjun He
	 *
	 */
	public class lab03 extends ApplicationFrame {	

		/**
		 * This method is help to create x and y-axis labels, size, lines, colors and so forth.
		 * 
		 * @param windowName
		 * @param chartTitle
		 */
		public lab03(String windowName, String chartTitle ) {
		super(windowName);
  
  		JFreeChart theChart = ChartFactory.createXYLineChart(chartTitle , "Integers" , "Results" ,
  			createDataset() , PlotOrientation.VERTICAL ,true , true , false);

  		// set up the chart
  		ChartPanel chartPanel = new ChartPanel( theChart );
  		chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
  		final XYPlot plot = theChart.getXYPlot();
  		
  		XYLineAndShapeRenderer line = new XYLineAndShapeRenderer();
  		
		setContentPane(chartPanel); 
  	
  		// line setting
  		line.setSeriesPaint( 0 , Color.RED );
  		line.setSeriesStroke( 0 , new BasicStroke( 1.5f ) );
  		plot.setRenderer(line); 
  
		
	}
	

		/**
		 * This method is use to generates data and show on the graph.
		 * Use a for loop and put into the graph.
		 * @return dataset, which is interval (x, y)
		 */
		private XYDataset createDataset( ) {
			// create the line graph
			final XYSeries graph = new XYSeries( "The Results of Square Root for the First 100 Integers" );  
			final XYSeriesCollection dataset = new XYSeriesCollection( );  
		
		
			   
			double result = 0;
			int i = 0;
			for(i = 1; i <= 100; i++) {

				result = Math.pow(i, 0.5);
				
			}
			// record time
			long endTime = System.nanoTime();


			graph.add(0,0);
			graph.add(i,result);
			
			dataset.addSeries( graph );
			
		return dataset;
		}
		

	
		public static void main( String[ ] args ) {
		// create and configure the window.
			lab03 chart = new lab03("Chart", "The Results of Square Root for the First 100 Integers");
			RefineryUtilities.centerFrameOnScreen( chart );  
			chart.setSize(500,500);
			chart.setVisible( true ); 
		
	}

}
