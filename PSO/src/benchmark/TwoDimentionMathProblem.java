package benchmark;

import java.util.LinkedList;
import java.util.Random;

import pso.Location;
import pso.PSOConstants;
import pso.Particle;
import pso.Velocity;

// this is the problem to be solved
// to find an x and a y that minimize the function below:
// f(x, y) = (2.8125 - x + x * y^4)^2 + (2.25 - x + x * y^2)^2 + (1.5 - x + x*y)^2
// where 1 <= x <= 4, and -1 <= y <= 1

public class TwoDimentionMathProblem implements ProblemSet, PSOConstants
{
	private final double LOC_X_LOW = 1;
	private final double LOC_X_HIGH = 4;
	private final double LOC_Y_LOW = -1;
	private final double LOC_Y_HIGH = 1;
	private final double VEL_LOW = -1;
	private final double VEL_HIGH = 1;
	private final int PROBLEM_DIMENSION = 2;
	private final double TARGET_VALUE = 0;
	private final double ERR_TOLERANCE = 1E-20; // the smaller the tolerance, the more accurate the result,
														// but the number of iteration is increased

	@Override
	public double evaluate(Location location)
	{
		double result = 0;
		double x = location.getLoc()[0]; // the "x" part of the location
		double y = location.getLoc()[1]; // the "y" part of the location

		result = Math.pow(2.8125 - x + x * Math.pow(y, 4), 2) + Math.pow(2.25 - x + x * Math.pow(y, 2), 2)
							+ Math.pow(1.5 - x + x * y, 2);

		return result;
	}

	@Override
	public double getErrTolarance()
	{
		return ERR_TOLERANCE;
	}

	@Override
	public LinkedList<Particle> initializeSwarm()
	{
		LinkedList<Particle> result = new LinkedList<Particle>();
		Random generator = new Random();
		Particle p;
		for (int i = 0; i < SWARM_SIZE; i++)
		{
			p = new Particle();

			// randomize location inside a space defined in Problem Set
			double[] loc = new double[PROBLEM_DIMENSION];

			loc[0] = LOC_X_LOW + generator.nextDouble() * (LOC_X_HIGH - LOC_X_LOW);
			loc[1] = LOC_Y_LOW + generator.nextDouble() * (LOC_Y_HIGH - LOC_Y_LOW);
			Location location = new Location(loc);

			// randomize velocity in the range defined in Problem Set
			double[] vel = new double[PROBLEM_DIMENSION];
			vel[0] = VEL_LOW + generator.nextDouble() * (VEL_HIGH - VEL_LOW);
			vel[1] = VEL_LOW + generator.nextDouble() * (VEL_HIGH - VEL_LOW);
			Velocity velocity = new Velocity(vel);

			p.setLocation(location);
			p.setVelocity(velocity);
			p.setProblem(this);
			result.add(p);
		}
		return result;
	}

	@Override
	public int getProblemDimention()
	{
		return PROBLEM_DIMENSION;
	}

	@Override
	public double getTargetValue()
	{
		return TARGET_VALUE;
	}

	@Override
	public double[] getClampMaxValues()
	{
		double[] result = new double[PROBLEM_DIMENSION];
		for(int i = 0; i < PROBLEM_DIMENSION; i++)
		{
			result[i] = VEL_HIGH;
		}
		return result;
	}

	@Override
	public double[] getClampMinValues()
	{
		double[] result = new double[PROBLEM_DIMENSION];
		for(int i = 0; i < PROBLEM_DIMENSION; i++)
		{
			result[i] = VEL_HIGH;
		}
		return result;
	}
	
	@Override
	public String getName()
	{
		return "TwoDimentionalMathProblem";
	}
}
