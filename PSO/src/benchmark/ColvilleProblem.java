package benchmark;

import java.util.LinkedList;
import java.util.Random;

import pso.Location;
import pso.PSOConstants;
import pso.Particle;
import pso.Velocity;

public class ColvilleProblem implements ProblemSet, PSOConstants
{
	public static final double ERR_TOLERANCE = 1E-20;
	private final int PROBLEM_DIMENSION = 4;
	private final double LOC_HIGH = 10;
	private final double LOC_LOW = -10;
	private final double VEL_LOW = -1;
	private final double VEL_HIGH = 1;
	private final double TARGET_VALUE = 0;
	@Override
	public double evaluate(Location location)
	{
		double[] loc = location.getLoc();
		double X1 = loc[0];
		double X2 = loc[1];
		double X3 = loc[2];
		double X4 = loc[3];
		double result = 100*Math.pow(X2 - Math.pow(X1,2), 2);
		result += Math.pow(1 - X1, 2);
		result += 90 * Math.pow(X4 - Math.pow(X3, 2), 2);
		result += Math.pow(1 - X3, 2);
		result += 10.1 * ( Math.pow(X2 - 1, 2) + Math.pow(X4 - 1, 2));
		result += 19.8 * (X2 - 1) * (X4 - 1);
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
			for(int j = 0; j < PROBLEM_DIMENSION; j++)
			{
				loc[j] = LOC_LOW + generator.nextDouble() * (LOC_HIGH - LOC_LOW);
			}
			Location location = new Location(loc);

			// randomize velocity in the range defined in Problem Set
			double[] vel = new double[PROBLEM_DIMENSION];
			for(int j = 0; j < PROBLEM_DIMENSION; j++)
			{
				vel[j] = VEL_LOW + generator.nextDouble() * (VEL_HIGH - VEL_LOW);
			}
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
		return "ColvilleProblem";
	}
}
