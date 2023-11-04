package gui;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Graph extends Application
{
	private List<CanvasLineChart> charts = new ArrayList<>(); 
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		primaryStage.setScene(new Scene(createContent()));
		primaryStage.show();
	}

	private Parent createContent()
	{
		Pane root = new Pane();
		root.setPrefSize(800, 600);
		Canvas canvas = new Canvas(800, 600);
		GraphicsContext g = canvas.getGraphicsContext2D();
		
		charts.add(new CanvasLineChart(g, Color.RED, new RandomDataSource()));
		charts.add(new CanvasLineChart(g, Color.BLUE, new RandomDataSource()));

		//Timer for animation loop, probably the thing we'll edit 
		AnimationTimer timer = new AnimationTimer() 
		{
			//Define an event handler 
			@Override
			public void handle(long currentTime) 
			{
				g.clearRect(0, 0, 800, 600);
				charts.forEach(CanvasLineChart::update);
			}
		};
		
		timer.start();
		
		root.getChildren().add(canvas);
		
		return root; 
	}
	
	private static class CanvasLineChart
	{
		private GraphicsContext g;
		private Color color;
		private DataSource<Double> dataSource;
		
		private Deque<Double> buffer = new ArrayDeque<>();
		
		private double oldx = -1;
		private double oldy = -1;
		
		public CanvasLineChart(GraphicsContext g, Color color, DataSource<Double> dataSource) 
		{
			this.g = g;
			this.color = color; 
			this.dataSource = dataSource;
		}
		
		public void update() 
		{
			double val = dataSource.getValue();
			
			if (buffer.size() > 800) 
			{
				buffer.removeFirst();
			}
		
			
			g.setStroke(color);
			
			buffer.forEach(y -> {
				if(oldy > -1) {
					g.strokeLine(oldx, oldy, oldx+1, y);
				}
				
				oldx += 1;
				oldy = y;
			});
			oldx = -1;
			oldy = -1;
		}
	}
	//Maybe want to make this public so that we can instantiate our function data?
	private static class RandomDataSource implements DataSource<Double>
	{
		private Random random = new Random();
		@Override
		public Double getValue()
		{
			return random.nextDouble();
		}
		
	}
	//Defines how we get the data to plot
	private interface DataSource<T>
	{
		T getValue();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
